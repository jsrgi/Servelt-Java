package com.itManage.DaoConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.itManage.controller.Sendmail;
import com.itManage.model.Count;
import com.itManage.model.Department;
import com.itManage.model.Divitions;
import com.itManage.model.Employee;
import com.itManage.model.Histroy;
import com.itManage.model.Project;
import com.itManage.model.Task;
import com.itManage.model.Team;
import com.itManage.model.User;
import com.itMange.db.DbConnection;

public class ServiceDao {

	public List<Divitions> getAll() throws Exception {
		List<Divitions> divList = new ArrayList<Divitions>();
		try {
			String query = "SELECT * FROM division WHERE div_STATUS=1 ORDER BY div_id";
			Connection conn = DbConnection.getSqlConnection();

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				Divitions divObj = new Divitions();
				divObj.setDiv_name(rs.getString("div_name"));
				divObj.setDiv_id(rs.getInt("div_id"));

				divList.add(divObj);
			}

			rs.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return divList;
	}

	public List<Department> findAll() throws Exception {
		List<Department> depList = new ArrayList<Department>();
		try {
			String query = "SELECT * FROM department WHERE dep_STATUS=1";
			Connection conn = DbConnection.getSqlConnection();

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				Department depObj = new Department();
				depObj.setName(rs.getString("dep_name"));
				// depObj.setDep_id(rs.getInt("dep_id"));
				depObj.setDiv_name(rs.getString("div_name"));
				depList.add(depObj);
			}

			rs.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return depList;
	}

	public List<Team> listAll() throws Exception {
		List<Team> teamList = new ArrayList<Team>();
		try {
			String query = "SELECT * FROM team WHERE t_STATUS=1";
			Connection conn = DbConnection.getSqlConnection();

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				Team teamObj = new Team();
				teamObj.setTeam_name(rs.getString("t_name"));
				teamObj.setDep_name(rs.getString("dep_name"));
				teamList.add(teamObj);
			}
			rs.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return teamList;
	}

	public static List<Employee> empAll() throws Exception {
		List<Employee> empList = new ArrayList<Employee>();
		try {
			String query = "SELECT * FROM emp WHERE emp_STATUS=1";
			Connection conn = DbConnection.getSqlConnection();

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				Employee emp = new Employee();
				emp.setEmp_id(rs.getInt("emp_id"));
				emp.setEmp_idNum(rs.getString("emp_idNum"));
				emp.setEmp_fname(rs.getString("emp_fname"));
				emp.setEmp_lname(rs.getString("emp_lname"));
				emp.setEmp_gender(rs.getString("emp_gender"));
				emp.setDob(rs.getString("emp_dob"));
				emp.setDoj(rs.getString("emp_doj"));
				emp.setEmp_email(rs.getString("emp_email"));
				emp.setEmp_role(rs.getString("emp_desiganation"));
				emp.setEmp_division(rs.getString("emp_division"));
				emp.setEmp_department(rs.getString("emp_department"));
				emp.setEmp_team(rs.getString("emp_team"));
				emp.setEmp_address(rs.getString("address"));
				emp.setEmp_uname(rs.getString("emp_uname"));
				emp.setEmp_pass(rs.getString("emp_pass"));
				empList.add(emp);
			}

			rs.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return empList;

	}
	public static List<Project> projectAll() throws Exception {
		List<Project> proList = new ArrayList<Project>();
		try {
			String query = "SELECT * FROM project1 WHERE p_status=1";
			Connection conn = DbConnection.getSqlConnection();

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				Project pro = new Project();
				pro.setP_id(rs.getInt("pro_id"));
				pro.setP_name(rs.getString("p_name"));
				pro.setP_des(rs.getString("p_des"));
				pro.setDoj(rs.getString("doj"));
				pro.setAggDate(rs.getString("aggDate"));
				pro.setManagerDetails(rs.getString("managerDetails"));
				pro.setEmail(rs.getString("email"));
				proList.add(pro);
			}

			rs.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return proList;

	}

	public void addUser(String emp_uname, String emp_pass, String emp_role) {

		Connection conn = null;

		try {
			conn = DbConnection.getSqlConnection();

			String query = "select emp_id from emp where emp_uname='" + emp_uname + "' ";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int empId = 0;
			while (rs.next()) {
				empId = rs.getInt("emp_id");

			}
			rs.close();
			System.out.println(empId);

			PreparedStatement pst1 = conn.prepareStatement(
					"insert into user_account(user_name,user_password,user_role,emp_id) values(?,?,?,?)");

			pst1.setString(1, emp_uname);
			pst1.setString(2, emp_pass);
			pst1.setString(3, emp_role);
			pst1.setInt(4, empId);
			pst1.execute();
			pst1.close();

			conn.close();

		} catch (Exception e) {
			System.out.println(e);// TODO: handle exception
		}

	}

	public void addEmp(Employee emp) {
		Connection conn = null;

		try {
			// emp=new Employee();
			conn = DbConnection.getSqlConnection();
			PreparedStatement pst = conn.prepareStatement(
					"insert into emp(emp_fname,emp_lname,emp_gender,emp_email,emp_desiganation,emp_division,emp_department,emp_team,address,emp_dob,emp_doj,emp_idNum,emp_uname,emp_pass) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			pst.setString(1, emp.getEmp_fname());
			pst.setString(2, emp.getEmp_lname());
			pst.setString(3, emp.getEmp_gender());
			pst.setString(4, emp.getEmp_email());
			pst.setString(5, emp.getEmp_role());
			pst.setString(6, emp.getEmp_division());
			pst.setString(7, emp.getEmp_department());
			pst.setString(8, emp.getEmp_team());
			pst.setString(9, emp.getEmp_address());
			pst.setString(10, emp.getDob());
			pst.setString(11, emp.getDoj());
			pst.setString(12, emp.getEmp_idNum());
			pst.setString(13, emp.getEmp_uname());
			pst.setString(14, emp.getEmp_pass());

			pst.execute();
			pst.close();
			conn.close();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

	}

	public void updateEmp(Employee emp) {
		Connection conn = null;
		try {

			conn = DbConnection.getSqlConnection();
			PreparedStatement pst = conn.prepareStatement("update emp set emp_fname=?,emp_lname=?,"
					+ "emp_gender=?,emp_email=?,emp_desiganation=?,emp_division=?,emp_department=?,"
					+ "emp_team=?,address=?,emp_dob=?,emp_doj=?,emp_uname=?,emp_pass=? 	where emp_id=?");

			pst.setString(1, emp.getEmp_fname());
			pst.setString(2, emp.getEmp_lname());
			pst.setString(3, emp.getEmp_gender());
			pst.setString(4, emp.getEmp_email());
			pst.setString(5, emp.getEmp_role());
			pst.setString(6, emp.getEmp_division());
			pst.setString(7, emp.getEmp_department());
			pst.setString(8, emp.getEmp_team());
			pst.setString(9, emp.getEmp_address());
			pst.setString(10, emp.getDob());
			pst.setString(11, emp.getDoj());
			pst.setString(12, emp.getEmp_uname());
			pst.setString(13, emp.getEmp_pass());
			pst.setInt(14, emp.getEmp_id());

			pst.execute();
			pst.close();
			conn.close();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);

		}

	}
	public void updateProject(Project pro) {
		Connection conn = null;
		try {

			conn = DbConnection.getSqlConnection();
			PreparedStatement pst = conn.prepareStatement("update project1 set p_name=?,p_des=?,"
					+ "doj=?,aggDate=?,managerDetails=?,email=?,email=?,numEmp=?	where pro_id=?");

			pst.setString(1, pro.getP_name());
			pst.setString(2, pro.getP_des());
			pst.setString(3, pro.getDoj() );
			pst.setString(4, pro.getAggDate());
			pst.setString(5, pro.getManagerDetails());
			pst.setString(6, pro.getEmail());
			pst.setInt(7, pro.getNumEmp());			
			pst.setInt(8, pro.getP_id());

			pst.execute();
			pst.close();
			conn.close();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);

		}

	}

	public void deleteEmp(int id) {

		Connection conn = null;
		Employee emp = null;
		try {
			System.out.println(id);
			conn = DbConnection.getSqlConnection();
			PreparedStatement pst = conn.prepareStatement("update emp set emp_status=? where emp_id=?");
			pst.setInt(1, 0);
			pst.setInt(2, id);

			pst.execute();
			pst.close();

			PreparedStatement pst1 = conn.prepareStatement("update user_account  set user_status=? where emp_id=?");
			pst1.setInt(1, 0);

			pst1.setInt(2, id);

			pst1.execute();
			pst1.close();

			conn.close();

		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}

	}
	public void deleteProject(int id) {

		Connection conn = null;

		try {
			System.out.println(id);
			conn = DbConnection.getSqlConnection();
			PreparedStatement pst = conn.prepareStatement("update project1 set p_status=? where pro_id=?");
			pst.setInt(1, 0);
			pst.setInt(2, id);

			pst.execute();
			pst.close();
			conn.close();

		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}

	}

	public List<Project> proList() throws Exception {
		List<Project> proList = new ArrayList<Project>();
		try {
			String query = "SELECT * FROM project WHERE p_STATUS=1";
			Connection conn = DbConnection.getSqlConnection();

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				Project pro = new Project();

				pro.setEmp_id(rs.getInt("createEmp_id"));
				pro.setP_id(rs.getInt("pro_id"));
				pro.setP_name(rs.getString("p_name"));
				pro.setP_des(rs.getString("p_des"));
				proList.add(pro);

			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return proList;

	}

	public int pro_id(String name) {
		int id = 0;
		Connection conn = null;
		try {

			String query = "SELECT * FROM project WHERE p_STATUS=1";
			conn = DbConnection.getSqlConnection();

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				if (name.equals(rs.getString("p_name"))) {
					id = rs.getInt("pro_id");
				}

			}
			// System.out.println(id);

			rs.close();
			conn.close();

		} catch (Exception e) {
			System.out.println(e);// TODO: handle exception
		}

		return id;

	}

	public List<Task> listTask(String name) throws Exception {

		List<Task> taskList = new ArrayList<Task>();
		try {
			String query = "SELECT * FROM task WHERE t_STATUS=1 and t_assignee='" + name + "' ";
			Connection conn = DbConnection.getSqlConnection();

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			System.out.println(name);
			while (rs.next()) {

				Task t = new Task();

				t.setT_id(rs.getInt("task_id"));
				t.setT_name(rs.getString("t_name"));
				t.setT_des(rs.getString("t_des"));
				t.setPro_id(rs.getInt("pro_id"));
				t.setP_name(rs.getString("p_name"));
				t.setCreate(rs.getString("t_createAt"));
				t.setAssignee(rs.getString("t_assignee"));
				t.setStatus(rs.getString("task_status"));
				t.setDate(rs.getString("t_sdate"));
				t.setDdate(rs.getString("t_ddate"));
				taskList.add(t);

			}

			rs.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		return taskList;

	}

	public List<Employee> empTeamList(String user_name) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = null;
		List<Employee> empTeamList = new ArrayList<Employee>();

		try {
			String query = "SELECT * FROM emp WHERE emp_STATUS=1";
			conn = DbConnection.getSqlConnection();

			Statement stmt = conn.createStatement();
			Statement stmt1 = conn.createStatement();

			ResultSet rs = stmt.executeQuery(query);
			ResultSet rs1 = stmt1.executeQuery(query);
			String divi = null;

			while (rs.next()) {
				Employee emp = new Employee();
				if (rs.getString("emp_uname").equals(user_name)) {
					divi = rs.getString("emp_division");

				}

			}

			// System.out.println(divi);
			stmt.close();
			rs.close();
			while (rs1.next()) {
				Employee emp = new Employee();
				if (divi.equals(rs1.getString("emp_division"))) {
					emp.setEmp_id(rs1.getInt("emp_id"));
					emp.setEmp_idNum(rs1.getString("emp_idNum"));
					emp.setEmp_fname(rs1.getString("emp_fname"));
					emp.setEmp_lname(rs1.getString("emp_lname"));
					emp.setEmp_gender(rs1.getString("emp_gender"));
					emp.setDob(rs1.getString("emp_dob"));
					emp.setDoj(rs1.getString("emp_doj"));
					emp.setEmp_email(rs1.getString("emp_email"));
					emp.setEmp_role(rs1.getString("emp_desiganation"));
					emp.setEmp_division(rs1.getString("emp_division"));
					emp.setEmp_department(rs1.getString("emp_department"));
					emp.setEmp_team(rs1.getString("emp_team"));
					emp.setEmp_address(rs1.getString("address"));
					emp.setEmp_uname(rs1.getString("emp_uname"));
					emp.setEmp_pass(rs1.getString("emp_pass"));
					empTeamList.add(emp);

				}

			}

			stmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		return empTeamList;
	}

	public int findEmpId(String user_name) {
		int id = 0;
		Connection conn = null;
		try {

			String query = "SELECT * FROM emp WHERE emp_STATUS=1";
			conn = DbConnection.getSqlConnection();

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				Employee emp = new Employee();
				if (rs.getString("emp_uname").equals(user_name)) {
					id = rs.getInt("emp_id");

				}

			}

			stmt.close();
			rs.close();
			conn.close();

		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}

		return id;

	}

	public int findProId(String user_name) {
		int id = 0;
		Connection conn = null;
		try {

			String query = "SELECT * FROM project WHERE p_STATUS=1";
			conn = DbConnection.getSqlConnection();

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				if (rs.getString("p_name").equals(user_name)) {
					id = rs.getInt("pro_id");

				}

			}

			stmt.close();
			rs.close();
			conn.close();
		}

		catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}

		return id;

	}

	public List<Employee> empProjectDetail() {
		List<Employee> empProList = new ArrayList<Employee>();

		Connection conn = null;
		try {
			String query = "SELECT * FROM projectempdetail WHERE d_STATUS=1";
			conn = DbConnection.getSqlConnection();

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				Employee emp = new Employee();
				emp.setEmp_id(rs.getInt("emp_id"));
				emp.setEmp_fname(rs.getString("emp_name"));
				emp.setEmp_role(rs.getString("emp_role"));
				emp.setP_id(rs.getInt("pro_id"));
				empProList.add(emp);

			}

			stmt.close();
			rs.close();
			conn.close();

		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}

		return empProList;

	}

	public List<Task> listTaskId(int id) throws Exception {
		List<Task> taskList = new ArrayList<Task>();
		try {
			String query = "SELECT * FROM task WHERE t_STATUS=1 and task_id='" + id + "' ";
			Connection conn = DbConnection.getSqlConnection();

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// System.out.println(name);

			while (rs.next()) {

				Task t = new Task();

				t.setT_id(rs.getInt("task_id"));
				t.setT_name(rs.getString("t_name"));
				t.setT_des(rs.getString("t_des"));
				t.setPro_id(rs.getInt("pro_id"));
				t.setP_name(rs.getString("p_name"));
				t.setCreate(rs.getString("t_createAt"));
				t.setAssignee(rs.getString("t_assignee"));
				t.setStatus(rs.getString("task_status"));
				t.setDate(rs.getString("t_sdate"));
				t.setDdate(rs.getString("t_ddate"));
				taskList.add(t);

			}

			rs.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		return taskList;

	}

	public String getPname(int id) {
		String pname = "";
		Connection conn = null;
		try {

			String query = "SELECT * FROM project WHERE p_STATUS=1";
			conn = DbConnection.getSqlConnection();

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				if (rs.getInt("pro_id") == id) {
					pname = rs.getString("p_name");

				}

			}

			stmt.close();
			rs.close();
			conn.close();
		}

		catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		return pname;

	}

	public List<Histroy> listUpdateTask() throws Exception {

		List<Histroy> taskList = new ArrayList<Histroy>();
		try {
			// String query = "select task.*,task_update.u_response from task_update,task
			// where task_update.t_id=task.task_id and task.t_assignee='"+name+"' and
			// task.t_status=1";

			String query = "select * from task_update";
			Connection conn = DbConnection.getSqlConnection();

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				Histroy h = new Histroy();
				h.setId(rs.getInt("u_id"));
				h.setPro_id(rs.getInt("pro_id"));
				h.setT_id(rs.getInt("t_id"));
				Timestamp s = rs.getTimestamp("u_time");
				h.setTime(s);
				h.setEmpid(rs.getInt("u_empid"));
				h.setPriority(rs.getInt("u_priority"));
				h.setDay(rs.getInt("u_days"));
				h.setHistroy(rs.getString("u_response"));
				taskList.add(h);
			}

			rs.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		return taskList;

	}

	public void status(String status, String name, String project, int id) {

		try {
			String query = "select * from task where t_status=1";
			Connection conn = DbConnection.getSqlConnection();

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			if (status.equals("Onhold") || status.equals("Ready to Use") || status.equals("Client clarification")
					|| status.equals(
							"Completed")) {/*
											 * 
											 * PreparedStatement pst = (PreparedStatement) conn
											 * .prepareStatement("update task set t_status=0 where task_id='"+id+"' ");
											 * 
											 * 
											 * pst.execute(); pst.close();
											 */

				while (rs.next()) {

					if (rs.getString("t_assignee").equals("NULL") && rs.getString("p_name").equals(project)) {
						int t_id = rs.getInt("task_id");
						newTask(t_id, name);

						break;
					}

				}

			}

		} catch (Exception e) {
			System.out.println(e);// TODO: handle exception
		}

	}

	public void newTask(int tid, String name) {
		Connection conn = null;
		try {
			conn = DbConnection.getSqlConnection();
			PreparedStatement pst = (PreparedStatement) conn
					.prepareStatement("update task set t_assignee='" + name + "' where task_id='" + tid + "' ");

			pst.execute();
			pst.close();
			conn.close();

		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}

	}

	public List<User> userList() throws Exception {

		List<User> user = new ArrayList<User>();
		try {
			String query = "SELECT * FROM user_account WHERE user_STATUS=1";
			Connection conn = DbConnection.getSqlConnection();

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				User user1 = new User();
				user1.setUser_name(rs.getString("user_name"));
				user1.setPassword(rs.getString("user_password"));
				user.add(user1);
			}

			rs.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		return user;

	}

	public static List<Employee> loginEmp(String name, String pass) throws Exception {
		List<Employee> empList = new ArrayList<Employee>();
		try {
			String query = "SELECT * FROM emp WHERE emp_STATUS=1 and emp_uname='" + name + "' and emp_pass='" + pass
					+ "'";
			Connection conn = DbConnection.getSqlConnection();

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				Employee emp = new Employee();

				emp.setEmp_id(rs.getInt("emp_id"));
				emp.setEmp_idNum(rs.getString("emp_idNum"));
				emp.setEmp_fname(rs.getString("emp_fname"));
				emp.setEmp_lname(rs.getString("emp_lname"));
				emp.setEmp_gender(rs.getString("emp_gender"));
				emp.setDob(rs.getString("emp_dob"));
				emp.setDoj(rs.getString("emp_doj"));
				emp.setEmp_email(rs.getString("emp_email"));
				emp.setEmp_role(rs.getString("emp_desiganation"));
				emp.setEmp_division(rs.getString("emp_division"));
				emp.setEmp_department(rs.getString("emp_department"));
				emp.setEmp_team(rs.getString("emp_team"));
				emp.setEmp_address(rs.getString("address"));
				emp.setEmp_uname(rs.getString("emp_uname"));
				emp.setEmp_pass(rs.getString("emp_pass"));
				empList.add(emp);

			}

			rs.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return empList;

	}

	public String getEmpName(String uname, String pass) {

		String ename = "";
		Connection conn = null;
		try {

			String query = "SELECT * FROM emp WHERE emp_STATUS=1";
			conn = DbConnection.getSqlConnection();

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				if (rs.getString("emp_uname").equals(uname) && rs.getString("emp_pass").equals(pass)) {
					ename = rs.getString("emp_fname");

				}

			}

			stmt.close();
			rs.close();
			conn.close();
		}

		catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		return ename;

	}

	public void getmailId(String name, String pname, String pdes) throws Exception {

		Connection conn = null;
		try {
			String query = "SELECT * FROM emp WHERE emp_STATUS=1";
			conn = DbConnection.getSqlConnection();

			Statement stmt = conn.createStatement();
			Statement stmt1 = conn.createStatement();

			ResultSet rs = stmt.executeQuery(query);
			ResultSet rs1 = stmt1.executeQuery(query);
			String divi = null;

			while (rs.next()) {
				Employee emp = new Employee();
				if (rs.getString("emp_uname").equals(name)) {
					divi = rs.getString("emp_division");

				}

			}

			System.out.println(divi);
			stmt.close();
			rs.close();
			String mail = null;
			while (rs1.next()) {
				Employee emp = new Employee();
				if (divi.equals(rs1.getString("emp_division"))) {
					mail = rs1.getString("emp_email").trim();
					System.out.println(mail);

					Sendmail.mail(mail, pname, pdes, name);

				}

			}

			stmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

	}

	public static List<Count> countAll() throws Exception {

		List<Count> countList = new ArrayList<Count>();
		try {
			Count c = new Count();
			int div = 0;
			int dep = 0;
			int team = 0;
			int emp = 0;
			int pro = 0;
			int cpro = 0;
			int inpro = 0;
			int task = 0;
			Connection conn = DbConnection.getSqlConnection();

			Statement stmt = conn.createStatement();

			// emp count
			ResultSet emprs = stmt.executeQuery("SELECT count(emp_id) as countemp from emp where emp_status=1 ");

			while (emprs.next()) {

				emp = emprs.getInt("countemp");

			}
			c.setEmp(emp);
			emprs.close();
			// div count

			ResultSet divrs = stmt.executeQuery("SELECT count(div_id) as countdiv from division where div_status=1  ");

			while (divrs.next()) {

				div = divrs.getInt("countdiv");

			}
			c.setDiv(div);
			divrs.close();
			// dep count

			ResultSet deprs = stmt
					.executeQuery("SELECT count(dep_id) as countdep from department where dep_status=1  ");

			while (deprs.next()) {

				dep = deprs.getInt("countdep");

			}
			c.setDep(dep);
			deprs.close();
			// team count

			ResultSet teamrs = stmt.executeQuery("SELECT count(t_id) as countteam from team where t_status=1");

			while (teamrs.next()) {

				team = teamrs.getInt("countteam");

			}
			c.setTeams(team);
			teamrs.close();

			// project count

			ResultSet prors = stmt.executeQuery("SELECT count(pro_id) as countpro from project where p_status=1");

			while (prors.next()) {

				pro = prors.getInt("countpro");

			}
			c.setPro(pro);
			prors.close();

			// compelte project

			ResultSet cprors = stmt.executeQuery("SELECT count(pro_id) as countcpro from project where p_status=0");

			while (cprors.next()) {

				cpro = cprors.getInt("countcpro");

			}
			c.setCpro(cpro);
			cprors.close();

			// Inpro project

			ResultSet inprors = stmt.executeQuery(
					"SELECT count(pro_id) as countInpro from task where task_status='Inprogress' GROUp by p_name ");

			while (inprors.next()) {

				inpro = inprors.getInt("countInpro");

			}
			c.setInpro(inpro);
			inprors.close();
			// task count

			ResultSet taskrs = stmt.executeQuery("select count(task_id)  as countInpro from task where t_status=1");

			while (taskrs.next()) {

				task = taskrs.getInt("countInpro");

			}
			c.setTask(task);
			taskrs.close();

			countList.add(c);
			conn.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		return countList;

	}

	public List<Task> allTask() throws Exception {

		List<Task> taskList = new ArrayList<Task>();
		try {
			String query = "SELECT * FROM task WHERE t_STATUS=1";
			Connection conn = DbConnection.getSqlConnection();

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// System.out.println(name);
			while (rs.next()) {

				Task t = new Task();

				t.setT_id(rs.getInt("task_id"));
				t.setT_name(rs.getString("t_name"));
				t.setT_des(rs.getString("t_des"));
				t.setPro_id(rs.getInt("pro_id"));
				t.setP_name(rs.getString("p_name"));
				t.setCreate(rs.getString("t_createAt"));
				t.setAssignee(rs.getString("t_assignee"));
				t.setStatus(rs.getString("task_status"));
				t.setDate(rs.getString("t_sdate"));
				t.setDdate(rs.getString("t_ddate"));
				taskList.add(t);

			}

			rs.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		return taskList;

	}

	public List<HashMap<String, String>> chart() {
		List<Count> countList = new ArrayList<Count>();
		HashMap<String, String> map = null;
		
		try {

			countList = countAll();
			for (int i = 0; i < countList.size(); i++) {
				
				map= new HashMap<String, String>();
				map.put("label","Divisions");
				
				
             
			}

		} catch (Exception e) {
			System.out.println(e);// TODO: handle exception
		}

		return null;

	}

}
