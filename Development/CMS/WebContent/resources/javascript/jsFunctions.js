function assignmentRadio(elementName)
{

	 var radio_check_val = "";
	 var i;
	 for (i = 0; i < document.getElementsByName(elementName).length; i++) {
	     if (document.getElementsByName(elementName)[i].checked) {
	          radio_check_val = document.getElementsByName(elementName)[i].value;
	          document.getElementsByName(elementName).value = radio_check_val;
	      }
       }
}

function assignCheckBox(checkbox)
{
	if (checkbox.checked) 
	{
		checkbox.value = 'Y';
	}
	else
	{
		checkbox.value = 'N';
    }
}


function show_hide(id) {
    var e = document.getElementById(id);
    if(e.style.display == 'block')
       e.style.display = 'none';
    else
       e.style.display = 'block';
 }