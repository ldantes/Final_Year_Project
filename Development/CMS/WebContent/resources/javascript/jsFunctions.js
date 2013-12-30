
function assignmentRadio(elementName) {

	        var radio_check_val = "";
	        var i;
	        for (i = 0; i < document.getElementsByName(elementName).length; i++) {
	            if (document.getElementsByName(elementName)[i].checked) {
	                radio_check_val = document.getElementsByName(elementName)[i].value;
	                document.getElementsByName(elementName).value = radio_check_val;
	                serviceUser.gender = radio_check_val;
	            }

	        }
	       



	    }