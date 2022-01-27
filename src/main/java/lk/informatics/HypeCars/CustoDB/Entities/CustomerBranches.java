package lk.informatics.HypeCars.CustoDB.Entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "branches")
public class CustomerBranches {
	
	@Id
	@Column(name = "branch_id")
	private String branchId;
	
	@Column(name = "branch_name", length = 15, nullable = false)
	private String branchName;
	
	@ManyToOne(targetEntity = CustomerDistricts.class, optional = false)
	@JoinColumn(name = "district_id", nullable = false)
	private CustomerDistricts districtId;
	
	@ManyToOne(targetEntity = CustomerProvinces.class, optional = false)
	@JoinColumn(name = "province_id", nullable = false)
	private CustomerProvinces provinceId;
	
	@OneToMany(mappedBy = "branchId")
	private Set<CustomerTransactions> customerTransactions;
	
	public CustomerBranches() {
		super();
	}
	
	public CustomerBranches(String branchId) {
		super();
		this.branchId = branchId;
	}

	public CustomerBranches(String branchId, String branchName, CustomerDistricts districtId,
			CustomerProvinces provinceId) {
		super();
		this.branchId = branchId;
		this.branchName = branchName;
		this.districtId = districtId;
		this.provinceId = provinceId;
	}

	public String getBranchId() {
		return branchId;
	}
	
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	
	public String getBranchName() {
		return branchName;
	}
	
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	public CustomerDistricts getDistrictId() {
		return districtId;
	}

	public void setDistrictId(CustomerDistricts districtId) {
		this.districtId = districtId;
	}

	public CustomerProvinces getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(CustomerProvinces provinceId) {
		this.provinceId = provinceId;
	}

	@Override
	public String toString() {
		return "CustomerBranches [branchId=" + branchId + ", branchName=" + branchName + ", districtId=" + districtId
				+ ", provinceId=" + provinceId + "]";
	}

}
