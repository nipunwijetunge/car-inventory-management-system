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
@Table(name = "districts")
public class CustomerDistricts {
	
	@Id
	@Column(name = "district_id", length = 15)
	private String districtId;
	
	@Column(name = "district", length = 100, nullable = false)
	private String districtName;
	
	@ManyToOne(targetEntity = CustomerProvinces.class, optional = false)
	@JoinColumn(name = "province_id", nullable = false)
	private CustomerProvinces provinceId;
	
	@OneToMany(mappedBy = "districtId")
	private Set<CustomerBranches> customerBranches;
	
	@OneToMany(mappedBy = "districtId")
	private Set<CustomerDetails> customerDetails;

	public CustomerDistricts() {
		super();
	}
	
	public CustomerDistricts(String districtId) {
		super();
		this.districtId = districtId;
	}

	public CustomerDistricts(String districtId, String districtName, CustomerProvinces provinceId) {
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

	public CustomerProvinces getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(CustomerProvinces provinceId) {
		this.provinceId = provinceId;
	}

	@Override
	public String toString() {
		return "CustomerDistricts [districtId=" + districtId + ", districtName=" + districtName + ", provinceId="
				+ provinceId + ", customerBranches=" + customerBranches + ", customerDetails=" + customerDetails + "]";
	}
}
