package com.es.webservice.dao.base;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: nieyunfei
 * Date: 11-6-8
 * Time: ����2:41
 * To change this template use File | Settings | File Templates.
 */
public class BaseSpringJdbcDao {

    @Resource
    protected JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List findBySql(String sql) {
        List<HashMap> result=new ArrayList<HashMap>();
        try{
           result=jdbcTemplate.query(sql,new RowMapper() {
                public Object mapRow(ResultSet rs,int index) throws SQLException {
                    HashMap item=new HashMap();
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        item.put(rs.getMetaData().getColumnName(i),rs.getObject(i));
                    }
                    return item;
                }
            } );
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
