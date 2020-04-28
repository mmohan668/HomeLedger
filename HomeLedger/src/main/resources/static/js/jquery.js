$(document).ready(function() {

	$('#dtBasicExample').DataTable();
	$('#dtBasicExample1').DataTable();
	$('.dataTables_length').addClass('bs-select');
	$("#particularType").change(function() {
		getBillAccounts();
	});
	$("#particularDescription").change(function() {
		getBillAmount();
	});
	$("#pdate").change(function() {
		var date = $("#pdate").val();
		var new_date = date.substring(5,7)+"/"+date.substring(8)+"/"+date.substring(0,4);
		$("#particularDate").val(new_date);
		$(".alert-success,.alert-danger").hide();
	});
	$("img").mouseenter(function() {
		$(this).width("20px");
	});
	$("img").mouseleave(function() {
		$(this).width("15px");
	});
});

function getBillAccounts(){
	var billType = $("#particularType").val();
	var data = {billType : billType};
	$.ajax({
		     url: 'getBillAccounts',
		    type: 'post',
		   async: false,
		    data: data,
		dataType: 'json',
		 success: function(data){
			$("#particularDescription").empty();
			$("#particularDescription").append("<option value=''>---Select---</option>");
			$.each(data, function(d, bill) {
				$("#particularDescription").append('<option value="'+bill.billAccount+'">'+bill.billAccount+'</option>');
			});	
		},
		error: function(){
			alert("OOPS! Some thing went wrong");
		}
	});
}
function getBillAmount(){
	var billDesc = $("#particularDescription").val();
	var data = {billDesc : billDesc};
	$.ajax({
		     url: 'getBillAmount',
		    type: 'post',
		   async: false,
		    data: data,
		dataType: 'json',
		 success: function(data){
			 $('#particularAmount').val(data);	
		},
		error: function(){
			alert("OOPS! Some thing went wrong");
		}
	});
}
function validatePassword() {
    var validator = $("#registerForm").validate({
        rules: {
        	mobileNumber : {
        		required : true,
    			number : true,
                minlength : 10,
                maxlength : 10
        	},
        	password : {
        		required : true,
        		minlength : 8,
        		maxlength : 16,
        	},
            confirmPassword : {
                equalTo : "#pwd"
            }
        },
        messages: {
        	firstName : "Enter First Name",
        	emailId : "Enter Valid Email Address",
        	mobileNumber : "Enter Valid Mobile Number",
        	password : {
        		pwcheck: "Password is not strong enough",
        	      checklower: "Need atleast 1 lowercase alphabet",
        	      checkupper: "Need atleast 1 uppercase alphabet",
        	      checkdigit: "Need atleast 1 digit"
        	},
        	confirmPassword: " Enter Confirm Password Same as Password"
        }
    });
}