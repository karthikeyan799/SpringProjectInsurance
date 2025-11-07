package com.insurance.rest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.dao.CustomerRepository;
import com.insurance.dto.CustomerDTO;
import com.insurance.dto.LoginDTO;
import com.insurance.dto.RegistrationDTO;
import com.insurance.entity.Policy;
import com.insurance.entity.Registration;
import com.insurance.exception.CustomerInternalServerException;
import com.insurance.exception.CustomerNotFoundException;
import com.insurance.exception.DuplicateValueException;
import com.insurance.response.LoginResponse;
import com.insurance.response.RegisterResponse;
import com.insurance.response.Response;
import com.insurance.response.ResponseObject;
import com.insurance.service.CustomerService;
import com.insurance.service.PolicyService;
import com.insurance.service.RegistrationService;
import com.insurance.service.UserService;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")

public class CustomerPolicyService {

	private static Logger logger = Logger.getLogger(CustomerPolicyService.class);
	@Autowired
	CustomerService customerService;

	@Autowired
	RegistrationService registration;

	@Autowired
	PolicyService policyService;

	@Autowired
	ResponseObject object;

	@Autowired
	CustomerRepository repository;

	@RequestMapping("/sayHello")
	public String sayhello() {
		return "Say Hello";
	}

	@RequestMapping("/sayHelloUser")
	public String sayHelloUser(@RequestParam(value = "name", defaultValue = "Jim") String name,
			@RequestParam(value = "phone", required = true) String phone) {
		return "say hello to " + name + " phone" + phone;
	}

	@RequestMapping("/sayHelloFolks/{empid}/{rollid}")
	public String sayHelloFolks(@PathVariable("empid") int id, @PathVariable("rollid") int rollid) {
		return "say hello to employee---->" + id + "Roll Id:" + rollid;
	}

	@RequestMapping("/GreetingUser")
	public GreetingMessage greetingUser() {
		GreetingMessage msg = new GreetingMessage();
		msg.setGreeting("******Say Hello*****");
		msg.setName("Jackson");
		return msg;
	}

	/**
	 *
	 *
	 * @author Don Doc
	 */
	@RequestMapping("/greeatingUserName")
	public GreetingMessage greetingUser(@RequestParam(value = "name", defaultValue = "jim") String name,
			@RequestParam(value = "msg") final String message) {
		GreetingMessage msg = new GreetingMessage();
		msg.setGreeting(message);
		msg.setName(name);
		return msg;
	}

	@RequestMapping("/GreetingVOUserName")
	public GreetingMessage greetingUser(GreetingMessage msg) {
		msg.setName(msg.getName().toUpperCase());
		msg.setGreeting(msg.getGreeting().toUpperCase());
		return msg;
	}

	@RequestMapping("/searchUsers")
	public List<Users> searchUsers(String searchString) {
		System.out.println(searchString);
		List<Users> l = new ArrayList<Users>();

		Users u1 = new Users();
		u1.setAge(21);
		u1.setName("kavi");

		Users u2 = new Users();
		u2.setAge(25);
		u2.setName("pavi");

		l.add(u1);
		l.add(u2);
		return l;
	}

	@RequestMapping(value = "/searchUsersMap")
	public Map<String, Users> searchUsersMap(String searchString) {
		Map<String, Users> l = new HashMap<String, Users>();
		Users u1 = new Users();
		u1.setAge(21);
		u1.setName("kavi");

		Users u2 = new Users();
		u2.setAge(25);
		u2.setName("pavi");

		l.put(u1.getName(), u1);
		l.put(u2.getName(), u2);
		return l;
	}

	@PostMapping("/searchUsersByPost")
	public List<Users> searchusersByPost(@RequestBody String searchString) {
		System.out.println("Post Request Send---->" + searchString);

		List<Users> l = new ArrayList<Users>();
		Users u1 = new Users();
		u1.setAge(21);
		u1.setName("kavi");

		Users u2 = new Users();
		u2.setAge(25);
		u2.setName("pavi");

		l.add(u1);
		l.add(u2);
		return l;
	}

	@RequestMapping(value = "/creatingGreeting", method = RequestMethod.POST)
	public GreetingMessage createGreeting(@RequestBody GreetingMessage msg) {
		System.out.println("message :" + msg.getGreeting());
		System.out.println("Name :" + msg.getName());
		msg.setGreeting("Happy New Year...");
		return msg;
	}

	@RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
	public ResponseObject addCustomer(@RequestBody CustomerDTO customer) {
		ResponseObject responseObject = new ResponseObject();
		logger.debug("Add Customer is triggered");
		responseObject = customerService.insert(customer);
		if (responseObject.getSuccessMessage() != null) {
			System.out.println(responseObject.getCustomer());
			System.out.println(responseObject.getSuccessMessage());
		} else if (responseObject.getSuccessMessage() == null) {
			System.out.println(responseObject.getFailureMessage());
		}
		return responseObject;
	}

//	@PostMapping("/login2")
//    public ResponseEntity<?> loginUser(@RequestBody Registration user) {
//        // Handle user login and validation
////		Registration existingUser = registration.getUserUsername(user.getUserName());
//		Registration existingUser = registration.getUserUsername(user.getUserName());
//        
//        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
//            // Successful login
//            return ResponseEntity.ok("Login successful");
//        } else {
//            // Authentication failed
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
//        }
//    }
//	@PostMapping("/login3")
//    public ResponseEntity<?> loginUser(@RequestBody Registration user) {
//        // Handle user login and validation
//		Registration existingUser = registration.getUserUsername(user.getUserName());
//        
//        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
//            // Successful login
//            return ResponseEntity.ok("Login successful");
//        } else {
//            // Authentication failed
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
//        }
//    }
//	@PostMapping("/login")
//	public ResponseEntity<ObjectResponse<Registration>> login(@RequestBody LoginDTO loginRequest) {
//
//		ObjectResponse<Registration> responseObject = registration.authenticateDoctorLogin(
////	            loginRequest.getUsername(),
////	            loginRequest.getPassword()
//				loginRequest.getPassword(), loginRequest.getUserName());
//		System.out.println(responseObject.getMessage());
//		System.out.println(responseObject.getData());
//		HttpStatus status = responseObject.isSuccess() ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
//		return new ResponseEntity<>(responseObject, status);
//	}

//	 @PostMapping(path = "/login5")
//	    public ResponseEntity<?> loginEmployee(@RequestBody LoginDTO loginDTO)
//	    {
//	        LoginResponse loginResponse = employeeService.loginEmployee(loginDTO);
//	        return ResponseEntity.ok(loginResponse);
//	    }
	
	@GetMapping("/excel")
	public ResponseEntity<Resource> downlodeExcel() throws IOException{
		String filename="category.xlsx";
		ByteArrayInputStream actualData=customerService.dataToExcel();
		InputStreamResource file=new InputStreamResource(actualData);
		
		
		   ResponseEntity< Resource> body=   ResponseEntity.ok()
		.header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename="+filename)
		.contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
		.body(file);
		return body;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public RegisterResponse registration(@RequestBody Registration register) {
		RegisterResponse responseObject = new RegisterResponse();
		logger.debug("Add Registration is triggered");
		responseObject = registration.insert(register);
		if (responseObject.getSuccessMessage() != null) {
			System.out.println(responseObject.getRegistered());
			System.out.println(responseObject.getSuccessMessage());
		} else if (responseObject.getSuccessMessage() == null) {
			System.out.println(responseObject.getFailureMessage());
		}
		return responseObject;
	}

	@Autowired
	private UserService service;

	@PostMapping(path = "/save")
	public String saveEmployee(@RequestBody RegistrationDTO employeeDTO) {
		String id = service.addEmployee(employeeDTO);
		return id;
	}

	@PostMapping(path = "/loginUser")
	public ResponseEntity<?> loginEmployee(@RequestBody LoginDTO loginDTO) {
		LoginResponse loginResponse = service.loginUser(loginDTO);
		System.out.println(loginResponse);
		return ResponseEntity.ok(loginResponse);
	}
//	@RequestMapping(value="login",method=RequestMethod.POST)
//	@PostMapping("/login")
//	@RequestMapping(value = "login", method = RequestMethod.POST)
//	public ResponseEntity<?> login(@RequestBody LoginDTO loginRequest) {
//
//	RegisterResponse responseObject = registration.authenticateDoctorLogin(loginRequest.getUserName(),
//				loginRequest.getPassword());
////		System.out.println(responseObject.getLog());
////		System.out.println(responseObject.getMessage());
//	System.out.println(responseObject.getSuccessMessage());
//	System.out.println(responseObject.getLogin());
//		HttpStatus status = responseObject.isSuccess() ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
//		return new ResponseEntity<>(responseObject, status);
//	}

	// Other methods and logic

	@RequestMapping(value = "/updateCustomer", method = RequestMethod.POST)
	public ResponseObject updateCustomer(@RequestBody CustomerDTO customer) {
		ResponseObject responseObject = new ResponseObject();
		logger.debug("Update Customer is triggered");
		responseObject = customerService.update(customer);
		if (responseObject.getSuccessMessage() != null) {
			System.out.println(responseObject.getCustomer());
			System.out.println(responseObject.getSuccessMessage());
		} else if (responseObject.getSuccessMessage() == null) {
			System.out.println(responseObject.getFailureMessage());
		}
		return responseObject;
	}

	@RequestMapping(value = "/fetchCustomerId", method = RequestMethod.GET)
	public ResponseObject fetchById(@RequestParam int customerId) {

		logger.debug("fetched Id is triggered");
		ResponseObject responseObject = new ResponseObject();
		responseObject = customerService.fetchCustomer(customerId);
		if (responseObject.getSuccessMessage() != null) {
			System.out.println(responseObject.getCustomer());
			System.out.println(responseObject.getSuccessMessage());
		} else if (responseObject.getSuccessMessage() == null) {
			System.out.println(responseObject.getFailureMessage());
		}

		return responseObject;

	}

	@DeleteMapping("/delete/{id}")
	String delete(@PathVariable int id) {
		if (!repository.existsById(id)) {
			throw new CustomerNotFoundException(id);
		}
		repository.deleteById(id);
		return "Customer id" + id + "has been deleted successfully...";
	}

	@RequestMapping(value = "/fetchAllCustomer", method = RequestMethod.GET)
	public ResponseObject fetchAllCustomer() throws CustomerInternalServerException {
		logger.debug("Fetch All Customer Is Triggered");
//			ResponseObject object = cbo1.fetchCustomers();
		ResponseObject responseObject = new ResponseObject();
		responseObject = customerService.fetchCustomers();

//		  ResponseObject responseObject = new ResponseObject();
//			responseObject = customerService.update(customer);
		if (responseObject.getListCustomer() != null) {
			System.out.println(responseObject.getSuccessMessage());
		} else if (responseObject.getListCustomer() == null) {
			System.out.println(responseObject.getFailureMessage());
		}
		return responseObject;
	}

	@RequestMapping(value = "/createPolicy", method = RequestMethod.POST)
	public ResponseObject addPolicy(@RequestBody Policy policy) {
		logger.debug("Create Policy is Triggered");

//		return policyService.insert(policy);
		ResponseObject responseObject = policyService.insert(policy);

		if (responseObject.getSuccessMessage() != null) {
			System.out.println(responseObject.getPolicy());
			System.out.println(responseObject.getSuccessMessage());
		} else if (responseObject.getSuccessMessage() == null) {
			System.out.println(responseObject.getFailureMessage());
		}
		return responseObject;
	}

	@RequestMapping(value = "/updatePolicy", method = RequestMethod.POST)
	public ResponseObject updatePolicy(@RequestBody Policy msg) {
		logger.debug("Updated Policy triggered");
		ResponseObject responseObject = policyService.update(msg);
		if (responseObject.getSuccessMessage() != null) {
			System.out.println(responseObject.getPolicy());
			System.out.println(responseObject.getSuccessMessage());
		} else if (responseObject.getSuccessMessage() == null) {
			System.out.println(responseObject.getFailureMessage());
		}
		return responseObject;
	}

	@RequestMapping(value = "/fetchPolicyById", method = RequestMethod.GET)
	public ResponseObject fetchPolicy(@RequestParam int id) throws DuplicateValueException {
		logger.debug("Fetch Policy By Id is triggered");
		System.out.println("Policy Id :" + id);

		ResponseObject obj = policyService.fetchPolicy(id);

		if (obj.getPolicy() != null) {
			System.out.println(obj.getPolicy());
			System.out.println(obj.getSuccessMessage());
		} else if (obj.getPolicy() == null) {
			System.out.println(obj.getFailureMessage());
		}
		return obj;

	}

	@RequestMapping(value = "/fetchAllPolicy", method = RequestMethod.GET)
	public ResponseObject fetchAllPolicy() {
		logger.debug("Fetch All Policy is triggered");
		ResponseObject object = policyService.fetchAllPolicy();
		if (object.getListPolicy() != null) {
			System.out.println(object.getListPolicy());
			System.out.println(object.getSuccessMessage());
		} else if (object.getListPolicy() == null) {
			System.out.println(object.getFailureMessage());
		}
		return object;

	}

	@RequestMapping(value = "/CutomerById", method = RequestMethod.GET)
	public ResponseObject findCustomerById(int customerId1) {
		logger.debug("Customer By Id is triggered");
//		final List<Customer> list = customerService.findCustomerById(customerId1);
		ResponseObject response = customerService.findCustomerById(customerId1);

		if (response.getListCustomer() != null) {
			System.out.println(response.getListCustomer());
			System.out.println(response.getSuccessMessage());
		} else {
			System.out.println(response.getFailureMessage());
		}
		return response;

	}

	@RequestMapping(value = "/CustomerByName", method = RequestMethod.GET)
	public ResponseObject findByCustomerName(final String string) {
		logger.debug("Find By Customer Name is triggered");
//		List<Customer> list = customerService.findByCustomerName(string);
		ResponseObject response = customerService.findByCustomerName(string);
		if (response.getListCustomer() != null) {
			System.out.println(response.getListCustomer());
			System.out.println(response.getSuccessMessage());

		} else if (response.getSuccessMessage() == null) {
			System.out.println(response.getFailureMessage());
		}

		return response;

//		return list;

	}

	@RequestMapping(value = "/findByCustomerName", method = RequestMethod.GET)
	public ResponseObject findByCustomerNameCustomized(String customerName) {
		logger.info("Find By Customer Name");
		ResponseObject response = customerService.findByCustomerNameCustomized(customerName);
		if (response.getSuccessMessage() != null) {
			System.out.println(response.getSuccessMessage());
			System.out.println(response.getCustomized());
		} else if (response.getSuccessMessage() == null) {
			System.out.println(response.getFailureMessage());
		}

		return response;

	}

	@RequestMapping(value = "/AllOrderByName", method = RequestMethod.GET)
	public ResponseObject findAllOrderByName() {
		// TODO Auto-generated method stub
		logger.debug("All Order By Name Triggered");

		ResponseObject obj = customerService.findAllOrderByName();
//		List<Customer> list =obj();
		if (obj.getSuccessMessage() != null) {
			System.out.println(obj.getSuccessMessage());
		} else if (obj.getSuccessMessage() == null) {
			System.out.println(obj.getFailureMessage());
		}

		return obj;

	}

	@RequestMapping(value = "/CustomerWithPolicyRight", method = RequestMethod.GET)
	public ResponseObject findCustomerWithPolicyRight() {
		// TODO Auto-generated method stub
		logger.debug("Customer With Policy Right outer Join Triggered");

		ResponseObject obj = customerService.findCustomerWithPolicyRight();
//		List<Customer> list =obj();
		if (obj.getSuccessMessage() != null) {
			System.out.println(obj.getSuccessMessage());
		} else if (obj.getSuccessMessage() == null) {
			System.out.println(obj.getFailureMessage());
		}

		return obj;

	}

	/**
	 *
	 *
	 * @author Don Foe
	 */
	@RequestMapping(value = "/CustomerWithPolicyLeft", method = RequestMethod.GET)
	public ResponseObject findCustomerWithPolicyLeft() {
		// TODO Auto-generated method stub
		logger.debug("Customer With Policy Left Outer Join Triggered");

		ResponseObject obj = customerService.findCustomerWithPolicyLeft();
		if (obj.getSuccessMessage() != null) {
			System.out.println(obj.getSuccessMessage());
		} else if (obj.getSuccessMessage() == null) {
			System.out.println(obj.getFailureMessage());
		}

		return obj;

	}

	@RequestMapping(value = "/CustomerPolicyCustomized", method = RequestMethod.GET)
	public ResponseObject findByCustomerPolicyCustomized() {
		// TODO Auto-generated method stub
		logger.debug("Customer Policy Customized Triggered");
		ResponseObject obj = customerService.findByCustomerPolicyCustomized();
		if (obj.getSuccessMessage() != null) {
			System.out.println(obj.getSuccessMessage());
		} else if (obj.getSuccessMessage() == null) {
			System.out.println(obj.getFailureMessage());
		}
		return obj;

	}

	@RequestMapping(value = "/CustomerWithPolicy", method = RequestMethod.GET)
	public ResponseObject findCustomerWithPolicy() {
		// TODO Auto-generated method stub
		logger.debug("Customer With Policy Inner Join Triggered");

		ResponseObject obj = customerService.findCustomerWithPolicy();
		if (obj.getSuccessMessage() != null) {
			System.out.println(obj.getSuccessMessage());
		} else if (obj.getSuccessMessage() == null) {
			System.out.println(obj.getFailureMessage());
		}

		return obj;

	}

	@RequestMapping(value = "/CustomerAvarageAge", method = RequestMethod.GET)
	public Response findAverageAge() {
		logger.debug("Customer Avarage Age Triggered");
		Response response = customerService.findCustomerAverageAge();
		if (response.getSuccessMessage() != null) {
			System.out.println(response.getSuccessMessage());
			System.out.println(response.getDouble1());
		} else {
			System.out.println(response.getFailureMessage());
		}
		return response;

	}

	@RequestMapping(value = "/CustomersCount", method = RequestMethod.GET)
	public Response findCustomersCount() {
		logger.debug("Customer Count Triggered");

		Response response = customerService.findCustomersCount();
		if (response.getSuccessMessage() != null) {
			System.out.println(response.getSuccessMessage());
			System.out.println(response.getInteger());
		} else {
			System.out.println(response.getFailureMessage());
		}
		return response;

	}

	@RequestMapping(value = "/MaximumCustomerSalary", method = RequestMethod.GET)
	public Response findMaximumCustomerSalary() {
		logger.debug("Minimum Customer Salary Triggered");
		Response response = customerService.findMaximumCustomerSalary();
		if (response.getSuccessMessage() != null) {
			System.out.println(response.getSuccessMessage());
			System.out.println(response.getDouble1());
		} else {
			System.out.println(response.getFailureMessage());
		}
		return response;

	}

	@RequestMapping(value = "/MinimumCustomerSalary", method = RequestMethod.GET)
	public Response findMinimumCustomerSalary() {
		logger.debug("Minimum Customer Salary Triggered");

		Response response = customerService.findMinimumCustomerSalary();
		if (response.getSuccessMessage() != null) {
			System.out.println(response.getSuccessMessage());
			System.out.println(response.getDouble1());
		} else {
			System.out.println(response.getFailureMessage());
		}
		return response;

	}

	@RequestMapping(value = "/SumCustomerSalary", method = RequestMethod.GET)
	public Response findSumCustomerSalary() {
		logger.debug("Totam Customer Salary Triggered");

		Response response = customerService.findSumCustomerSalary();
		if (response.getSuccessMessage() != null) {
			System.out.println(response.getSuccessMessage());
			System.out.println(response.getDouble1());
		} else {
			System.out.println(response.getFailureMessage());
		}
		return response;

	}

	@RequestMapping(value = "/getcustomerAverageSalaryByCity", method = RequestMethod.GET)
	public List<String> getAverageSalaryByCity() {
		logger.debug("Average Salary By City Triggered");

		return customerService.getAverageSalaryByCity();
	}

	@RequestMapping(value = "/policyCount", method = RequestMethod.GET)
	public Integer findPolicyCount() {
		logger.debug("Total Policy Count Triggered");

		return policyService.findPolicyCount();
	}

	@RequestMapping(value = "/MaximumPolicyAmount", method = RequestMethod.GET)
	public Double findMaximumPolicyAmount() {
		logger.debug("Maximum Policy Amount triggered");

		return policyService.findMaximumPolicyAmount();
	}

	@RequestMapping(value = "/MinimumPolicyAmount", method = RequestMethod.GET)
	public Double findMinimumPolicyAmount() {
		logger.debug("Minimum Policy Amount triggered");

		return policyService.findMinimumPolicyAmount();
	}

	@RequestMapping(value = "/SumPolicyAmount", method = RequestMethod.GET)
	public Double findSumPolicyAmount() {
		logger.debug("total Policy Amount triggered");

		return policyService.findSumPolicyAmount();
	}

	@RequestMapping(value = "/PolicyAverageAmount", method = RequestMethod.GET)
	public Double findPolicyAverageAmount() {
		logger.debug(" Policy Average Amount triggered");

		return policyService.findPolicyAverageAmount();
	}

	@RequestMapping(value = "/getGroupedByPolicyType", method = RequestMethod.GET)
	public List<String> getGroupedByPolicyType() {
		logger.debug("GroupedBy Policy Type Triggered");

		return policyService.getGroupedByPolicyType();
	}
}
