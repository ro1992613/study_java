package com.common.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.entity.APIResult;
import com.common.service.PermissionService;
import com.common.utils.StringUtil;
@Controller
@RequestMapping("/rightmanage/permission")
public class PermissionController {
	@Autowired
	PermissionService permissionService;
	
	@RequestMapping(value="/addPermission",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@RequiresPermissions("rightmanage:permission:addPermission")
	@ResponseBody
	public APIResult<String> addPermission(HttpServletRequest request, HttpServletResponse response){
		APIResult<String> result=new APIResult<>();
		result.setCode(0);
		String permission_id="permission_"+StringUtil.getID();
		String permission_name=request.getParameter("permission_name");
		String permission_url=request.getParameter("permission_url");
		String permission_type=request.getParameter("permission_type");
		String permission_father_id=request.getParameter("permission_father_id");
		if(!StringUtil.IsNullOrEmpty(permission_name)
				&&!StringUtil.IsNullOrEmpty(permission_url)
				&&!StringUtil.IsNullOrEmpty(permission_type)){
			boolean rs=permissionService.addPermission(permission_id,permission_father_id,permission_name,permission_url,permission_type);
			if(rs){
				result.setCode(1);
			}
		}
		
		return result;
	}
	@RequiresPermissions("rightmanage:permission:deletePermission")
	@RequestMapping(value="/deletePermission",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public APIResult<String> deletePermission(HttpServletRequest request, HttpServletResponse response){
		APIResult<String> result=new APIResult<>();
		result.setCode(0);
		String permission_id=request.getParameter("permission_id");
		if(!StringUtil.IsNullOrEmpty(permission_id)){
			boolean rs=permissionService.deletePermission(permission_id);
			if(rs){
				result.setCode(1);
			}
		}
		return result;	}
	@RequiresPermissions("rightmanage:permission:getPermissionInfo")
	@RequestMapping(value="/getPermissionInfo",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public APIResult<Map<String,Object>> getPermissionInfo(HttpServletRequest request, HttpServletResponse response){
		APIResult<Map<String,Object>> result=new APIResult<>();
		result.setCode(0);
		String permission_id=request.getParameter("permission_id");
		if(!StringUtil.IsNullOrEmpty(permission_id)){
			Map<String,Object> map=permissionService.getPermissionInfo(permission_id);
			if(map!=null){
				result.setCode(1);
				result.setData(map);
			}
		}
		return result;
	}
	@RequiresPermissions("rightmanage:permission:updatePermission")
	@RequestMapping(value="/updatePermission",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public APIResult<String> updatePermission(HttpServletRequest request, HttpServletResponse response){
		APIResult<String> result=new APIResult<>();
		result.setCode(0);
		String permission_id=request.getParameter("permission_id");
		String permission_name=request.getParameter("permission_name");
		String permission_url=request.getParameter("permission_url");
		String permission_type=request.getParameter("permission_type");
		String permission_father_id=request.getParameter("permission_father_id");
		if(!StringUtil.IsNullOrEmpty(permission_id)
				&&!StringUtil.IsNullOrEmpty(permission_name)
				&&!StringUtil.IsNullOrEmpty(permission_url)
				&&!StringUtil.IsNullOrEmpty(permission_type)
				&&!StringUtil.IsNullOrEmpty(permission_father_id)){
			boolean rs=permissionService.updatePermission(permission_id,permission_father_id,permission_name,permission_url,permission_type);
			if(rs){
				result.setCode(1);
			}
		}
		
		return result;	
	}
	@RequiresPermissions("rightmanage:permission:getAllPermissions")
	@RequestMapping(value="/getAllPermissions",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public APIResult<List<Map<String,Object>>> getAllPermissions(HttpServletRequest request, HttpServletResponse response){
		APIResult<List<Map<String,Object>>> result=new APIResult<>();		
		List<Map<String,Object>> permissions=permissionService.getAllPermissions();
		if(permissions==null || permissions.size()==0){
			result.setCode(0);
			result.setMsg("no data!");
		}else{	
			result.setCode(1);
			result.setData(permissions);
		}
		return result;
	}
	@RequiresPermissions("rightmanage:permission:getPermissionsByRoleId")
	@RequestMapping(value="/getPermissionsByRoleId",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public APIResult<List<Map<String, Object>>> getPermissionsByRoleId(HttpServletRequest request, HttpServletResponse response){
		APIResult<List<Map<String,Object>>> result=new APIResult<>();		
		String role_id=request.getParameter("role_id");
		List<Map<String,Object>> permissions=permissionService.getPermissionsByRoleId(role_id);
		if(permissions==null || permissions.size()==0){
			result.setCode(0);
			result.setMsg("no data!");
		}else{	
			result.setCode(1);
			result.setData(permissions);
		}
		return result;
	}
	
}
