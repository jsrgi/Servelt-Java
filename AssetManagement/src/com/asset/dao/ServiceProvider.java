package com.asset.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.asset.dbConnect.DbConnect;
import com.asset.model.Complaint;
import com.asset.model.Count;
import com.asset.model.Employee;
import com.asset.model.Hardware;
import com.asset.model.OpSys;
import com.asset.model.Request;
import com.asset.model.Software;

public class ServiceProvider {
	
	public List<Employee> getAllEmp() throws Exception{
		List<Employee> empList = new ArrayList<Employee>();
		
		try {
			
			String qry="SELECT * FROM `emptable` WHERE empStatus=1 ORDER BY empId";
			Connection conn=DbConnect.getSqlConnection();
			
			Statement stm=conn.createStatement();
			ResultSet rs=stm.executeQuery(qry);
			
			while(rs.next()){
				Employee empObj=new Employee();
				empObj.setEmpId(rs.getString("empId"));
				//empId is column name in the table(instead of column name we can use column index)
				empObj.setEmpName(rs.getString("empName"));
				empObj.setEmpDob(rs.getString("empDob"));
				empObj.setEmpGen(rs.getString("empGen"));
				empObj.setEmpMobile(rs.getString("empMobile"));
				empObj.setEmpDoj(rs.getString("empDoj"));
				empObj.setEmpMail(rs.getString("empMail"));
				empObj.setEmpPwd(rs.getString("empPwd"));
				empObj.setEmpConPwd(rs.getString("empConPwd"));
				empObj.setEmpSal(rs.getString("empSal"));
				empObj.setEmpAdrs(rs.getString("empAdrs"));
				empObj.setEmpStatus(rs.getString("empStatus"));
				
				empList.add(empObj);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
		}		
		return empList;
	}
	
	/************************************************************************************/
	
	
	public List<Hardware> getAllHard() throws Exception{
		List<Hardware> hardList = new ArrayList<Hardware>();
		
		try {
			String qry="SELECT * FROM `hardtable` WHERE hardStatus=1 ORDER BY hardId";
			Connection conn=DbConnect.getSqlConnection();
			
			Statement stm=conn.createStatement();
			ResultSet rs=stm.executeQuery(qry);
			
			while(rs.next()){
				Hardware hardObj=new Hardware();
				hardObj.setHardId(rs.getString("hardId"));
				hardObj.setHardCat(rs.getString("hardCat"));
				hardObj.setHardBrand(rs.getString("hardBrand"));
				hardObj.setHardDes(rs.getString("hardDes"));
				hardObj.setHardStock(rs.getString("hardStock"));
				hardObj.setHardWar(rs.getString("hardWar"));
				
				hardList.add(hardObj);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
		}
		return hardList;
		
	}
	
	/************************************************************************************/
	
	public List<Software> getAllSoft() throws Exception{
		List<Software> softList = new ArrayList<Software>();
		
		try {
			String qry="SELECT * FROM `softtable` WHERE softStatus =  1 ORDER BY softId";
			Connection conn=DbConnect.getSqlConnection();
			
			Statement stm=conn.createStatement();
			ResultSet rs=stm.executeQuery(qry);
			
			while(rs.next()){
				Software softObj=new Software();
				softObj.setSoftId(rs.getString("softId"));
				softObj.setSoftName(rs.getString("softName"));
				softObj.setSoftVer(rs.getString("softVer"));
				softObj.setSoftLice(rs.getString("softLice"));
				softObj.setSoftPlat(rs.getString("softPlat"));
				softObj.setSoftAvl(rs.getString("softAvl"));
				
				softList.add(softObj);
				
				
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
		}
		return softList;
	}
	
	/************************************************************************************/
	
	public List<OpSys> getAllOpSys() throws Exception{
		
		List<OpSys> osList = new ArrayList<OpSys>();
		try {
			
			String qry = "SELECT * FROM `ostable` WHERE osStatus = 1 ORDER BY osId";
			Connection conn=DbConnect.getSqlConnection();
			
			Statement stm=conn.createStatement();
			ResultSet rs=stm.executeQuery(qry);
			
			while(rs.next()){
				OpSys osObj = new OpSys();
				osObj.setOsId(rs.getString("osId"));
				osObj.setOsType(rs.getString("osType"));
				osObj.setOsVer(rs.getString("osVer"));
				osObj.setOsLice(rs.getString("osLice"));
				osObj.setOsBit(rs.getString("osBit"));
				osObj.setOsCost(rs.getString("osCost"));
				osObj.setOsAvl(rs.getString("osAvl"));
				
				osList.add(osObj);
			}
			
		} catch (Exception e) {
			System.err.println(e);
		}
		return osList;
	}
	
	/************************************************************************************/
	
	public List<Request> getAllRequest() throws Exception{
		List<Request> reqList = new ArrayList<Request>();
		try {
			String qry = "SELECT * FROM `reqtable` WHERE reqStatus='requested' ORDER BY reqId";
			Connection conn = DbConnect.getSqlConnection();
			
			Statement stm=conn.createStatement();
			ResultSet rs=stm.executeQuery(qry);
			
			while(rs.next()){
				Request reqObj = new Request();
				reqObj.setReqId(rs.getString("reqId"));
				reqObj.setEmpId(rs.getString("empId"));
				reqObj.setEmpName(rs.getString("empName"));
				reqObj.setReqType(rs.getString("reqType"));
				reqObj.setReqTypeId(rs.getString("reqTypeId"));
				reqObj.setReqName(rs.getString("reqName"));
				reqObj.setReqVer(rs.getString("reqVer"));
				reqObj.setReqQry(rs.getString("reqQry"));
				
				reqList.add(reqObj);
			}
			
		} catch (Exception e) {
		System.err.println(e);	
		}
		
		return reqList;
		
	}
	
	/************************************************************************************/
	
	public List<Complaint> getAllComplaint() throws Exception{
		List<Complaint> comList = new ArrayList<Complaint>();
		try {
			
			String qry="SELECT * FROM `comtable` WHERE comStatus=1 ORDER BY comId";
			Connection conn=DbConnect.getSqlConnection();
			Statement stm = conn.createStatement();
			ResultSet rs=stm.executeQuery(qry);
			
			while(rs.next())
			{
				Complaint comObj=new Complaint();
				
				comObj.setComId(rs.getString("comId"));
				comObj.setEmpId(rs.getString("empId"));
				comObj.setEmpName(rs.getString("empName"));
				comObj.setComType(rs.getString("comType"));
				comObj.setComRecDt(rs.getString("comRecDt"));
				comObj.setComTarDt(rs.getString("comTarDt"));
				comObj.setComDesc(rs.getString("comDesc"));
				comObj.setComStat(rs.getString("comStat"));
				
				comList.add(comObj);
			}
			
		} catch (Exception e) {
			System.err.println(e);
		}
		return comList;
	}
	
/************************************************************************************/
	
	public List<Complaint> getEmpComplaint(String id) throws Exception{
		List<Complaint> comList = new ArrayList<Complaint>();
		try {
			Connection conn=DbConnect.getSqlConnection();
			
			String qry = "SELECT * FROM `comtable` WHERE empId=?";
			
			PreparedStatement pst = conn.prepareStatement(qry);
			pst.setString(1, id);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next())
			{
				Complaint comObj=new Complaint();
				comObj.setComId(rs.getString("comId"));
				comObj.setEmpId(rs.getString("empId"));
				comObj.setEmpName(rs.getString("empName"));
				comObj.setComType(rs.getString("comType"));
				comObj.setComDesc(rs.getString("comDesc"));
				comObj.setComRecDt(rs.getString("comRecDt"));
				comObj.setComTarDt(rs.getString("comTarDt"));
				comObj.setComStat(rs.getString("comStat"));
				
				comList.add(comObj);
			}
			
		} catch (Exception e) {
			System.err.println(e);
		}
		return comList;
	}
	
	/************************************************************************************/
	
	public List<Employee> getEmpProfile(String id) throws Exception{
		List<Employee> empList = new ArrayList<Employee>();
		try {
			Connection conn=DbConnect.getSqlConnection();
			
			String qry="SELECT * FROM `emptable` WHERE empId=?";
			
			PreparedStatement pst = conn.prepareStatement(qry);
			pst.setString(1, id);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next())
			{
				Employee empObj=new Employee();
				empObj.setEmpId(rs.getString("empId"));
				//empId is column name in the table(instead of column name we can use column index)
				empObj.setEmpName(rs.getString("empName"));
				empObj.setEmpDob(rs.getString("empDob"));
				empObj.setEmpGen(rs.getString("empGen"));
				empObj.setEmpMobile(rs.getString("empMobile"));
				empObj.setEmpDoj(rs.getString("empDoj"));
				empObj.setEmpMail(rs.getString("empMail"));
				empObj.setEmpPwd(rs.getString("empPwd"));
				empObj.setEmpConPwd(rs.getString("empConPwd"));
				empObj.setEmpSal(rs.getString("empSal"));
				empObj.setEmpAdrs(rs.getString("empAdrs"));
				//empObj.setEmpStatus(rs.getString("empStatus"));
				
				empList.add(empObj);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
		}
		return empList;
	}
	
	/************************************************************************************/
	
	public List<Request> getEmpRequest(String id) throws Exception{
		List<Request> reqList = new ArrayList<Request>();
		try {
			Connection conn=DbConnect.getSqlConnection();
			
			String qry = "SELECT * FROM `reqtable` WHERE empId=?";
			
			PreparedStatement pst = conn.prepareStatement(qry);
			pst.setString(1, id);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next())
			{
				Request reqObj = new Request();
				reqObj.setReqId(rs.getString("reqId"));
				reqObj.setEmpId(rs.getString("empId"));
				reqObj.setEmpName(rs.getString("empName"));
				reqObj.setReqType(rs.getString("reqType"));
				reqObj.setReqTypeId(rs.getString("reqTypeId"));
				reqObj.setReqName(rs.getString("reqName"));
				reqObj.setReqVer(rs.getString("reqVer"));
				reqObj.setReqQry(rs.getString("reqQry"));
				reqObj.setReqStatus(rs.getString("reqStatus"));
				
				reqList.add(reqObj);
			}
			
		} catch (Exception e) {
			System.err.println(e);
		}
		return reqList;
	}
	
	/************************************************************************************/
	
	public void addEmp(Employee emp)
	{
		Connection conn=null;
		
		try {
			conn=DbConnect.getSqlConnection();
			String sql="insert into emptable(empId,empName,empDob,empGen,empMobile,empDoj,empMail,empPwd,empConPwd,empSal,empAdrs)values(?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, emp.getEmpId());
			pst.setString(2, emp.getEmpName());
			pst.setString(3, emp.getEmpMail());
			pst.setString(4, emp.getEmpMobile());
			pst.setString(5, emp.getEmpPwd());
			pst.setString(6, emp.getEmpDoj());
			pst.setString(7, emp.getEmpSal());
			
			pst.executeUpdate();
			pst.close();
			
			String str="insert into usertable(userId,userName,userMail,userPwd)values(?,?,?,?)";
			PreparedStatement pstmt=conn.prepareStatement(str);
			
			pstmt.setString(1, emp.getEmpId());
			pstmt.setString(2, emp.getEmpName());
			pstmt.setString(3, emp.getEmpMail());
			pstmt.setString(4, emp.getEmpPwd());
			
			pstmt.executeUpdate();
			pstmt.close();
			
			conn.close();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
	
	/************************************************************************************/
	
	public void updateEmp(Employee emp)
	{
      Connection conn=null;
		
		try {
			/*emp=new Employee();*/
			conn=DbConnect.getSqlConnection();
			String sql="update emptable set empName=?,empDob=?,empGen=?,empMobile=?,empDoj=?,empMail=?,empPwd=?,empConPwd=?,empSal=?,empAdrs=? where empId=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			
			
			
			pst.setString(1, emp.getEmpName());
			pst.setString(2, emp.getEmpDob());
			pst.setString(3, emp.getEmpGen());
			pst.setString(4, emp.getEmpMobile());
			pst.setString(5, emp.getEmpDoj());
			pst.setString(6, emp.getEmpMail());
			pst.setString(7, emp.getEmpPwd());
			pst.setString(8, emp.getEmpConPwd());
			pst.setString(9, emp.getEmpSal());
			pst.setString(10, emp.getEmpAdrs());
			pst.setString(11, emp.getEmpId());
			
			pst.executeUpdate();
			pst.close();
			
			
			String str="update usertable set userName=?,userMail=?,userPwd=? where userId=?";
			
			//String str="update usertable set(userName,userMail,userPwd)values(?,?,?) where userId=?";
			PreparedStatement pstmt=conn.prepareStatement(str);
			
			pstmt.setString(1, emp.getEmpName());
			pstmt.setString(2, emp.getEmpMail());
			pstmt.setString(3, emp.getEmpPwd());
			pstmt.setString(4, emp.getEmpId());
			
			pstmt.executeUpdate();
			pstmt.close();
			
			
			conn.close();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
	
	/************************************************************************************/
	
	public void deleteEmp(String empId)
	{
		Connection conn=null;
		try{
			conn=DbConnect.getSqlConnection();
		String sql="update emptable set empStatus=0 where empId=?";
		PreparedStatement pst=conn.prepareStatement(sql);
		
		pst.setString(1, empId);
		pst.executeUpdate();
		pst.close();
		
		
		String str="update usertable set userStatus=0 where userId=?";
		PreparedStatement pstmt=conn.prepareStatement(str);
		pstmt.setString(1, empId);
		pstmt.executeUpdate();
		pstmt.close();
		
		conn.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	/************************************************************************************/
	
	public void addHard(Hardware hard)
	{
Connection conn=null;
		
		try {
			conn=DbConnect.getSqlConnection();
			String sql="insert into hardtable(hardId,hardCat,hardBrand,hardDes,hardStock,hardWar)values(?,?,?,?,?,?)";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, hard.getHardId());
			pst.setString(2, hard.getHardCat());
			pst.setString(3, hard.getHardBrand());
			pst.setString(4, hard.getHardDes());
			pst.setString(5, hard.getHardStock());
			pst.setString(6, hard.getHardWar());
						
			pst.executeUpdate();
			pst.close();
			conn.close();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
	}
	
	/************************************************************************************/
	
	public void updateHard(Hardware hard)
	{
		Connection conn=null;
		
		try {
			conn=DbConnect.getSqlConnection();
			String sql="update hardtable set hardCat=?,hardBrand=?,hardDes=?,hardStock=?,hardWar=? where hardId=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			
			pst.setString(1, hard.getHardCat());
			pst.setString(2, hard.getHardBrand());
			pst.setString(3, hard.getHardDes());
			pst.setString(4, hard.getHardStock());
			pst.setString(5, hard.getHardWar());
			pst.setString(6, hard.getHardId());
			
			pst.executeUpdate();
			pst.close();
			conn.close();
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	/************************************************************************************/
	
	public void deleteHard(String hardId)
	{
		Connection conn=null;
		
		try {
			conn=DbConnect.getSqlConnection();
			String sql="update hardtable set hardStatus=0 where hardId=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			
			pst.setString(1, hardId);
			
			pst.executeUpdate();
			pst.close();
			conn.close();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
	}
	
	/************************************************************************************/
	
	public void addSoft(Software soft)
	{
			Connection conn=null;
		
		try {
			conn=DbConnect.getSqlConnection();
			String sql="insert into softtable(softId,softName,softVer,softLice,softPlat,softAvl)values(?,?,?,?,?,?)";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, soft.getSoftId());
			pst.setString(2, soft.getSoftName());
			pst.setString(3, soft.getSoftVer());
			pst.setString(4, soft.getSoftLice());
			pst.setString(5, soft.getSoftPlat());
			pst.setString(6, soft.getSoftAvl());
			
						
			pst.executeUpdate();
			pst.close();
			conn.close();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

	}
	
	/************************************************************************************/
	
	public void updateSoft(Software soft)
	{
Connection conn=null;
		
		try {
			conn=DbConnect.getSqlConnection();
			String sql="update softtable set softName=?,softVer=?,softLice=?,softPlat=?,softAvl=? where softId=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			
			
			pst.setString(1, soft.getSoftName());
			pst.setString(2, soft.getSoftVer());
			pst.setString(3, soft.getSoftLice());
			pst.setString(4, soft.getSoftPlat());
			pst.setString(5, soft.getSoftAvl());
			pst.setString(6, soft.getSoftId());
			
			pst.executeUpdate();
			pst.close();
			conn.close();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
	}
	
	/************************************************************************************/
	
	public void deleteSoft(String softId)
	{
Connection conn=null;
		
		try {
			conn=DbConnect.getSqlConnection();
			String sql="update softtable set softStatus=0 where softId=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			
			pst.setString(1, softId);
			
			pst.executeUpdate();
			pst.close();
			conn.close();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
	
	/************************************************************************************/
	
	public void addOpSys(OpSys os)
	{
			Connection conn=null;
		
		try {
			conn=DbConnect.getSqlConnection();
			String sql="insert into ostable(osId,osType,osLice,osVer,osBit,osCost,osAvl)values(?,?,?,?,?,?,?)";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, os.getOsId());
			pst.setString(2, os.getOsType());
			pst.setString(3, os.getOsLice());
			pst.setString(4, os.getOsVer());
			pst.setString(5, os.getOsBit());
			pst.setString(6, os.getOsCost());
			pst.setString(7, os.getOsAvl());
						
			pst.executeUpdate();
			pst.close();
			conn.close();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
	
	/************************************************************************************/
	
	public void updateOpSys(OpSys os)
	{
		Connection conn=null;
		
		try {
			conn=DbConnect.getSqlConnection();
			String sql="update ostable set osType=?,osLice=?,osVer=?,osBit=?,osCost=?,osAvl=? where osId=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			
			pst.setString(1, os.getOsType());
			pst.setString(2, os.getOsLice());
			pst.setString(3, os.getOsVer());
			pst.setString(4, os.getOsBit());
			pst.setString(5, os.getOsCost());
			pst.setString(6, os.getOsAvl());
			pst.setString(7, os.getOsId());
						
			pst.executeUpdate();
			pst.close();
			conn.close();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
	
	/************************************************************************************/
	
	public void deleteOpSys(String osId)
	{
Connection conn=null;
		
		try {
			conn=DbConnect.getSqlConnection();
			String sql="update ostable set osStatus=0 where osId=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			
			pst.setString(1, osId);
			
			pst.executeUpdate();
			pst.close();
			conn.close();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
	
	/************************************************************************************/
	
	public void updateHardStatus(String hardId)
	{
		String qry="UPDATE hardtable SET hardStock = ? WHERE hardId = ?";
		String totalHardQty = getTotalHard(hardId);
		//System.out.println(totalHardQty);
		int stock=Integer.parseInt(totalHardQty)-1;
		//System.out.println(stock);
		String total=""+stock;
		//System.out.println(total);
		try
		{
			PreparedStatement pst = DbConnect.getSqlConnection().prepareStatement(qry);
			pst.setString(1, total);
			pst.setString(2, hardId);
			pst.executeUpdate();
			pst.close();
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
		
	}
	
	/************************************************************************************/
	
	public String getTotalHard(String hardId)
	{
		String qry="SELECT hardStock FROM `hardtable` WHERE hardId='";
		String qty="0";
		try {
			Statement stmt=DbConnect.getSqlConnection().createStatement();
			stmt.executeQuery(qry+hardId+"'");
			ResultSet rs=stmt.getResultSet();
			
			while(rs.next()){
				qty=rs.getString("hardStock");
			}
			stmt.close();
			
		} catch (Exception e) {
			System.err.println(e);
		}
		return qty;
	}
	
	/************************************************************************************/
	
	public void updateSoftStatus(String softId)
	{
		String qry="UPDATE softtable SET softAvl = ? WHERE softId = ?";
		String totalHardQty = getTotalSoft(softId);
		//System.out.println(totalHardQty);
		int stock=Integer.parseInt(totalHardQty)-1;
		//System.out.println(stock);
		String total=""+stock;
		//System.out.println(total);
		try
		{
			PreparedStatement pst = DbConnect.getSqlConnection().prepareStatement(qry);
			pst.setString(1, total);
			pst.setString(2, softId);
			pst.executeUpdate();
			pst.close();
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
	
	}
	
	/************************************************************************************/
	
	public String getTotalSoft(String softId)
	{
		String qry="SELECT softAvl FROM `softtable` WHERE softId='";
		String qty="0";
		try {
			Statement stmt=DbConnect.getSqlConnection().createStatement();
			stmt.executeQuery(qry+softId+"'");
			ResultSet rs=stmt.getResultSet();
			
			while(rs.next()){
				qty=rs.getString("softAvl");
			}
			stmt.close();
			
		} catch (Exception e) {
			System.err.println(e);
		}
		return qty;
	}
	
	/************************************************************************************/
	
	public void updateOpSysStatus(String osId)
	{
		String qry="UPDATE ostable SET osAvl = ? WHERE osId = ?";
		String totalHardQty = getTotalOs(osId);
		//System.out.println(totalHardQty);
		int stock=Integer.parseInt(totalHardQty)-1;
		//System.out.println(stock);
		String total=""+stock;
		//System.out.println(total);
		try
		{
			PreparedStatement pst = DbConnect.getSqlConnection().prepareStatement(qry);
			pst.setString(1, total);
			pst.setString(2, osId);
			pst.executeUpdate();
			pst.close();
		}
		catch(Exception e)
		{
			System.err.println(e);
		}	
	}
	
	

	/************************************************************************************/
	
	public String getTotalOs(String osId)
	{
		String qry="SELECT osAvl FROM `ostable` WHERE osId='";
		String qty="0";
		try {
			Statement stmt=DbConnect.getSqlConnection().createStatement();
			stmt.executeQuery(qry+osId+"'");
			ResultSet rs=stmt.getResultSet();
			
			while(rs.next()){
				qty=rs.getString("osAvl");
			}
			stmt.close();
			
		} catch (Exception e) {
			System.err.println(e);
		}
		return qty;
	}
	
	/************************************************************************************/

	public void updateComStat(Complaint com)
	{
		Connection conn = null;
		try {
			
			conn=DbConnect.getSqlConnection();
			if(com.getComStat().equals("solved"))
			{
				String sql="update comtable set comStatus=0,comStat=? where comId=?";
				PreparedStatement pst=conn.prepareStatement(sql);
				
				pst.setString(1, com.getComStat());
				pst.setString(2, com.getComId());
				
				pst.executeUpdate();
				pst.close();
			}
			else
			{
				String sql="update comtable set comStat=? where comId=?";
				PreparedStatement pst=conn.prepareStatement(sql);
				
				pst.setString(1, com.getComStat());
				pst.setString(2, com.getComId());
				
				pst.executeUpdate();
				pst.close();
			}
			conn.close();
			
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	/************************************************************************************/
	
	public List<Count> getAllCount() throws Exception
	{
		List<Count> cntList=new ArrayList<Count>();
		try {
			Connection conn=DbConnect.getSqlConnection();
			Statement stm=conn.createStatement();
			Count cntObj=new Count();
			
			ResultSet rs1=stm.executeQuery("SELECT COUNT(comId) AS comCnt FROM `comtable`");
			while(rs1.next())
			{
				cntObj.setComCnt(rs1.getInt("comCnt"));
				
			}
			
			ResultSet rs2=stm.executeQuery("SELECT COUNT(reqId) AS reqCnt FROM `reqtable`");
			while(rs2.next())
			{
				cntObj.setReqCnt(rs2.getInt("reqCnt"));
			}
			
			ResultSet rs3=stm.executeQuery("SELECT COUNT(empId) AS empCnt FROM `emptable`");
			while(rs3.next())
			{
				cntObj.setEmpCnt(rs3.getInt("empCnt"));
			}
			
			ResultSet rs4=stm.executeQuery("SELECT COUNT(softId) AS softCnt FROM `softtable`");
			while(rs4.next())
			{
				cntObj.setSoftCnt(rs4.getInt("softCnt"));
			}
			
			ResultSet rs5=stm.executeQuery("SELECT COUNT(hardId) AS hardCnt FROM `hardtable`");
			while(rs5.next())
			{
				cntObj.setHardCnt(rs5.getInt("hardCnt"));
			}
	
			ResultSet rs6=stm.executeQuery("SELECT COUNT(osId) AS osCnt FROM `ostable`");
			while(rs6.next())
			{
				cntObj.setOsCnt(rs6.getInt("osCnt"));				
			}
			
			ResultSet rs7=stm.executeQuery("SELECT COUNT(comStat) as comSol FROM `comtable` WHERE comStat='solved'");
			while(rs7.next())
			{
				cntObj.setComSol(rs7.getInt("comSol"));
			}
			
			ResultSet rs8=stm.executeQuery("SELECT COUNT(comStat) as comIn FROM `comtable` WHERE comStat='Inprogress'");
			while(rs8.next())
			{
				cntObj.setComIn(rs8.getInt("comIn"));
			}
			
			ResultSet rs9=stm.executeQuery("SELECT COUNT(comStat) as comView FROM `comtable` WHERE comStat='viewed'");
			while(rs9.next())
			{
				cntObj.setComView(rs9.getInt("comView"));
			}
			
			ResultSet rs10=stm.executeQuery("SELECT COUNT(comStat) as comCom FROM `comtable` WHERE comStat='complainted'");
			while(rs10.next())
			{
				cntObj.setComCom(rs10.getInt("comCom"));
			}
			
			ResultSet rs11=stm.executeQuery("SELECT COUNT(reqStatus) as reqReq FROM `reqtable` WHERE reqStatus='requested'");
			while(rs11.next())
			{
				cntObj.setReqReq(rs11.getInt("reqReq"));
			}
			
			ResultSet rs12=stm.executeQuery("SELECT COUNT(reqStatus) as reqAcc FROM `reqtable` WHERE reqStatus='accepted'");
			while(rs12.next())
			{
				cntObj.setReqAcc(rs12.getInt("reqAcc"));
			}
			
			ResultSet rs13=stm.executeQuery("SELECT COUNT(reqStatus) as reqRej FROM `reqtable` WHERE reqStatus='rejected'");
			while(rs13.next())
			{
				cntObj.setReqRej(rs13.getInt("reqRej"));
			}
			
			
			
			
			cntList.add(cntObj);
			
		} catch (Exception e) {
			System.err.println(e);
		}
		return cntList;
	}
	
	/************************************************************************************/
}
