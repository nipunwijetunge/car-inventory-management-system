package lk.informatics.HypeCars.HRPartnerDB.Entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "provinces")
public class HRProvinces {
	
	@Id
	@Column(name = "province_id", length = 15)
	private String provinceId;
	
	@Column(name = "province_name", nullable = false)
	private String provinceName;
	
	@OneToMany(mappedBy = "provinceId")
	private Set<HRBranches> branches;
	
	@OneToMany(mappedBy = "provinceId")
	private Set<HRDistricts> districts;
	
	@OneToMany(mappedBy = "provinceId")
	private Set<HREmployees> employees;

	public HRProvinces() {
		super();
	}

	public HRProvinces(String provinceId) {
		super();
		this.provinceId = provinceId;
	}

	public HRProvinces(String provinceId, String provinceName) {
		super();
		this.provinceId = provinceId;
		this.provinceName = provinceName;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	@Override
	public String toString() {
		return "Provinces [provinceId=" + provinceId + ", provinceName=" + provinceName + "]";
	}
	
}
