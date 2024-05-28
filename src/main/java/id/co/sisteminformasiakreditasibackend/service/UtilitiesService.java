package id.co.sisteminformasiakreditasibackend.service;

import java.util.Map;

public interface UtilitiesService {
    String login(Map<String, Object> data);
    String getListMenu(Map<String, Object> data);
    String getListProvinsi(Map<String, Object> data);
    String getListKabupaten(Map<String, Object> data);
    String getListKecamatan(Map<String, Object> data);
    String getListKelurahan(Map<String, Object> data);
    String getListKaryawan(Map<String, Object> data);
}
