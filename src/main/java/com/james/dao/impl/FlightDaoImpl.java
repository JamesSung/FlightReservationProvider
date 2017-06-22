package com.james.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import com.james.bo.Flight;
import com.james.dao.FlightDao;

@Component
public class FlightDaoImpl implements FlightDao{

	@PersistenceContext(unitName = "myPu")
	protected EntityManager entityManager;

	@Override
	public void persist(Flight flight) {
		entityManager.persist(flight);	
	}

	@Override
	public void merge(Flight flight) {
		entityManager.merge(flight);	
	}

	@Override
	public void remove(Flight flight) {
		entityManager.remove(flight);	
	}

	@Override
	public Flight find(String id) {
		return entityManager.find(Flight.class, id);
	}

	@Override
	public List<Flight> findByDepartureAndArrival(String departureCode, String arrivalCode) {
        
		TypedQuery<Flight> query = entityManager.createQuery( "SELECT f FROM flights f WHERE f.departureCode = :departureCode and f.arrivalCode = :arrivalCode ORDER BY f.departureDate ASC", Flight.class);
		query.setParameter("departureId", departureCode);
		query.setParameter("arrivalId", arrivalCode);
		
        return query.getResultList();
	}

	@Override
	public List<Flight> findByDepartureAndArrivalAndDepartureDateBetween(String departureCode, String arrivalCode, Date from, Date to, boolean hasReturn) {
        String cond = ("all".equals(departureCode)) ? " " : hasReturn ? "f.departureCode IN (:departureCode, :arrivalCode) AND" : " f.departureCode = :departureCode AND ";
        String cond2 = ("all".equals(arrivalCode)) ? " " : hasReturn ? "f.arrivalCode IN (:arrivalCode, :departureCode) AND" : " f.arrivalCode = :arrivalCode AND ";
		
        //looking up direct flights from A to B
        TypedQuery<Flight> query = entityManager.createQuery( "SELECT f "
															+ "FROM flights f WHERE "
															+ cond + cond2
															+ " f.departureDate BETWEEN :from AND :to "
															+ " ORDER BY f.departureCode, f.departureDate ", Flight.class);
		
		//native query for direct flights from A to B
		// db.flights.aggregate([ { $match :{$and:[ { departureCode : { $in: ["YYZ", "JFK"] } }, { arrivalCode : { $in: ["YYZ", "JFK"] } } ]} } ]);

        if(!"all".equals(departureCode)) query.setParameter("departureCode", departureCode);
		if(!"all".equals(arrivalCode)) query.setParameter("arrivalCode", arrivalCode);
		
		query.setParameter("from", from, TemporalType.DATE);
		query.setParameter("to", to, TemporalType.DATE);
		
        return query.getResultList();
	}

	@Override
	public List<Flight> findAll() {
        
		TypedQuery<Flight> query = entityManager.createQuery( "SELECT f FROM flights f ORDER BY f.departureDate ASC", Flight.class);
		
        return query.getResultList();
	}
}
