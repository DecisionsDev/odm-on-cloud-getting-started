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

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.ssl.SSLContexts;

public class RESTJSONJavaClient {

	/**
	 * Get a closeable HTTP client
	 * 
	 * @return closeable HTTP client
	 * @throws Exception
	 */
	public static synchronized CloseableHttpClient getClient() throws Exception {
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
		  SSLContexts.createDefault(),
		  new String[] { "TLSv1.2" },
		  null,
		  SSLConnectionSocketFactory.getDefaultHostnameVerifier());
	
		CloseableHttpClient client = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		return client;
	}

	/*
	 * Return credentials for user id and password
	 */
	private static String getBase64Credentials(String userId,String userPassword)
	{
		String authstring = userId + ":" + userPassword;
		String encoded = Base64.encodeBase64String(authstring.getBytes());
		return encoded;
	}

	/**
	 * Execute a ruleset with the supplied payload to a specified endpoint URI using the 
	 * supplied user id and password for authentication.
	 * 
	 * @param endpointURI
	 * @param userId
	 * @param userPassword
	 * @param payload
	 * @return ruleset execution response as String
	 * @throws Exception
	 */
	public static String executeRulesetWithJSONPayload(String endpointURI, String userId,
			String userPassword, String payload) throws Exception {
		String result = "";
		String errorMessage = "An error occurred when invoking the decision service at "
				+ endpointURI
				+ ":\n";
		HttpPost httpPost = null;

		CloseableHttpClient client = getClient();
		try {
			try {
				httpPost = new HttpPost(endpointURI);
			} catch (Exception e) {
				throw new RuntimeException(errorMessage 
						+ e.getClass().getName() + ": " + e.getMessage());
			}
			// Authorization header required when accessing from internet
			String encodedValue = getBase64Credentials(userId, userPassword);
			String authorization = "Basic " + encodedValue;
			httpPost.addHeader("Authorization", authorization);

			// Add content type to header
			httpPost.addHeader("Content-Type", "application/json");

			CloseableHttpResponse response = null;

			try {
				// Set entity to payload for execution
				httpPost.setEntity(new StringEntity(payload));
				// Execute Post method using the HTTP client and retrieve response
				try {
					response = client.execute(httpPost);
				} catch (Exception e) {
					throw new RuntimeException(errorMessage
							+ e.getClass().getName() + ": " + e.getMessage());
				}

				if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
					int statusCode = response.getStatusLine().getStatusCode();
					System.err.println("Status Code: " + statusCode);
					System.err.println("Status Line: " + response.getStatusLine());
					String responseEntity = EntityUtils.toString(response.getEntity());
					System.err.println("Response Entity: " + responseEntity);
					if (statusCode == 302) {
						throw new RuntimeException(errorMessage
								+ "Unauthorized. Check your user ID and password.");
					}
					else {
						throw new RuntimeException(errorMessage
								+ response.getStatusLine() + ": " + responseEntity);
					}
				} else {
					result = EntityUtils.toString(response.getEntity());
					System.out.println("Result: " + result);
				}
			} finally {
				if (response != null) {
					response.close();
				}
			}
		} finally {
			client.close();
		}

		return result;
	}

}

