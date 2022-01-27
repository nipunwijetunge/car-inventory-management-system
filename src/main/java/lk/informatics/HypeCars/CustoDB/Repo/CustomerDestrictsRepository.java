package lk.informatics.HypeCars.CustoDB.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import lk.informatics.HypeCars.CustoDB.Entities.CustomerDistricts;
import lk.informatics.HypeCars.CustoDB.Entities.CustomerProvinces;

public interface CustomerDestrictsRepository extends JpaRepository<CustomerDistricts, String>, QueryByExampleExecutor<CustomerDistricts>{
	
}
