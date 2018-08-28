package com.plan.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.common.utils.DateUtils;
import com.common.utils.StringUtil;

@Component
public class TaskDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public boolean addTask(String user_id,String list_id, String task_name, String task_level,String task_date, String remark) {
		boolean result=false;
		Date date=new Date();
		try {
			if(!StringUtil.IsNullOrEmpty(task_date)){
				date=DateUtils.stringToDate(task_date, "yyyy-MM-dd");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		String sql="INSERT INTO pl_task(user_id,task_id,list_id,task_name,task_createtime,task_completetime,task_updatetime,task_state,task_level,remark) VALUES(?,?,?,?,?,NOW(),NOW(),0,?,?)";
		String content_sql="insert into pl_content(task_id) values(?)";
		try {
			String task_id="task_"+System.nanoTime();
			if(jdbcTemplate.update(sql,user_id,task_id,list_id,task_name,new java.sql.Date(date.getTime()),Integer.parseInt(task_level),remark)==1){
				jdbcTemplate.update(content_sql,task_id);
				result=true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public boolean deleteTask(String task_id) {
		boolean result=false;
		String sql="DELETE FROM pl_task WHERE task_id=?";
		String content_sql="delete from pl_content where task_id=?";
		try {
			if(jdbcTemplate.update(sql,task_id)==1){
				jdbcTemplate.update(content_sql,task_id);
				result=true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public boolean updateTask(String task_id,String list_id, String task_name, String task_state, String task_level, String remark) {
		boolean result=false;
		String sql="UPDATE pl_task SET list_id=?,task_name=?,task_level=?,remark=?,task_state=?,task_updatetime=NOW(),task_completetime=NOW() WHERE task_id=?";
		try {
			if(jdbcTemplate.update(sql,list_id,task_name,Integer.parseInt(task_level),remark,Integer.parseInt(task_state),task_id)==1){
				result=true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public List<String> getListIdByUserId(String user_id) {
		String sql="select list_id from pl_list where user_id=?";
		List<String> list=new ArrayList<>();
		try {
			for(Map<String,Object> map:jdbcTemplate.queryForList(sql,user_id)){
				list.add(map.get("list_id").toString());
			}
		} catch (Exception e) {
		}
		return list;
	}

	public List<Map<String, Object>> getTasks(String list_id, String task_state, int page_no, int page_size) {
		// TODO Auto-generated method stub
		String sql="select * from pl_task where list_id=?";
		try {
			if(!StringUtil.IsNullOrEmpty(task_state)){
				int state=Integer.parseInt(task_state);
				sql=sql+" and task_state="+state;
			}
		} catch (Exception e) {
		}finally {
			sql=sql+" order by task_updatetime desc,task_createtime desc";
			sql=sql+" limit "+(page_no-1)*page_size+","+page_size;
		}
		
		List<Map<String, Object>> list=new ArrayList<>();
		try {
			list.addAll(jdbcTemplate.queryForList(sql,list_id));
		} catch (Exception e) {
		}
		return list;
	}

	public int getTasksCount(String list_id, String task_state) {
		int rs=0;
		String sql="select * from pl_task where list_id=?";
		try {
			if(!StringUtil.IsNullOrEmpty(task_state)){
				int state=Integer.parseInt(task_state);
				sql=sql+" and task_state="+state;
			}
		} catch (Exception e) {
		}
		
		try {
			rs=rs+jdbcTemplate.queryForList(sql,list_id).size();
		} catch (Exception e) {
		}
		return rs;
	}

	public List<Map<String, Object>> getAllTasks(String user_id, String task_state, int page_no, int page_size) {
		// TODO Auto-generated method stub
				String sql="select * from pl_task where user_id=?";
				try {
					if(!StringUtil.IsNullOrEmpty(task_state)){
						int state=Integer.parseInt(task_state);
						sql=sql+" and task_state="+state;
					}
				} catch (Exception e) {
				}finally {
					sql=sql+" order by task_updatetime desc,task_createtime desc";
					sql=sql+" limit "+(page_no-1)*page_size+","+page_size;
				}
				
				List<Map<String, Object>> list=new ArrayList<>();
				try {
					list.addAll(jdbcTemplate.queryForList(sql,user_id));
				} catch (Exception e) {
				}
				return list;
	}

	public int getAllTasksCount(String user_id, String task_state) {
		int rs=0;
		String sql="select task_id from pl_task where user_id=?";
		try {
			if(!StringUtil.IsNullOrEmpty(task_state)){
				int state=Integer.parseInt(task_state);
				sql=sql+" and task_state="+state;
			}
		} catch (Exception e) {
		}
		
		try {
			rs=rs+jdbcTemplate.queryForList(sql,user_id).size();
		} catch (Exception e) {
		}
		return rs;
	}

	public Map<String, Object> getListInfo(String task_id) {
		String sql="select * from pl_task where task_id=?";
		Map<String, Object> map=null;
		try {
			if(!StringUtil.IsNullOrEmpty(task_id)){
				map=jdbcTemplate.queryForMap(sql,task_id);
			}
		} catch (Exception e) {
		}
		return map;
	}
}
