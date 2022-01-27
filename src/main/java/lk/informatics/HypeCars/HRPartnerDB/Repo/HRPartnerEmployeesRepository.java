package lk.informatics.HypeCars.HRPartnerDB.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import lk.informatics.HypeCars.HRPartnerDB.Entities.HRBranches;
import lk.informatics.HypeCars.HRPartnerDB.Entities.HREmployees;

public interface HRPartnerEmployeesRepository extends JpaRepository<HREmployees, String>{
	
	@Query("SELECT MAX(e.sequence) FROM HREmployees e")
	public int getLastSeqNumber();
	
	@Query("SELECT e FROM HREmployees e WHERE e.employeeId = ?1")
	public HREmployees findEmployeeById(String employeeId);
	
	@Query("SELECT e.sequence FROM HREmployees e WHERE e.employeeId = ?1")
	public int getSeqNoForEmployee(String employeeId);
	
	@Query("SELECT e.leaveCount FROM HREmployees e WHERE e.employeeId = ?1")
	public int getLeaveCountByEmployeeId(String employeeId);
	
	@Query("SELECT e FROM HREmployees e WHERE LOWER(e.employeeName) LIKE %:employeeName%")
	public List<HREmployees> findEmployeeByName(@Param("employeeName") String employeeName);
	
	@Query("SELECT e FROM HREmployees e WHERE LOWER(e.branchId) = ?1")
	public List<HREmployees> findEmployeeByBranch(HRBranches branchId);
	
	@Query("SELECT e FROM HREmployees e WHERE LOWER(e.nic) = ?1")
	public List<HREmployees> findEmployeeByNic(String nic);
	
	@Query("SELECT e FROM HREmployees e WHERE DAY(SYSDATE()) = DAY(e.dob) AND "
			+ "MONTH(SYSDATE()) = MONTH(e.dob)")
	public List<HREmployees> employeeBDayNotification();
	
	@Query("SELECT e FROM HREmployees e WHERE DAY(SYSDATE()) = DAY(e.joinedDate) AND "
			+ "MONTH(SYSDATE()) = MONTH(e.joinedDate)")
	public List<HREmployees> employeeWorkAnniversaryNotification();

}