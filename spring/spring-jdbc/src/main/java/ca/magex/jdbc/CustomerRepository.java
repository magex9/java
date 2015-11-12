package ca.magex.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void init() throws Exception {
		System.out.println("Creating tables");
		jdbcTemplate.execute("drop table customers if exists");
		jdbcTemplate.execute("create table customers(" + "id serial, first_name varchar(255), last_name varchar(255))");

		String[] fullNames = new String[] { "John Woo", "Jeff Dean", "Josh Bloch", "Josh Long" };
		for (String fullname : fullNames) {
			String[] name = fullname.split(" ");
			System.out.printf("Inserting customer record for %s %s\n", name[0], name[1]);
			jdbcTemplate.update("INSERT INTO customers(first_name,last_name) values(?,?)", name[0], name[1]);
		}
	}

	public List<Customer> getCustomers() {
		System.out.println("Querying for customer records where first_name = 'Josh':");
		return jdbcTemplate.query("select id, first_name, last_name from customers where first_name = ?",
			new Object[] { "Josh" }, new RowMapper<Customer>() {
				@Override
				public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
					return new Customer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"));
				}
			});
	}

}
