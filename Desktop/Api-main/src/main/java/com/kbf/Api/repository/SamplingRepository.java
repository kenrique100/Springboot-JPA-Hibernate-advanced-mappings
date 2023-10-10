package com.kbf.Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kbf.Api.model.FishSampling;



public interface SamplingRepository extends JpaRepository<FishSampling, Integer> {

}
