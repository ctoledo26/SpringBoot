package com.promineotech.jeep.controller;


import static org.assertj.core.api.Assertions.assertThat;




import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import com.promineotech.jeep.entity.JeepModel;
import com.promineotech.jeep.entity.Order;

 


@SpringBootTest (webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {"classpath:flyway/migrations/V1.0__Jeep_Schema.sql",
			"classpath:flyway/migrations/V1.1__Jeep_Data.sql"},
			config = @ SqlConfig( encoding = "utf-8"))

class CreateOrderTest {
	
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int serverPort;
	/**
	 * 
	 */	
	@Test

	void testCreateOrderReturnsSuccess201() {
		// Given : an order as JSON
		String body = createOrderBody();
		String uri = String.format("http://localhost:%d/orders", serverPort);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);
		//When: order is sent
		
		ResponseEntity<Order> response = restTemplate.exchange(uri, HttpMethod.POST, bodyEntity, Order.class);
		
		//Then: a 201 status is returned
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		
		
		//And: the returned order is correct
		assertThat(response.getBody()).isNotNull();
		
		Order order = response.getBody();
		
		assertThat(order.getCustomer().getCustomerId()).isEqualTo("MAYNARD_TORBJORG");
		assertThat(order.getModel().getModelId()).isEqualTo(JeepModel.GLADIATOR);
		assertThat(order.getModel().getTrimLevel()).isEqualTo("Willys");
		assertThat(order.getModel().getNumDoors()).isEqualTo(4);
		assertThat(order.getColor().getColorId()).isEqualTo("EXT_GRANITE_CRYSTAL");
		assertThat(order.getEngine().getEngineId()).isEqualTo("6_4_GAS");
		assertThat(order.getTire().getTireId()).isEqualTo("265_GOODYEAR");
		assertThat(order.getOptions()).hasSize(6);
}

	protected String createOrderBody() {
		//@formatter: off
	
		return "{\n"
				+ "  \"customer\":\"MAYNARD_TORBJORG\",\n"
				+ "  \"model\":\"GLADIATOR\",\n"
				+ "  \"trim\":\"Willys\",\n"
				+ "  \"doors\":4,\n"
				+ "  \"color\":\"EXT_GRANITE_CRYSTAL\",\n"
				+ "  \"engine\":\"6_4_GAS\",\n"
				+ "  \"tire\":\"265_GOODYEAR\",\n"
				+ "  \"options\":[\n"
				+ "    \"DOOR_QUAD_4\",\n"
				+ "    \"EXT_AEV_LIFT\",\n"
				+ "    \"EXT_WARN_WINCH\",\n"
				+ "    \"EXT_WARN_BUMPER_FRONT\",\n"
				+ "    \"EXT_WARN_BUMPER_REAR\",\n"
				+ "    \"EXT_ARB_COMPRESSOR\"\n"
				+ "  ]\n"
				+ "}";
		
				//@formatter: on
	}
	


}