package lk.informatics.HypeCars.HypeInventoryDB.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "branches")
public class HypeBranches {
	
	@Id
	@Column(name = "branch_id", length = 15)
	private String branchId;
	
	@Column(name = "branch_name", nullable = false, length = 100)
	private String branchName;
	
	@ManyToOne(targetEntity = HypeDistricts.class)
	@JoinColumn(name = "district_id", nullable = false)
	private HypeDistricts districtId;
	
	@ManyToOne(targetEntity = HypeProvinces.class)
	@JoinColumn(name = "province_id", nullable = false)
	private HypeProvinces provinceId;
	
	public HypeBranches() {
		super();
	}

	public HypeBranches(String branchId) {
		super();
		this.branchId = branchId;
	}

	public HypeBranches(String branchId, String branchName, HypeDistricts districtId, HypeProvinces provinceId) {
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
	
	public HypeDistricts getDistrictId() {
		return districtId;
	}

	public void setDistrictId(HypeDistricts districtId) {
		this.districtId = districtId;
	}

	public HypeProvinces getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(HypeProvinces provinceId) {
		this.provinceId = provinceId;
	}

	@Override
	public String toString() {
		return "HypeBranches [branchId=" + branchId + ", branchName=" + branchName + ", districtId=" + districtId
				+ ", provinceId=" + provinceId + "]";
	}
	
}