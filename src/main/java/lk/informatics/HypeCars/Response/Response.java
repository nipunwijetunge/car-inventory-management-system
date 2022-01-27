package lk.informatics.HypeCars.Response;

import java.util.List;

public class Response {
	public static final int SUCCESS = 0;
	public static final int DATA_FOUND = 1;
	public static final int ERROR = -1;
	public static final int PRIMARY_KEY_VIOLATION = -2;
	public static final int DATA_ALREADY_EXISTS = -3;
	public static final int NO_DATA_FOUND = -4;
	public static final int KEY_VIOLATION = -5;
	public static final int ALREADY_CANCELED = -6;
	public static final int ENTITY_NOT_FOUND = -7;
	public static final int NOT_AVAILABLE = -8;
	
	private int id;
	private String status;
	private String description;
	//private String returnedData;
	private List<?> returnedData;
	
	public Response() {
		super();
	}

	public Response(int id, String status, String description) {
		super();
		this.id = id;
		this.status = status;
		this.description = description;
	}

	public Response(int id, String status, String description, List<?> returnedData) {
		super();
		this.id = id;
		this.status = status;
		this.description = description;
		this.returnedData = returnedData;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<?> getReturnedData() {
		return returnedData;
	}

	public void setReturnedData(List<?> returnedData) {
		this.returnedData = returnedData;
	}
	
}
