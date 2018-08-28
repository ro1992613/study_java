package com.common.spider;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Component  
@Configurable  
@EnableScheduling 
public class JandanPicTask {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public void work() throws Exception{
		int size=20;
		
		Document doc = Jsoup.connect("http://jandan.net/pic").get();
		Element index_el = doc.select("div.cp-pagenavi > a").first();
		int page=Integer.parseInt(index_el.text());
		
		for(int i=1;i<=size;i++){
			try {
				doc=null;
				final List<String> list=new ArrayList<>();
				final int final_page=page-i;
				String url="http://jandan.net/pic/page-"+final_page+"#comments";
				doc = Jsoup.connect(url).get();
				Elements pic_els=doc.select("a.view_img_link");
				for(Element e:pic_els){
					list.add(e.attr("href"));
				}
				jdbcTemplate.batchUpdate("insert into jandan_pic(pic,date,page) values(?,NOW(),?)", new BatchPreparedStatementSetter() {
					
					@Override
					public void setValues(PreparedStatement pst, int index) throws SQLException {
						// TODO Auto-generated method stub
						pst.setString(1, list.get(index));
						pst.setInt(2, final_page);
					}
					
					@Override
					public int getBatchSize() {
						// TODO Auto-generated method stub
						return list.size();
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
