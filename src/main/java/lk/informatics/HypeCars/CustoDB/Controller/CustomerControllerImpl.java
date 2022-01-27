package lk.informatics.HypeCars.CustoDB.Controller;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import lk.informatics.HypeCars.CustoDB.Entities.CustomerBranches;
import lk.informatics.HypeCars.CustoDB.Entities.CustomerDetails;
import lk.informatics.HypeCars.CustoDB.Entities.CustomerDistricts;
import lk.informatics.HypeCars.CustoDB.Entities.CustomerProvinces;
import lk.informatics.HypeCars.CustoDB.Entities.CustomerTransactions;
import lk.informatics.HypeCars.CustoDB.Service.CustomerService;
import lk.informatics.HypeCars.Response.Response;

@RestController
@RequestMapping("/customer")
public class CustomerControllerImpl implements CustomerController{
	
	@Autowired
	private CustomerService service;
	
	@Override
	@RequestMapping(value = "/getCustomerDistricts", method = RequestMethod.GET)
	@ResponseBody
	public Response getCustomerDistricts(@RequestParam String provinceId){
		try {
			return service.getCustomerDistricts(provinceId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	@RequestMapping(value = "/getCustomers", method = RequestMethod.GET)
	@ResponseBody
	public Response getCustomers(){
		try {
			return service.getCustomers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	@RequestMapping(value = "/getCustomersBranches", method = RequestMethod.GET)
	@ResponseBody
	public Response getCustomerBranches(@RequestParam String districtId) {
		try {
			return service.getCustomerBranches(districtId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	@RequestMapping(value = "/getCustomersProvinces", method = RequestMethod.GET)
	@ResponseBody
	public Response getCustomerProvinces() {
		try {
			return service.getCustomerProvinces();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
//	@Override
//	@RequestMapping(value = "/getDistrictsByName", method = RequestMethod.GET)
//	@ResponseBody
//	public List<CustomerDistricts> getDistrictsByProvince(@RequestParam String district){
//		try {
//			return service.getCustomerDistricts(district);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return null;
//	}
	
	@Override
	@RequestMapping(value = "/setCustomerDistricts", method = RequestMethod.POST)
	public Response setCustomerDistricts(@RequestBody CustomerDistricts district) {
		try {
			return service.addCustomerDistricts(district);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	@RequestMapping(value = "/setCustomerProvinces", method = RequestMethod.POST)
	public Response setCustomerProvinces(@RequestBody CustomerProvinces province) {
		try {
			return service.addCustomerProvinces(province);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	@RequestMapping(value = "/setCustomerBranches", method = RequestMethod.POST)
	public Response setCustomerBranches(@RequestBody CustomerBranches branch) {
		try {
			return service.addCustomerBranches(branch);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	@RequestMapping(value = "/createCustomer", method = RequestMethod.POST)
	public Response createCustomer(@RequestBody CustomerDetails customer) {
		try {
			return service.createCustomer(customer);
		}  catch (EntityNotFoundException e) {
			return new Response(Response.ENTITY_NOT_FOUND, "FAILED", "Foreign key not found.");
		} catch (JpaObjectRetrievalFailureException e) {
			return new Response(Response.ENTITY_NOT_FOUND, "FAILED", "Foreign key not found.");
		} catch (DataIntegrityViolationException e) {
			return new Response(Response.KEY_VIOLATION, "FAILED", "Duplicate index.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	@RequestMapping(value = "/searchCustomerById", method = RequestMethod.GET)
	@ResponseBody
	public Response searchCustomerById(@RequestParam String customerId) {
		try {
			return service.checkIfCustomerExists(customerId);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	@RequestMapping(value = "/editCustomer", method = RequestMethod.POST)
	public Response editCustomer(@RequestBody CustomerDetails customer) {
		try {
			return service.editCustomer(customer);
		}  catch (EntityNotFoundException e) {
			return new Response(Response.ENTITY_NOT_FOUND, "FAILED", "Foreign key not found.");
		} catch (JpaObjectRetrievalFailureException e) {
			return new Response(Response.ENTITY_NOT_FOUND, "FAILED", "Foreign key not found.");
		} catch (DataIntegrityViolationException e) {
			return new Response(Response.KEY_VIOLATION, "FAILED", "Duplicate index.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	@RequestMapping(value = "/searchCustomerByName", method = RequestMethod.GET)
	@ResponseBody
	public Response searchCustomerByName(@RequestParam String customerName) {
		try {
			return service.searchCustomersByName(customerName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	@RequestMapping(value = "/searchCustomerByNic", method = RequestMethod.GET)
	@ResponseBody
	public Response searchCustomerByNic(@RequestParam String customerNic) {
		try {
			return service.searchCustomerByNic(customerNic);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	@RequestMapping(value = "/searchCustomerByDistrict", method = RequestMethod.GET)
	@ResponseBody
	public Response searchCustomerByDistrict(@RequestParam String districtId) {
		CustomerDistricts district = new CustomerDistricts();
		district.setDistrictId(districtId);
		
		try {
			return service.searchCustomerByDistrict(district);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	@RequestMapping(value = "/searchCustomerByProvince", method = RequestMethod.GET)
	@ResponseBody
	public Response searchCustomerByProvince(@RequestParam String provinceId) {
		CustomerProvinces province = new CustomerProvinces();
		province.setProvinceId(provinceId);
		
		try {
			return service.searchCustomerByProvince(province);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	@RequestMapping(value = "/addCustomerTransaction", method = RequestMethod.POST)
	public Response addCustomerTransaction(@RequestBody CustomerTransactions transaction) {
		try {
			return service.addCustomerTransaction(transaction);
		}  catch (EntityNotFoundException e) {
			return new Response(Response.ENTITY_NOT_FOUND, "FAILED", "Foreign key not found.");
		} catch (JpaObjectRetrievalFailureException e) {
			return new Response(Response.ENTITY_NOT_FOUND, "FAILED", "Foreign key not found.");
		} catch (DataIntegrityViolationException e) {
			return new Response(Response.KEY_VIOLATION, "FAILED", "Duplicate index.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	@RequestMapping(value = "/searchTransactionByVehicleNumber", method = RequestMethod.GET)
	@ResponseBody
	public Response searchTransactionByVehicleNumber(@RequestParam String vehicleNumber) {
		try {
			return service.getTransactionByVehicleNumber(vehicleNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	@RequestMapping(value = "/searchTransactionByBranchId", method = RequestMethod.GET)
	@ResponseBody
	public Response searchTransactionByBranchId(@RequestParam String branchId) {
		CustomerBranches branch = new CustomerBranches();
		branch.setBranchId(branchId);
		
		try {
			return service.getTransactionByBranchId(branch);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	@RequestMapping(value = "/searchTransactionByDate", method = RequestMethod.GET)
	@ResponseBody
	public Response searchTransactionByDate(@RequestParam String date) {
		Date sqlDate = Date.valueOf(date);
		
		try {
			return service.getTransactionByDate(sqlDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@RequestMapping(value = "/searchTransactionByDateRange", method = RequestMethod.GET)
	@ResponseBody
	public Response searchTransactionByDateRange(@RequestParam String date1, @RequestParam String date2) {
		Date sqlDate1 = Date.valueOf(date1);
		Date sqlDate2 = Date.valueOf(date2);
		
		try {
			return service.getTransactionByDateRange(sqlDate1, sqlDate2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
