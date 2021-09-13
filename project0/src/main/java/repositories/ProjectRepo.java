package repositories;

import models.Project;
import utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepo {

    private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();


    // Read project
    public Project getByProject (String projectName) {

        try (Connection conn = cu.getConnection()){

            String sql = "select * from music_app.\"projects\" where project_name = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, projectName);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Project p = new Project(
                        rs.getInt("id"),
                        rs.getString("project_name"),
                        rs.getInt("project_num"),
                        rs.getString("owner")
                );
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Create project
    public void add(String pName, Integer pNum, String owner) {

        try (Connection conn = cu.getConnection()) {

            String sql = "insert into music_app\"projects\" values (default, ?, ?, ?) returning *";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, pName);
            ps.setInt(2, pNum);
            ps.setString(3, owner);

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
