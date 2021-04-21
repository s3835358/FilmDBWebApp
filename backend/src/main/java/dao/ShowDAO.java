package dao;

import models.Show;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ShowDAO {

    public static void add(Show show) {

        Connection con = DBConnection.createConnection();

        try {

            // Create a MySQL prepared statement

            PreparedStatement statement = con.prepareStatement("INSERT INTO `show` (show_title, genre, length, type, proco_id, year, added_on, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

            // Plug in the parameters

            statement.setString(1, show.getTitle());
            statement.setInt(2, show.getGenre());
            statement.setDouble(3, show.getLength());
            statement.setString(4, show.getType());
            statement.setInt(5, show.getProcoId());
            statement.setInt(6, show.getYear());
            statement.setString(7, String.valueOf(show.getAddedOn()));
            statement.setInt(8, show.getStatus());

            // Insert the row

            statement.executeUpdate();

            return;

        } catch (Exception e) {

            System.out.println("PANIC: Error inserting show");
            System.out.println("ERROR: " + e.getMessage());

        }

        DBConnection.closeConnection(con);

    }

}