package com.james.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * @author James sung
 *
 */

@Entity(name="flights")
@Table(name="flights", indexes = { @Index(name = "dept_arrv_deptdt_idx", columnList = "departureCode, arrivalCode, departureDate") })
public class Flight implements Serializable{
	
	private static final long serialVersionUID = 2914695046322584434L;

	@Id
    @GeneratedValue(generator = "flight_id")
    @GenericGenerator(name = "flight_id", strategy = "uuid2")
    private String id;
	
	private String planeName;
	
	private String company;
	
	/**
	 * Departure airport code
	 */
	private String departureCode;
	
	/**
	 * Departure airport code
	 */
	private String arrivalCode;


	@ManyToOne(targetEntity = Airport.class, cascade = { CascadeType.ALL }, fetch=FetchType.EAGER)
	@JoinColumn(name="departureId", nullable=false)
	private Airport departure;
	
	@ManyToOne(targetEntity = Airport.class, cascade = { CascadeType.ALL }, fetch=FetchType.EAGER)
	@JoinColumn(name="arrivalId", nullable=false)
	private Airport arrival;
	
	@Temporal(TemporalType.DATE)
	private Date departureDate;
	
	private BigDecimal price;
	
	//@ManyToMany(targetEntity = Booking.class, mappedBy = "flights", fetch=FetchType.LAZY)
	//private List<Booking> bookings;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlaneName() {
		return planeName;
	}

	public void setPlaneName(String planeName) {
		this.planeName = planeName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDepartureCode() {
		return departureCode;
	}

	public void setDepartureCode(String departureCode) {
		this.departureCode = departureCode;
	}

	public String getArrivalCode() {
		return arrivalCode;
	}

	public void setArrivalCode(String arrivalCode) {
		this.arrivalCode = arrivalCode;
	}

	public Airport getDeparture() {
		return departure;
	}

	public void setDeparture(Airport departure) {
		this.departure = departure;
	}

	public Airport getArrival() {
		return arrival;
	}

	public void setArrival(Airport arrival) {
		this.arrival = arrival;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

//	public List<Booking> getBookings() {
//		return bookings;
//	}
//
//	public void setBookings(List<Booking> bookings) {
//		this.bookings = bookings;
//	}
	
	@Override
	public String toString(){
		return "Flight:[id: "+this.id+", planeName: " 
							+ this.planeName + ", company: " 
							+ this.company +", departureCode: " 
							+ this.departureCode +", arrivalCode: " 
							+ this.arrivalCode +", departure: [" 
							+ this.departure +"], arrival: [" 
							+ this.arrival +"], departureDate: " 
							+ this.departureDate +", price: " 
							+ this.price +"]";
	}	
}
