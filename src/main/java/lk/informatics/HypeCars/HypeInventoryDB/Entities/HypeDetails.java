package lk.informatics.HypeCars.HypeInventoryDB.Entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "hype_details")
public class HypeDetails {
	
	@Id
	@Column(name = "inv_id", length = 15)
	private String inventoryId;
	
	@Column(name = "vehicle_reg_number", nullable = false, unique = true)
	private String vehicleRegNumber;
	
	@Column(name = "purchase_price", precision = 10, scale = 2, nullable = false)
	private double purchasePrice;
	
	@Column(name = "image_path")
	@Lob
	private String image;
	
	@Column(name = "available_flag", nullable = false)
	private boolean isAvailable = true;
	
	@Column(name = "date", nullable = false)
	private Date date;
	
	@Column(name = "sequence", nullable = false)
	private int sequence;
	
	@ManyToOne(targetEntity = HypeBranches.class)
	@JoinColumn(name = "branch_id", nullable = false)
	private HypeBranches branchId;
	
	@ManyToOne(targetEntity = HypeVehicleMakes.class)
	@JoinColumn(name = "make_id", nullable = false)
	private HypeVehicleMakes makeId;
	
	public HypeDetails() {
		super();
	}

	public HypeDetails(String inventoryId, String vehicleRegNumber, double purchasePrice, String image,
			boolean isAvailable, Date date, HypeBranches branchId, HypeVehicleMakes makeId) {
		super();
		this.inventoryId = inventoryId;
		this.vehicleRegNumber = vehicleRegNumber;
		this.purchasePrice = purchasePrice;
		this.image = image;
		this.isAvailable = isAvailable;
		this.date = date;
		this.branchId = branchId;
		this.makeId = makeId;
	}

	public String getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(String inventoryId) {
		this.inventoryId = inventoryId;
	}

	public String getVehicleRegNumber() {
		return vehicleRegNumber;
	}

	public void setVehicleRegNumber(String vehicleRegNumber) {
		this.vehicleRegNumber = vehicleRegNumber;
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public HypeBranches getBranchId() {
		return branchId;
	}

	public void setBranchId(HypeBranches branchId) {
		this.branchId = branchId;
	}

	public HypeVehicleMakes getMakeId() {
		return makeId;
	}

	public void setMakeId(HypeVehicleMakes makeId) {
		this.makeId = makeId;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	@Override
	public String toString() {
		return "HypeDetails [inventoryId=" + inventoryId + ", vehicleRegNumber=" + vehicleRegNumber + ", purchasePrice="
				+ purchasePrice + ", image=" + image + ", isAvailable=" + isAvailable + ", date=" + date + ", branchId="
				+ branchId + ", makeId=" + makeId + "]";
	}
	
}
