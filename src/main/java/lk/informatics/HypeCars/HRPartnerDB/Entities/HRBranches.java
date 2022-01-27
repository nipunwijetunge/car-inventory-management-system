package lk.informatics.HypeCars.HRPartnerDB.Entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "branches")
public class HRBranches {
	
	@Id
	@Column(name = "branch_id", length = 15)
	private String branchId;
	
	@Column(name = "branch_name", nullable = false)
	private String branchName;
	
	@ManyToOne(targetEntity = HRDistricts.class, optional = false)
	@JoinColumn(name = "district_id", nullable = false)
	private HRDistricts districtId;
	
	@ManyToOne(targetEntity = HRProvinces.class, optional = false)
	@JoinColumn(name = "province_id", nullable = false)
	private HRProvinces provinceId;
	
	@OneToMany(mappedBy = "branchId")
	private Set<HREmployees> employees;
	
	public HRBranches() {
		super();
	}
	
	public HRBranches(String branchId) {
		super();
		this.branchId = branchId;
	}

	public HRBranches(String branchId, String branchName, HRDistricts districtId, HRProvinces provinceId) {
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

	public HRDistricts getDistrictId() {
		return districtId;
	}

	public void setDistrictId(HRDistricts districtId) {
		this.districtId = districtId;
	}

	public HRProvinces getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(HRProvinces provinceId) {
		this.provinceId = provinceId;
	}

	@Override
	public String toString() {
		return "Branches [branchId=" + branchId + ", branchName=" + branchName + ", districtId=" + districtId
				+ ", provinceId=" + provinceId + "]";
	}

}
