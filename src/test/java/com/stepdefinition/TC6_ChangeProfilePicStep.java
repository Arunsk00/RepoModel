package com.stepdefinition;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.pojo.profilepic.ChangeProfilePic_Output_POJO;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class TC6_ChangeProfilePicStep extends BaseClass {
	Response response;

	@Given("User add header and bearer authentication for accessing changeProfilePic endpoint")
	public void userAddHeaderAndBearerAuthenticationForAccessingChangeProfilePicEndpoint() {
		List<Header> listHeaders = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + TC1_LoginStep.globalDatas.getLogtoken());
		Header h3 = new Header("Content-Type", "multipart/form-data");
		listHeaders.add(h1);
		listHeaders.add(h2);
		listHeaders.add(h3);
		Headers headers = new Headers(listHeaders);
		addHeaders(headers);
	}

	@When("User add request body through formdata {string}")
	public void userAddRequestBodyThroughFormdata(String formData) throws FileNotFoundException, IOException {
		addFormData(formData, new File(getProjectPath() + getPropertyFileValue("profilePic")));
	}

	@When("User send {string} request for changeProfilePic endpoint")
	public void userSendRequestForChangeProfilePicEndpoint(String type) {
		response = getRequestType(type, Endpoints.CHANGEPROFILEPIC);
		int statusCode = getStatusCode(response);
		TC1_LoginStep.globalDatas.setStatusCode(statusCode);
	}

	@Then("User verify the changeProfilePic response message matches {string}")
	public void userVerifyTheChangeProfilePicResponseMessageMatches(String expProfilePicMessage) {
		ChangeProfilePic_Output_POJO as = response.as(ChangeProfilePic_Output_POJO.class);
		String actProfilePicMessage = as.getMessage();
		Assert.assertEquals("Verify Profile updated Successfully", expProfilePicMessage, actProfilePicMessage);
	}

}
