package id.co.sisteminformasiakreditasibackend.service.impl;

import id.co.sisteminformasiakreditasibackend.repository.PolmanAstraRepository;
import id.co.sisteminformasiakreditasibackend.service.RataRataBebanDTPRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class RataRataBebanDTPRServiceImpl implements RataRataBebanDTPRService {

    @Autowired
    PolmanAstraRepository polmanAstraRepository;

    @Override
    public String getDataRataRataBebanDTPR(Map<String, Object> data) {
        List<String> dataList = new ArrayList<>();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            dataList.add(entry.getValue().toString());
        }
        String result = polmanAstraRepository.callProcedure("akd_GetRataRataBebanDTPR", dataList.toArray(new String[0]));
        return result;
    }
}
