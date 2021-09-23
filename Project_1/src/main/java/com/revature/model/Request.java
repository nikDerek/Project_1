package com.revature.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity

@Table(name = "requests")


public class Request {
	
	@Id
	@Column(name="requestId")
	@GeneratedValue(generator = "request_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(allocationSize = 1, name = "request_id_seq", sequenceName = "request_id_seq")
	private int requestId;
	
	
	@Column(name = "request4")
	private String request4;
	@Column(name = "requestAmount")
	private int requestAmount;
	@Column(name = "requestStatus")
	private int requestStatus;
	
	public Request() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Request(int requestId, String request4, int requestAmount, int requestStatus) {
		super();
		this.requestId = requestId;
		this.request4 = request4;
		this.requestAmount = requestAmount;
		this.requestStatus = requestStatus;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public String getRequest4() {
		return request4;
	}

	public void setRequest4(String request4) {
		this.request4 = request4;
	}

	public int getRequestAmount() {
		return requestAmount;
	}

	public void setRequestAmount(int requestAmount) {
		this.requestAmount = requestAmount;
	}

	public int getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(int requestStatus) {
		this.requestStatus = requestStatus;
	}

	@Override
	public int hashCode() {
		return Objects.hash(request4, requestAmount, requestId, requestStatus);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		return Objects.equals(request4, other.request4) && requestAmount == other.requestAmount
				&& requestId == other.requestId && requestStatus == other.requestStatus;
	}

	@Override
	public String toString() {
		return "Request [requestId=" + requestId + ", request4=" + request4 + ", requestAmount=" + requestAmount
				+ ", requestStatus=" + requestStatus + "]";
	}
	
	
	
	
}
