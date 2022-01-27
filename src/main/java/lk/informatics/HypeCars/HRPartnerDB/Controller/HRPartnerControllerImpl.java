package lk.informatics.HypeCars.HRPartnerDB.Controller;

import java.sql.SQLIntegrityConstraintViolationException;
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

import lk.informatics.HypeCars.HRPartnerDB.Entities.HRBranches;
import lk.informatics.HypeCars.HRPartnerDB.Entities.HRDistricts;
import lk.informatics.HypeCars.HRPartnerDB.Entities.HREmployees;
import lk.informatics.HypeCars.HRPartnerDB.Entities.HRLeaveDetails;
import lk.informatics.HypeCars.HRPartnerDB.Entities.HRLeaveTypes;
import lk.informatics.HypeCars.HRPartnerDB.Entities.HRProvinces;
import lk.informatics.HypeCars.HRPartnerDB.Service.HRPartnerService;
import lk.informatics.HypeCars.Response.Response;

@RestController
@RequestMapping("/hrPartner")
public class HRPartnerControllerImpl implements HRPartnerController{
	
	@Autowired
	private HRPartnerService service;

	@Override
	@RequestMapping(value = "/getHrBranches", method = RequestMethod.GET)
	@ResponseBody
	public Response getHRBranches(@RequestParam String districtId){
		try {
			return service.getHRBranches(districtId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	@RequestMapping(value = "/getHrDistricts", method = RequestMethod.GET)
	@ResponseBody
	public Response getHRDistricts(String provinceId) {
		try {
			return service.getHRDistricts(provinceId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	@RequestMapping(value = "/getHrProvinces", method = RequestMethod.GET)
	@ResponseBody
	public Response getHRProvinces() {
		try {
			return service.getHRProvinces();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	@RequestMapping(value = "/addHrDistrict", method = RequestMethod.POST)
	public Response setHrDistricts(@RequestBody HRDistricts district) {
		try {
			return service.addHrDistricts(district);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	@RequestMapping(value = "/addHrProvince", method = RequestMethod.POST)
	public Response setHrProvinces(@RequestBody HRProvinces province) {
		try {
			return service.addHrProvinces(province);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	@RequestMapping(value = "/addHrBranch", method = RequestMethod.POST)
	public Response setHrBranches(@RequestBody HRBranches branch) {
		try {
			return service.addHrBranches(branch);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	@RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
	public Response addEmployee(HREmployees employee) {
		try {
			return service.createEmployee(employee);
		} catch (DataIntegrityViolationException e) {
			return new Response(Response.KEY_VIOLATION, "FAILED", "Duplicated key found.");
		} catch (EntityNotFoundException e) {
			return new Response(Response.ENTITY_NOT_FOUND, "FAILED", "Foreign key not found.");
		} catch (JpaObjectRetrievalFailureException e) {
			return new Response(Response.ENTITY_NOT_FOUND, "FAILED", "Foreign key not found.");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	@RequestMapping(value = "/editEmployee", method = RequestMethod.POST)
	public Response editEmployee(HREmployees employee) {
		try {
			return service.editEmployee(employee);
		} catch (DataIntegrityViolationException e) {
			return new Response(Response.KEY_VIOLATION, "FAILED", "Duplicated key found. check employee ID again.");
		}  catch (EntityNotFoundException e) {
			return new Response(Response.ENTITY_NOT_FOUND, "FAILED", "Foreign key not found.");
		} catch (JpaObjectRetrievalFailureException e) {
			return new Response(Response.ENTITY_NOT_FOUND, "FAILED", "Foreign key not found.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	@RequestMapping(value = "/disableEmployeeWhenResigned", method = RequestMethod.POST)
	public Response disableEmployeeWhenResigned(HREmployees employee) {
		try {
			return service.disableEmployeeWhenResigned(employee);
		}  catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	@RequestMapping(value = "/addLeaveType")
	public Response setHrLeaveTypes(HRLeaveTypes leaveType) {
		try {
			return service.addHrLeaveTypes(leaveType);
		}  catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	@RequestMapping(value = "/applyForLeave", method = RequestMethod.POST)
	public Response applyForLeave(@RequestBody HRLeaveDetails leave) {
		try {
			return service.applyForLeave(leave);
		} catch (EntityNotFoundException e) {
			return new Response(Response.ENTITY_NOT_FOUND, "FAILED", "Foreign key not found.");
		} catch (JpaObjectRetrievalFailureException e) {
			return new Response(Response.ENTITY_NOT_FOUND, "FAILED", "Foreign key not found.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	@RequestMapping(value = "/editLeave", method = RequestMethod.POST)
	public Response editLeave(HRLeaveDetails leave) {
		try {
			return service.editLeave(leave);
		} catch (EntityNotFoundException e) {
			return new Response(Response.ENTITY_NOT_FOUND, "FAILED", "Foreign key not found.");
		} catch (JpaObjectRetrievalFailureException e) {
			return new Response(Response.ENTITY_NOT_FOUND, "FAILED", "Foreign key not found.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	@RequestMapping(value = "/cancelLeave", method = RequestMethod.GET)
	public Response cancelLeave(@RequestParam String leaveId) {
		try {
			return service.cancelLeave(leaveId);
		}  catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	@RequestMapping(value = "/searchEmployeeByName", method = RequestMethod.GET)
	public Response searchEmployeeByName(String employeeName) {
		try {
			return service.searchEmployeeByName(employeeName);
		}  catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	@RequestMapping(value = "/searchEmployeeByBranch", method = RequestMethod.GET)
	public Response searchEmployeeByBranch(HRBranches branch) {
		try {
			return service.searchEmployeeByBranch(branch);
		}  catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	@RequestMapping(value = "/searchEmployeeByNic", method = RequestMethod.GET)
	public Response searchEmployeeByNic(String nic) {
		try {
			return service.searchEmployeeByNic(nic);
		}  catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	@RequestMapping(value = "/getLeaveDetailsForEmployee", method = RequestMethod.GET)
	public Response getLeaveDetailsForEmployee(HREmployees employeeId) {
		try {
			return service.getLeaveDetailsForEmployee(employeeId);
		}  catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	@RequestMapping(value = "/getBdayNotificatios", method = RequestMethod.GET)
	public Response getBdayNotifications() {
		try {
			return service.getBdayNotifications();
		}  catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	@RequestMapping(value = "/getAnniversaryNotifications", method = RequestMethod.GET)
	public Response getAnniversaryNotifications() {
		try {
			return service.getAnniversaryNotifications();
		}  catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
