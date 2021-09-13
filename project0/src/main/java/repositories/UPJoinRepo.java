package repositories;

import models.UPJoin;
import utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UPJoinRepo {

    private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

    // Get all projects by username (inner join)
    public List<UPJoin> getAll(String username) {

        List<UPJoin> projects = new ArrayList<>();

        try(Connection conn = cu.getConnection()) {

            String sql = "select u.\"username\", p.\"project_name\", p.\"project_num\" from music_app.\"users\" u " +
                    "inner join music_app.\"projects\" p " +
                    "on u.\"username\" = p.\"owner\" where username=?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                UPJoin p = new UPJoin(
                        rs.getString("username"),
                        rs.getString("project_name"),
                        rs.getInt("project_num")
                );

                projects.add(p);
            }
            return projects;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
