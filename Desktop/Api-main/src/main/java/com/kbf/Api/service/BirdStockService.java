package com.kbf.Api.service;

import java.util.List;

import com.kbf.Api.model.BirdStock;

public interface BirdStockService {

	BirdStock createStock(BirdStock stock);

    List<BirdStock> findAll(); 

    BirdStock getStockById(int stockId);

    BirdStock updateStockById(int stockId, BirdStock stock);

    String deleteStockById (int stockID);

}
