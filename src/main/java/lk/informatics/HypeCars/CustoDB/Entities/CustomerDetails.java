package lk.informatics.HypeCars.CustoDB.Entities;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cus_details")
public class CustomerDetails {
	
	@Id
	@Column(name = "cus_id", length = 15, updatable = false)
	private String customerId;
	
	@Column(name = "name", length = 100, nullable = false)
	private String customerName;
	
	@Column(name = "nic", length = 15, nullable = false, unique = true)
	private String customerNic;
	
	@Column(name = "address", nullable = false)
	private String customerAddress;
	
	@Column(name = "dob", nullable = false)
	private Date customerDOB;
	
	@Column(name = "gender", length = 20, nullable = false)
	private String customerGender;
	
	@Column(name = "telephone_number", length = 15, nullable = false)
	private String customerPhone;
	
	@ManyToOne(targetEntity = CustomerDistricts.class, optional = false)
	@JoinColumn(name = "district_id", nullable = false)
	private CustomerDistricts districtId;
	
	@ManyToOne(targetEntity = CustomerProvinces.class, optional = false)
	@JoinColumn(name = "province_id", nullable = false)
	private CustomerProvinces provinceId;
	
	@OneToMany(mappedBy = "customerId")
	private Set<CustomerTransactions> customerTransactions;

	public CustomerDetails() {
		super();
	}

	public CustomerDetails(String customerId) {
		super();
		this.customerId = customerId;
	}

	public CustomerDetails(String customerId, String customerName, String customerNic, String customerAddress,
			Date customerDOB, String customerGender, String customerPhone, CustomerDistricts districtId,
			CustomerProvinces provinceId) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerNic = customerNic;
		this.customerAddress = customerAddress;
		this.customerDOB = customerDOB;
		this.customerGender = customerGender;
		this.customerPhone = customerPhone;
		this.districtId = districtId;
		this.provinceId = provinceId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerNic() {
		return customerNic;
	}

	public void setCustomerNic(String customerNic) {
		this.customerNic = customerNic;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public Date getCustomerDOB() {
		return customerDOB;
	}

	public void setCustomerDOB(Date customerDOB) {
		this.customerDOB = customerDOB;
	}

	public String getCustomerGender() {
		return customerGender;
	}

	public void setCustomerGender(String customerGender) {
		this.customerGender = customerGender;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
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
		return "CustomerDetails [customerId=" + customerId + ", customerName=" + customerName + ", customerNic="
				+ customerNic + ", customerAddress=" + customerAddress + ", CustomerDOB=" + customerDOB
				+ ", customerGender=" + customerGender + ", customerPhone=" + customerPhone + ", districtId="
				+ districtId + ", provinceId=" + provinceId + "]";
	}
	
}
