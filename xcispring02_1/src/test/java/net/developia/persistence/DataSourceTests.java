package net.developia.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.extern.java.Log;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log
public class DataSourceTests {
	// root-context.xml에 정의된 bean 중 DataSource를 구현한 클래스를 가져옴.
	@Autowired
	private DataSource dataSource;

	@Test
	public void testConnection() {
		try (Connection con = dataSource.getConnection()) {
			log.info(con.toString());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
