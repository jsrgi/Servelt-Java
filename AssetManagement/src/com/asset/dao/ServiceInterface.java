package com.asset.dao;

import java.util.List;

import com.asset.model.Complaint;
import com.asset.model.Employee;
import com.asset.model.Hardware;
import com.asset.model.OpSys;
import com.asset.model.Request;
import com.asset.model.Software;



public interface ServiceInterface {
	public List<Employee> getAllEmp();
	public List<Employee> getEmpProfile();
	public List<Hardware> getAllHard();
	public List<Software> getAllSoft();
	public List<OpSys> getAllOpSys();
	public List<Request> getAllRequest();
	public List<Request> getEmpRequest();
	public List<Complaint> getAllComplaint();
	public List<String> getAllCount();
 
	public void addEmp();
	public void updateEmp();
	public void deleteEmp();
	
	public void addHard();
	public void updateHard();
	public void deleteHard();
	
	public void addSoft();
	public void updateSoft();
	public void deleteSoft();
	
	public void addOpSys();
	public void updateOpSys();
	public void deleteOpSys();
	
	public void updateHardStatus();
	public String getTotalHard();
	
	public void updateSoftStatus();
	public String getTotalSoft();
	
	public void updateOpSysStatus();
	public String getTotalOs();
	
	public void updateComStat();
}
