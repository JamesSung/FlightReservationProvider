package com.james.dao;

import java.util.Date;
import java.util.List;

import com.james.bo.Flight;

public interface FlightDao {
	public void persist(Flight airport);
	
	public void merge(Flight airport);
	
	public void remove(Flight airport);
	
	public Flight find(String id);
	
	public List<Flight> findByDepartureAndArrival(String departureCode, String arrivalCode);
	
	public List<Flight> findByDepartureAndArrivalAndDepartureDateBetween(String departureCode, String arrivalCode, Date from, Date to);

	public List<Flight> findAll();

}
