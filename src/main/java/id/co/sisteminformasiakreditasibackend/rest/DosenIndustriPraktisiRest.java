package id.co.sisteminformasiakreditasibackend.rest;

import id.co.sisteminformasiakreditasibackend.config.EncodeData;
import id.co.sisteminformasiakreditasibackend.service.DosenIndustriPraktisiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/DosenIndustriPraktisi")
public class DosenIndustriPraktisiRest {
    @Autowired
    private DosenIndustriPraktisiService dosenIndustriPraktisiService;

    @Autowired
    private EncodeData encodeData;

    @PostMapping("/GetDosenIndustriPraktisi")
    public ResponseEntity<String> getDosenIndustriPraktisi(@RequestBody Map<String, Object> data) {
        try {
            Map<String, Object> encodedData = encodeData.htmlEncodeObject(data);
            String result = dosenIndustriPraktisiService.getDataDosenIndustriPraktisi(encodedData);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to get data", e);
        }
    }
}
