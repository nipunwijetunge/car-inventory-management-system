package lk.informatics.HypeCars.HRPartnerDB.Entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "districts")
public class HRDistricts {
	
	@Id
	@Column(name = "district_id", length = 15)
	private String districtId;
	
	@Column(name = "district_name", nullable = false)
	private String districtName;
	
	@ManyToOne(targetEntity = HRProvinces.class, optional = false)
	@JoinColumn(name = "province_id", nullable = false)
	private HRProvinces provinceId;
	
	@OneToMany(mappedBy = "districtId")
	private Set<HRBranches> branches;
	
	@OneToMany(mappedBy = "districtId")
	private Set<HREmployees> employees;

	public HRDistricts() {
		super();
	}

	public HRDistricts(String districtId) {
		super();
		this.districtId = districtId;
	}

	public HRDistricts(String districtId, String districtName, HRProvinces provinceId) {
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

	public void setDistrictName(String district_name) {
		this.districtName = district_name;
	}

	public HRProvinces getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(HRProvinces provinceId) {
		this.provinceId = provinceId;
	}

	@Override
	public String toString() {
		return "HRDistricts [districtId=" + districtId + ", districtName=" + districtName + ", provinceId=" + provinceId
				+ ", branches=" + branches + "]";
	}

}
