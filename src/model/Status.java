package model;

public class Status {

	private String status;
	private String delayMinutes;
	private String flightId;
	
	public Status(){}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDelayMinutes() {
		return delayMinutes;
	}

	public void setDelayMinutes(String delayMinutes) {
		this.delayMinutes = delayMinutes;
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
}
