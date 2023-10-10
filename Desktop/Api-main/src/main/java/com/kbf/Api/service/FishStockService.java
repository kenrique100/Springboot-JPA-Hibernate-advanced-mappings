package com.kbf.Api.service;

import java.util.List;

import com.kbf.Api.model.FishStock;

public interface FishStockService {

    FishStock createStock(FishStock stock);

    List<FishStock> findAll();

    FishStock getStockById(int stockId);

    FishStock updateStockById(int stockId, FishStock stock);

    String deleteStockById (int stockID);

}
