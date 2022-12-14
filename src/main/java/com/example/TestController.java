package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
@GetMapping("/hello")
public String wish() {
	return "Hello S ";
}

@PostMapping(path = "/save-cust-info")
public String customerInformation(@RequestBody Customer cust) {

    /* You can write your DAO logic here.
     * For time being I am printing the customer data just to show the POST call is working.
     */

    return "Customer information saved successfully ::." + cust.getCustNo() + " " + cust.getName() + " " + cust.getCountry();
}

@PostMapping(path = "/save-cust-info1/{cust}")
public String customerInformation(@PathVariable String cust) {

    /* You can write your DAO logic here.
     * For time being I am printing the customer data just to show the POST call is working.
     */

    return "Customer information saved successfully ::." + cust;
}


@Autowired CustomerService customerService;

/**
* Get all the customer available in the underlying system
* @return list of customers
*/
@GetMapping
public ResponseEntity < List < Customer >> getCustomers() {
	List < Customer > customers = customerService.getCustomers();
	return new ResponseEntity < >(customers, HttpStatus.OK);
}

/**
* Create a customer with the system.This end point accepts customer information in 
* the json format.It will create and send back the data to the REST customer.
* @param customer
* @return newely created customer
*/
@PostMapping(value = "/customer")
public ResponseEntity < Customer > createCustomer(@RequestBody Customer customer) {
	final Customer customerData = customerService.createCustomer(customer);
	return new ResponseEntity < >(customerData, HttpStatus.CREATED);
}

 /**
 * Deleted the customer from the system.client will pass the ID for the customer and this 
 * end point will remove customer from the system if found.
 * @param id
 * @return
 */
@DeleteMapping(value = "/customer/{id}")
public ResponseEntity < String > deleteCustomer(@PathVariable Long id) {
	customerService.deleteCustomer(id);
	return new ResponseEntity < >(HttpStatus.OK);
}

 /**
 * Get the customer detail based on the id passed by the client API.
 * @param id
 * @return customer detail
 */
@GetMapping(value = "/customer/{id}")
public ResponseEntity < Customer > getCustomer(@PathVariable Long id) {
	Customer customer = customerService.getCustomer(id);
	return new ResponseEntity < >(customer, HttpStatus.OK);
}

}
