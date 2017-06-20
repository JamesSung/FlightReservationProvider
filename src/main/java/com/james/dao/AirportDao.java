package com.james.dao;

import java.util.List;

import com.james.bo.Airport;

public interface AirportDao {
	
	public void persist(Airport airport);
	
	public void merge(Airport airport);
	
	public void remove(Airport airport);
	
	public Airport find(String id);
	
	public Airport findByCode(String code);
	
	public List<Airport> findByCity(String city);
	
	public List<Airport> findAll();
}
