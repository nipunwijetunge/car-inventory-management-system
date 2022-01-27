package lk.informatics.HypeCars.CustoDB.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cus_sequence")
public class CustomerSequence {
	
	@Id
	@Column(name = "year")
	private int year;

	@Column(name = "id", nullable = false)
	private int id;

	public CustomerSequence() {
		super();
	}

	public CustomerSequence(int year, int id) {
		super();
		this.year = year;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "CustomerSequence [id=" + id + ", year=" + year + "]";
	}
	
}
