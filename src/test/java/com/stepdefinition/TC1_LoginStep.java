package com.stepdefinition;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.globals.GlobalDatas;
import com.pojo.login.Login_Output_POJO;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class TC1_LoginStep extends BaseClass {
	Response response;
	
	static GlobalDatas globalDatas = new GlobalDatas(); 

	@Given("User add header")
	public void userAddHeader() {
		addHeader("accept", "application/json");
	}

	@When("User add basic authentication for login")
	public void userAddBasicAuthenticationForLogin() throws FileNotFoundException, IOException {
		basicAuth(getPropertyFileValue("username"), getPropertyFileValue("password"));
	}

	@When("User send {string} request for login endpoint")
	public void userSendRequestForLoginEndpoint(String type) {
		response = getRequestType(type, Endpoints.POSTMANBASICAUTH);
		int statusCode = getStatusCode(response);
		globalDatas.setStatusCode(statusCode);
		
	}

	@Then("User verify thr login response body firstName matches {string} and get the logtoken saved")
	public void userVerifyThrLoginResponseBodyFirstNameMatchesAndGetTheLogtokenSaved(String expFirstName) {
		Login_Output_POJO as = response.as(Login_Output_POJO.class);
		String actFirstName = as.getData().getFirst_name();
		Assert.assertEquals("Verify First Name", expFirstName, actFirstName);
		
		String logtoken = as.getData().getLogtoken();
		globalDatas.setLogtoken(logtoken);
	}
}
