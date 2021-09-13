package repositories;

import models.Loop;
import utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoopRepo {

    private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

    // Read loop
    public Loop getByLoop (String loopTitle) {

        try (Connection conn = cu.getConnection()) {

            String sql = "select * from music_app\"loops\" where loop_title = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, loopTitle);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Loop l = new Loop(
                        rs.getInt("id"),
                        rs.getString("loop_title"),
                        rs.getBoolean("standard_sub"),
                        rs.getBoolean("premium_sub")
                );
                return l;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
