package lk.informatics.HypeCars.HRPartnerDB.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lk.informatics.HypeCars.HRPartnerDB.Entities.HREmployees;
import lk.informatics.HypeCars.HRPartnerDB.Entities.HRLeaveDetails;

public interface HRPartnerLeaveDetailsRepository extends JpaRepository<HRLeaveDetails, String>{
	
	@Query("SELECT l FROM HRLeaveDetails l WHERE l.leaveId = ?1")
	public HRLeaveDetails getLeaveByLeaveId(String leaveId);
	
	@Query("SELECT l FROM HRLeaveDetails l WHERE l.employeeId = ?1")
	public List<HRLeaveDetails> findLeaveDetailsByEmployeeId(HREmployees employeeId);
}
