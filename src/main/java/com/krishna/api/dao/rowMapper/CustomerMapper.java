package com.krishna.api.dao.rowMapper;

import com.krishna.api.mode.Customers;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class CustomerMapper implements RowMapper<Customers> {

    @Override
    public Customers mapRow (ResultSet rs, int rowNum) throws SQLException {
        Customers customer = new Customers();
        customer.setCustomerNumber(rs.getInt("customerNumber"));
        customer.setCustomerName(rs.getString("customerName"));
        return customer;
    }
}
