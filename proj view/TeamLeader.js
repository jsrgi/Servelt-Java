/**
 * 
 */

$(document)
		.ready(
				function() {
					var len = 0;
					$("#numEmp").click(
							function() {
								for (var i = 1; i < 26; i++) {
									$("#numEmp").append(
											$('<option value=' + i + '>' + i
													+ '</option>'));
								}
							});

					$('#numEmp')
							.change(
									function() {

										len = this.value;
										// alert(len);
										$('#c').empty();

										for (var i = 1; i <= len; i++) {
											$(
													'<br><br><div class="form-group"><label  class="col-sm-2">Employess</label><div class="col-sm-4" id="'
															+ i
															+ '"></div><div class="col-sm-4" id="'
															+ (i + len)
															+ '"></div></div> ')
													.appendTo('#c');

										}

										for (var j = 1; j <= len; j++) {
											var select = $(" <input type='text' name='empname"
													+ j
													+ "' id='empname"
													+ j
													+ "' placeholder='Employee' class='form-control' required='required'/>");
											var role = $(" <input type='text' name='emprole"
													+ j
													+ "' id='emprole"
													+ j
													+ "' placeholder='Department;' class='form-control' required='required'/><br>");

											$('#' + j + '').html(select);
											$('#' + (j + len) + '').html(role);

										}
										$
												.get(
														"GetTeamEmp",
														function(result) {
															// debugger;
															// console.log(result)
															// ;
															$
																	.each(
																			result,
																			function(
																					key,
																					value) {
																				// console.log(
																				// key
																				// + ":
																				// " +
																				// value
																				// );
																				if (key == "Records") {
																					var n = 1;
																					$
																							.each(
																									value,
																									function(
																											i,
																											j) {
																										// console.log(
																										// i +
																										// ": "
																										// +
																										// j.div_name
																										// );

																										if (j.emp_role == "employee") {
																											// alert(j.emp_role);
																											// alert(n);

																											$(
																													'#empname'
																															+ n
																															+ '')
																													.val(
																															j.emp_fname);
																											$(
																													'#emprole'
																															+ n
																															+ '')
																													.val(
																															j.emp_team);

																											n++;
																										}
																									});
																				}
																			});
														});

										/*
										 * for(var k=1;k<=len;k++) {
										 * 
										 * $('#empid'+k+'').val(k); }
										 */

									});
					
					sessionStorage.setItem("project_id", session.getAttribute("project_id"));
					sessionStorage.setItem("project_name", session.getAttribute("project_name"));
					
				});
