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
import com.common.service.RoleService;
import com.common.utils.StringUtil;
@Controller
@RequestMapping("/rightmanage/role")
public class RoleController {
	@Autowired
	RoleService roleService;
	@RequiresPermissions("rightmanage:role:addRole")
	@RequestMapping(value="/addRole",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public APIResult<String> addRole(HttpServletRequest request, HttpServletResponse response){
		APIResult<String> result=new APIResult<>();
		result.setCode(0);
		String role_id="role_"+StringUtil.getID();
		String role_name=request.getParameter("role_name");
		String remark=request.getParameter("remark");
		if(!StringUtil.IsNullOrEmpty(role_name)){
			boolean rs=roleService.addRole(role_id,role_name,remark);
			if(rs){
				result.setCode(1);
			}
		}
		
		return result;
	}
	@RequiresPermissions("rightmanage:role:deleteRole")
	@RequestMapping(value="/deleteRole",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public APIResult<String> deleteRole(HttpServletRequest request, HttpServletResponse response){
		APIResult<String> result=new APIResult<>();
		result.setCode(0);
		String role_id=request.getParameter("role_id");
		if(!StringUtil.IsNullOrEmpty(role_id)){
			boolean rs=roleService.deleteRole(role_id);
			if(rs){
				result.setCode(1);
			}
		}
		return result;
	}
	@RequiresPermissions("rightmanage:role:getRoloInfo")
	@RequestMapping(value="/getRoloInfo",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public APIResult<Map<String,Object>> getRoloInfo(HttpServletRequest request, HttpServletResponse response){
		APIResult<Map<String,Object>> result=new APIResult<>();
		result.setCode(0);
		String role_id=request.getParameter("role_id");
		if(!StringUtil.IsNullOrEmpty(role_id)){
			Map<String,Object> map=roleService.getRoloInfo(role_id);
			if(map!=null){
				result.setCode(1);
				result.setData(map);
			}
		}
		return result;
	}
	@RequiresPermissions("rightmanage:role:updateRole")
	@RequestMapping(value="/updateRole",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public APIResult<String> updateRole(HttpServletRequest request, HttpServletResponse response){
		APIResult<String> result=new APIResult<>();
		result.setCode(0);
		String role_id=request.getParameter("role_id");
		String role_name=request.getParameter("role_name");
		String remark=request.getParameter("remark");
		if(!StringUtil.IsNullOrEmpty(role_id)
				&&!StringUtil.IsNullOrEmpty(role_name)){
			boolean rs=roleService.updateRole(role_id,role_name,remark);
			if(rs){
				result.setCode(1);
			}
		}
		
		return result;
	}
	@RequiresPermissions("rightmanage:role:getAllRoles")
	@RequestMapping(value="/getAllRoles",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public APIResult<List<Map<String,Object>>> getAllRoles(HttpServletRequest request, HttpServletResponse response){
		APIResult<List<Map<String,Object>>> result=new APIResult<>();		
		List<Map<String,Object>> roles=roleService.getAllRoles();
		if(roles==null || roles.size()==0){
			result.setCode(0);
			result.setMsg("no data!");
		}else{	
			result.setCode(1);
			result.setData(roles);
		}
		return result;
	}
	@RequiresPermissions("rightmanage:role:updateRolePermissions")
	@RequestMapping(value="/updateRolePermissions",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public APIResult<String> updateRolePermissions(HttpServletRequest request, HttpServletResponse response){
		APIResult<String> result=new APIResult<>();
		result.setCode(0);
		String role_id=request.getParameter("role_id");
		String permission_id=request.getParameter("permission_id").trim();
		String[] permission_ids={};
		if(!StringUtil.IsNullOrEmpty(permission_id)){
			permission_ids=permission_id.split(",");
		}
		boolean rs=roleService.updateRolePermissions(role_id,permission_ids);
		if(rs){
			result.setCode(1);
		}
		return result;
	}
}
