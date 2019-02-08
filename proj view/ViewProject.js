$(document).ready(function() {
	debugger;
	$('#projectList').jtable({
		title : 'Project Details',
		actions : {
			listAction : 'ViewProject?action=list',
			createAction : 'AddProject.html',
			updateAction : 'ViewProject?action=update',
			deleteAction : 'ViewProject?action=delete'
		},
	
		fields : {
			

			P_id : {
				title : 'Project Id',
				width : '10%',
				key : true,
				list : true,
				edit : false,
				create : true
			},
			P_name : {
				title : 'Project Name',
				width : '20%',
				edit : true
				
			},
			P_des: {
				title : 'Project detail',
				width : '30%',
				edit : true
			},
			Doj: {
				title : 'Project start date',
				width : '10%',
				edit : false
			},
			AggDate : {
				title : 'End date',
				width : '10%',
				edit : true
			},
			ManagerDetails: {
				title : 'Manager Details',
				width : '25%',
				edit : true
			},
			Email : {
				title : 'Contact EMail ID',
				width : '25%',
				edit : true
			},
			NumEmp: {
				title : 'Number of Employees',
				width : '10%',
				edit : true
			}
		}
	});
	$('#projectList').jtable('load');
}); 
