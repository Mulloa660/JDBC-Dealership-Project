package com.yearup.dealership.db;

import com.mysql.cj.x.protobuf.MysqlxPrepare;
import com.yearup.dealership.models.Vehicle;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
    private DataSource dataSource;

    public VehicleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicle(Vehicle vehicle) {
        String query = "INSERT INTO vehicles (vin, make, model, year, sold, color, vehicleType, odometer, price) VALUES (?,?,?,?,?,?,?,?,?);";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, vehicle.getVin());
            preparedStatement.setString(2, vehicle.getMake());
            preparedStatement.setString(3, vehicle.getModel());
            preparedStatement.setInt(4, vehicle.getYear());
            preparedStatement.setBoolean(5, vehicle.isSold());
            preparedStatement.setString(6, vehicle.getColor());
            preparedStatement.setString(7, vehicle.getVehicleType());
            preparedStatement.setInt(8, vehicle.getOdometer());
            preparedStatement.setDouble(9, vehicle.getPrice());

            int rows = preparedStatement.executeUpdate();
            System.out.println(rows + "rows affected!");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void removeVehicle(String VIN) {
        // TODO: Implement the logic to remove a vehicle
        String query = "DELETE FROM vehicles WHERE vin = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, VIN);

            int rows = preparedStatement.executeUpdate();
            System.out.println(rows + "rows affected!");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public List<Vehicle> searchByPriceRange(double minPrice, double maxPrice) {
        // TODO: Implement the logic to search vehicles by price range
        String query = "SELECT minPrice, maxPrice FROM vehicles;";
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setDouble(1, minPrice);
            preparedStatement.setDouble(2, maxPrice);

            ResultSet results = preparedStatement.executeQuery();{
                while (results.next()) {
                    minPrice = results.getDouble("minPrice");
                    maxPrice = results.getDouble("maxPrice");

                    Vehicle vehicle = new Vehicle(minPrice, maxPrice);

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new ArrayList<>();

    }

    public List<Vehicle> searchByMakeModel(String make, String model) {
        // TODO: Implement the logic to search vehicles by make and model
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE make = ? AND model = ?;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, make);
            preparedStatement.setString(2, model);

            ResultSet results = preparedStatement.executeQuery();
            {
                while (results.next()) {
                    make = results.getString("make");
                    model = results.getString("model");

                    Vehicle vehicle = new Vehicle(make, model);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new ArrayList<>();
    }

        public List<Vehicle> searchByYearRange(int minYear, int maxYear) {
        // TODO: Implement the logic to search vehicles by year range
            List<Vehicle> vehicles = new ArrayList<>();
            String query = "SELECT minYear, maxYear FROM vehicles WHERE year BETWEEN ? AND ?;";
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query);) {
                preparedStatement.setInt(1,minYear);
                preparedStatement.setInt(2, maxYear);

                ResultSet results = preparedStatement.executeQuery();
                {
                    while (results.next()) {
                     minYear = results.getInt("minYear");
                        maxYear = results.getInt("maxYear");

                        Vehicle vehicle = new Vehicle(minYear, maxYear);
                    }
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return new ArrayList<>();

    }

    public List<Vehicle> searchByColor(String color) {
        // TODO: Implement the logic to search vehicles by color
        String query = "SELECT color FROM vehicles;";
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
             preparedStatement.setString(1, color);


            ResultSet results = preparedStatement.executeQuery();{
                while (results.next()) {
                    color = results.getString("color");


                    Vehicle vehicle = new Vehicle();

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new ArrayList<>();
    }

    public List<Vehicle> searchByMileageRange(int minMileage, int maxMileage) {
        // TODO: Implement the logic to search vehicles by mileage range
        String query = "SELECT minMileage, maxMileage FROM vehicles;";
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setInt(1, minMileage );
            preparedStatement.setInt(2, maxMileage);


            ResultSet results = preparedStatement.executeQuery();{
                while (results.next()) {
                    minMileage = results.getInt("minMileage");
                    maxMileage = results.getInt("maxMileage;");


                    Vehicle vehicle = new Vehicle(minMileage, maxMileage);

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new ArrayList<>();

    }

    public List<Vehicle> searchByType(String type) {
        // TODO: Implement the logic to search vehicles by type
        String query = "SELECT type FROM vehicles;";
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, type);


            ResultSet results = preparedStatement.executeQuery();{
                while (results.next()) {
                    type = results.getString("type");


                    Vehicle vehicle = new Vehicle();

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new ArrayList<>();

    }

    private Vehicle createVehicleFromResultSet(ResultSet resultSet) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setVin(resultSet.getString("VIN"));
        vehicle.setMake(resultSet.getString("make"));
        vehicle.setModel(resultSet.getString("model"));
        vehicle.setYear(resultSet.getInt("year"));
        vehicle.setSold(resultSet.getBoolean("SOLD"));
        vehicle.setColor(resultSet.getString("color"));
        vehicle.setVehicleType(resultSet.getString("vehicleType"));
        vehicle.setOdometer(resultSet.getInt("odometer"));
        vehicle.setPrice(resultSet.getDouble("price"));
        return vehicle;
    }
}
