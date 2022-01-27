package lk.informatics.HypeCars.HRPartnerDB.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import lk.informatics.HypeCars.HRPartnerDB.Entities.HRBranches;
import lk.informatics.HypeCars.HRPartnerDB.Entities.HRDistricts;
import lk.informatics.HypeCars.HRPartnerDB.Entities.HREmployees;
import lk.informatics.HypeCars.HRPartnerDB.Entities.HRLeaveDetails;
import lk.informatics.HypeCars.HRPartnerDB.Entities.HRLeaveTypes;
import lk.informatics.HypeCars.HRPartnerDB.Entities.HRProvinces;
import lk.informatics.HypeCars.Response.Response;

public interface HRPartnerController {
	// get methods for default data
	public Response getHRBranches(@RequestParam String districtId);
	public Response getHRDistricts(@RequestParam String provinceId);
	public Response getHRProvinces();
	
	// Temporary methods to add default data
	public Response setHrDistricts(@RequestBody HRDistricts district);
	public Response setHrProvinces(@RequestBody HRProvinces province);
	public Response setHrBranches(@RequestBody HRBranches branch);
	
	// create employees
	public Response addEmployee(@RequestBody HREmployees employee);
	
	// edit employee
	public Response editEmployee(@RequestBody HREmployees employee);
	public Response disableEmployeeWhenResigned(@RequestBody HREmployees employee);
	
	// apply, edit and cancel leaves
	public Response applyForLeave(@RequestBody HRLeaveDetails leave);
	public Response editLeave(@RequestBody HRLeaveDetails leave);
	public Response cancelLeave(@RequestParam String leaveId);
	
	// temporary methods to add leave types
	public Response setHrLeaveTypes(@RequestBody HRLeaveTypes leaveType);
	
	// search employees by name, nic and branch
	public Response searchEmployeeByName(@RequestParam String employeeName);
	public Response searchEmployeeByBranch(@RequestParam HRBranches branch);
	public Response searchEmployeeByNic(@RequestParam String nic);
	
	// get leave details for employee
	public Response getLeaveDetailsForEmployee(@RequestParam HREmployees employeeId);
	
	// birthday and work anniversary reminder
	public Response getBdayNotifications();
	public Response getAnniversaryNotifications();
}
