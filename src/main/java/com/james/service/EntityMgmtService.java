package com.james.service;

import com.james.bo.Airport;
import com.james.bo.Booking;
import com.james.bo.Flight;

public interface EntityMgmtService {
    
	/**
	 * Insert a airport data into the airport table
	 * 
	 * @param airport object of Airport
	 */
	public void createAirport(Airport airport);
	
	/**
	 * Insert a flight information into the flight table
	 * 
	 * @param flight object of Flight
	 */
	public void createFlight(Flight flight);

	/**
	 * Remove a airport data from the airport table
	 * 
	 * @param airport object of Airport
	 */
	public void removeAirport(Airport airport);
	
	/**
	 * Remove a flight information from the flight table
	 * 
	 * @param flight object of Flight
	 */
	public void removeFlight(Flight flight);

	/**
	 * Insert a booking information into the booking table
	 * 
	 * @param booking object of Booking
	 */
	public void createBooking(Booking flight);

}
