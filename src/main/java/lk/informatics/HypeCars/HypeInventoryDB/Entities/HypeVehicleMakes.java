package lk.informatics.HypeCars.HypeInventoryDB.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "makes")
public class HypeVehicleMakes {
	
	@Id
	@Column(name = "make_id", length = 15)
	private String makeId;
	
	@Column(name = "company_name", length = 100, nullable = false)
	private String companyName;
	
	@Column(name = "country", length = 100, nullable = false)
	private String country;
	
	@Column(name = "sequence", nullable = false)
	private int sequence;
	
	public HypeVehicleMakes() {
		super();
	}

	public HypeVehicleMakes(String makeId) {
		super();
		this.makeId = makeId;
	}

	public HypeVehicleMakes(String companyName, String country) {
		super();
		this.companyName = companyName;
		this.country = country;
	}

	public String getMakeId() {
		return makeId;
	}

	public void setMakeId(String makeId) {
		this.makeId = makeId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	@Override
	public String toString() {
		return "HypeVehicleMakes [makeId=" + makeId + ", companyName=" + companyName + ", country=" + country + "]";
	}
	
}
