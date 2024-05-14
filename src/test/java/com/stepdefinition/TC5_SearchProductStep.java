package com.stepdefinition;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.pojo.product.SearchProduct_Input_POJO;
import com.pojo.product.SearchProduct_Output_POJO;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class TC5_SearchProductStep extends BaseClass{
	Response response;

	@Given("User add header for searchProduct")
	public void userAddHeaderForSearchProduct() {
		List<Header> listHeaders = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");
		listHeaders.add(h1);
		listHeaders.add(h2);
		Headers headers = new Headers(listHeaders);
		addHeaders(headers);
	}

	@When("User add request body to get productList by passing {string}")
	public void userAddRequestBodyToGetProductListByPassing(String product) {
		SearchProduct_Input_POJO searchProduct_Input_POJO = new SearchProduct_Input_POJO(product);
		addObjectBody(searchProduct_Input_POJO);
	}

	@When("User send {string} request for searchProduct endpoint")
	public void userSendRequestForSearchProductEndpoint(String type) {
		response = getRequestType(type, Endpoints.SEARCHPRODUCT);
		int statusCode = getStatusCode(response);
		TC1_LoginStep.globalDatas.setStatusCode(statusCode);
	}

	@Then("User verify the searchProduct response message matches {string}")
	public void userVerifyTheSearchProductResponseMessageMatches(String expSearchMessage) {
		SearchProduct_Output_POJO as = response.as(SearchProduct_Output_POJO.class);
		String actSearchMessage = as.getMessage();
		Assert.assertEquals("Verify OK Message", expSearchMessage, actSearchMessage);
	}

}
