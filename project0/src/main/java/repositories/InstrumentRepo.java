package repositories;

import models.Instrument;
import utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InstrumentRepo {

    private ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

    // Read instrument
    public Instrument getByInstrument (String instrumentTitle) {

        try (Connection conn = cu.getConnection()) {

            String sql = "select * from music_app.\"instruments\" where instrument_title = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, instrumentTitle);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Instrument i = new Instrument(
                        rs.getInt("id"),
                        rs.getString("instrument_title"),
                        rs.getBoolean("standard_sub"),
                        rs.getBoolean("premium_sub")
                );
                return i;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
