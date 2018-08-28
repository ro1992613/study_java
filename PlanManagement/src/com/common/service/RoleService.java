package com.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.common.dao.RoleDao;

@Component
public class RoleService {
	@Autowired 
	RoleDao roleDao;

	public boolean addRole(String role_id, String role_name, String remark) {
		// TODO Auto-generated method stub
		return roleDao.addRole(role_id,role_name,remark);
	}

	public boolean deleteRole(String role_id) {
		// TODO Auto-generated method stub
		return roleDao.deleteRole(role_id);
	}

	public boolean updateRole(String role_id, String role_name, String remark) {
		// TODO Auto-generated method stub
		return roleDao.updateRole(role_id,role_name,remark);
	}
	public Map<String, Object> getRoloInfo(String role_id) {
		// TODO Auto-generated method stub
		return roleDao.getRoloInfo(role_id);
	}
	public List<Map<String, Object>> getAllRoles() {
		// TODO Auto-generated method stub
		return roleDao.getAllRoles();
	}

	public boolean updateRolePermissions(String role_id, String[] permission_ids) {
		// TODO Auto-generated method stub
		return roleDao.updateRolePermissions(role_id,permission_ids);
	}
}
