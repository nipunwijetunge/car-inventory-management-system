package lk.informatics.HypeCars.HRPartnerDB.Entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "leave_details")
public class HRLeaveDetails {
	
	@Id
	@Column(name = "leave_id", length = 15)
	private String leaveId;
	
	@Column(name = "start_date", nullable = false)
	private Date startDate;
	
	@Column(name = "end_date", nullable = false)
	private Date endDate;
	
	@Column(name = "is_canceled", nullable = false)
	private boolean isCanceled = false;
	
	@ManyToOne(targetEntity = HREmployees.class)
	@JoinColumn(name = "emp_id", nullable = false)
	private HREmployees employeeId;
	
	@ManyToOne(targetEntity = HRLeaveTypes.class)
	@JoinColumn(name = "type_id", nullable = false)
	private HRLeaveTypes typeId;
	
	public HRLeaveDetails() {
		super();
	}

	public HRLeaveDetails(String leaveId) {
		super();
		this.leaveId = leaveId;
	}

	public HRLeaveDetails(String leaveId, Date startDate, Date endDate, HREmployees employeeId, HRLeaveTypes typeId) {
		super();
		this.leaveId = leaveId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.employeeId = employeeId;
		this.typeId = typeId;
	}

	public String getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(String leaveId) {
		this.leaveId = leaveId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public HREmployees getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(HREmployees employeeId) {
		this.employeeId = employeeId;
	}

	public HRLeaveTypes getTypeId() {
		return typeId;
	}

	public void setTypeId(HRLeaveTypes typeId) {
		this.typeId = typeId;
	}

	public boolean isCanceled() {
		return isCanceled;
	}

	public void setCanceled(boolean isCanceled) {
		this.isCanceled = isCanceled;
	}

	@Override
	public String toString() {
		return "HRLeaveDetails [leaveId=" + leaveId + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", employeeId=" + employeeId + ", typeId=" + typeId + "]";
	}
	
}
