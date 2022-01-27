package lk.informatics.HypeCars.HypeInventoryDB.Entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lk.informatics.HypeCars.HRPartnerDB.Entities.HRBranches;
import lk.informatics.HypeCars.HRPartnerDB.Entities.HRDistricts;

@Entity
@Table(name = "districts")
public class HypeDistricts {
	
	@Id
	@Column(name = "district_id", length = 15)
	private String districtId;
	
	@Column(name = "district", length = 50, nullable = false)
	private String districtName;
	
	@ManyToOne(targetEntity = HypeProvinces.class)
	@JoinColumn(name = "province_id", nullable = false)
	private HypeProvinces provinceId;
	
	@OneToMany(mappedBy = "districtId")
	private Set<HypeBranches> branches; 
	
	public HypeDistricts() {
		super();
	}

	public HypeDistricts(String districtId) {
		super();
		this.districtId = districtId;
	}

	public HypeDistricts(String districtId, String districtName, HypeProvinces provinceId) {
		super();
		this.districtId = districtId;
		this.districtName = districtName;
		this.provinceId = provinceId;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public HypeProvinces getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(HypeProvinces provinceId) {
		this.provinceId = provinceId;
	}

	@Override
	public String toString() {
		return "HypeDistricts [districtId=" + districtId + ", districtName=" + districtName + ", provinceId="
				+ provinceId + "]";
	}
	
}
