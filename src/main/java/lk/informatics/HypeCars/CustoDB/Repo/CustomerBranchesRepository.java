package lk.informatics.HypeCars.CustoDB.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lk.informatics.HypeCars.CustoDB.Entities.CustomerBranches;

@Repository
public interface CustomerBranchesRepository extends JpaRepository<CustomerBranches, String>{
	
}
