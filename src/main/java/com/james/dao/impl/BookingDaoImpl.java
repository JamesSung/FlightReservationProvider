package com.james.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import com.james.bo.Booking;
import com.james.dao.BookingDao;
import com.james.util.AESCryptor;

@Component
public class BookingDaoImpl implements BookingDao {

	@PersistenceContext(unitName = "myPu")
	protected EntityManager entityManager;

	@Override
	public void persist(Booking booking) {
		entityManager.persist(booking);	
	}

	@Override
	public void merge(Booking booking) {
		entityManager.merge(booking);	
	}
	
	@Override
	public void remove(Booking booking) {
		entityManager.remove(booking);	
	}

	@Override
	public Booking find(String id) {
		return entityManager.find(Booking.class, id);
	}

	@Override
	public List<Booking> findByBooker(String booker) {
		
		TypedQuery<Booking> query = entityManager.createQuery( "SELECT b FROM bookings b WHERE b.booker = :booker ORDER BY b.bookDate ASC", Booking.class);
		query.setParameter("booker", booker);
		
        return query.getResultList();
	}
	
	@Override
	public List<Booking> findByBookerAndPassword(String booker, String password) {
		
		TypedQuery<Booking> query = entityManager.createQuery( "SELECT b FROM bookings b WHERE b.booker = :booker AND b.password = :password ORDER BY b.bookDate ASC", Booking.class);
		query.setParameter("booker", booker);
		query.setParameter("password", password);
		
        return query.getResultList();
	}

	@Override
	public List<Booking> findAll() {
		
		TypedQuery<Booking> query = entityManager.createQuery( "SELECT b FROM bookings b ORDER BY b.bookDate ASC", Booking.class);
		
        return query.getResultList();
	}

	
}
