package com.capgemini.model;

import org.springframework.stereotype.Component;

@Component
public class FriendManagementResp {

	String status;
	String description;



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

	public FriendManagementResp() {

	}

	public FriendManagementResp(String status, String description) {
		super();
		this.status = status;
		this.description = description;
	}







}
