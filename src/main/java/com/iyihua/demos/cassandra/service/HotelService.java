package com.iyihua.demos.cassandra.service;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iyihua.demos.cassandra.domain.Hotel;
import com.iyihua.demos.cassandra.repository.HotelRepository;


@Service
@Transactional
public class HotelService {

	@Autowired
	HotelRepository repository;

	public java.util.List<Hotel> findAll(){
		Iterable<Hotel> iteObj = repository.findAll();
		return StreamSupport.stream(iteObj.spliterator(), false)
			    .collect(Collectors.toList());
	}
}
