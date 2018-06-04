/*
* Copyright IBM Corp. 1987, 2018
* 
* Licensed to the Apache Software Foundation (ASF) under one
* or more contributor license agreements.  See the NOTICE file
* distributed with this work for additional information
* regarding copyright ownership.  The ASF licenses this file
* to you under the Apache License, Version 2.0 (the
* "License"); you may not use this file except in compliance
* with the License.  You may obtain a copy of the License at
* 
* http://www.apache.org/licenses/LICENSE-2.0
* 
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied.  See the License for the
* specific language governing permissions and limitations
* under the License.
* 
**/

package com.ibm.odm.samples;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.text.MessageFormat;
import java.util.Collection;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import miniloan.Borrower;
import miniloan.Loan;

/**
 * Servlet implementation class Validate
 */
@WebServlet("/validate")
public class Validate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Validate() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ")
				.append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		JsonReader jsonReader = Json.createReader(request.getInputStream());
		JsonObject json = jsonReader.readObject();
		jsonReader.close();
		log(json.toString());


		String result = validate(json);
		response.setContentType("application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.write(result);
		pw.flush();
		pw.close();

	}

	public String validate(JsonObject json) {
		try {
			Loan loan = new Loan(json.getInt("amount"),
								 json.getInt("duration"), 
								 json.getJsonNumber("yearly-interest-rate").doubleValue());

			Borrower borrower = new Borrower(json.getString("name"),
											 json.getInt("credit-score"), 
											 json.getInt("yearly-income"));
						
			String result = "";
			String server = json.getString("server");
			String ruleset = json.getString("ruleset");
			String instanceType = json.getString("instanceType");			
			String url = "https://" + server +"/odm/" +
			 instanceType + "/DecisionService/rest/v1/" + ruleset;
			boolean showTrace = (boolean) json.getBoolean("showTrace");
			result = validateWithJRules(loan, borrower, url, json, showTrace);
			System.out.println(result);
			return result;
		} catch (ClassCastException e) {
			e.printStackTrace();
			return errorToJSON(e);
		}

	}

	// ODM Validation Part
	public String validateWithJRules(Loan loan, Borrower borrower, String endpointURI, JsonObject json, boolean showTrace) {

		JsonObject payload = getJSONPayload(loan, borrower, showTrace);


		String userName = json.getString("user");
		String password = json.getString("password");

		System.out.println("endpointURI = " + endpointURI);
		System.out.println("userName = " + userName);

		JsonObject jsonResponse = null;
		String response = null;
		try {
			response = RESTJSONJavaClient.executeRulesetWithJSONPayload(endpointURI, userName, password, payload.toString());
			JsonReader jsonReader = Json.createReader(new StringReader(response));
			jsonResponse = jsonReader.readObject();
			 jsonReader.close();
		} catch (Exception e) {
			e.printStackTrace();
			return errorToJSON(e);
		}

		// Retrieve execution result from returned loan result
		JsonObject loanResponseJSON = (JsonObject) jsonResponse.get("loan");

		boolean loanApproved = loanResponseJSON.getBoolean("approved");
		JsonObject value = Json.createObjectBuilder()
			.add("approved",loanApproved)
			.add("response", jsonResponse)
			.build();

		return value.toString();
	}

	private JsonObject getJSONPayload(Loan loan, Borrower borrower,
			boolean showTrace) {
		if (showTrace) {
			JsonObject payload = Json
					.createObjectBuilder()
					.add("borrower",
							Json.createObjectBuilder()
									.add("name", borrower.getName())
									.add("creditScore",
											borrower.getCreditScore())
									.add("yearlyIncome",
											borrower.getYearlyIncome()).build())
					.add("loan",
							Json.createObjectBuilder()
									.add("amount", loan.getAmount())
									.add("duration", loan.getDuration())
									.add("yearlyInterestRate",
											loan.getYearlyInterestRate())
									.build())
					.add("__TraceFilter__",
							Json.createObjectBuilder()
									.add("infoRulesFired", true)
									.add("infoTotalRulesFired", true)
									.add("infoTasksExecuted", true)
									.add("infoTotalTasksExecuted", true)
									.add("infoOutputString", true).build())
					.build();
			return payload;
		}
		JsonObject payload = Json
				.createObjectBuilder()
				.add("borrower",
						Json.createObjectBuilder()
								.add("name", borrower.getName())
								.add("creditScore", borrower.getCreditScore())
								.add("yearlyIncome", borrower.getYearlyIncome())
								.build())
				.add("loan",
						Json.createObjectBuilder()
								.add("amount", loan.getAmount())
								.add("duration", loan.getDuration())
								.add("yearlyInterestRate",
										loan.getYearlyInterestRate()).build())
				.build();
		return payload;
	}


	private String errorToJSON(Exception e) {
		JsonObject value = Json.createObjectBuilder()
		.add("error",true)
		.add("text", e.toString())
		.build();
		return value.toString();
	}

	
	protected static String escapeString(String str) {
		return str.replaceAll("\"", "\\\\\"").replaceAll("\n", "");
	}

	protected static String formatTrace(String ruleName, String taskName) {
		String format = Messages.getString("messagefiredinruletask");
		Object[] arguments = { ruleName, taskName };
		return MessageFormat.format(format, arguments);
	}

}
