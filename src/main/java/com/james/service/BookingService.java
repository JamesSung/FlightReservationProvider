package com.james.service;

import java.util.Date;
import java.util.List;

import com.james.bo.Airport;
import com.james.bo.Booking;
import com.james.bo.Flight;

public interface BookingService {
	
	/**
	 * Returns a list of airports located in a specific city
	 * 
	 * @param city Name of city to find (e.g. Toronto, Quebec)
	 * @return list of airports located in a city
	 */
	public List<Airport> findAirportsByCity(String city);

	/**
	 * Returns a list of airports located in a specific city
	 * 
	 * @param code airport code to find (e.g. YYZ)
	 * @return airport located matched by code
	 */
	public Airport findAirportsByCode(String code);

	/**
	 * Returns a list of all the airports
	 * 
	 * @return list of airports
	 */
	public List<Airport> findAllAirports();

	/**
	 * Returns a list of flights searched by airports
	 * 
	 * @param departureCode 3digit airport code defined by the International Air Transport Association
	 * @param arrivalCode 3digit airport code defined by the International Air Transport Association
	 * @return list of flights searched by airport codes
	 */
	public List<Flight> findFlightsByAirport(String departureCode, String arrivalCode);

	/**
	 * Returns a list of all the flights
	 * 
	 * @return list of flights
	 */
	public List<Flight> findAllFlights();

	/**
	 * Returns flight matched by id
	 * 
	 * @param id flight id
	 * @return flight
	 */
	public Flight findFlightById(String id);

	
	/**
	 * Returns a list of flights searched by airports and departure data
	 * 
	 * @param departureCode 3digit airport code defined by the International Air Transport Association
	 * @param arrivalCode 3digit airport code defined by the International Air Transport Association
	 * @param from date for searching
	 * @param to date for searching
	 * @param hasReturn including return ticket or not
	 * @return list of flights searched by airports and departure data
	 */
	public List<Flight> findFlightsByDepartureAndArrivalAndDepartureDateBetween(String departureCode, String arrivalCode, Date from, Date to, boolean hasReturn);
	
	/**
	 * Returns a list of bookings belong to a specific user
	 * 
	 * @param booker user id
	 * @return list of bookings belong to a user
	 */
	public List<Booking> findBookingsByBooker(String booker);
	
	/**
	 * Returns a list of bookings belong to a specific user
	 * 
	 * @param booker user id
	 * @param password password
	 * @return list of bookings belong to a user
	 */
	public List<Booking> findBookingsByBookerAndPassword(String booker, String password);
	
}
