package com.demo.data;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import sun.security.provider.ConfigFile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by kingwu on 16/12/2016.
 */

@Repository
public class JdbcSpitterRepository implements SpitterRepository {
    private JdbcOperations jdbcOperations;

    @Autowired
    public JdbcSpitterRepository(JdbcOperations jdbcOperations){
        this.jdbcOperations = jdbcOperations;
    }

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    @CachePut(value = "spitterCache", key = "#result.id")
    public Spitter addSpitter(Spitter spitter) {
        String insert_sql = "insert into spitter(username, password, firstName, lastName, email) values(?, ?, ?, ?, ?)";
//        jdbcOperations.update(insert_sql,
//                spitter.getUsername(),
//                spitter.getPassword(),
//                spitter.getFirstName(),
//                spitter.getLastName(),
//                spitter.getEmail());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareCall(insert_sql);
                ps.setString(1, spitter.getUsername());
                ps.setString(2, spitter.getPassword());
                ps.setString(3, spitter.getFirstName());
                ps.setString(4, spitter.getLastName());
                ps.setString(5, spitter.getEmail());
                return ps;
            }
        }, keyHolder);

        
        long id = keyHolder.getKey().longValue();
        spitter.setId(id);
        return spitter;
    }

    @Override
    @Cacheable(value = "spitterCache")
    public Spitter findOne(long id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            //Spitter spitter = sqlSession.selectOne("com.demo.data.MyBatisMapper.selectSpitterByID", id);
            MyBatisMapper mapper = sqlSession.getMapper(MyBatisMapper.class);
            Spitter spitter = mapper.selectSpitter(id);
            System.out.println(spitter.toString());
            return spitter;
        }finally {
            sqlSession.close();
        }

//        String select_sql = "select * from spitter where id = ?";
//        return jdbcOperations.queryForObject(select_sql, new RowMapper<Spitter>(){
//
//            @Override
//            public Spitter mapRow(ResultSet resultSet, int i) throws SQLException {
//                Spitter spitter = new Spitter();
//                spitter.setId(resultSet.getLong("id"));
//                spitter.setUsername(resultSet.getString("username"));
//                spitter.setPassword(resultSet.getString("password"));
//                spitter.setFirstName(resultSet.getString("firstName"));
//                spitter.setLastName(resultSet.getString("lastName"));
//                spitter.setEmail(resultSet.getString("email"));
//                return spitter;
//            }
//        }, id);
    }
}
