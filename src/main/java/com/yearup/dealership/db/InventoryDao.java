package com.yearup.dealership.db;

import javax.sql.DataSource;
import java.sql.*;

public class InventoryDao {
    private DataSource dataSource;

    public InventoryDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicleToInventory(String vin, int dealershipId) {

        String query = "INSERT INTO inventory (dealership_id, vin) VALUES (?,?);";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setInt(1, dealershipId);
            preparedStatement.setString(2, vin);


            int rows = preparedStatement.executeUpdate();
            System.out.println(rows + "rows affected!");

        } catch (Exception ex) {
            ex.printStackTrace();
            // TODO: Implement the logic to add a vehicle to the inventory
        }
    }
        public void removeVehicleFromInventory(String vin){
            // TODO: Implement the logic to remove a vehicle from the inventory
            String query = "DELETE FROM inventory WHERE vin = ?";

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, vin);

                int rows = preparedStatement.executeUpdate();
                System.out.println(rows + "rows affected!");

            } catch (Exception ex) {
                ex.printStackTrace();
        }
    }
}