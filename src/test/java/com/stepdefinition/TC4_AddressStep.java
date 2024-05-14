package com.stepdefinition;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.pojo.address.AddUserAddress_Input_POJO;
import com.pojo.address.AddUserAddress_Output_POJO;
import com.pojo.address.DeleteUserAddress_Input_POJO;
import com.pojo.address.DeleteUserAddress_Output_POJO;
import com.pojo.address.GetUserAddress_Output_POJO;
import com.pojo.address.UpdateUserAddress_Input_POJO;
import com.pojo.address.UpdateUserAddress_Output_POJO;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class TC4_AddressStep extends BaseClass {
	Response response;

	@Given("User add header and bearer authentication for accessing addUserAddress endpoint")
	public void userAddHeaderAndBearerAuthenticationForAccessingAddUserAddressEndpoint() {
		List<Header> listHeaders = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + TC1_LoginStep.globalDatas.getLogtoken());
		Header h3 = new Header("Content-Type", "application/json");
		listHeaders.add(h1);
		listHeaders.add(h2);
		listHeaders.add(h3);
		Headers headers = new Headers(listHeaders);
		addHeaders(headers);
	}

	@When("User add request body for add new address {string},{string},{string},{string}, {int} , {int} , {int} ,{string},{string} and {string}")
	public void userAddRequestBodyForAddNewAddressAnd(String first_name, String last_name, String mobile,
			String apartment, int state, int city, int country, String zipcode, String address, String address_type) {
		AddUserAddress_Input_POJO address_Input_POJO = new AddUserAddress_Input_POJO(first_name, last_name, mobile,
				apartment, TC1_LoginStep.globalDatas.getStateId(), TC1_LoginStep.globalDatas.getCityId(), country,
				zipcode, address, address_type);
		addObjectBody(address_Input_POJO);
	}

	@When("User send {string} request for addUserAddress endpoint")
	public void userSendRequestForAddUserAddressEndpoint(String type) {
		response = getRequestType(type, Endpoints.ADDUSERADDRESS);
		int statusCode = getStatusCode(response);
		TC1_LoginStep.globalDatas.setStatusCode(statusCode);
	}

	@Then("User verify the addUserAddress response message matches {string} and save the addressId")
	public void userVerifyTheAddUserAddressResponseMessageMatchesAndSaveTheAddressId(String expAddMessage) {
		AddUserAddress_Output_POJO as = response.as(AddUserAddress_Output_POJO.class);
		String actAddMessage = as.getMessage();
		Assert.assertEquals("Verify Address added Successfully", expAddMessage, actAddMessage);

		int addressId = as.getAddress_id();
		String address_Id = String.valueOf(addressId);
		TC1_LoginStep.globalDatas.setAddress_Id(address_Id);
	}

	@Given("User add header and bearer authentication for accessing updateUserAddress endpoint")
	public void userAddHeaderAndBearerAuthenticationForAccessingUpdateUserAddressEndpoint() {
		List<Header> listHeaders = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + TC1_LoginStep.globalDatas.getLogtoken());
		Header h3 = new Header("Content-Type", "application/json");
		listHeaders.add(h1);
		listHeaders.add(h2);
		listHeaders.add(h3);
		Headers headers = new Headers(listHeaders);
		addHeaders(headers);
	}

	@When("User add request body for update address by passing {string}, {string},{string},{string},{string}, {int} , {int} , {int} ,{string},{string} and {string}")
	public void userAddRequestBodyForUpdateAddressByPassingAnd(String address_Id, String first_name, String last_name,
			String mobile, String apartment, int state, int city, int country, String zipcode, String address,
			String address_type) {
		UpdateUserAddress_Input_POJO updateUserAddress_Input_POJO = new UpdateUserAddress_Input_POJO(
				TC1_LoginStep.globalDatas.getAddress_Id(), first_name, last_name, mobile, apartment,
				TC1_LoginStep.globalDatas.getStateId(), TC1_LoginStep.globalDatas.getCityId(), country, zipcode,
				address, address_type);
		addObjectBody(updateUserAddress_Input_POJO);

	}

	@When("User send {string} request for updateUserAddress endpoint")
	public void userSendRequestForUpdateUserAddressEndpoint(String type) {
		response = getRequestType(type, Endpoints.UPDATEUSERADDRESS);
		int statusCode = getStatusCode(response);
		TC1_LoginStep.globalDatas.setStatusCode(statusCode);
	}

	@Then("User verify the updateUserAddress response message matches {string}")
	public void userVerifyTheUpdateUserAddressResponseMessageMatches(String expUpdateMessage) {
		UpdateUserAddress_Output_POJO as = response.as(UpdateUserAddress_Output_POJO.class);
		String actUpdateMessage = as.getMessage();
		Assert.assertEquals("Verify Address added successfully", expUpdateMessage, actUpdateMessage);
	}

	@Given("User add header and bearer authentication for accessing getUserAddress endpoint")
	public void userAddHeaderAndBearerAuthenticationForAccessingGetUserAddressEndpoint() {
		List<Header> listHeaders = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + TC1_LoginStep.globalDatas.getLogtoken());
		listHeaders.add(h1);
		listHeaders.add(h2);
		Headers headers = new Headers(listHeaders);
		addHeaders(headers);
	}

	@When("User send {string} request for getUserAddress endpoint")
	public void userSendRequestForGetUserAddressEndpoint(String type) {
		response = getRequestType(type, Endpoints.GETUSERADDRESSES);
		int statusCode = getStatusCode(response);
		TC1_LoginStep.globalDatas.setStatusCode(statusCode);
	}

	@Then("User verify the getUserAddress response message matches {string}")
	public void userVerifyTheGetUserAddressResponseMessageMatches(String expGetMessage) {
		GetUserAddress_Output_POJO as = response.as(GetUserAddress_Output_POJO.class);
		String actGetMessage = as.getMessage();
		Assert.assertEquals("Verify OK Message", expGetMessage, actGetMessage);
	}

	@Given("User add header and bearer authentication for accessing deleteUserAddress endpoint")
	public void userAddHeaderAndBearerAuthenticationForAccessingDeleteUserAddressEndpoint() {
		List<Header> listHeaders = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + TC1_LoginStep.globalDatas.getLogtoken());
		Header h3 = new Header("Content-Type", "application/json");
		listHeaders.add(h1);
		listHeaders.add(h2);
		listHeaders.add(h3);
		Headers headers = new Headers(listHeaders);
		addHeaders(headers);
	}

	@When("User add request body for delete address by addressId")
	public void userAddRequestBodyForDeleteAddressByAddressId() {
		DeleteUserAddress_Input_POJO deleteUserAddress_Input_POJO = new DeleteUserAddress_Input_POJO(
				TC1_LoginStep.globalDatas.getAddress_Id());
		addObjectBody(deleteUserAddress_Input_POJO);
	}

	@When("User send {string} request for deleteAddress endpoint")
	public void userSendRequestForDeleteAddressEndpoint(String type) {
		response = getRequestType(type, Endpoints.DELETEUSERADDRESS);
		int statusCode = getStatusCode(response);
		TC1_LoginStep.globalDatas.setStatusCode(statusCode);
	}

	@Then("User verify the deleteUserAddress response message matches {string}")
	public void userVerifyTheDeleteUserAddressResponseMessageMatches(String expDeleteMessage) {
		DeleteUserAddress_Output_POJO as = response.as(DeleteUserAddress_Output_POJO.class);
		String actDeleteMessage = as.getMessage();
		Assert.assertEquals("Verify Address Deleted Successfully", expDeleteMessage, actDeleteMessage);
	}

}
