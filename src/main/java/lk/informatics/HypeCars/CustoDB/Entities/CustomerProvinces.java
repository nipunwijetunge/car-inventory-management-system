package lk.informatics.HypeCars.CustoDB.Entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "provinces")
public class CustomerProvinces {
	
	@Id
	@Column(name = "province_id", length = 15)
	private String provinceId;
	
	@Column(name = "province")
	private String provinceName;
	
	@OneToMany(mappedBy = "provinceId")
	private Set<CustomerDetails> customerDetails;
	
	@OneToMany(mappedBy = "provinceId")
	private Set<CustomerBranches> customerBranches;
	
	@OneToMany(mappedBy = "provinceId")
	private Set<CustomerDistricts> customerDistricts;

	public CustomerProvinces() {
		super();
	}
	
	public CustomerProvinces(String provinceId) {
		super();
		this.provinceId = provinceId;
	}

	public CustomerProvinces(String provinceId, String provinceName) {
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
