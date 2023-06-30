/*
* Copyright IBM Corp. 1987, 2023
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

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Messages {
	private static final String BUNDLE_NAME = "com.ibm.odm.samples.messages"; //$NON-NLS-1$

	private Messages() {
	}
	
	public static String getString(String key) {
        String text = null;
        try {
            ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME);
            text = bundle.getString(key);
        } catch (Exception e) {
        	e.printStackTrace();
        	Logger logger = Logger.getLogger(Messages.class.getName());
        	logger.log(Level.INFO, "Unable to retrieve key in bundle ' key='" + key  +"'"); 
            ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME, Locale.US);
            text = bundle.getString(key);
        }
        if (text==null) text = key;
        return text;
    }
	

}
