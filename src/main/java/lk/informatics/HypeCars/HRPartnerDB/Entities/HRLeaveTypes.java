package lk.informatics.HypeCars.HRPartnerDB.Entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "leave_types")
public class HRLeaveTypes {
	
	@Id
	@Column(name = "type_id", length = 15)
	private String leaveTypeId;
	
	@Column(name = "description", nullable = true)
	private String description;
	
	@OneToMany(mappedBy = "typeId")
	private Set<HRLeaveDetails> leaves;
	
	public HRLeaveTypes() {
		super();
	}

	public HRLeaveTypes(String leaveTypeId) {
		super();
		this.leaveTypeId = leaveTypeId;
	}

	public HRLeaveTypes(String leaveTypeId, String description) {
		super();
		this.leaveTypeId = leaveTypeId;
		this.description = description;
	}

	public String getLeaveTypeId() {
		return leaveTypeId;
	}

	public void setLeaveTypeId(String leaveTypeId) {
		this.leaveTypeId = leaveTypeId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "HRLeaveTypes [leaveTypeId=" + leaveTypeId + ", description=" + description + "]";
	}
	
}
