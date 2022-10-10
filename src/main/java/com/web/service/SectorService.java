package com.web.service;

import com.web.model.Sector;
import com.web.repository.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectorService {

    @Autowired
    private SectorRepository sectorRepository;

    public List<Sector> findAll(){
        return sectorRepository.findAll();
    }
}
