package com.pojo.address;

import java.util.ArrayList;

public class GetUserAddress_Output_POJO {
	
	 private int status;
	 private String message;
	private ArrayList<GetAddresses> data;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ArrayList<GetAddresses> getData() {
		return data;
	}

	public void setData(ArrayList<GetAddresses> data) {
		this.data = data;
	}

}
