package lk.informatics.HypeCars.CustoDB.Service;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ch.qos.logback.core.joran.conditional.IfAction;
import lk.informatics.HypeCars.CustoDB.Entities.CustomerBranches;
import lk.informatics.HypeCars.CustoDB.Entities.CustomerDetails;
import lk.informatics.HypeCars.CustoDB.Entities.CustomerDistricts;
import lk.informatics.HypeCars.CustoDB.Entities.CustomerProvinces;
import lk.informatics.HypeCars.CustoDB.Entities.CustomerSequence;
import lk.informatics.HypeCars.CustoDB.Entities.CustomerTransactions;
import lk.informatics.HypeCars.CustoDB.Repo.CustomerBranchesRepository;
import lk.informatics.HypeCars.CustoDB.Repo.CustomerDestrictsRepository;
import lk.informatics.HypeCars.CustoDB.Repo.CustomerDetailsRepository;
import lk.informatics.HypeCars.CustoDB.Repo.CustomerProvincesRepository;
import lk.informatics.HypeCars.CustoDB.Repo.CustomerSequenceRepository;
import lk.informatics.HypeCars.CustoDB.Repo.CustomerTransactionsRepository;
import lk.informatics.HypeCars.Response.Response;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	private static DecimalFormat decimalFormatter = new DecimalFormat("00000");
	
	@Autowired
	private CustomerBranchesRepository customerBranchesRepo;
	
	@Autowired
	private CustomerDetailsRepository customerDetailsRepo;
	
	@Autowired
	private CustomerDestrictsRepository customerDestrictsRepo;
	
	@Autowired
	private CustomerProvincesRepository customerProvincesRepo;
	
	@Autowired
	private CustomerSequenceRepository customerSequenceRepo;
	
	@Autowired
	private CustomerTransactionsRepository customerTransactionsRepo;
	
	private Response validateUserNic(String nic) {
		if (nic.matches("^[0-9]{9}[x|X|v|V]|[0-9]{12}$")) {
			return new Response(Response.SUCCESS, "SUCCESS", "NIC number is valid");
		} else {
			return new Response(Response.ERROR, "FAILED", "NIC number is invalid");
		}
	}
	
	@Override
	public Response getCustomerDistricts(String provinceId){
		List<CustomerDistricts> districts = new ArrayList<CustomerDistricts>();
		
		CustomerProvinces province = new CustomerProvinces();
		province.setProvinceId(provinceId);
		
		CustomerDistricts district = new CustomerDistricts();
		district.setProvinceId(province);
		
		Example<CustomerDistricts> matcher = Example.of(district, ExampleMatcher.matchingAll());
		
		customerDestrictsRepo.findAll(matcher)
			.forEach(districts::add);
		
		if (!districts.isEmpty()) {
			return new Response(Response.SUCCESS, "SUCCESS", "Customer districts list", districts);
		} else {
			return new Response(Response.NO_DATA_FOUND, "FAILED", "No matching data found");
		}
		
	}
	
	@Override
	public Response getCustomers(){
		List<CustomerDetails> customers = new ArrayList<CustomerDetails>();
		
		customerDetailsRepo.findAll()
			.forEach(customers::add);
		
		if (!customers.isEmpty()) {
			return new Response(Response.SUCCESS, "SUCCESS", "Customers list", customers);
		} else {
			return new Response(Response.NO_DATA_FOUND, "FAILED", "No data found");
		}
	}
	
	@Override
	public Response getCustomerBranches(String districtId) {
		List<CustomerBranches> branches = new ArrayList<CustomerBranches>();
		
		CustomerDistricts district = new CustomerDistricts();
		district.setDistrictId(districtId);
		
		CustomerBranches branch = new CustomerBranches();
		branch.setDistrictId(district);
		
		Example<CustomerBranches> matcher = Example.of(branch, ExampleMatcher.matchingAll());
		
		customerBranchesRepo.findAll(matcher)
			.forEach(branches::add);
		
		if (!branches.isEmpty()) {
			return new Response(Response.SUCCESS, "SUCCESS", "Customer branches list", branches);
		} else {
			return new Response(Response.NO_DATA_FOUND, "FAILED", "No matching data found");
		}
		
	}
	
	@Override
	public Response getCustomerProvinces() {
		List<CustomerProvinces> provinces = new ArrayList<CustomerProvinces>();
		
		customerProvincesRepo.findAll()
			.forEach(provinces::add);
		
		if (!provinces.isEmpty()) {
			return new Response(Response.SUCCESS, "SUCCESS", "Customer provinces list", provinces);
		} else {
			return new Response(Response.NO_DATA_FOUND, "FAILED", "No data found");
		}
		
	}
	
	@Override
	public Response addCustomerDistricts(CustomerDistricts district) {
		customerDestrictsRepo.save(district);
		
		return new Response(Response.SUCCESS, "SUCCESS", "Customer destrict saved!");
	}
	
	@Override
	public Response addCustomerProvinces(CustomerProvinces province) {
		customerProvincesRepo.save(province);
		
		return new Response(Response.SUCCESS, "SUCCESS", "Customer province saved!");
	}
	
	@Override
	public Response addCustomerBranches(CustomerBranches branch) {
		customerBranchesRepo.save(branch);
		
		return new Response(Response.SUCCESS, "SUCCESS", "Customer branch saved!");
	}
	
	@Override
	public String customerIdGenerator() {
		int thisYear = Calendar.getInstance().get(Calendar.YEAR);
		String formattedSeqNo = "";
		
		CustomerSequence dbYear = customerSequenceRepo.findSeqNoOfCurrentYear(thisYear);
		
		if (dbYear == null) {
			CustomerSequence newSeq = new CustomerSequence(thisYear, 1);
			customerSequenceRepo.save(newSeq);
			
			formattedSeqNo = decimalFormatter.format(newSeq.getId());
			
			return Integer.toString(thisYear)+"-"+formattedSeqNo;
			
		} else if (dbYear.getYear() != thisYear) {
			CustomerSequence newSeq = new CustomerSequence(thisYear, 1);
			customerSequenceRepo.save(newSeq);
			
			formattedSeqNo = decimalFormatter.format(newSeq.getId());
			
			return Integer.toString(thisYear)+"-"+formattedSeqNo;
			
		} else {
			Optional<CustomerSequence> seqToUpdateResponse = customerSequenceRepo.findById(dbYear.getYear());
			CustomerSequence seqToUpdate = seqToUpdateResponse.get();
			
			seqToUpdate.setId(seqToUpdate.getId()+1);
			customerSequenceRepo.save(seqToUpdate);
			
			formattedSeqNo  = decimalFormatter.format(seqToUpdate.getId());
			
			return Integer.toString(thisYear)+"-"+formattedSeqNo;
			
		}
	}
	
	@Override
	public Response createCustomer(CustomerDetails customer) {
		List<CustomerDetails> customers = new ArrayList<CustomerDetails>();
		
		Example<CustomerDetails> checkIfExistsList = Example.of(customer, ExampleMatcher.matchingAll());
		customerDetailsRepo.findAll(checkIfExistsList)
			.forEach(customers::add);
		
		if (!customers.isEmpty()) {
			return new Response(Response.DATA_ALREADY_EXISTS, "FAILED", "Data already exists.");
		} else {
			if(validateUserNic(customer.getCustomerNic()).getId() == 0) {
				customer.setCustomerId(customerIdGenerator());
				CustomerDetails customerTest = customerDetailsRepo.save(customer);
				
				if (customerTest != null) {
					List<String> customerId = new ArrayList<String>();
					customerId.add(customerTest.getCustomerId());
					return new Response(Response.SUCCESS, "SUCCESS", "Customer added successfully.", customerId);
				} else {
					return new Response(Response.ERROR, "FAILED", "Customer adding failed.");
				}
			
			} else {
				return validateUserNic(customer.getCustomerNic());
			}
			
		}
		
	}

	@Override
	public Response checkIfCustomerExists(String customerId) throws JsonProcessingException {
		List<CustomerDetails> result = new ArrayList<CustomerDetails>();
		
		CustomerDetails matcher = new CustomerDetails();
		matcher.setCustomerId(customerId);
		
		Example<CustomerDetails> checkIfExistsList = Example.of(matcher, ExampleMatcher.matchingAll());
		customerDetailsRepo.findAll(checkIfExistsList)
			.forEach(result::add);
		
		// ObjectMapper mapper = new ObjectMapper();
		// mapper.enable(SerializationFeature.INDENT_OUTPUT);
		
		//System.out.println(result.get(0).toString());
		
		//String listToJson = mapper.writeValueAsString(result);
		
		//List<JsonNode> list = mapper.convertValue(node, new TypeReference<List<JsonNode>>() {});
		
		if (!result.isEmpty()) {
			return new Response(Response.DATA_FOUND, "SUCCESS", "Customer found in database.", result);
		} else {
			return new Response(Response.NO_DATA_FOUND, "FAILED", "Customer not found in databse for "+customerId);
		}

	} 
	
	@Override
	public Response editCustomer(CustomerDetails customer) throws JsonProcessingException {
		
		if (checkIfCustomerExists(customer.getCustomerId()).getId() == 1) {
			if(validateUserNic(customer.getCustomerNic()).getId() == 0) {
				customerDetailsRepo.save(customer);
				return new Response(Response.SUCCESS, "SUCCESS", "Changes saved for "+customer.getCustomerId());
			
			} else {
				return validateUserNic(customer.getCustomerNic());
			}
		} else {
			return checkIfCustomerExists(customer.getCustomerId());
		}
			
	}

	@Override
	public Response searchCustomersByName(String customerName) {
		List<CustomerDetails> result = customerDetailsRepo.findByCustomerName(customerName);
		
		if (!result.isEmpty()) {
			return new Response(Response.DATA_FOUND, "SUCCESS", "Customer found in database", result);
		} else {
			return new Response(Response.NO_DATA_FOUND, "FAILED", "Customer not found in database");
		}
		
	}

	@Override
	public Response searchCustomerByNic(String customerNic) {
		List<CustomerDetails> result = customerDetailsRepo.findByCustomerNic(customerNic);
		
		if (!result.isEmpty()) {
			return new Response(Response.DATA_FOUND, "SUCCESS", "Customer found in database", result);
		} else {
			return new Response(Response.NO_DATA_FOUND, "FAILED", "Customer not found in database");
		}
		
	}

	@Override
	public Response searchCustomerByDistrict(CustomerDistricts districtId) {
		List<CustomerDetails> result = customerDetailsRepo.findByDistrictId(districtId);
		
		if (!result.isEmpty()) {
			return new Response(Response.DATA_FOUND, "SUCCESS", "Customer found in database", result);
		} else {
			return new Response(Response.NO_DATA_FOUND, "FAILED", "Customer not found in database");
		}
	}

	@Override
	public Response searchCustomerByProvince(CustomerProvinces provinceId) {
		List<CustomerDetails> result = customerDetailsRepo.findByProvinceId(provinceId);
		
		if (!result.isEmpty()) {
			return new Response(Response.DATA_FOUND, "SUCCESS", "Customer found in database", result);
		} else {
			return new Response(Response.NO_DATA_FOUND, "FAILED", "Customer not found in database");
		}
	}

	@Override
	public Response addCustomerTransaction(CustomerTransactions transaction) {
		List<CustomerTransactions> transactions = customerTransactionsRepo.findTransactionByVehicleNumber(transaction.getVehicleNumber());
		
//		Example<CustomerTransactions> matcher = Example.of(transaction, ExampleMatcher.matchingAll());
//		customerTransactionsRepo.findAll(matcher)
//			.forEach(transactions::add);
		
		if (!transactions.isEmpty()) {
			return new Response(Response.DATA_ALREADY_EXISTS, "FAILED", "Transaction already exists.");
		
		} else {
			CustomerTransactions transactionTest = customerTransactionsRepo.save(transaction);
			
			List<CustomerTransactions> transactionDetails = new ArrayList<CustomerTransactions>();
			transactionDetails.add(transactionTest);
			
			if (transactionTest != null) {
				return new Response(Response.SUCCESS, "SUCCESS", "Transaction saved successfully.", transactionDetails);
			
			} else {
				return new Response(Response.ERROR, "FAILED", "An error occured!");
			}
		}
	
	}

	@Override
	public Response getTransactionByVehicleNumber(String vehicleNumber) {
		List<CustomerTransactions> transactions = customerTransactionsRepo.findTransactionByVehicleNumber(vehicleNumber);
		
		if (!transactions.isEmpty()) {
			return new Response(Response.DATA_FOUND, "SUCCESS", "Transaction found.", transactions);
		} else {
			return new Response(Response.NO_DATA_FOUND, "FAILED", "No matchig data found.");
		}
	}

	@Override
	public Response getTransactionByBranchId(CustomerBranches branchId) {
		List<CustomerTransactions> transactions = customerTransactionsRepo.findTransactionByBranchId(branchId);
		
		if (!transactions.isEmpty()) {
			return new Response(Response.DATA_FOUND, "SUCCESS", "Transaction found in database.", transactions);
		} else {
			return new Response(Response.NO_DATA_FOUND, "FAILED", "No matching data found.");
		}
	}

	@Override
	public Response getTransactionByDate(Date date) {
		List<CustomerTransactions> transactions = customerTransactionsRepo.findTransactionByDate(date);
		
		if (!transactions.isEmpty()) {
			return new Response(Response.DATA_FOUND, "SUCCESS", "Transaction found in database.", transactions);
		} else {
			return new Response(Response.NO_DATA_FOUND, "FAILED", "No matching data found.");
		}
	}

	@Override
	public Response getTransactionByDateRange(Date date1, Date date2) {
		List<CustomerTransactions> transactions = customerTransactionsRepo.findTransactionByDateRange(date1, date2);

		if (!transactions.isEmpty()) {
			return new Response(Response.DATA_FOUND, "SUCCESS", "Transaction found in database.", transactions);
		} else {
			return new Response(Response.NO_DATA_FOUND, "FAILED", "No matching data found.");
		}
		
	}
	
}