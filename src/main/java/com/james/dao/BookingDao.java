package com.james.dao;

import java.util.List;

import com.james.bo.Booking;

public interface BookingDao {
	public void persist(Booking booking);
	
	public void merge(Booking booking);

	public void remove(Booking booking);
	
	public Booking find(String id);
	
	public List<Booking> findByBooker(String booker);

	public List<Booking> findByBookerAndPassword(String booker, String password);
	
	public List<Booking> findAll();
}
