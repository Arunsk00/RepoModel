package com.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseClass {
	WebDriver driver;
	RequestSpecification reqSpec;
	Response response;

	public void addHeader(String key, String value) {
		reqSpec = RestAssured.given().header(key, value);
	}

	public void addHeaders(Headers headers) {
		reqSpec = RestAssured.given().headers(headers);
	}

	public void addStringBody(String body) {
		reqSpec = reqSpec.body(body);
	}

	public void addFormData(String key, File value) {
		reqSpec = reqSpec.multiPart(key, value);
	}

	public void addObjectBody(Object body) {
		reqSpec = reqSpec.body(body);
	}

	public void basicAuth(String username, String password) {
		reqSpec = reqSpec.auth().preemptive().basic(username, password);
	}

	public void addQueryParam(String key, String value) {
		reqSpec = reqSpec.queryParam(key, value);
	}

	public void addPathParam(String key, String value) {
		reqSpec = reqSpec.pathParam(key, value);
	}

	public Response getRequestType(String type, String endpoint) {
		switch (type) {
		case "GET":
			response = reqSpec.log().all().get(endpoint);
			break;
		case "POST":
			response = reqSpec.log().all().post(endpoint);
			break;
		case "PUT":
			response = reqSpec.log().all().put(endpoint);
			break;
		case "DELETE":
			response = reqSpec.log().all().delete(endpoint);
			break;

		default:
			break;
		}
		return response;
	}

	public int getStatusCode(Response response) {
		int statusCode = response.getStatusCode();
		return statusCode;
	}

	public String getResBodyAsString(Response response) {
		String string = response.asString();
		return string;
	}

	public String getResBodyAsPrettyString(Response response) {
		String prettyString = response.asPrettyString();
		return prettyString;
	}

	public static String getProjectPath() {
		String path = System.getProperty("user.dir");
		return path;
	}

	public static String getPropertyFileValue(String key) throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream(getProjectPath() + "\\src\\test\\resources\\Config\\Config.properties"));
		String value = (String) properties.get(key);
		return value;
	}

}
