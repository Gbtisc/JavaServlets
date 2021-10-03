function cleanForm() {
	document.getElementById("firstName").value = "";
	document.getElementById("lastName").value = "";
	document.getElementById("userName").value = "";
	document.getElementById("userPassword").value = "";
	document.getElementById("userEmail").value = "";
	var radioButtons = document.getElementsByName("userStatus");
	
	for (var i = 0; i < radioButtons.length; i++) {
		var radioButton = radioButtons[i];
		radioButton.checked = false;
	}
}

function validateEmail() {
	var userEmail = document.getElementById("userEmail").value;
	
	if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(userEmail)) {
    	return (true);
	} else if (userEmail == "") {
		return (true);
	}
	swal('ERROR','You have entered an invalid email address','error');
    return (false);
}

function getValues() {
	var userId = document.getElementById("userId").value;
	var firstName = document.getElementById("firstName").value;
	var firstName = document.getElementById("firstName").value;
	var lastName = document.getElementById("lastName").value;
	var userName = document.getElementById("userName").value;
	var userPassword = document.getElementById("userPassword").value;
	var userEmail = document.getElementById("userEmail").value;
	var radioButtons = document.getElementsByName("userStatus");
	var active = radioButtons[0];
	var inactive = radioButtons[1];
	var userStatus;
	
	if (active.checked == false) {
		if (inactive.checked == false) {
			userStatus = "";
		} else {
			userStatus = "0";
		}
	} else {
		userStatus = "1";
	}
	
	var values = [userId, firstName, lastName, userName, userPassword, 
	userEmail, userStatus];
	
	return values;
}

function executeUpdate() {
	var values = getValues();
	var response;
	
	if (!values[1] != "" || !values[2] != "" || !values[3] != "" || 
	!values[4] != "" || !values[5] != "" || !values[6] != "") {
		swal('ERROR','You need to fill all the fields','error');
	} else {
		if (values[0] != "") {
			var url = '/registerForm/getUser?userId='+values[0]+'&firstName='+
			values[1]+'&lastName='+values[2]+'&userName='+values[3]+
			'&userPassword='+values[4]+'&userEmail='+values[5]+'&userStatus='+
			values[6]+'&result='+'updateUser';
			
			var xmlRequest = new XMLHttpRequest();
			xmlRequest.open('GET', url, true);
			
			xmlRequest.onreadystatechange = function() {
				if(xmlRequest.readyState == 4 && xmlRequest.status == 200) {
					response = xmlRequest.responseText;
					responseAction(response);
				} else {
					swal('ERROR','There was an error with the server','error');
				}
			}
			xmlRequest.send('');
			cleanForm();
			document.getElementById("userId").value = "";
		} else {
			var url = '/registerForm/getUser?firstName='+values[1]+'&lastName='+
			values[2]+'&userName='+values[3]+'&userPassword='+values[4]+
			'&userEmail='+values[5]+'&userStatus='+values[6]+'&result='+
			'createUser';
			
			var xmlRequest = new XMLHttpRequest();
			xmlRequest.open('GET', url, true);
			
			xmlRequest.onreadystatechange = function() {
				if(xmlRequest.readyState == 4 && xmlRequest.status == 200) {
					response = xmlRequest.responseText;
					responseAction(response);
				} else {
					swal('ERROR','There was an error with the server','error');
				}
			}
			xmlRequest.send('');
			cleanForm();
		}
	}
}

function responseAction(response) {
	switch (response) {
		case "CR":
			swal("SUCCESS", "The user was successfully created!", "success").then((reloadForm) => {
				location.reload();
			});
			break;
		case "UP":
			swal("SUCCESS", "The user was successfully updated!", "success").then((reloadForm) => {
				readUsers();
			});
			break;
		case "F":
			swal('ERROR','The user name or the email already exists','error');
			break;
		case "DB":
			swal('ERROR','There was an error with the database','error');
			break;
		case "DR":
			swal('ERROR','There was an error with the drivers','error');
			break;
		default:
			swal('ERROR','There was an invalid response action','error');
			break;
	}
}

function readUsers() {
	var filter = document.getElementById("filter").value;
	var xmlRequest = new XMLHttpRequest();
	var url = '/registerForm/getUser?result=';
	
	switch (filter) {
		case "select":
			var table = document.getElementById("content");
			cleanTable(table);
			break;
		case "all":
			xmlRequest.open('GET', url+'readAllUsers', true);
			xmlRequest.send('');
			break;
		case "active":
			xmlRequest.open('GET', url+'readActiveUsers', true);
			xmlRequest.send('');
			break;
		case "inactive":
			xmlRequest.open('GET', url+'readInactiveUsers', true);
			xmlRequest.send('');
			break;
		default:
			swal('ERROR','There was an invalid select action','error');
			break;
	}
	
	xmlRequest.onreadystatechange = function() {
		if(xmlRequest.readyState == XMLHttpRequest.DONE && xmlRequest.status == 200) {
			var response = xmlRequest.responseText;
			
			if(response == "") {
				swal('ERROR','The response is getting nothing','error');
			} else {
				var jsonString = JSON.parse(response);
				createTable(jsonString);
			}
		}
	}
}

function createTable(list) {
	var table = document.getElementById("content");
	
	cleanTable(table);
	
	for(var i in list) {
		var row = document.createElement("tr");
		row.setAttribute("id", "contentRow" + i);
		
		var text;
		var column;
		var userStatus;
		var button;
		var input;
		
		column = document.createElement("td");
		text = document.createTextNode(list[i].firstName);
		column.appendChild(text);
		row.appendChild(column);
		
		column = document.createElement("td");
		text = document.createTextNode(list[i].lastName);
		column.appendChild(text);
		row.appendChild(column);
		
		column = document.createElement("td");
		text = document.createTextNode(list[i].userName);
		column.appendChild(text);
		row.appendChild(column);
		
		column = document.createElement("td");
		text = document.createTextNode(list[i].userEmail);
		column.appendChild(text);
		row.appendChild(column);
		
		column = document.createElement("td");
		
		if(list[i].userStatus == 1) {
			userStatus = 'active';
		} else {
			userStatus = 'inactive';
		}
		
		text = document.createTextNode(userStatus);
		column.appendChild(text);
		row.appendChild(column);
		
		column = document.createElement("td");
		button = document.createElement('input');
		button.setAttribute('type', 'button');
		button.setAttribute('name', parseInt(i, 10)+1);
		button.setAttribute('value', 'UPDATE');
		button.setAttribute('onclick', 'setValues(this.name)');
		column.appendChild(button);
		
		button = document.createElement('input');
		button.setAttribute('type', 'button');
		button.setAttribute('name', list[i].userId);
		button.setAttribute('value', 'DELETE');
		button.setAttribute('onclick', 'deleteUser(this.name)');
		column.appendChild(button);
		
		row.appendChild(column);
		table.appendChild(row);
		
		input = document.createElement("input");
		input.setAttribute('type', 'hidden');
		input.setAttribute('name', 'userId' + i);
		input.setAttribute('id', 'userId' + i);
		input.setAttribute('value', list[i].userId);
		table.appendChild(input);
	}
}

function cleanTable(table) {
	if(table.rows.length > 1) {
		for(var i = 0; table.rows.length - 1; i++) {
			var element = document.getElementById("contentRow"+i);
			element.remove();
		}
	}
}

function setValues(index) {
	var intIndex = parseInt(index, 10)-1;
	
	document.getElementById("firstName").value = document.getElementById("content").rows[index].cells[0].innerHTML;
	document.getElementById("lastName").value = document.getElementById("content").rows[index].cells[1].innerHTML;
	document.getElementById("userName").value = document.getElementById("content").rows[index].cells[2].innerHTML;
	document.getElementById("userEmail").value = document.getElementById("content").rows[index].cells[3].innerHTML;
	document.getElementById(document.getElementById("content").rows[index].cells[4].innerHTML).checked = true;
	document.getElementById("userId").value = document.getElementById("userId" + intIndex).value;
}

function deleteUser(id) {
	swal({
		title: "Are you sure?",
		text: "Once deleted, you will not be able to recover this user!",
		icon: "warning",
		buttons: true,
		dangerMode: true,
	}).then((willDelete) => {
		if (willDelete) {
			var xmlRequest = new XMLHttpRequest();
			var url = '/registerForm/getUser?result=deleteUser&userId='+id;
			xmlRequest.open('GET', url, true);
			
			xmlRequest.onreadystatechange = function() {
				if(xmlRequest.readyState == XMLHttpRequest.DONE && xmlRequest.status == 200) {
					console.log("User deleted");
				}
			}
			xmlRequest.send('');
			
		    swal("SUCCESS", "The user was successfully deleted!", "success").then((reloadForm) => {
				readUsers();
			});
		} else {
	    	swal("Operation aborted!");
		}
	});
}