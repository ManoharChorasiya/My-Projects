package com.info.emicalc.daoimpl;



import java.sql.*;

import com.info.emicalc.dao.UserDAO;
import com.info.emicalc.entity.User;
import com.info.emicalc.util.DBConnection;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean register(User user) {
        String sql = "INSERT INTO users(username, password, full_name) VALUES (?, ?, ?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword()); 
            ps.setString(3, user.getFullName());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public User login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username=? AND password=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(rs.getInt("id"),
                                    rs.getString("username"),
                                    rs.getString("password"),
                                    rs.getString("full_name"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean isUsernameTaken(String username) {
        String sql = "SELECT id FROM users WHERE username=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return true; 
        }
    }
}
