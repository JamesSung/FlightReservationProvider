package com.james.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import com.james.bo.Airport;
import com.james.dao.AirportDao;

@Component
public class AirportDaoImpl implements AirportDao{

	@PersistenceContext(unitName = "myPu")
	protected EntityManager entityManager;

	@Override
	public void persist(Airport airport) {
		entityManager.persist(airport);	
	}

	@Override
	public void merge(Airport airport) {
		entityManager.merge(airport);		
	}

	@Override
	public void remove(Airport airport) {
		entityManager.remove(airport);		
	}

	@Override
	public Airport find(String id) {
		return entityManager.find(Airport.class, id);
	}

	@Override
	public Airport findByCode(String code) {

		TypedQuery<Airport> query = entityManager.createQuery( "SELECT a FROM airports a WHERE a.code = :code", Airport.class);
		query.setParameter("code", code);
		
        return query.getSingleResult();
	}

	@Override
	public List<Airport> findByCity(String city) {
		
		TypedQuery<Airport> query = entityManager.createQuery( "SELECT a FROM airports a WHERE a.city = :city", Airport.class);
		query.setParameter("city", city);
		
        return query.getResultList();
	}

	@Override
	public List<Airport> findAll() {
		
		TypedQuery<Airport> query = entityManager.createQuery( "SELECT a FROM airports a ORDER BY a.name ASC", Airport.class);
		
        return query.getResultList();
	}
}
