package com.common.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.common.dao.PermissionDao;

@Component
public class PermissionService {
	@Autowired 
	PermissionDao permissionDao;

	public boolean addPermission(String permission_id,String permission_father_id, String permission_name, String permission_url,
			String permission_type) {
		// TODO Auto-generated method stub
		return permissionDao.addPermission(permission_id,permission_father_id,permission_name,permission_url,permission_type);
	}

	public boolean deletePermission(String permission_id) {
		// TODO Auto-generated method stub
		permissionDao.deletePermission(permission_id);
		List<Map<String, Object>> child=permissionDao.getChildPermissionId(permission_id);
		System.out.println(child.size());
		if(child!=null && child.size()>0){
			for(Map<String, Object> map:child){
				deletePermission(map.get("permission_id").toString());
			}
		}
		return true;
	}

	public boolean updatePermission(String permission_id,String permission_father_id, String permission_name, String permission_url,
			String permission_type) {
		// TODO Auto-generated method stub
		return permissionDao.updatePermission(permission_id,permission_father_id,permission_name,permission_url,permission_type);
	}
	public Map<String, Object> getPermissionInfo(String permission_id) {
		// TODO Auto-generated method stub
		return permissionDao.getPermissionInfo(permission_id);
	}
	public List<Map<String, Object>> getAllPermissions() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list=permissionDao.getAllPermissions();
		List<Map<String, Object>> rs=new ArrayList<>();
		if(list!=null && list.size()>0){
			for(Map<String, Object> data:list){
				Map<String,Object> map=new HashMap<>();
				map.put("id", data.get("permission_id"));
				map.put("pId",  data.get("permission_father_id"));
				map.put("name",  data.get("permission_name"));
				rs.add(map);
			}
		}
		return rs;
	}
	public List<Map<String, Object>> getPermissionsByRoleId(String role_id) {
		// TODO Auto-generated method stub
		return permissionDao.getPermissionsByRoleId(role_id);
	}
	public List<Map<String, Object>> getChildPermissionId(String permission_id) {
		// TODO Auto-generated method stub
		return permissionDao.getChildPermissionId(permission_id);
	}
}
