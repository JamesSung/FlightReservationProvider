package com.james.service.impl;

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
import com.james.service.EntityMgmtService;

@Component
public class EntityMgmtServiceImpl implements EntityMgmtService {

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
	public void createAirport(Airport airport) {
		if(logger.isDebugEnabled()){
			logger.debug("DataMgmtServiceImpl.createAirport:" + airport);
		}
		
		airportDao.persist(airport);
		
	}
    
	@Override
	@Transactional
	public void createFlight(Flight flight){
		if(logger.isDebugEnabled()){
			logger.debug("DataMgmtServiceImpl.createFlight:" + flight);
		}
		
    	flight.setDeparture(airportDao.findByCode(flight.getDepartureCode()));
    	flight.setArrival(airportDao.findByCode(flight.getArrivalCode()));
		flightDao.persist(flight);
	}

	@Override
	@Transactional
	public void removeAirport(Airport airport) {
		if(logger.isDebugEnabled()){
			logger.debug("DataMgmtServiceImpl.removeAirport:" + airport);
		}
		
		Airport ar = airportDao.find(airport.getId());
		if(ar != null)
			airportDao.remove(ar);
		
	}
    
	@Override
	@Transactional
	public void removeFlight(Flight flight){
		if(logger.isDebugEnabled()){
			logger.debug("DataMgmtServiceImpl.removeFlight:" + flight);
		}
		
		Flight fl = flightDao.find(flight.getId());
		if(fl != null)		
			flightDao.remove(fl);
	}
	
	@Override
	@Transactional
	public void createBooking(Booking booking){
		if(logger.isDebugEnabled()){
			logger.debug("DataMgmtServiceImpl.createBooking:" + booking);
		}
		
		bookingDao.persist(booking);
	}


}
