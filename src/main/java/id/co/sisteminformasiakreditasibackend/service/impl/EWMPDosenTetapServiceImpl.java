package id.co.sisteminformasiakreditasibackend.service.impl;

import id.co.sisteminformasiakreditasibackend.repository.PolmanAstraRepository;
import id.co.sisteminformasiakreditasibackend.service.EWMPDosenTetapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class EWMPDosenTetapServiceImpl implements EWMPDosenTetapService {
    @Autowired
    PolmanAstraRepository polmanAstraRepository;

    @Override
    public String getDataEWMPDosenTetap(Map<String, Object> data){
        List<String> dataList = new ArrayList<>();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            dataList.add(entry.getValue().toString());
        }
        String result = polmanAstraRepository.callProcedure("akd_EWMP", dataList.toArray(new String[0]));
        return result;
    }
}
