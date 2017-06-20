package com.james.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.james.bo.Airport;
import com.james.bo.Booking;
import com.james.bo.Flight;
import com.james.dao.AirportDao;
import com.james.dao.BookingDao;
import com.james.dao.FlightDao;
import com.james.service.BookingService;

@Component
public class BookingServiceImpl implements BookingService{

    @Autowired 
    AirportDao airportDao;
    
    @Autowired
    FlightDao flightDao;
    
    @Autowired
    BookingDao bookingDao;
    
    @Autowired
    Logger logger;
    
	@Override
	@Transactional
	public List<Airport> findAirportsByCity(String city) {
		
		List<Airport> ars = airportDao.findByCity(city);
		
		if(logger.isDebugEnabled()){
			logger.debug("BookingServiceImpl.findAirportsByCity:");
			ars.forEach(ar -> logger.debug(ar));
		}
		
		return ars;
	}
	
	@Override
	@Transactional
	public Airport findAirportsByCode(String code) {
		
		Airport ar = airportDao.findByCode(code);
		
		if(logger.isDebugEnabled()){
			logger.debug("BookingServiceImpl.findAirportsByCity: " + ar);
		}
		
		return ar;
	}

	@Override
	@Transactional
	public List<Airport> findAllAirports() {
		
		List<Airport> ars = airportDao.findAll();
		
		if(logger.isDebugEnabled()){
			logger.debug("BookingServiceImpl.findAllAirports:");
			ars.forEach(ar -> logger.debug(ar));
		}
		
		return ars;
	}
	
	@Override
	@Transactional
	public List<Flight> findFlightsByAirport(String departureCode, String arrivalCode) {
		List<Flight> fls = flightDao.findByDepartureAndArrival(departureCode, arrivalCode);

		if(logger.isDebugEnabled()){
			logger.debug("BookingServiceImpl.findFlightsByAirport:");
			fls.forEach(fl -> logger.debug(fl));
		}
		
		return fls;
	}

	@Override
	@Transactional
	public List<Flight> findAllFlights() {
		List<Flight> fls = flightDao.findAll();

		if(logger.isDebugEnabled()){
			logger.debug("BookingServiceImpl.findFlightsByAirport:");
			fls.forEach(fl -> logger.debug(fl));
		}
		
		return fls;
	}

	@Override
	@Transactional
	public Flight findFlightById(String id) {
		return flightDao.find(id);
	}
	
	@Override
	@Transactional
	public List<Flight> findFlightsByDepartureAndArrivalAndDepartureDateBetween(String departureCode, String arrivalCode, Date from,
			Date to) {
		List<Flight> fls = flightDao.findByDepartureAndArrivalAndDepartureDateBetween(departureCode, arrivalCode, from, to);

		if(logger.isDebugEnabled()){
			logger.debug("BookingServiceImpl.findFlightsByDepartureAndArrivalAndDepartureDateBetween:");
			fls.forEach(fl -> logger.debug(fl));
		}
		
		return fls;
	}
	
	@Override
	@Transactional
	public List<Booking> findBookingsByBooker(String booker) {
		List<Booking> bks = bookingDao.findByBooker(booker);
		
		if(logger.isDebugEnabled()){
			logger.debug("BookingServiceImpl.findBookingsByBooker:");
			bks.forEach(bk -> logger.debug(bk));
		}
		
		return bks;
		
	}

	@Override
	@Transactional
	public List<Booking> findBookingsByBookerAndPassword(String booker, String password) {
		List<Booking> bks = bookingDao.findByBookerAndPassword(booker, password);
		
		if(logger.isDebugEnabled()){
			logger.debug("BookingServiceImpl.findBookingsByBooker:");
			bks.forEach(bk -> logger.debug(bk));
		}
		
		return bks;
		
	}

}
