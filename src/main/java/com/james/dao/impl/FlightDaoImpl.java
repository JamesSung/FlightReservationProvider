package com.james.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
	public List<Flight> findByDepartureAndArrivalAndDepartureDateBetween(String departureCode, String arrivalCode, Date from, Date to) {
        String cond = ("all".equals(departureCode)) ? " ":" f.departureCode = :departureCode AND ";
        String cond2 = ("all".equals(arrivalCode)) ? " ":" f.arrivalCode = :arrivalCode AND ";
		TypedQuery<Flight> query = entityManager.createQuery( "SELECT f "
															+ "FROM flights f WHERE "
															+ cond + cond2
															+ " f.departureDate BETWEEN :from AND :to ", Flight.class);
		if(!"all".equals(departureCode)) query.setParameter("departureCode", departureCode);
		if(!"all".equals(arrivalCode)) query.setParameter("arrivalCode", arrivalCode);
		query.setParameter("from", from);
		query.setParameter("to", to);
		
        return query.getResultList();
	}

	@Override
	public List<Flight> findAll() {
        
		TypedQuery<Flight> query = entityManager.createQuery( "SELECT f FROM flights f ORDER BY f.departureDate ASC", Flight.class);
		
        return query.getResultList();
	}
}
