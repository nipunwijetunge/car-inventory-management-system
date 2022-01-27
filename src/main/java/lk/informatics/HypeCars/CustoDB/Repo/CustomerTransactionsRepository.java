package lk.informatics.HypeCars.CustoDB.Repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import lk.informatics.HypeCars.CustoDB.Entities.CustomerBranches;
import lk.informatics.HypeCars.CustoDB.Entities.CustomerTransactions;

public interface CustomerTransactionsRepository extends JpaRepository<CustomerTransactions, String>{
	
	@Query("SELECT c FROM CustomerTransactions c WHERE LOWER(c.vehicleNumber) = ?1")
	public List<CustomerTransactions> findTransactionByVehicleNumber(String vehicleNumber);
	
	@Query("SELECT c FROM CustomerTransactions c WHERE LOWER(c.branchId) = ?1")
	public List<CustomerTransactions> findTransactionByBranchId(CustomerBranches branchId);
	
	@Query("SELECT c FROM CustomerTransactions c WHERE LOWER(c.date) = ?1")
	public List<CustomerTransactions> findTransactionByDate(Date date);
	
	@Query("SELECT c FROM CustomerTransactions c WHERE LOWER(c.date) BETWEEN ?1 AND ?2")
	public List<CustomerTransactions> findTransactionByDateRange(Date date1, Date date2);
}
