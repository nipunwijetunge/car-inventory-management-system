package lk.informatics.HypeCars.CustoDB.Entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "transaction")
public class CustomerTransactions {
	
	@Id
	@Column(name = "transaction_id", insertable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionId;
	
	@Column(name = "vehicle_number", length = 20, nullable = false, unique = true)
	private String vehicleNumber;
	
	@Column(name = "transaction_type", length = 100, nullable = false)
	private String transactionType;
	
	@Column(name = "price", precision = 10, scale = 2, nullable = false)
	private double price;
	
	@Column(name = "date", nullable = false, insertable = false, updatable = false)
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@ManyToOne(targetEntity = CustomerDetails.class, optional = false)
	@JoinColumn(name = "cus_id", nullable = false)
	private CustomerDetails customerId;
	
	@ManyToOne(targetEntity = CustomerBranches.class, optional = false)
	@JoinColumn(name = "branch_id", nullable = false)
	private CustomerBranches branchId;
	
	public CustomerTransactions() {
		super();
	}

	public CustomerTransactions(String vehicleNumber, String transactionType, double price,
			Date date, CustomerDetails customerId, CustomerBranches branchId) {
		super();
		this.vehicleNumber = vehicleNumber;
		this.transactionType = transactionType;
		this.price = price;
		this.date = date;
		this.customerId = customerId;
		this.branchId = branchId;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public CustomerDetails getCustomerId() {
		return customerId;
	}

	public void setCustomerId(CustomerDetails customerId) {
		this.customerId = customerId;
	}

	public CustomerBranches getBranchId() {
		return branchId;
	}

	public void setBranchId(CustomerBranches branchId) {
		this.branchId = branchId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "CustomerTransactions [vehicleNumber=" + vehicleNumber
				+ ", transactionType=" + transactionType + ", price=" + price + ", date=" + date + ", customerId="
				+ customerId + ", branchId=" + branchId + "]";
	}
	
}
