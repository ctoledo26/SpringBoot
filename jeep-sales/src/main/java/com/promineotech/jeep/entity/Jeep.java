package com.promineotech.jeep.entity;

import java.math.BigDecimal;
import java.util.Comparator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor


public class Jeep {
	
	public static final Jeep builder = null;
	private Long modelPK;
	  private JeepModel modelId;
	  private String trimLevel;
	  private int numDoors;
	  private int wheelSize;
	  private BigDecimal basePrice;

	  
//	  
	  @JsonIgnore
	 public Long getModelPK() {
		 return modelPK;
		 
	 }
	  
	  public int compareTo(Jeep that) {
	    return Comparator
	        .comparing(Jeep::getModelId)
	        .thenComparing(Jeep::getTrimLevel)
	        .thenComparing(Jeep::getNumDoors)
	        .compare(this,that);
	}
}
