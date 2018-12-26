$(document).ready(function(){
	
	
	$.get("GetDivi",function(result){
		debugger;
	//console.log(result)	;
	$.each(result,function( key,value ) {
			// console.log( key + ": " + value );
			 if(key=="Records"){
			  $.each(value,function( i,j ) {
	  		 //console.log( i + ": " + j.div_name );
				  $("#selectDiv").append(
					        $('<option value='+j.div_name+'>'+j.div_name+'</option>')
					    );	 
				  });
			     } 
			  });
	});
 	
	
	$("#selectDiv").on('change',function(){
		 
	     $("#selectDep").empty();
		  $("#selectDep").append(
				  $('<option value="select">Select</option>')					    
			       );
	      var name=this.value;
	      //alert(name);
	            
	       $.get("GetDepNames",function(result){
				 //debugger;
			     //console.log(result)	;
				$.each(result,function( key,value ) {
						 //console.log( key + ": " + value );
						 if(key=="Records"){
						  $.each(value,function( i,dep ) {
				  		 //console.log( i + ": " + j.dep_name );
				  		 if(name==dep.div_name)
				  	      {		 
							  $("#selectDep").append(
								        $('<option value='+dep.name+'>'+dep.name+'</option>')
								    );
						  }

				   });								
					 }							
        		});
	      });                                 				  
	  });
 

	
	
	
	
	
	

	   $("#selectDep").on('change',function(){
		     $("#selectTeam").empty();
		 	
			  $("#selectTeam").append(
				        $('<option value="select">Select</option>')
				    	   
				    );
			  var name=this.value;
			  $.get("GetTeamNames",function(result){
					 debugger;
				console.log(result)	;
					$.each(result,function( key,value ) {
							 //console.log( key + ": " + value );
							 if(key=="Records"){
							  $.each(value,function( i,team ) {
					  		 //console.log( i + ": " + j.dep_name );
					  		 if(name==team.dep_name)
					  	      {		 
								  $("#selectTeam").append(
									        $('<option value='+team.team_name+'>'+team.team_name+'</option>')
									    );
							  }

					   });								
						 }							

				});
		   });                                 				  
			  
			  
		  });

	
	
	
	
	
	
	
	
	
	
	
	
});










