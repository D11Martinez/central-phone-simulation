$(document).ready(function(){
	$('#btnSubtmitLogin').hide();
	$('#btnNextLogin').show();
	$('#btnReturnLogin').hide();
	$('#btnRecuperar').show();
	$('#loginForm #password').hide();
});

function returnLogin(){
	$('#btnSubtmitLogin').hide();
	$('#btnNextLogin').show();
	$('#btnReturnLogin').hide();
	$('#btnRecuperar').show();
	$('#loginForm #password').hide();	
	
	$('#loginForm #username').prop( "readonly", false );
}

function checkUsername() {

	var username = $('#loginForm #username').val();
	
	$.ajax({
	  type:"GET",
	  contentType : "application/json",
	  url:"/login/username",
	  cache:false,
	  timeout : 6000,
	  data:{
		  username: $('#loginForm #username').val()
	  	},
	  dataType:'json',	  
	  success:function (response) {
		  var error = 0;
		  console.log(response);
		  if(response.result == "1"){
			  console.log(" Usuario encontrado: \n"+response.result);
			  toastr.success(" Usuario encontrado ");
			  
			  $('#loginForm #username').prop( "readonly", true );
			  
			  $('#btnSubtmitLogin').show();
			  $('#btnNextLogin').hide();
			  $('#btnReturnLogin').show();
			  $('#btnRecuperar').show();
			  $('#loginForm #password').show();			  
		  }
		  if(response.result == "2"){
			  console.log("Usuario bloqueado: \n"+response.result);
			  toastr.error(" El usuario se encuentra bloqueado. ");
		  }
	  },
	  error:function () {
		  toastr.error("El usuario no se encuentra registrado.");
	  }
	});
}