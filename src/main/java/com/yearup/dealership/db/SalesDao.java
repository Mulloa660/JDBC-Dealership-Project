package com.yearup.dealership.db;

import com.yearup.dealership.models.SalesContract;

import javax.sql.DataSource;
import java.sql.*;

public class SalesDao {
    private DataSource dataSource;

    public SalesDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addSalesContract(SalesContract salesContract) {
        int keyReturned = 0;
        String query = "INSERT INTO sales_contracts (VIN, sales_date, price) VALUES (?,?,?);";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {
            preparedStatement.setString(1, salesContract.getVin());
            preparedStatement.setDate(2, Date.valueOf(salesContract.getSaleDate()));
            preparedStatement.setDouble(3, salesContract.getPrice());

            int rows = preparedStatement.executeUpdate();
            System.out.println(rows + "rows affected!");

        } catch (Exception ex) {
            ex.printStackTrace();
        // TODO: Implement the logic to add a sales contract
    }
}
