package com.forrage.service;

import com.forrage.modele.*;
import com.forrage.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DetailService {

    @Autowired
    private DetailRepository detailRepository;

    @Autowired
    private DevisRepository devisRepository;

    @Transactional
    public void save(Detail detail){
        if(detail.getDevis() != null){
            int idDevis = detail.getDevis().getIdDevis();
            if(idDevis != 0){
                Devis d = devisRepository.findById(idDevis).orElse(null);
                detail.setDevis(d);
            } else {
                detail.setDevis(null);
            }
        }

        detailRepository.save(detail);
    }

    public List<Detail> getDetailsByIdDevis(int idDevis){
        return detailRepository.findByDevisIdDevis(idDevis);
    }
}
