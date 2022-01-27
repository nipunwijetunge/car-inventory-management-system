package lk.informatics.HypeCars.CustoDB.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import lk.informatics.HypeCars.CustoDB.Entities.CustomerSequence;

public interface CustomerSequenceRepository extends JpaRepository<CustomerSequence, Integer>, QueryByExampleExecutor<CustomerSequence>{
	
	@Query("SELECT new CustomerSequence(s.year, s.id) from CustomerSequence s WHERE s.year = ?1")
	public CustomerSequence findSeqNoOfCurrentYear(int year);
	
}
