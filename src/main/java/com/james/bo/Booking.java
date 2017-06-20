package com.james.bo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.james.util.AESCryptor;
import com.james.util.PWConverter;

/**
 * 
 * @author James sung
 *
 */

@Entity(name="bookings")
@Table(name="bookings", indexes = { @Index(name = "booker_idx", columnList = "booker") })
public class Booking implements Serializable {

	private static final long serialVersionUID = -8794503573178217902L;

	@Id
    @GeneratedValue(generator = "booking_id")
    @GenericGenerator(name = "booking_id", strategy = "uuid2")
    private String id;
	
	@Temporal(TemporalType.DATE)
	private Date bookDate;
	
	private int numOfTickets = 1;
	
	private String booker;
	
	@Convert(converter = PWConverter.class)
	private String password;

	@Enumerated(EnumType.ORDINAL)
	private BookingStatus status = BookingStatus.PENDING;

	@ElementCollection
	@OrderColumn(name = "departureDate")
	private List<Flight> flights;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getBookDate() {
		return bookDate;
	}

	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}

	public int getNumOfTickets() {
		return numOfTickets;
	}

	public void setNumOfTickets(int numOfTickets) {
		this.numOfTickets = numOfTickets;
	}

	public String getBooker() {
		return booker;
	}

	public void setBooker(String booker) {
		this.booker = booker;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BookingStatus getStatus() {
		return status;
	}

	public void setStatus(BookingStatus status) {
		this.status = status;
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}
	
	@Override
	public String toString(){
		return "Booking:[id: "+this.id+", bookDate: " 
							+ this.bookDate + ", numOfTickets: " 
							+ this.numOfTickets +", booker: " 
							+ this.booker +", password: " 
							+ this.password +", status: " 
							+ this.status +", flights: [" 
							+ this.flights
							+"]]";
	}	
}
