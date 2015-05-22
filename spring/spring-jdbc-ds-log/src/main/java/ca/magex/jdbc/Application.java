package ca.magex.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class Application {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void run() {
		jdbcTemplate.execute("drop table test if exists");
		jdbcTemplate.execute("create table test(name varchar(255))");
		jdbcTemplate.update("INSERT INTO test(name) values (?)", "A");
		jdbcTemplate.update("INSERT INTO test(name) values (?)", "B");
		jdbcTemplate.update("INSERT INTO test(name) values (?)", "C");
		jdbcTemplate.query("select name from test", new RowMapper<String>() {
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("name");
			}
		});
	}

	@SuppressWarnings("resource")
	public static void main(String args[]) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-config.xml");
		Application app = context.getBean(Application.class);
		app.run();
	}

}