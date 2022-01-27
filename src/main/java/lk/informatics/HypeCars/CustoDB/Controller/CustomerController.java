package lk.informatics.HypeCars.CustoDB.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import lk.informatics.HypeCars.CustoDB.Entities.CustomerBranches;
import lk.informatics.HypeCars.CustoDB.Entities.CustomerDetails;
import lk.informatics.HypeCars.CustoDB.Entities.CustomerDistricts;
import lk.informatics.HypeCars.CustoDB.Entities.CustomerProvinces;
import lk.informatics.HypeCars.CustoDB.Entities.CustomerTransactions;
import lk.informatics.HypeCars.Response.Response;

public interface CustomerController {
	// get methods for default data
	public Response getCustomerDistricts(@RequestParam String ptovinceId);
	public Response getCustomers();
	public Response getCustomerBranches(@RequestParam String districtId);
	public Response getCustomerProvinces();
	
	// Temporary for adding districts and provinces
	public Response setCustomerDistricts(@RequestBody CustomerDistricts destrict);
	public Response setCustomerProvinces(@RequestBody CustomerProvinces province);
	public Response setCustomerBranches(@RequestBody CustomerBranches branch);
	
	// customer creating
	public Response createCustomer(@RequestBody CustomerDetails customer);
	public Response searchCustomerById(@RequestParam String customerId);
	
	// edit customer
	public Response editCustomer(@RequestBody CustomerDetails customer);
	
	// search customer
	public Response searchCustomerByName(@RequestParam String customerName);
	public Response searchCustomerByNic(@RequestParam String customerNic);
	public Response searchCustomerByDistrict(@RequestParam String districtId);
	public Response searchCustomerByProvince(@RequestParam String provinceId);
	
	// set transaction data
	public Response addCustomerTransaction(@RequestBody CustomerTransactions transaction);
	
	// get transaction data
	public Response searchTransactionByVehicleNumber(@RequestParam String vehicleNumber);
	public Response searchTransactionByBranchId(@RequestParam String branchId);
	public Response searchTransactionByDate(@RequestParam String date);
	public Response searchTransactionByDateRange(@RequestParam String date1, @RequestParam String date2);
}
