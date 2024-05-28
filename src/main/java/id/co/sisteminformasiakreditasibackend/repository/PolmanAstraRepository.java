package id.co.sisteminformasiakreditasibackend.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PolmanAstraRepository {
    private static final Logger logger = LoggerFactory.getLogger(PolmanAstraRepository.class);
    private Connection oConnection;

    public PolmanAstraRepository(@Value("${spring.datasource.url}") String url,
                                 @Value("${spring.datasource.username}") String username,
                                 @Value("${spring.datasource.password}") String password) {
        try {
            oConnection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            logger.error("Error establishing connection", e);
        }
    }

    public String callProcedure(String spName, String[] parameters) {
        List<Map<String, Object>> results = new ArrayList<>();
        try (PreparedStatement oCommand = oConnection.prepareStatement("EXEC " + spName + " " + prepareParameters(50))) {
            oConnection.setAutoCommit(false);
            oCommand.clearParameters();

            for (int i = 0; i < 50; i++) {
                if (i < parameters.length) {
                    oCommand.setString(i + 1, parameters[i]);
                } else {
                    oCommand.setString(i + 1, "");
                }
            }

            results = executeQueryAndGetResults(oCommand);
            oConnection.commit();
            oConnection.setAutoCommit(true);
        } catch (SQLException e) {
            logger.error("Error executing stored procedure", e);
            rollbackConnection();
        }
        return convertToJson(results);
    }

    private List<Map<String, Object>> executeQueryAndGetResults(PreparedStatement oCommand) throws SQLException {
        List<Map<String, Object>> results = new ArrayList<>();
        try (ResultSet resultSet = oCommand.executeQuery()) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                Map<String, Object> row = new LinkedHashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object columnValue = resultSet.getObject(i);
                    row.put(columnName, columnValue);
                }
                results.add(row);
            }
        }
        return results;
    }

    private void rollbackConnection() {
        try {
            if (oConnection != null && !oConnection.getAutoCommit()) {
                oConnection.rollback();
            }
        } catch (SQLException ex) {
            logger.error("Failed to rollback connection", ex);
        }
    }

    private String convertToJson(List<Map<String, Object>> data) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(data);
        } catch (Exception e) {
            logger.error("Failed to convert to JSON", e);
            return null;
        }
    }

    private String prepareParameters(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append("?, ");
        }
        sb.setLength(sb.length() - 2); // Remove the trailing comma and space
        return sb.toString();
    }

    private void logException(SQLException e) {
        logger.error("SQL Exception occurred", e);
    }

    private void logQueryResults(List<Map<String, Object>> results) {
        results.forEach(result -> logger.info("Query result: {}", result));
    }
}
