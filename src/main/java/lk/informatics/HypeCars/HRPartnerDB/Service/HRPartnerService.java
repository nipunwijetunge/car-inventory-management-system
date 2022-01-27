package lk.informatics.HypeCars.HRPartnerDB.Service;

import java.util.List;

import lk.informatics.HypeCars.HRPartnerDB.Entities.HRBranches;
import lk.informatics.HypeCars.HRPartnerDB.Entities.HRDistricts;
import lk.informatics.HypeCars.HRPartnerDB.Entities.HREmployees;
import lk.informatics.HypeCars.HRPartnerDB.Entities.HRLeaveDetails;
import lk.informatics.HypeCars.HRPartnerDB.Entities.HRLeaveTypes;
import lk.informatics.HypeCars.HRPartnerDB.Entities.HRProvinces;
import lk.informatics.HypeCars.Response.Response;

public interface HRPartnerService {
	// get methods for default data
	public Response getHRBranches(String districtId);
	public Response getHRDistricts(String provinceId);
	public Response getHRProvinces();
	
	// Temporary methods to add default data
	public Response addHrDistricts(HRDistricts district);
	public Response addHrProvinces(HRProvinces province);
	public Response addHrBranches(HRBranches branch);
	
	// create employees
	public Response createEmployee(HREmployees employee);
	
	// edit employees
	public Response editEmployee(HREmployees employee);
	public Response disableEmployeeWhenResigned(HREmployees employee);
	
	// apply, edit and cancel leaves
	public Response applyForLeave(HRLeaveDetails leave);
	public Response editLeave(HRLeaveDetails leave);
	public Response cancelLeave(String leaveId);
	
	// temporary methods to add leave types
	public Response addHrLeaveTypes(HRLeaveTypes leaveType);
	
	// search employees by name, nic and branch
	public Response searchEmployeeByName(String employeeName);
	public Response searchEmployeeByBranch(HRBranches branch);
	public Response searchEmployeeByNic(String nic);
	
	// get leave details for employee
	public Response getLeaveDetailsForEmployee(HREmployees employeeId);
	
	// birthday and work anniversary reminder
	public Response getBdayNotifications();
	public Response getAnniversaryNotifications();
}
