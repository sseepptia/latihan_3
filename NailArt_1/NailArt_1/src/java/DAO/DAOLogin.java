/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import POJO.Login;
import DAO.ConnectionManager;
 
public class DAOLogin {
    private DataSource dataSource; // Inject your DataSource or connection pool

    public Login findUserByEmail(String email) {
        Connection connection = ConnectionManager.getConnection();
        try (
             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM login WHERE email = ?")) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Login login = new Login();
                login.setEmail(rs.getString("email"));
                login.setPassword(rs.getString("password"));
                return login;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveLogin(Login login) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement("INSERT INTO login (email, password) VALUES (?, ?)")) {
            stmt.setString(1, login.getEmail());
            stmt.setString(2, login.getPassword());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
