package com.promineotech.jeep.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.service.JeepSalesService;

import lombok.extern.slf4j.Slf4j;

@RestController

// This annotation allows us to log (its a logging bridge)
@Slf4j
public class DefaultJeepSalesController implements JeepSalesController {
	
	 @Autowired
	 private JeepSalesService jeepSalesService;
	  @Override
	  public List<Jeep> fetchJeeps(String model, String trim) {
		  // Causes logger to log whatever parameters we pass in model and trim
		  log.debug("model={} , trim={}", model, trim);
	    return jeepSalesService.fetchJeeps(model, trim);
	  }

	}
