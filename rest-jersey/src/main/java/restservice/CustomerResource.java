package restservice;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;


//ctrl + shift + f to indent in eclipse
@Path("customers")
public class CustomerResource {
// ConcurrentHashMap -                    A hash                                          table                                                                                    supporting full concurrency of retrievals and adjustable expected concurrency for updates.                  
	static private Map<Integer, Customer> customerDB = new ConcurrentHashMap<Integer, Customer>();

// An  AtomicInteger                                                                                                                                                                                                                    is used in applications such as atomically incremented counters                  
	static private AtomicInteger idCounter = new AtomicInteger();
	
	
	
	
	/*
	 * TO INVOKE THIS FROM POSTMAN 
	 * 1)POST METHOD http://localhost:8080/rest-jersey/thisthat/customers/
	 * 2)BODY>RAW>(APPLICATION/XML)
	 * 3)IN BODY <customer><firstname>Joaquin Loera</firstname><lastname>Burke</lastname><email>bill.burke@gmail.com</email></customer>
	 * 
	 * */
	@POST
	@Consumes("application/xml")
	public Customer createCustomer(Customer customer) {
		customer.id = idCounter.incrementAndGet();
		customerDB.put(customer.id, customer);
		return customer;
	}

	
	/*http://localhost:8080/rest-jersey/thisthat/customers/1*/
	@GET
	@Path("{id}")
	@Produces("application/xml")
	public Customer getCustomer(@PathParam("id") int id) {
		Customer customer = customerDB.get(id);
		return customer;
	}

	@PUT
	@Path("{id}")
	@Consumes("application/xml")
	public void updateCustomer(@PathParam("id") int id, Customer update) {
		Customer current = customerDB.get(id);
		current.firstname = update.firstname;
		current.lastname = update.lastname;
		current.email = update.email;
		customerDB.put(current.id, current);
	}

	@DELETE
	@Path("{id}")
	public void deleteCustomer(@PathParam("id") int id) {
		Customer current = customerDB.remove(id);
		if (current == null)
			throw new WebApplicationException(Response.Status.NOT_FOUND);
	}
}
