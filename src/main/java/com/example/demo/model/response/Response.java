package com.example.demo.model.response;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
	
	private LocalDateTime timeStamp;
	private int statusCode;
	private HttpStatus status;
	private String reason;
	private String message;
	private String developerMessage;
	private Map<?, ?> data;

	Response(Builder builder) {
		timeStamp = builder.timeStamp;
		statusCode = builder.statusCode;
		status = builder.status;
		reason = builder.reason;
		message = builder.message;
		developerMessage = builder.developerMessage;
		data = builder.data;

	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		protected int statusCode;
		protected HttpStatus status;
		protected String reason;
		protected String message;
		protected String developerMessage;
		protected Map<?, ?> data;
		protected LocalDateTime timeStamp;

		public Builder TimeStamp(LocalDateTime timeStamp) {
			this.timeStamp = timeStamp;
			return this;
		}

		public Builder StatusCode(int statusCode) {
			this.statusCode = statusCode;
			return this;
		}

		public Builder Status(HttpStatus status) {
			this.status = status;
			return this;
		}

		public Builder Reason(String reason) {
			this.reason = reason;
			return this;
		}

		public Builder Message(String message) {
			this.message = message;
			return this;
		}

		public Builder DeveloperMessage(String developerMessage) {
			this.developerMessage = developerMessage;
			return this;
		}

		public Builder Data(Map<?, ?> data) {
			this.data = data;
			return this;
		}

		public Response build() {
			return new Response(this);
		}
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

	public Map<?, ?> getData() {
		return data;
	}

	public void setData(Map<?, ?> data) {
		this.data = data;
	}
	
}
