package lk.informatics.HypeCars.HypeInventoryDB.Entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lk.informatics.HypeCars.HRPartnerDB.Entities.HRBranches;
import lk.informatics.HypeCars.HRPartnerDB.Entities.HRDistricts;

@Entity
@Table(name = "provinces")
public class HypeProvinces {
	
	@Id
	@Column(name = "province_id", length = 15)
	private String provinceId;
	
	@Column(name = "province", nullable = false, length = 100)
	private String provinceName;
	
	@OneToMany(mappedBy = "provinceId")
	private Set<HypeDistricts> districts;
	
	@OneToMany(mappedBy = "provinceId")
	private Set<HypeBranches> branches;
	
	public HypeProvinces() {
		super();
	}
	
	public HypeProvinces(String provinceId) {
		super();
		this.provinceId = provinceId;
	}

	public HypeProvinces(String provinceId, String provinceName) {
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
		return "HypeProvinces [provinceId=" + provinceId + ", provinceName=" + provinceName + "]";
	}
	
}
