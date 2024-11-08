package net.developia.persistence;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.java.Log;

@Log
public class JDBCTests {
	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testConnection() {
		try (Connection con = 
				DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521/freepdb1",
						"ace",
						"ace")) {
//			System.out.println(con);
			log.info(con.toString());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
