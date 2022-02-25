/*
* Copyright IBM Corp. 1987, 2022
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

$(document).ready(
		function() {
			hide_panels();

			// process the form
			$('form').submit(
					function(event) {
						// stop the form from submitting the normal way and
						// refreshing the page
						event.preventDefault();
						hide_panels();
						if ($("#trace").is(':checked')) {
							showTrace = true
						} else {
							showTrace = false
						}
						var formData = {
							'name' : $('input[name=name]').val(),
							'server' : $('input[name=server]').val(),
							'ruleset' : $('input[name=ruleset]').val(),
							'user' : $('input[name=user]').val(),
							'password' : $('input[name=password]').val(),
							'instanceType' : $('input[name=instanceType]:checked', '#instanceChoice').val(),
							'yearly-income' : parseInt($('input[name=yearly-income]').val()),
							'credit-score' : parseInt($('input[name=credit-score]').val()),
							'amount' : parseInt($('input[name=amount]').val()),
							'duration' : parseInt($('input[name=duration]').val()),
							'yearly-interest-rate' : parseFloat($('input[name=yearly-interest-rate]').val()),
							'showTrace' : showTrace
						};

						// process the form
						var request = $.ajax({
							type : 'POST',
							url : 'validate',
							data : JSON.stringify(formData),
							dataType : 'json',
						});

						request.done(function(data) {
							console.log(data);
							$('#info-panel').css('visibility', 'visible');
							if (!data.error) {
								if (data.approved == true) {
									$('#info-header').attr('class','card-header bg-success');
									$('#info-header').text('Approved request')
									$('#info-text').html(format_json(data.response))

								} else if (data.approved == false) {
									$('#info-header').attr('class','card-header bg-warning');
									$('#info-header').text('Rejected request')
									$('#info-text').html(format_json(data.response))
								}
							} else {
								if (data.error == true) {
									$('#info-text').text(data.text)

								} else {
									$('#info-text').text("An unknown error has occured")
								}

								$('#info-header').attr('class','card-header bg-danger');
								$('#info-header').text('Error')
							}
						});

						request.fail(function(jqXHR, textStatus) {
							console.log("Request failed: " + textStatus);
							$('#info-neader').attr('class','card-header bg-error');
							$('#info-header').text('Error')
							$('#info-text').text(textStatus)
						});

					});

		});

function format_lines(messages) {
	var t = messages.map(function(l) {
		return l.line;
	});
	return t.join("</br>");
}

function format_json(obj) {
	var str = JSON.stringify(obj, null, 4 );
	return  "<pre><code>" + str + "<\pre><\code>";
}

function hide_panels() {
	$('#info-panel').css('visibility', 'hidden');
}