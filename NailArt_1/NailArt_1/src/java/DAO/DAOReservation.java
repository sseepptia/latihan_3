package DAO;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import DAO.ConnectionManager;
import POJO.Reservation;

public class DAOReservation {

    public List<Reservation> getAllReservations() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Reservation> reservations = new ArrayList<>();

        try {
            connection = ConnectionManager.getConnection();

            if (connection != null) {
                String query = "SELECT id, name, phoneNumber, date, hours FROM reservations";
                preparedStatement = connection.prepareStatement(query);

                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Reservation reservation = new Reservation();
                    reservation.setId(resultSet.getInt("id"));
                    reservation.setName(resultSet.getString("name"));
                    reservation.setPhoneNumber(resultSet.getString("phoneNumber"));
                    reservation.setDate(resultSet.getTimestamp("date"));
                    reservation.setHours(resultSet.getTimestamp("hours"));

                    reservations.add(reservation);
                }
            } else {
                System.out.println("Connection is null. Please check your database connection.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return reservations;
    }

    public boolean insertReservation(Reservation reservation) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionManager.getConnection();

            if (connection != null) {
                String query = "INSERT INTO reservations (name, phoneNumber, date, hours) VALUES (?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(query);

                preparedStatement.setString(1, reservation.getName());
                preparedStatement.setString(2, reservation.getPhoneNumber());
                preparedStatement.setTimestamp(3, new java.sql.Timestamp(reservation.getDate().getTime()));
                preparedStatement.setTimestamp(4, new java.sql.Timestamp(reservation.getHours().getTime()));

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected == 1; // Returns true if one row was inserted, false otherwise
            } else {
                System.out.println("Connection is null. Please check your database connection.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false in case of an exception
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean updateReservation(Reservation reservation) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionManager.getConnection();

            if (connection != null) {
                String query = "UPDATE reservations SET name=?, phoneNumber=?, date=?, hours=? WHERE id=?";
                preparedStatement = connection.prepareStatement(query);

                preparedStatement.setString(1, reservation.getName());
                preparedStatement.setString(2, reservation.getPhoneNumber());
                preparedStatement.setTimestamp(3, new java.sql.Timestamp(reservation.getDate().getTime()));
                preparedStatement.setTimestamp(4, new java.sql.Timestamp(reservation.getHours().getTime()));
                preparedStatement.setInt(5, reservation.getId());

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected == 1; // Returns true if one row was updated, false otherwise
            } else {
                System.out.println("Connection is null. Please check your database connection.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false in case of an exception
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean deleteReservation(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionManager.getConnection();

            if (connection != null) {
                String query = "DELETE FROM reservations WHERE id = ?";
                preparedStatement = connection.prepareStatement(query);

                preparedStatement.setInt(1, id);

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected == 1; // Returns true if one row was deleted, false otherwise
            } else {
                System.out.println("Connection is null. Please check your database connection.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false in case of an exception
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public Reservation getReservationById(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Reservation reservation = null;

        try {
            connection = ConnectionManager.getConnection();

            if (connection != null) {
                String query = "SELECT id, name, phoneNumber, date, hours FROM reservations WHERE id = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, id);

                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    reservation = new Reservation();
                    reservation.setId(resultSet.getInt("id"));
                    reservation.setName(resultSet.getString("name"));
                    reservation.setPhoneNumber(resultSet.getString("phoneNumber"));
                    reservation.setDate(resultSet.getTimestamp("date"));
                    reservation.setHours(resultSet.getTimestamp("hours"));
                }
            } else {
                System.out.println("Connection is null. Please check your database connection.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return reservation;
    }
}
