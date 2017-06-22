

//import static org.junit.Assert.*;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.TemporalType;
//import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Test;


import com.james.bo.Airport;
import com.james.bo.Booking;
import com.james.bo.Flight;
import com.james.dao.impl.BookingDaoImpl;
import com.james.util.AESCryptor;



/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		  {
			   "classpath:beans.xml"
			  }
			)*/
public class DaoImplTest extends JpaBaseTestCase{
	
	@Test
	public void testCreateAirport(){
		
		Airport airport = new Airport();
		airport.setCode("YYZ");
		airport.setName("Pearson");
		airport.setCity("Toronto");
		airport.setCountry("Canada");
		
		Airport airport2 = new Airport();
		airport2.setCode("ICH");
		airport2.setName("Inchun International");
		airport2.setCity("Inchun");
		airport2.setCountry("Korea");
		
		em.persist(airport);
		em.persist(airport2);
	}

	@Test
	public void testCreateFlight(){
		
		Flight flight = new Flight();
		flight.setPlaneName("777");
		flight.setCompany("Air Canada");
		flight.setPrice(BigDecimal.valueOf(1000));
		flight.setDepartureDate(new Date(System.currentTimeMillis()));
		flight.setDepartureCode("YYZ");
		flight.setArrivalCode("ICH");
		flight.setDeparture(new Airport("YYZ",null,null,null));
		flight.setArrival(new Airport("ICH",null,null,null));
		
		em.persist(flight);
	}

	@Test
	public void testCreateBooking(){
		
		Booking booking = new Booking();
		booking.setBookDate(new Date(System.currentTimeMillis()));
		booking.setBooker("james@g.com");
		booking.setNumOfTickets(1);
		
		Flight flight = new Flight();
		flight.setId("2bef5474-34bc-4656-a62d-be6e76fc1e8e");
		
		List<Flight> fls = new ArrayList<Flight>();
		fls.add(flight);
		booking.setFlights(fls);
		
		em.persist(booking);
	}
	
	@Test
	public void testFindAirportByCity(){

		TypedQuery<Airport> query = em.createQuery( "SELECT a FROM airports a WHERE a.city = :city", Airport.class);
		query.setParameter("city", "Toronto");
		List<Airport> airports = query.getResultList();
		
        //assertNotNull(query.getResultList());
        if(airports != null){
        	for(Airport airport : airports){
        		System.out.println(airport);
        	}
        }
	}
	
	@Test
	public void testFindBookingByBooker(){

		TypedQuery<Booking> query = em.createQuery( "SELECT b FROM bookings b WHERE b.booker = :booker ORDER BY b.bookDate ASC", Booking.class);
		//query.setParameter("booker", "james@g.com");
		query.setParameter("booker", "sung@gmail.com");
		
        List<Booking> bookings = query.getResultList();
		
        assertNotNull(bookings);
		
        bookings.forEach(booking -> System.out.println(booking));
	}

	@Test
	public void testFindBookingByBookerAndPassword(){

		TypedQuery<Booking> query = em.createQuery( "SELECT b FROM bookings b WHERE b.booker = :booker AND b.password = :password ORDER BY b.bookDate ASC", Booking.class);
		//String pwd = AESCryptor.getInstance().encrypt("1111");
		//System.out.println("password: " + pwd);
		
		query.setParameter("booker", "sung@gmail.com");
		query.setParameter("password", "1111");

        List<Booking> bookings = query.getResultList();
        assertNotNull(bookings);
		
        bookings.forEach(booking -> System.out.println(booking));
	}
	
	@Test
	public void testFindFlightByAirport(){
		
        // Find all the available flights ordered by departureDate
        List<Flight> flights = em
            .createQuery( "SELECT f FROM flights f WHERE f.departureCode = 'YYZ' and f.arrivalCode = 'ICH' ORDER BY f.departureDate ASC" , Flight.class )
            .getResultList();
		
        assertNotNull(flights);
		
        flights.forEach(flight -> System.out.println(flight));
	}
	
	@Test
	public void testFindFlightsByDepartureAndArrivalAndDepartureDateBetween(){
		
		TypedQuery<Flight> query = em.createQuery( "SELECT f "
				+ "FROM flights f WHERE "
				+ " f.departureCode IN (:departureCode, :arrivalCode) AND" 
				+ " f.departureCode IN (:arrivalCode, :departureCode) AND"
				+ " f.departureDate BETWEEN :from AND :to "
				+ " ORDER BY f.departureCode, f.departureDate ", Flight.class);

		query.setParameter("departureCode","YYZ");
		query.setParameter("arrivalCode", "JFK");

    	Date from = new Date(System.currentTimeMillis());
    	
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(from);
    	cal.add(Calendar.DAY_OF_MONTH, 10);
    	
    	Date to = cal.getTime();
    	
    	
		query.setParameter("from", from, TemporalType.DATE);
		query.setParameter("to", to, TemporalType.DATE);

		
		List<Flight> flights = query.getResultList();
		
        assertNotNull(flights);
		
        flights.forEach(flight -> System.out.println(flight));
	}
	
	@Test
	public void testFindAllFlight(){
		
        // Find all the available flights ordered by departureDate
        List<Flight> flights = em
            .createQuery( "SELECT f FROM flights f ORDER BY f.departureDate ASC" , Flight.class )
            .getResultList();
		
        assertNotNull(flights);
		
        flights.forEach(flight -> System.out.println(flight));
	}	
	
	@Test
	public void testCreateAirportNFlight(){
		
		isRollback = false;
		
		Airport airport = new Airport();
		airport.setCode("ICT");
		airport.setName("Inchun International ");
		airport.setCity("Inchun");
		airport.setCountry("Korea");
		
		Flight flight = new Flight();
		flight.setPlaneName("777");
		flight.setCompany("Air Canada");
		flight.setPrice(BigDecimal.valueOf(1000));
		flight.setDepartureDate(new Date(System.currentTimeMillis()));
		flight.setDepartureCode("YYZ");
		flight.setArrivalCode("ICH");
		flight.setDeparture(new Airport("YYZ",null,null,null));
		flight.setArrival(new Airport("ICH",null,null,null));
		
		em.persist(airport);
		em.persist(flight);
	}
}
