package com.stepdefinition;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.pojo.address.CityList;
import com.pojo.address.CityList_Input_POJO;
import com.pojo.address.CityList_Output_POJO;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class TC3_GetCityIdStep extends BaseClass {
	Response response;

	@Given("User add header for cityList")
	public void userAddHeaderForCityList() {
		List<Header> listHeaders = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");
		listHeaders.add(h1);
		listHeaders.add(h2);
		Headers headers = new Headers(listHeaders);
		addHeaders(headers);

	}

	@When("User add request body to get cityList by passing stateId")
	public void userAddRequestBodyToGetCityListByPassingStateId() {
		CityList_Input_POJO cityList_Input_POJO = new CityList_Input_POJO(TC1_LoginStep.globalDatas.getState_Id());
		addObjectBody(cityList_Input_POJO);
	}

	@When("User send {string} request for cityList endpoint")
	public void userSendRequestForCityListEndpoint(String type) {
		response = getRequestType(type, Endpoints.CITYLIST);
		int statusCode = getStatusCode(response);
		TC1_LoginStep.globalDatas.setStatusCode(statusCode);
	}

	@Then("User verify the cityList response body matches {string} and save the cityId")
	public void userVerifyTheCityListResponseBodyMatchesAndSaveTheCityId(String expCityName) {
		CityList_Output_POJO as = response.as(CityList_Output_POJO.class);
		ArrayList<CityList> cityList = as.getData();
		for (CityList eachCityList : cityList) {
			String actCityName = eachCityList.getName();
			if (actCityName.equals("Panruti")) {
				int cityId = eachCityList.getId();
				String city_Id = String.valueOf(cityId);
				TC1_LoginStep.globalDatas.setCityId(cityId);
				TC1_LoginStep.globalDatas.setCity_Id(city_Id);
				Assert.assertEquals("Verify City Name Panruti", expCityName, actCityName);
			}
		}
	}

}
