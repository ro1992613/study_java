package junit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class JDBCTest extends BaseJunit{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Test
	public void test1() {
		String sql="select * from test";
		try {
			System.out.println(jdbcTemplate.queryForList(sql));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
