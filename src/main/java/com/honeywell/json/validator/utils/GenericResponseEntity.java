package com.honeywell.json.validator.utils;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

/*
 *This is used to pass the response back even if it is good request or a bad request
 */
public class GenericResponseEntity<T> implements Serializable {

	private static final long serialVersionUID = 3584992125254266797L;

	T data;
	private String status;
	private int responseCode;

	public GenericResponseEntity(T data, String status, int responseCode) {
		super();
		this.data = data;
		this.status = status;
		this.responseCode = responseCode;
	}

	public GenericResponseEntity(T data, int i) {
		super();
		this.data = data;
		this.status = HttpStatus.OK.toString();
		this.responseCode = i;
	}

	public GenericResponseEntity(T data) {
		super();
		this.data = data;
		this.status = HttpStatus.OK.toString();
		this.responseCode = HttpStatus.OK.value();
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

}
