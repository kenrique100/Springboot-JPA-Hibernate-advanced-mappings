package com.kbf.Api.service;

import com.kbf.Api.model.Pond;
import com.kbf.Api.model.dto.PondDTO;

import java.util.List;

public interface PondService {

    Pond createPond(Pond pond);

    List<Pond> findAll();
    
    List<PondDTO> getAll();

    Pond getPondById(int pondId);
    
    Pond getPondByPondName(String pondName);

    Pond updatePondById(int pondId, Pond pond);

    String deletePondById(int pondId);

}
