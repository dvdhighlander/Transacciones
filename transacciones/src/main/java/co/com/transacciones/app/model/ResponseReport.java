package co.com.transacciones.app.model;

import java.util.List;

public class ResponseReport {

	String status;
	List<ReportDTO> movementList;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<ReportDTO> getMovementList() {
		return movementList;
	}
	public void setMovementList(List<ReportDTO> movementList) {
		this.movementList = movementList;
	}
	public ResponseReport(String status, List<ReportDTO> movementList) {
		super();
		this.status = status;
		this.movementList = movementList;
	}
	
	
}
