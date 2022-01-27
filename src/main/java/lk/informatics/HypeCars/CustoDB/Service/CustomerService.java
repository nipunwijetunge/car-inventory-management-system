package lk.informatics.HypeCars.CustoDB.Service;

import java.sql.Date;

import com.fasterxml.jackson.core.JsonProcessingException;

import lk.informatics.HypeCars.CustoDB.Entities.CustomerBranches;
import lk.informatics.HypeCars.CustoDB.Entities.CustomerDetails;
import lk.informatics.HypeCars.CustoDB.Entities.CustomerDistricts;
import lk.informatics.HypeCars.CustoDB.Entities.CustomerProvinces;
import lk.informatics.HypeCars.CustoDB.Entities.CustomerTransactions;
import lk.informatics.HypeCars.Response.Response;

public interface CustomerService {
	// get methods for default data
	public Response getCustomerDistricts(String provinceId);
	public Response getCustomers();
	public Response getCustomerBranches(String districtId);
	public Response getCustomerProvinces();
	
	// Temporary for adding districts and provinces
	public Response addCustomerDistricts(CustomerDistricts district);
	public Response addCustomerProvinces(CustomerProvinces province);
	public Response addCustomerBranches(CustomerBranches branch);
	
	// customer creating
	public String customerIdGenerator();
	public Response createCustomer(CustomerDetails customer);
	
	// edit customer
	public Response checkIfCustomerExists(String customerId) throws JsonProcessingException;
	public Response editCustomer(CustomerDetails customer) throws JsonProcessingException;
	
	// search customer
	public Response searchCustomersByName(String customerName);
	public Response searchCustomerByNic(String customerNic);
	public Response searchCustomerByDistrict(CustomerDistricts districtId);
	public Response searchCustomerByProvince(CustomerProvinces provinceId);
	
	// set transaction data
	public Response addCustomerTransaction(CustomerTransactions transaction);
	
	// get transaction data
	public Response getTransactionByVehicleNumber(String vehicleNumber);
	public Response getTransactionByBranchId(CustomerBranches branchId);
	public Response getTransactionByDate(Date date);
	public Response getTransactionByDateRange(Date date1, Date date2);
}
