package lk.informatics.HypeCars.HRPartnerDB.Service;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.joran.conditional.IfAction;
import lk.informatics.HypeCars.HRPartnerDB.Entities.HRBranches;
import lk.informatics.HypeCars.HRPartnerDB.Entities.HRDistricts;
import lk.informatics.HypeCars.HRPartnerDB.Entities.HREmployees;
import lk.informatics.HypeCars.HRPartnerDB.Entities.HRLeaveDetails;
import lk.informatics.HypeCars.HRPartnerDB.Entities.HRLeaveTypes;
import lk.informatics.HypeCars.HRPartnerDB.Entities.HRProvinces;
import lk.informatics.HypeCars.HRPartnerDB.Repo.HRPartnerBranchesRepository;
import lk.informatics.HypeCars.HRPartnerDB.Repo.HRPartnerDistrictsRepository;
import lk.informatics.HypeCars.HRPartnerDB.Repo.HRPartnerEmployeesRepository;
import lk.informatics.HypeCars.HRPartnerDB.Repo.HRPartnerLeaveDetailsRepository;
import lk.informatics.HypeCars.HRPartnerDB.Repo.HRPartnerLeaveTypesRepository;
import lk.informatics.HypeCars.HRPartnerDB.Repo.HRPartnerProvincesRepository;
import lk.informatics.HypeCars.Response.Response;

@Service
public class HRPartnerServiceImpl implements HRPartnerService{
	
	private static DecimalFormat decimalFormatter = new DecimalFormat("0000");
	
	@Autowired
	private HRPartnerBranchesRepository hrpartnerBranchesRepo;
	
	@Autowired
	private HRPartnerDistrictsRepository hrpartnerDistrictsRepo;
	
	@Autowired
	private HRPartnerProvincesRepository hrpartnerProvincesRepo;
	
	@Autowired
	private HRPartnerEmployeesRepository hrpartnerEmployeesRepo;
	
	@Autowired
	private HRPartnerLeaveTypesRepository hrpartnerLeaveTypesRepo;
	
	@Autowired
	private HRPartnerLeaveDetailsRepository hrpartnerLeaveDetailsRepo;
	
	private Response validatePhoneNumber(String phone) {
		if (phone.matches("^[0|(\\+94)][7][0|1|2|5|6|7|8][0-9]{9,10}$")) {
			return new Response(Response.SUCCESS, "SUCCESS", "Phone number is valid");
		} else {
			return new Response(Response.ERROR, "FAILED", "Phone number is invalid");
		}
	}
	
	private Response validateDateFormat(String date) {
		if (date.matches("^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$")) {
			return new Response(Response.SUCCESS, "SUCCESS", "Date is in valid format.");
		} else {
			return new Response(Response.ERROR, "FAILED", "Date is in invalid format.");
		}
	}
	
	private Response validateUserNic(String nic) {
		if (nic.matches("^[0-9]{9}[x|X|v|V]|[0-9]{12}$")) {
			return new Response(Response.SUCCESS, "SUCCESS", "NIC number is valid.");
		} else {
			return new Response(Response.ERROR, "FAILED", "NIC number is invalid.");
		}
	}

	@Override
	public Response getHRBranches(String districtId){
		List<HRBranches> branches = new ArrayList<HRBranches>();
		
		HRDistricts district = new HRDistricts();
		district.setDistrictId(districtId);
		
		HRBranches branch = new HRBranches();
		branch.setDistrictId(district);
		
		Example<HRBranches> matcher = Example.of(branch, ExampleMatcher.matchingAll());
		
		hrpartnerBranchesRepo.findAll(matcher)
			.forEach(branches::add);
		
		if (!branches.isEmpty()) {
			return new Response(Response.SUCCESS, "SUCCESS", "HR districts list.", branches);
		
		} else {
			return new Response(Response.NO_DATA_FOUND, "FAILED", "Invalid district ID.");
		
		} 
	
	}
	
	@Override
	public Response getHRDistricts(String provinceId) {
		List<HRDistricts> districts = new ArrayList<HRDistricts>();
		
		HRProvinces province = new HRProvinces();
		province.setProvinceId(provinceId);
		
		HRDistricts district = new HRDistricts();
		district.setProvinceId(province);
		
		Example<HRDistricts> matcher = Example.of(district, ExampleMatcher.matchingAll());
		
		hrpartnerDistrictsRepo.findAll(matcher)
			.forEach(districts::add);
		
		if (!districts.isEmpty()) {
			return new Response(Response.SUCCESS, "SUCCESS", "HR districts data.", districts);
		} else {
			return new Response(Response.ERROR, "FAILED", "Invalid province ID.");
		}

	}

	@Override
	public Response getHRProvinces() {
		List<HRProvinces> provinces = new ArrayList<HRProvinces>();
		
		hrpartnerProvincesRepo.findAll()
			.forEach(provinces::add);
		
		if (!provinces.isEmpty()) {
			return new Response(Response.SUCCESS, "SUCCESS", "HR provinces list", provinces);
		} else {
			return new Response(Response.ERROR, "FAILED", "No data found.");
		}

	}

	@Override
	public Response addHrDistricts(HRDistricts district) {
		HRDistricts districtTest = hrpartnerDistrictsRepo.save(district);
		
		if (districtTest != null) {
			return new Response(Response.SUCCESS, "SUCCESS", "HRDistrict saved successfully.");
		
		} else {
			return new Response(Response.ERROR, "FAILED", "Failed to save data.");
		
		}
	
	}

	@Override
	public Response addHrProvinces(HRProvinces province) {
		HRProvinces provinceTest = hrpartnerProvincesRepo.save(province);
		
		if (provinceTest != null) {
			return new Response(Response.SUCCESS, "SUCCESS", "HRProvince saved successfully.");
		
		} else {
			return new Response(Response.ERROR, "FAILED", "Failed to save data.");
		
		}
		
	}

	@Override
	public Response addHrBranches(HRBranches branch) {
		HRBranches branchTest = hrpartnerBranchesRepo.save(branch);
		
		if (branchTest != null) {
			return new Response(Response.SUCCESS, "SUCCESS", "HRBranch saved successfully.");
		
		} else {
			return new Response(Response.ERROR, "FAILED", "Failed to save data.");
		
		}
	}

	@Override
	public Response createEmployee(HREmployees employee) {
		List<HREmployees> employees = new ArrayList<HREmployees>();
		
		Example<HREmployees> matcher = Example.of(employee, ExampleMatcher.matchingAll());
		hrpartnerEmployeesRepo.findAll(matcher)
			.forEach(employees::add);
		
		if (!employees.isEmpty()) {
			return new Response(Response.DATA_ALREADY_EXISTS, "FAILED", "Data already exists.");
		
		} else {
			if (validateUserNic(employee.getNic()).getId() == 0) {
				
//				if (validatePhoneNumber(employee.getPhone()).getId() == 0) {
					
					int lastSeq = hrpartnerEmployeesRepo.getLastSeqNumber();
					
					if (lastSeq == 0) {
						employee.setEmployeeId("EMP-0001");
						employee.setSequence(1);
					
					} else {
						String formattedSeqNo = decimalFormatter.format(lastSeq+1);
						employee.setEmployeeId("EMP-"+formattedSeqNo);
						employee.setSequence(lastSeq+1);
					}
					
					HREmployees emloyeeTest = hrpartnerEmployeesRepo.save(employee);
					
					if (emloyeeTest != null) {
						return new Response(Response.SUCCESS, "SUCCESS", "Employee added successfully.");
					
					} else {
						return new Response(Response.ERROR, "FAILED", "Failed to add employee.");
					}
					
//				} else {
//					return new Response(Response.ERROR, "FAILED", "Invalid phone number format.");
//				}
				
			} else {
				return new Response(Response.ERROR, "FAILED", "Invalid NIC Number.");
			}
			
		}
		
	}
	
	

	@Override
	public Response editEmployee(HREmployees employee) {
		//List<HREmployees> result = new ArrayList<HREmployees>();
		
		HREmployees result = hrpartnerEmployeesRepo.findEmployeeById(employee.getEmployeeId());
		
		if (result == null) {
			return new Response(Response.NO_DATA_FOUND, "FAILED", "No matching employee found.");
		
		} else {
			if (validateUserNic(employee.getNic()).getId() == 0) {
				int seqNo = hrpartnerEmployeesRepo.getSeqNoForEmployee(employee.getEmployeeId());
				employee.setSequence(seqNo);
				
				hrpartnerEmployeesRepo.save(employee);
				return new Response(Response.SUCCESS, "SUCCESS", "Changes saved for "+employee.getEmployeeId());
			
			} else {
				return validateUserNic(employee.getNic());
			}
		}
	}

	@Override
	public Response disableEmployeeWhenResigned(HREmployees employee) {
		//List<HREmployees> result = new ArrayList<HREmployees>();
		
		HREmployees result = hrpartnerEmployeesRepo.findEmployeeById(employee.getEmployeeId());
		
		if (result == null) {
			return new Response(Response.NO_DATA_FOUND, "FAILED", "No matching employee found.");
		
		} else {
			//int seqNo = hrpartnerEmployeesRepo.getSeqNoForEmployee(employee.getEmployeeId());
			//employee.setSequence(seqNo);
			result.setEnable(false);
			result.setResignDate(employee.getResignDate());
			
			hrpartnerEmployeesRepo.save(result);
			return new Response(Response.SUCCESS, "SUCCESS", "Changes saved for "+employee.getEmployeeId());
		}
	}

	@Override
	public Response addHrLeaveTypes(HRLeaveTypes leaveType) {
		HRLeaveTypes leaveTypeTest = hrpartnerLeaveTypesRepo.save(leaveType);
		
		if (leaveTypeTest != null) {
			return new Response(Response.SUCCESS, "SUCCESS", "Leave type added successfully.");
		
		} else {
			return new Response(Response.ERROR, "FAILED", "Failed to add leave type.");
		}
	}

	@Override
	public Response applyForLeave(HRLeaveDetails leave) {
		HREmployees employee = hrpartnerEmployeesRepo.findEmployeeById(leave.getEmployeeId().getEmployeeId());
		
		if (employee != null) {
			int leaveCount = hrpartnerEmployeesRepo.getLeaveCountByEmployeeId(leave.getEmployeeId().getEmployeeId());
			
			employee.setLeaveCount(leaveCount+1);
			hrpartnerEmployeesRepo.save(employee);
			
			String leaveId = leave.getEmployeeId().getEmployeeId()+"."+leave.getTypeId().getLeaveTypeId()+"."+(leaveCount+1);
			leave.setLeaveId(leaveId);
			
			hrpartnerLeaveDetailsRepo.save(leave);
			
			List<HRLeaveDetails> leaves = new ArrayList<HRLeaveDetails>();
			leaves.add(leave);
			
			return new Response(Response.SUCCESS, "SUCCESS", "Leave saved successfully.", leaves);
			
			
		
		} else {
			return new Response(Response.NO_DATA_FOUND, "FAILED", "Employee not found in database.");
		}
		
	}

	@Override
	public Response editLeave(HRLeaveDetails leave) {
		HRLeaveDetails leaveTest = hrpartnerLeaveDetailsRepo.getLeaveByLeaveId(leave.getLeaveId());
		
		if (leaveTest != null) {
			if(!leaveTest.isCanceled()) {
				hrpartnerLeaveDetailsRepo.save(leave);
				
				return new Response(Response.SUCCESS, "SUCCESS", "Leave details updated.");
			} else {
				return new Response(Response.ALREADY_CANCELED, "FAILED", "Leave already canceled.");
			}
		} else {
			return new Response(Response.NO_DATA_FOUND, "FAILED", "Leave not found in database.");
		}
	}

	@Override
	public Response cancelLeave(String leaveId) {
		HRLeaveDetails leaveTest = hrpartnerLeaveDetailsRepo.getLeaveByLeaveId(leaveId);
		
		if (leaveTest != null) {
			if (!leaveTest.isCanceled()) {
				leaveTest.setCanceled(true);
				hrpartnerLeaveDetailsRepo.save(leaveTest);
				
				return new Response(Response.SUCCESS, "SUCCESS", "Leave canceled.");
			
			} else {
				return new Response(Response.ALREADY_CANCELED, "FAILED", "Leave is already canceled.");
			} 
		} else {
			return new Response(Response.NO_DATA_FOUND, "FAILED", "No matching data found.");
		}

	}
	
	private static String encodeFileToBase64Binary(File file) throws Exception{
        FileInputStream fileInputStreamReader = new FileInputStream(file);
        byte[] bytes = new byte[(int)file.length()];
        fileInputStreamReader.read(bytes);
        fileInputStreamReader.close();
        
        return new String(Base64.getEncoder().encodeToString(bytes));
	}

	@Override
	public Response searchEmployeeByName(String employeeName) {
		List<HREmployees> employees = hrpartnerEmployeesRepo.findEmployeeByName(employeeName);
		
		for (HREmployees hrEmployees : employees) {
			try {
				String base64String = encodeFileToBase64Binary(new File(hrEmployees.getProfilePic()));
				hrEmployees.setProfilePic(base64String);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (!employees.isEmpty()) {
			return new Response(Response.DATA_FOUND, "SUCCESS", "Employee found in database", employees);
		
		} else {
			return new Response(Response.NO_DATA_FOUND, "FAILED", "Employee not found in database");
		
		}
		
	}

	@Override
	public Response searchEmployeeByBranch(HRBranches branch) {
		List<HREmployees> employees = hrpartnerEmployeesRepo.findEmployeeByBranch(branch);
		
		for (HREmployees hrEmployees : employees) {
			try {
				String base64String = encodeFileToBase64Binary(new File(hrEmployees.getProfilePic()));
				hrEmployees.setProfilePic(base64String);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (!employees.isEmpty()) {
			return new Response(Response.DATA_FOUND, "SUCCESS", "Employees found in database", employees);
		
		} else {
			return new Response(Response.NO_DATA_FOUND, "FAILED", "Employees not found in database");
		
		}

	}

	@Override
	public Response searchEmployeeByNic(String nic) {
		List<HREmployees> employees = hrpartnerEmployeesRepo.findEmployeeByNic(nic);
		
		for (HREmployees hrEmployees : employees) {
			try {
				String base64String = encodeFileToBase64Binary(new File(hrEmployees.getProfilePic()));
				hrEmployees.setProfilePic(base64String);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (!employees.isEmpty()) {
			return new Response(Response.DATA_FOUND, "SUCCESS", "Employee found in database", employees);
		
		} else {
			return new Response(Response.NO_DATA_FOUND, "FAILED", "Employee not found in database");
		
		}

	}

	@Override
	public Response getLeaveDetailsForEmployee(HREmployees employeeId) {
		List<HRLeaveDetails> leaves = hrpartnerLeaveDetailsRepo.findLeaveDetailsByEmployeeId(employeeId);
		
		if (!leaves.isEmpty()) {
			return new Response(Response.DATA_FOUND, "SUCCESS", "Leave details found for the employee.", leaves);
		} else {
			return new Response(Response.NO_DATA_FOUND, "FAILED", "No data found.");
		}

	}

	@Override
	public Response getBdayNotifications() {
		List<HREmployees> bDayEmployees = hrpartnerEmployeesRepo.employeeBDayNotification();
		
		if (!bDayEmployees.isEmpty()) {
			return new Response(Response.SUCCESS, "SUCCESS", "Today is birthday.", bDayEmployees);
		} else {
			return new Response(Response.NO_DATA_FOUND, "FAILED", "No birthday today.");
		}

	}

	@Override
	public Response getAnniversaryNotifications() {
		List<HREmployees> anniEmployees = hrpartnerEmployeesRepo.employeeWorkAnniversaryNotification();
		
		if (!anniEmployees.isEmpty()) {
			return new Response(Response.SUCCESS, "SUCCESS", "Today is anniversary.", anniEmployees);
		} else {
			return new Response(Response.NO_DATA_FOUND, "FAILED", "No anniversary today");
		}

	}
	
}
