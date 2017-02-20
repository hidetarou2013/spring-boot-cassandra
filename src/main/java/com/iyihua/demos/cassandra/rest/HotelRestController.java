package com.iyihua.demos.cassandra.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iyihua.demos.cassandra.domain.Hotel;
import com.iyihua.demos.cassandra.service.HotelService;


@RestController
@RequestMapping("rest/cassandra/hotel")
public class HotelRestController {

	@Autowired
	HotelService service;

	@GetMapping
	List<Hotel> getHotelAll() {
		// curl http://localhost:8081/rest/cassandra/hotel -i -XGET
		return this.service.findAll();
	}

}
