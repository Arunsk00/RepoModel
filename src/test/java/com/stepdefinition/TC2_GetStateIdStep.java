package com.stepdefinition;

import java.util.ArrayList;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.pojo.address.StateList;
import com.pojo.address.StateList_Output_POJO;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class TC2_GetStateIdStep extends BaseClass {
	Response response;
	
	@Given("User add header for stateList")
	public void userAddHeaderForStateList() {
		addHeader("accept", "application/json");
	}

	@When("User send {string} request for stateList endpoint")
	public void userSendRequestForStateListEndpoint(String type) {
		response = getRequestType(type, Endpoints.STATELIST);
		int statusCode = getStatusCode(response);
		TC1_LoginStep.globalDatas.setStatusCode(statusCode);
	}

	@Then("User verify the stateList response body matches {string} and save the stateId")
	public void userVerifyTheStateListResponseBodyMatchesAndSaveTheStateId(String expStateName) {
		StateList_Output_POJO as = response.as(StateList_Output_POJO.class);
		ArrayList<StateList> stateList = as.getData();
		for (StateList eachStateList : stateList) {
			String actStateName = eachStateList.getName();
			if (actStateName.equals(expStateName)) {
				int stateId = eachStateList.getId();
				String state_Id = String.valueOf(stateId);
				TC1_LoginStep.globalDatas.setStateId(stateId);
				TC1_LoginStep.globalDatas.setState_Id(state_Id);
				Assert.assertEquals("Verify Tamil Nadu", expStateName, actStateName);
				break;
			}
			
		}
	}

}
