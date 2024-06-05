package id.co.sisteminformasiakreditasibackend.rest;

import id.co.sisteminformasiakreditasibackend.config.EncodeData;
import id.co.sisteminformasiakreditasibackend.service.DTPTService;
import id.co.sisteminformasiakreditasibackend.service.KualifikasiTenagaKependidikanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/KualifikasiKependidikan")
public class KualifikasiTenagaKependidikanRest {
    @Autowired
    private KualifikasiTenagaKependidikanService kualifikasiTenagaKependidikanService;

    @Autowired
    private EncodeData encodeData;

    @PostMapping("/getKualifikasiKependidikan")
    public ResponseEntity<String> getDTPT(@RequestBody Map<String, Object> data) {
        try {
            Map<String, Object> encodedData = encodeData.htmlEncodeObject(data);
            String result = kualifikasiTenagaKependidikanService.getKualifikasiKependidikan(encodedData);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to get data", e);
        }
    }
}
