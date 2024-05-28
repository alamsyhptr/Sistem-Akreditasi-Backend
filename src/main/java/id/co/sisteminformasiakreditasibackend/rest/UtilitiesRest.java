package id.co.sisteminformasiakreditasibackend.rest;

import id.co.sisteminformasiakreditasibackend.config.EncodeData;
import id.co.sisteminformasiakreditasibackend.service.UtilitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/Utilities")
public class UtilitiesRest {
    @Autowired
    private UtilitiesService utilitiesService;

    @Autowired
    private EncodeData encodeData;

    @PostMapping("/Login")
    public ResponseEntity<String> login(@RequestBody Map<String, Object> data) {
        try {
            Map<String, Object> encodedData = encodeData.htmlEncodeObject(data);
            String result = utilitiesService.login(encodedData);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to login", e);
        }
    }

    @PostMapping("/GetListMenu")
    public ResponseEntity<String> getListMenu(@RequestBody Map<String, Object> data) {
        try {
            Map<String, Object> encodedData = encodeData.htmlEncodeObject(data);
            String result = utilitiesService.getListMenu(encodedData);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to get list menu", e);
        }
    }

    @PostMapping("/GetListProvinsi")
    public ResponseEntity<String> getListProvinsi(@RequestBody Map<String, Object> data) {
        try {
            Map<String, Object> encodedData = encodeData.htmlEncodeObject(data);
            String result = utilitiesService.getListProvinsi(encodedData);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to get list provinsi", e);
        }
    }

    @PostMapping("/GetListKabupaten")
    public ResponseEntity<String> getListKabupaten(@RequestBody Map<String, Object> data) {
        try {
            Map<String, Object> encodedData = encodeData.htmlEncodeObject(data);
            String result = utilitiesService.getListKabupaten(encodedData);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to get list kabupaten", e);
        }
    }

    @PostMapping("/GetListKecamatan")
    public ResponseEntity<String> getListKecamatan(@RequestBody Map<String, Object> data) {
        try {
            Map<String, Object> encodedData = encodeData.htmlEncodeObject(data);
            String result = utilitiesService.getListKecamatan(encodedData);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to get list kecamatan", e);
        }
    }

    @PostMapping("/GetListKelurahan")
    public ResponseEntity<String> getListKelurahan(@RequestBody Map<String, Object> data) {
        try {
            Map<String, Object> encodedData = encodeData.htmlEncodeObject(data);
            String result = utilitiesService.getListKelurahan(encodedData);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to get list kelurahan", e);
        }
    }

    @PostMapping("/GetListKaryawan")
    public ResponseEntity<String> getListKaryawan(@RequestBody Map<String, Object> data) {
        try {
            Map<String, Object> encodedData = encodeData.htmlEncodeObject(data);
            String result = utilitiesService.getListKaryawan(encodedData);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to get list karyawan", e);
        }
    }
}

