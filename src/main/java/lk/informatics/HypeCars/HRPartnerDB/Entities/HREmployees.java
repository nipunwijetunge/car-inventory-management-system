package lk.informatics.HypeCars.HRPartnerDB.Entities;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "employees")
public class HREmployees {
	
	@Id
	@Column(name = "emp_id", length = 15)
	private String employeeId;
	
	@Column(name = "name", nullable = false)
	private String employeeName;
	
	@Column(name = "dob", nullable = false)
	private Date dob;
	
	@Column(name = "telephone_number", length = 15, nullable = false)
	private String phone;
	
	@Column(name = "joined_date", nullable = false)
	private Date joinedDate;
	
	@Column(name = "designation", length = 100, nullable = false)
	private String designation;
	
	@Column(name = "gender", length = 15, nullable = false)
	private String gender;
	
	@Column(name = "nic", length = 15, nullable = false, unique = true)
	private String nic;
	
	@Column(name = "profile_image")
	@Lob
	private String profilePic;
	
	@Column(name = "resign_date")
	private Date resignDate;
	
	@Column(name = "is_enable", nullable = false)
	private boolean isEnable = true;
	
	@Column(name = "sequence", nullable = false)
	private int sequence;
	
	@Column(name = "leave_count", nullable = false)
	private int leaveCount;
	
	@ManyToOne(targetEntity = HRBranches.class)
	@JoinColumn(name = "branch_id", nullable = false)
	private HRBranches branchId;
	
	@ManyToOne(targetEntity = HRDistricts.class)
	@JoinColumn(name = "district_id", nullable = false)
	private HRDistricts districtId;
	
	@ManyToOne(targetEntity = HRProvinces.class)
	@JoinColumn(name = "province_id", nullable = false)
	private HRProvinces provinceId;
	
	@OneToMany(mappedBy = "employeeId")
	private Set<HRLeaveDetails> leaves;
	
	public HREmployees() {
		super();
	}
	
	public HREmployees(String employeeId) {
		super();
		this.employeeId = employeeId;
	}

	public HREmployees(int sequence) {
		super();
		this.sequence = sequence;
	}

	public HREmployees(String employeeName, Date dob, String phone, Date joinedDate,
			String designation, String gender, String nic, String profilePic, Date resignDate, boolean isEnable,
			HRBranches branchId, HRDistricts districtId, HRProvinces provinceId) {
		super();
		this.employeeName = employeeName;
		this.dob = dob;
		this.phone = phone;
		this.joinedDate = joinedDate;
		this.designation = designation;
		this.gender = gender;
		this.nic = nic;
		this.profilePic = profilePic;
		this.resignDate = resignDate;
		this.isEnable = isEnable;
		this.branchId = branchId;
		this.districtId = districtId;
		this.provinceId = provinceId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getJoinedDate() {
		return joinedDate;
	}

	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNic() {
		return nic;
	}

	public void setNic(String nic) {
		this.nic = nic;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public Date getResignDate() {
		return resignDate;
	}

	public void setResignDate(Date resignDate) {
		this.resignDate = resignDate;
	}

	public boolean isEnable() {
		return isEnable;
	}

	public void setEnable(boolean isEnable) {
		this.isEnable = isEnable;
	}

	public HRBranches getBranchId() {
		return branchId;
	}

	public void setBranchId(HRBranches branchId) {
		this.branchId = branchId;
	}

	public HRDistricts getDistrictId() {
		return districtId;
	}

	public void setDistrictId(HRDistricts districtId) {
		this.districtId = districtId;
	}

	public HRProvinces getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(HRProvinces provinceId) {
		this.provinceId = provinceId;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public int getLeaveCount() {
		return leaveCount;
	}

	public void setLeaveCount(int leaveCount) {
		this.leaveCount = leaveCount;
	}

	@Override
	public String toString() {
		return "HREmployees [employeeId=" + employeeId + ", employeeName=" + employeeName + ", dob=" + dob + ", phone="
				+ phone + ", joinedDate=" + joinedDate + ", designation=" + designation + ", gender=" + gender
				+ ", nic=" + nic + ", profilePic=" + profilePic + ", resignDate=" + resignDate + ", isEnable="
				+ isEnable + ", branchId=" + branchId + ", districtId=" + districtId + ", provinceId=" + provinceId
				+ "]";
	}

}
