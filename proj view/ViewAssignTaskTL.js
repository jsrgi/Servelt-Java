$(document).ready(function() {
	debugger;
	$('#ViewAssignTaskList').jtable({
		title : 'Assigned Task Details',
		actions : {
			listAction : 'ViewAssignTaskTL?action=list',
			updateAction : 'ViewAssignTaskTL?action=update',
			deleteAction : 'ViewAssignTaskTL?action=delete'
		},
	
		fields : {			

			t_id : {
				title : 'Task Id',
				width : '10%',
				key : true,
				list : true,
				edit : false,
				create : true
			},
			t_name : {
				title : 'Task Name',
				width : '20%',
				edit : true
				
			},
			t_des: {
				title : 'Task detail',
				width : '30%',
				edit : true
			},
			t_des: {
				title : 'Task detail',
				width : '30%',
				edit : true
			},
			p_name: {
				title : 'Project Name',
				width : '30%',
				edit : false
			},
			date: {
				title : 'Start date',
				width : '10%',
				edit : false
			},
			ddate : {
				title : 'Due date',
				width : '10%',
				edit : true
			},
			assignee: {
				title : 'Assignee Details',
				width : '25%',
				edit : true
			},
			create : {
				title : 'Created By',
				width : '25%',
				edit : false
			}
		}
	});
	$('#ViewAssignTaskList').jtable('load');
}); 
