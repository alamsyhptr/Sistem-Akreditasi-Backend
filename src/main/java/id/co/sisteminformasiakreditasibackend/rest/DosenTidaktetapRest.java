package id.co.sisteminformasiakreditasibackend.rest;

import id.co.sisteminformasiakreditasibackend.config.EncodeData;
import id.co.sisteminformasiakreditasibackend.service.DosenTidakTetapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/DosenTidaktetap")
public class DosenTidaktetapRest {

    @Autowired
    private DosenTidakTetapService dosenTidakTetapService;

    @Autowired
    private EncodeData encodeData;

    @PostMapping("/getDosenTidaktetap")
    public ResponseEntity<String> getDosenTidaktetap(@RequestBody Map<String, Object> data) {
        try {
            Map<String, Object> encodedData = encodeData.htmlEncodeObject(data);
            String result = dosenTidakTetapService.getDataDosenTidaktetap(encodedData);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to get data", e);
        }
    }
}
