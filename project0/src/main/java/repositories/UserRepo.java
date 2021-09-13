package repositories;

import models.User;
import utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepo {

    private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

    // Read user
    public User getByUsername(String username) {

        try (Connection conn = cu.getConnection()) {

            String sql = "select * from music_app\"users\" where username = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User u = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getBoolean("standard_sub"),
                        rs.getBoolean("premium_sub")
                );
                return u;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Create user
    public void add(String username, String password, Boolean stdSub, Boolean prmSub) {

        try (Connection conn = cu.getConnection()) {

            String sql = "insert into music_app.\"users\" values (default, ?, ?, ?, ?) returning *";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setBoolean(3, stdSub);
            ps.setBoolean(4, prmSub);

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
