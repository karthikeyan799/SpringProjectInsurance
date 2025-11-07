package com.insurance.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.insurance.bo.CustomerBO;
import com.insurance.bo.PolicyBO;
import com.insurance.dao.CustomerCustomized;
import com.insurance.dao.CustomerPolicyCustomized;
import com.insurance.dto.CustomerDTO;
import com.insurance.entity.Customer;
import com.insurance.entity.Policy;
import com.insurance.exception.CustomerInternalServerException;
import com.insurance.exception.CustomerNotFoundException;
import com.insurance.exception.DuplicateValueException;
import com.insurance.response.Response;
import com.insurance.response.ResponseObject;
//import com.mysql.cj.result.Row;

/**
 *
 *
 * @author Don Doc
 */
@Component
public class CustomerService {

	public CustomerService() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 *
	 *
	 * @author Don Doc
	 */
	Logger logger = Logger.getLogger(CustomerService.class);

	/**
	 *
	 *
	 * @author Don Doc
	 */
	@Autowired
	private CustomerBO customerBo;

	@Autowired
	private PolicyBO policyBo;
	/**
	 *
	 *
	 * @author Don Doc
	 */
	private Customer customer = new Customer();
	@Autowired

	/**
	 *
	 *
	 * @author Don Doc
	 */
	static ResponseObject object = new ResponseObject();

	/**
	 *
	 *
	 * @author Don Doc
	 */
	public ResponseObject insert(final CustomerDTO dto) {
		ResponseObject responseObject = new ResponseObject();
		Customer customer1 = new Customer();
		try {

			customer1.setCustomerName(dto.getCustomerName());
			customer1.setCustomerAge(dto.getCustomerAge());
			customer1.seteMail(dto.geteMail());
			customer1.setGender(dto.getGender());
//			customer1.setCreaditLimit(dto.getCreaditLimit());
			customer1.setPhoneNumber(dto.getPhoneNumber());
			customer1.setSalary(dto.getSalary());
//			customer1.setState(dto.getState());
			customer1.setCity(dto.getCity());

			Customer customer = customerBo.insert(customer1);

			logger.info(customer);
			logger.info(customer1.getCustomerId() + " Added Successfully...");

			responseObject.setCustomer(customer);
			responseObject.setSuccessMessage(customer1.getCustomerId() + " is added successfully..");

		} catch (CustomerInternalServerException r) {
			logger.error("Error When Adding... " + r);
			responseObject.setFailureMessage(r.getMessage());

		} catch (DataIntegrityViolationException ex) {
			logger.error("Duplicate Violation " + ex);
			responseObject.setFailureMessage("Duplicate Violation... Please check email and phoneNumber");

		}
		return responseObject;
	}

	/**
	 *
	 *
	 * @author Don Doc
	 */
	public ResponseObject update(final CustomerDTO dto) {
		ResponseObject responseObject = new ResponseObject();
		Customer customer1 = new Customer();

		try {
			customer1.setCustomerId(dto.getCustomerId());
			customer1.setCustomerName(dto.getCustomerName());
			customer1.setCustomerAge(dto.getCustomerAge());
			customer1.seteMail(dto.geteMail());
			customer1.setGender(dto.getGender());
//			customer1.setCreaditLimit(dto.getCreaditLimit());
			customer1.setPhoneNumber(dto.getPhoneNumber());
			customer1.setSalary(dto.getSalary());
//			customer1.setState(dto.getState());
			customer1.setCity(dto.getCity());
			Customer customer = customerBo.insert(customer1);
			logger.info(customer);
			logger.info(customer1.getCustomerId() + " Update Successfully...");
			responseObject.setCustomer(customer);
			responseObject.setSuccessMessage(customer1.getCustomerId() + " is Update successfully..");

		} catch (CustomerInternalServerException r) {
			logger.error(customer1.getCustomerId() + "  Error When Updating... " + r);

			responseObject.setFailureMessage(r.getMessage());

//		} catch (DataIntegrityViolationException ex) {

		} catch (DuplicateValueException ex) {
			logger.error("Duplicate Violation " + ex);
			responseObject.setFailureMessage("Duplicate Violation... Please check email and phoneNumber");

		}
		return responseObject;
	}

	/**
	 *
	 *
	 * @author Don Doc
	 */
	public ResponseObject fetchCustomer(final int customerId) {
//		logger.debug("Fetch Customer Id Is Triggered");
//		customer = customerBo.findCustomer(customerId);
		ResponseObject responseObject = new ResponseObject();
//		= customerBo.findCustomer(customerId);
		CustomerDTO customerDto = new CustomerDTO();
//		ArrayList<Customer> dtoList = new ArrayList<Customer>();
		try {
			customer = customerBo.findCustomer(customerId);
//			if (customer.getCustomerId()!= null) {
//				Customer c1 = obj.getCustomer();
			customerDto.setCustomerId(customer.getCustomerId());
			customerDto.setCustomerName(customer.getCustomerName());
			customerDto.setCreatedAt(customer.getCreatedAt());
			customerDto.setCity(customer.getCity());
//			customerDto.setCreaditLimit(customer.getCreaditLimit());
//			customerDto.setCreatedAt(customer.getCreatedAt());
			customerDto.setCustomerAge(customer.getCustomerAge());
			customerDto.seteMail(customer.geteMail());
			customerDto.setGender(customer.getGender());
//			customerDto.setPhoneNumber(customer.getPhoneNumber());
			customerDto.setSalary(customer.getSalary());
//			customerDto.setState(customer.getState());
			customerDto.setUpdatedAt(customer.getUpdatedAt());
//			dtoList.add(customerDto);
//			dtoList.addAll(customerDto);
//			dtoList.add(customer);
//			responseObject.setCustomer(customer);
//			responseObject.setListCutsomer(dtoList);
			responseObject.setCustomer(customer);
			responseObject.setSuccessMessage("Customer Id " + customerId + " is Fected Successfully");
//			logger.info(dtoList);
			logger.info(customerDto);
			logger.info(customerId + " is fetched  successfully...");

		} catch (CustomerNotFoundException r) {

			logger.error("service Customer ID is Failure .." + r);
//			System.out.println(customer.getCustomerId() + " Customer ID is Failure .." + r);
			responseObject.setFailureMessage(customerId + " Customer ID is not Found");

		}
		return responseObject;
	}

	public ByteArrayInputStream dataToExcel() throws IOException {
		Workbook workBook = new XSSFWorkbook();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Sheet sheet = workBook.createSheet("sheet1");

		Row row = sheet.createRow(0);
		List<Customer> list1 = customerBo.findCustomers();
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("NAME");
		row.createCell(2).setCellValue("AGE");
		row.createCell(3).setCellValue("GENDER");
		row.createCell(4).setCellValue("CITY");
		row.createCell(5).setCellValue("E-MAIL");
		row.createCell(6).setCellValue("PHONE-NUMBER");
		row.createCell(7).setCellValue("SALARY");
		int dataRowIndex = 1;
		for (Customer customer : list1) {
			Row dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(customer.getCustomerId());
			dataRow.createCell(1).setCellValue(customer.getCustomerName());
			dataRow.createCell(2).setCellValue(customer.getCustomerAge());
			dataRow.createCell(3).setCellValue(customer.getGender());
			dataRow.createCell(4).setCellValue(customer.getCity());
			dataRow.createCell(5).setCellValue(customer.geteMail());
			dataRow.createCell(6).setCellValue(customer.getPhoneNumber());
			dataRow.createCell(7).setCellValue(customer.getSalary());
			
			dataRowIndex++;
		}
		workBook.write(out);
		workBook.close();

		return new ByteArrayInputStream(out.toByteArray());
	}

	public void generationExcel(String excel) throws IOException {
		List<Customer> list = customerBo.findCustomers();
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("customer");
		HSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("NAME");
		row.createCell(2).setCellValue("AGE");
		row.createCell(3).setCellValue("GENDER");

		int dataRowInt = 1;
		for (Customer cus : list) {
			HSSFRow dataRow = sheet.createRow(dataRowInt);
			dataRow.createCell(0).setCellValue(cus.getCustomerId());
			dataRow.createCell(1).setCellValue(cus.getCustomerName());
			dataRow.createCell(2).setCellValue(cus.getCustomerAge());
			dataRow.createCell(3).setCellValue(cus.getGender());
			dataRowInt++;

		}
		FileOutputStream output = new FileOutputStream(excel);
		workbook.write(output);
		output.flush();
		output.close();

	}

//	public Workbook generatExcel(String excelFile) throws Exception {
//		List<Customer> list1 = customerBo.findCustomers();
//		FileInputStream inputStream = new FileInputStream(new File(excelFile));
//		Workbook wb = WorkbookFactory.create(inputStream);
//		Sheet sh =wb.createSheet("sheet1");
//		FileOutputStream fop;
//		Row row;
//		Cell cell;
//
//		row = sh.createRow(1);
//		row.createCell(0).setCellValue("ID");
//		cell=row.createCell(0);
//		cell.setCellValue("karthi");
//		System.out.println(cell.getStringCellValue());
////		int dataRowIndex = 1;
////		for (Customer customer : list1) {
////			Row dataRow = sh.createRow(dataRowIndex);
////			dataRow.createCell(0).setCellValue(customer.getCustomerId());
////			dataRow.createCell(1).setCellValue(customer.getCustomerName());
////			dataRow.createCell(2).setCellValue(customer.getCustomerAge());
////			dataRow.createCell(3).setCellValue(customer.getGender());
////			dataRowIndex++;
////		}
//		FileOutputStream output = new FileOutputStream(excelFile);
//		wb.write(output);
//		output.flush();
//		output.close();
//		return wb;
//	}

//	public XSSFWorkbook generatExcel(String excelFile) throws IOException {
//		List<Customer> list1 = customerBo.findCustomers();
////		FileInputStream inputStream= new FileInputStream(new File(excelFile));
//		XSSFWorkbook workBook = new XSSFWorkbook(excelFile);
////		String excelFile = "E:\\Logs\\File.xlsx";
////		HSSFWorkbook workBook = new HSSFWorkbook();
//		XSSFSheet sheet = workBook.createSheet("Customer Info");
//		XSSFRow row = sheet.createRow(0);
//
//		row.createCell(0).setCellValue("ID");
//		row.createCell(1).setCellValue("NAME");
//		row.createCell(2).setCellValue("AGE");
//		row.createCell(3).setCellValue("GENDER");
//		row.createCell(4).setCellValue("CITY");
//		row.createCell(5).setCellValue("E-MAIL");
//		row.createCell(6).setCellValue("PHONE-NUMBER");
//		row.createCell(6).setCellValue("SALARY");
//
//		int dataRowIndex = 1;
//		for (Customer customer : list1) {
//			XSSFRow dataRow = sheet.createRow(dataRowIndex);
//			dataRow.createCell(0).setCellValue(customer.getCustomerId());
//			dataRow.createCell(1).setCellValue(customer.getCustomerName());
//			dataRow.createCell(2).setCellValue(customer.getCustomerAge());
//			dataRow.createCell(3).setCellValue(customer.getGender());
//			dataRow.createCell(4).setCellValue(customer.getCity());
//			dataRow.createCell(5).setCellValue(customer.geteMail());
//			dataRow.createCell(6).setCellValue(customer.getPhoneNumber());
//			dataRow.createCell(7).setCellValue(customer.getSalary());
//			dataRowIndex++;
//		}
////		FileInputStream inputStream= new FileInputStream(sheet);
//		FileOutputStream output = new FileOutputStream(excelFile);
//		workBook.write(output);
////		output.flush();
//		output.close();
//		workBook.close();
//		return workBook;
////		return list1;
//		
//	}

	/**
	 *
	 *
	 * @author Don Doc
	 */
	public ResponseObject fetchCustomers() {

		ResponseObject responseObject = new ResponseObject();

		List<Customer> list1 = new ArrayList<Customer>();
		try {
			List<Customer> list = customerBo.findCustomers();

//		if (list!= null) {
			if (list != null) {
				for (Customer customer : list) {
//				dto = new CustomerDTO();
					CustomerDTO dto = new CustomerDTO();
					dto.setCustomerId(customer.getCustomerId());
					dto.setCustomerName(customer.getCustomerName());
					dto.setCustomerAge(customer.getCustomerAge());
//					dto.setState(customer.getState());
					dto.setCity(customer.getCity());
					dto.setGender(customer.getGender());
					dto.setPhoneNumber(customer.getPhoneNumber());
//					dto.setCreaditLimit(customer.getCreaditLimit());
					dto.setCreatedAt(customer.getCreatedAt());
					dto.setUpdatedAt(customer.getUpdatedAt());
					dto.seteMail(customer.geteMail());
					dto.setSalary(customer.getSalary());

					list1.add(customer);
				}
			}
			responseObject.setSuccessMessage("Fetch All Successfully");
			logger.info(list1);
			responseObject.setListCustomer(list1);
//			responseObject.setListCutsomer(list1);
//			} else if (list == null) {
//				responseObject.setFailureMessage("Fetch ");

		} catch (CustomerInternalServerException e) {
			logger.error("FetchAll Records doed not exist in the tabel \n", e);

			responseObject.setFailureMessage("Retreive customer Records does not exist in the list ");
//			responseObject.
//			System.out.println("Retreive customer  does not exist in the list " + e);
		}
//			return customerBo.findCustomers();
		return responseObject;

	}

	/**
	 *
	 *
	 * @author Don Doc
	 */
	public ResponseObject findCustomerById(final int customerId) {
		ResponseObject responseObject = new ResponseObject();

		try {

			final List<Customer> list = customerBo.findCustomerById(customerId);

//			List<Customer> list = obj.getListCutsomer();
			final CustomerDTO dto = new CustomerDTO();
			final List<Customer> list1 = new ArrayList<Customer>();
			if (list != null) {

				for (final Customer customer : list) {

					dto.setCustomerId(customer.getCustomerId());
					dto.setCustomerName(customer.getCustomerName());
					dto.setCustomerAge(customer.getCustomerAge());
//					dto.setState(customer.getState());
					dto.setCity(customer.getCity());
					dto.setGender(customer.getGender());
					dto.setPhoneNumber(customer.getPhoneNumber());
//					dto.setCreaditLimit(customer.getCreaditLimit());
					dto.setCreatedAt(customer.getCreatedAt());
					dto.setUpdatedAt(customer.getUpdatedAt());
					dto.seteMail(customer.geteMail());
					dto.setSalary(customer.getSalary());
					customer.getPolicy();
//					list1.add(dto);
					list1.add(customer);

				}
				responseObject.setListCustomer(list1);
				responseObject.setSuccessMessage(customerId + " Customer ID is fetched successfully..");
//				if (customerId != 0) {
//				logger.info(customerId + " Customer ID is fetched successfully..");
//				object.setSuccessMessage(customerId + " Customer ID is fetched successfully..");
//				object.setListCutsomer(cus);
////				object.setListCustomer(cus);
////				object.setPolicy(null);
			}

		} catch (CustomerNotFoundException r) {
			logger.error("CustomerId is does not match in the table.\n" + r);
			responseObject.setFailureMessage("fetching customer id is not exist in the table " + r);
			System.out.println("fetching customer id is not exist in the table " + r);
//		}catch(CustomerOKException r) {
//			object.setFailureMessage("custome OK Exception for byid");
		}
//		return customerBo.findCustomerById(customerId);
		return responseObject;
	}

	/**
	 *
	 *
	 * @author Don Doc
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void transactionPropagation(Customer customer, Policy policy) {
		System.out.println("Before Customer");

//			logger.info("Customer.");
		customerBo.insert(customer);
		System.out.println("After Customer");
		System.out.println("Before Policy");
//			logger.info("Policy.");
		policyBo.insert(policy);
		System.out.println("After Customer");
	}

	/**
	 *
	 *
	 * @author Don Doc
	 */
	public ResponseObject findByCustomerName(final String string) {

		logger.debug("Customer Name is Triggered");
		ResponseObject responseObject = new ResponseObject();
		try {
			List<Customer> list = customerBo.findByCustomerName(string);
			final Customer dto = new Customer();
			final List<Customer> list1 = new ArrayList<Customer>();
//			if (list != null) {
			for (final Customer customer : list) {
				dto.setCustomerId(customer.getCustomerId());
				dto.setCustomerName(customer.getCustomerName());
				dto.setCustomerAge(customer.getCustomerAge());
//				dto.setState(customer.getState());
				dto.setCity(customer.getCity());
				dto.setGender(customer.getGender());
				dto.setPhoneNumber(customer.getPhoneNumber());
//				dto.setCreaditLimit(customer.getCreaditLimit());
				dto.setCreatedAt(customer.getCreatedAt());
				dto.setUpdatedAt(customer.getUpdatedAt());
				dto.seteMail(customer.geteMail());
				dto.setSalary(customer.getSalary());
				customer.getPolicy();
				list1.add(customer);
			}
//			}

			logger.info("Customer name is Successfull");
			logger.info(list1);
			System.out.println(string + "Customer name is Successfully");
			responseObject.setSuccessMessage(string + " Customer name is Successfull ");
			responseObject.setListCustomer(list1);
//			}
		} catch (CustomerInternalServerException e) {
			responseObject.setFailureMessage(string + " Customer name is failure");
			logger.error("Customer name is failure " + e);
		}
		return responseObject;

	}

	/**
	 *
	 *
	 * @author Don Doc
	 */
	public ResponseObject findByCustomerNameCustomized(final String customerame) {
		logger.info("CustomerName Customized is Triggered");
		ResponseObject responseObject = new ResponseObject();

		try {
			List<CustomerCustomized> list = customerBo.findByCustomerNameCustomized(customerame);

			if (list != null) {
				for (CustomerCustomized customized : list) {
					customized.getState();
//					customized.getCustomerId();
					customized.getCity();
					customized.getCustomerName();
					customized.getEmail();
				}
			}

			logger.info(list);
			logger.info("Customer name is Successfull");
			responseObject.setSuccessMessage("Customer name is Successfull");
			responseObject.setCustomized(list);

		} catch (CustomerInternalServerException e) {
			logger.error("Customer Name is failure" + e);
			responseObject.setFailureMessage(e.getMessage());
		}
		return responseObject;
	}

	/**
	 *
	 *
	 * @author Don Doc
	 */
	public ResponseObject findAllOrderByName() {
		logger.debug("All Order By Name is triggered");

		ResponseObject responseObject = new ResponseObject();
		try {
			List<Customer> list = customerBo.findAllOrderByName();
			final CustomerDTO dto = new CustomerDTO();
			final List<Customer> list1 = new ArrayList<Customer>();
			if (list != null) {
				for (final Customer customer : list) {
					dto.setCustomerId(customer.getCustomerId());
					dto.setCustomerName(customer.getCustomerName());
					dto.setCustomerAge(customer.getCustomerAge());
//					dto.setState(customer.getState());
					dto.setCity(customer.getCity());
					dto.setGender(customer.getGender());
					dto.setPhoneNumber(customer.getPhoneNumber());
//					dto.setCreaditLimit(customer.getCreaditLimit());
					dto.setCreatedAt(customer.getCreatedAt());
					dto.setUpdatedAt(customer.getUpdatedAt());
					dto.seteMail(customer.geteMail());
					dto.setSalary(customer.getSalary());
					customer.getPolicy();
					list1.add(customer);
				}
			}
			logger.info(list1);
			logger.info("All Order By Name is Successfull");
			responseObject.setListCustomer(list1);
			responseObject.setSuccessMessage("All Order By Name is Successfull");
		} catch (CustomerNotFoundException c) {
			logger.error("All Order By Name is Failure");

			responseObject.setFailureMessage("All Order By Name is Failure");
		}
		return responseObject;
	}

	/**
	 *
	 *
	 * @author Don Doc
	 */
	public ResponseObject findCustomerWithPolicyRight() {
		ResponseObject responseObject = new ResponseObject();
		logger.debug("Customer With Policy Right Outer Join is Troiggered");

		try {
			List<Customer> list = customerBo.findCustomerWithPolicyRight();
			final CustomerDTO dto = new CustomerDTO();
			final List<Customer> list1 = new ArrayList<Customer>();
			if (list != null) {
				for (final Customer customer : list) {
					dto.setCustomerId(customer.getCustomerId());
					dto.setCustomerName(customer.getCustomerName());
					dto.setCustomerAge(customer.getCustomerAge());
//					dto.setState(customer.getState());
					dto.setCity(customer.getCity());
					dto.setGender(customer.getGender());
					dto.setPhoneNumber(customer.getPhoneNumber());
//					dto.setCreaditLimit(customer.getCreaditLimit());
					dto.setCreatedAt(customer.getCreatedAt());
					dto.setUpdatedAt(customer.getUpdatedAt());
					dto.seteMail(customer.geteMail());
					dto.setSalary(customer.getSalary());
					customer.getPolicy();
					list1.add(customer);
				}
			}
			logger.info(list1);
			logger.info("Customer With Policy Right Outer Join is Successfull");
			responseObject.setListCustomer(list1);
			responseObject.setSuccessMessage("Customer With Policy Right Outer Join is Successfull");
		} catch (CustomerNotFoundException c) {
			logger.error("Customer with Policy Right Outer JOin is Failure");

			responseObject.setFailureMessage("Customer with Policy Right Outer JOin is Failure");
		}
		return responseObject;

	}

	/**
	 *
	 *
	 * @author Don Doc
	 */
	public ResponseObject findCustomerWithPolicyLeft() {
		ResponseObject responseObject = new ResponseObject();
		logger.debug("Customer With Policy Left Outer Join is Troiggered");

		try {
			List<Customer> list = customerBo.findCustomerWithPolicyLeft();
			final CustomerDTO dto = new CustomerDTO();
			final List<Customer> list1 = new ArrayList<Customer>();
			if (list != null) {
				for (final Customer customer : list) {
					dto.setCustomerId(customer.getCustomerId());
					dto.setCustomerName(customer.getCustomerName());
					dto.setCustomerAge(customer.getCustomerAge());
//					dto.setState(customer.getState());
					dto.setCity(customer.getCity());
					dto.setGender(customer.getGender());
					dto.setPhoneNumber(customer.getPhoneNumber());
//					dto.setCreaditLimit(customer.getCreaditLimit());
					dto.setCreatedAt(customer.getCreatedAt());
					dto.setUpdatedAt(customer.getUpdatedAt());
					dto.seteMail(customer.geteMail());
					dto.setSalary(customer.getSalary());
					customer.getPolicy();
					list1.add(customer);
				}
			}
			logger.info(list1);
			logger.info("Customer With Policy Left Outer Join is Successfull");
			responseObject.setListCustomer(list1);
			responseObject.setSuccessMessage("Customer With Policy Left Outer Join is Successfull");
		} catch (CustomerNotFoundException c) {
			logger.error("Customer with Policy Left Outer JOin is Failure");

			responseObject.setFailureMessage("Customer with Policy Left Outer JOin is Failure");
		}
		return responseObject;

	}

	/**
	 *
	 *
	 * @author Don Doc
	 */
	public ResponseObject findByCustomerPolicyCustomized() {
		ResponseObject response = new ResponseObject();
		try {
			List<CustomerPolicyCustomized> list = customerBo.findByCustomerPolicyCustomized();
			final CustomerDTO dto = new CustomerDTO();
			Policy policy = new Policy();
			final List<CustomerPolicyCustomized> list1 = new ArrayList<CustomerPolicyCustomized>();
			if (list != null) {
				for (CustomerPolicyCustomized custom : list) {
					dto.setCustomerName(custom.getCustomerName());
					dto.setPhoneNumber(custom.getPhoneNumber());
					dto.seteMail(custom.getEmail());
					policy.setPolicyType(custom.getPolicyType());
					policy.setPolicyNumber(custom.getPolicyNumber());
					list1.add(custom);
				}
			}
			logger.info(list1);
			logger.info("CustomerPolicyCustomized is Successfull");
			response.setCustomerPolicyCustomized(list1);
			response.setSuccessMessage("CustomerPolicyCustomized is Successfull");

		} catch (CustomerNotFoundException e) {
			logger.info("CustomerPolicyCustomized is failure");
			response.setFailureMessage("CustomerPolicyCustomized is failure");
		}
		return response;

	}

	/**
	 *
	 *
	 * @author Don Doc
	 */
	public ResponseObject findCustomerWithPolicy() {
		ResponseObject responseObject = new ResponseObject();
		logger.debug("Customer With Policy inner Join is Troiggered");

		try {
			List<Customer> list = customerBo.findCustomerWithPolicy();
			final CustomerDTO dto = new CustomerDTO();
			final List<Customer> list1 = new ArrayList<Customer>();
			if (list != null) {
				for (final Customer customer : list) {
					dto.setCustomerId(customer.getCustomerId());
					dto.setCustomerName(customer.getCustomerName());
					dto.setCustomerAge(customer.getCustomerAge());
//					dto.setState(customer.getState());
					dto.setCity(customer.getCity());
					dto.setGender(customer.getGender());
					dto.setPhoneNumber(customer.getPhoneNumber());
//					dto.setCreaditLimit(customer.getCreaditLimit());
					dto.setCreatedAt(customer.getCreatedAt());
					dto.setUpdatedAt(customer.getUpdatedAt());
					dto.seteMail(customer.geteMail());
					dto.setSalary(customer.getSalary());
					customer.getPolicy();
					list1.add(customer);
				}
			}
			logger.info(list1);
			logger.info("Customer With Policy Inner Join is Successfull");
			responseObject.setListCustomer(list1);
			responseObject.setSuccessMessage("Customer With Policy Inner Join is Successfull");
		} catch (CustomerNotFoundException c) {
			logger.error("Customer with Policy Inner JOin is Failure");

			responseObject.setFailureMessage("Customer with Policy Inner JOin is Failure");
		}
		return responseObject;

	}

	/**
	 *
	 *
	 * @author Don Doc
	 */
	public Response findCustomerAverageAge() {
//		logger.debug("Customer Average is triggered");
		Response response = new Response();
		try {
			Double customer = customerBo.findCustomerAverageAge();
			logger.info(customer);
			logger.info("Customer Average Age is Successfull");
			response.setDouble1(customer);
			response.setSuccessMessage("Customer Average Age is Successfull");

		} catch (CustomerNotFoundException e) {
			logger.error("Customer Average Age is failure" + e);
			response.setFailureMessage("Customer Average Age is failure");
		}
		return response;
	}

	/**
	 *
	 *
	 * @author Don Doc
	 */
	public Response findMaximumCustomerSalary() {
//		logger.debug("Maximum Customer Salary is Successfully");
//		return customerBo.findMaximumCustomerSalary();
		Response response = new Response();
		try {
			Double customer = customerBo.findMaximumCustomerSalary();
			logger.info(customer);
			logger.info("Customer Maximum is Successfull");
			response.setDouble1(customer);
			response.setSuccessMessage("Customer Maximum Salary is Successfull");

		} catch (CustomerNotFoundException e) {
			logger.error("Customer Maximum Salary is failure" + e);
			response.setFailureMessage("Customer Maximum Salary is failure");
		}
		return response;
	}

	/**
	 *
	 *
	 * @author Don Doc
	 */
	public Response findCustomersCount() {
//		logger.info("customers Count  is Successfull");
//		return customerBo.findCustomersCount();
		Response response = new Response();
		try {
			Integer customer = customerBo.findCustomersCount();
			logger.info(customer);
			logger.info("Total Customer is Successfull");
			response.setInteger(customer);
			response.setSuccessMessage("Total Customer is Successfull");

		} catch (CustomerNotFoundException e) {
			logger.error("Total Customer is failure" + e);
			response.setFailureMessage("Total Customer is failure");
		}
		return response;

	}

	/**
	 *
	 *
	 * @author Don Doc
	 */
	public Response findSumCustomerSalary() {
//		logger.info("Total Customer Salary Amount is Sucessfully");
//		return customerBo.findSumCustomerSalary();
		Response response = new Response();
		try {
			Double customer = customerBo.findSumCustomerSalary();
			logger.info(customer);
			logger.info("Total Customer Salary is Successfull");
			response.setDouble1(customer);
			response.setSuccessMessage("Total Customer Salary is Successfull");

		} catch (CustomerNotFoundException e) {
			logger.error("Total Customer Salary is failure" + e);
			response.setFailureMessage("Total Customer Salary is failure");
		}
		return response;

	}

	/**
	 *
	 *
	 * @author Don Doc
	 */
	public Response findMinimumCustomerSalary() {
//		logger.info("Minimum Salary is Successfull");
//		return customerBo.findMinimumCustomerSalary();
		Response response = new Response();
		try {
			Double customer = customerBo.findMinimumCustomerSalary();
			logger.info(customer);
			logger.info("Minimum Customer Salary is Successfull");
			response.setDouble1(customer);
			response.setSuccessMessage("Minimum Customer Salary is Successfull");

		} catch (CustomerNotFoundException e) {
			logger.error("Minimum Customer Salary is failure" + e);
			response.setFailureMessage("Minimum Customer Salary is failure");
		}
		return response;

	}

	/**
	 *
	 *
	 * @author Don Doc
	 */
	public List<String> getAverageSalaryByCity() {
		logger.info("Grouped Average Salary By City Successfull");
		return customerBo.getAverageSalaryByCity();

	}
//
//	public ResponseObject fetchPolicy(int customerId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
