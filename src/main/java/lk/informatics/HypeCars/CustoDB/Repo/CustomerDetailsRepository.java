package lk.informatics.HypeCars.CustoDB.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import lk.informatics.HypeCars.CustoDB.Entities.CustomerDetails;
import lk.informatics.HypeCars.CustoDB.Entities.CustomerDistricts;
import lk.informatics.HypeCars.CustoDB.Entities.CustomerProvinces;

public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails, String>{
	
	@Query("SELECT c FROM CustomerDetails c WHERE LOWER(c.customerName) LIKE %:customerName%")
	public List<CustomerDetails> findByCustomerName(@Param("customerName") String customerName);
	
	@Query("SELECT c FROM CustomerDetails c WHERE LOWER(c.customerNic) = ?1")
	public List<CustomerDetails> findByCustomerNic(String customerNic);
	
	@Query("SELECT c FROM CustomerDetails c WHERE LOWER(c.districtId) = ?1")
	public List<CustomerDetails> findByDistrictId(CustomerDistricts districtId);
	
	@Query("SELECT c FROM CustomerDetails c WHERE LOWER(c.provinceId) = ?1")
	public List<CustomerDetails> findByProvinceId(CustomerProvinces provinceId);
}
