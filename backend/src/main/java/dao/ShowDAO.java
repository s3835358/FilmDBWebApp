package dao;

import models.Genre;
import models.Show;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ShowDAO {

    public static Show get(Integer showId) {

        Connection con = DBConnection.createConnection();
        Show show = new Show();

        try {

            PreparedStatement statement = con.prepareStatement("SELECT * FROM show WHERE id = ?");
            statement.setInt(1, showId);
            ResultSet res = statement.executeQuery();

            if (res.next()) {

                show.setId(res.getInt("show_id"));
                show.setTitle(res.getString("show_title"));
                show.setGenre(res.getInt("genre"));
                show.setLength(res.getInt("length"));
                show.setType(res.getString("type"));
                show.setProcoId(res.getInt("proco_id"));
                show.setYear(res.getInt("year"));
                show.setAddedOn(res.getInt("added_on"));
                show.setStatus(res.getInt("status"));

            }

        } catch (Exception e) {

            System.out.println("PANIC: Failed to get show");
            System.out.println("ERROR: " + e.getMessage());

        }

        return show;

    }

    public static ArrayList<Show> getPendingAutoApproval() {

        Connection con = DBConnection.createConnection();
        ArrayList<Show> shows = new ArrayList<Show>();

        try {

            PreparedStatement statement = con.prepareStatement("SELECT * FROM show WHERE status = 3");
            ResultSet res = statement.executeQuery();

            while (res.next()) {

                Show show = new Show();

                show.setId(res.getInt("show_id"));
                show.setTitle(res.getString("show_title"));
                show.setGenre(res.getInt("genre"));
                show.setLength(res.getInt("length"));
                show.setType(res.getString("type"));
                show.setProcoId(res.getInt("proco_id"));
                show.setYear(res.getInt("year"));
                show.setAddedOn(res.getInt("added_on"));
                show.setStatus(res.getInt("status"));

                shows.add(show);

            }

        } catch (Exception e) {

            System.out.println("PANIC: Failed to get shows");
            System.out.println("ERROR: " + e.getMessage());

        }

        return shows;

    }

    public static ArrayList<Show> getPending() {

        Connection con = DBConnection.createConnection();
        ArrayList<Show> shows = new ArrayList<Show>();

        try {

            PreparedStatement statement = con.prepareStatement("SELECT * FROM show WHERE status = 0");
            ResultSet res = statement.executeQuery();

            while (res.next()) {

                Show show = new Show();

                show.setId(res.getInt("show_id"));
                show.setTitle(res.getString("show_title"));
                show.setGenre(res.getInt("genre"));
                show.setLength(res.getInt("length"));
                show.setType(res.getString("type"));
                show.setProcoId(res.getInt("proco_id"));
                show.setYear(res.getInt("year"));
                show.setAddedOn(res.getInt("added_on"));
                show.setStatus(res.getInt("status"));

                shows.add(show);

            }

        } catch (Exception e) {

            System.out.println("PANIC: Failed to get shows");
            System.out.println("ERROR: " + e.getMessage());

        }

        return shows;

    }

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

    public static void update(Show show) {

        Connection con = DBConnection.createConnection();

        try {

            // Create a MySQL prepared statement

            PreparedStatement statement = con.prepareStatement("UPDATE `show` SET show_title = ?, genre = ?, length = ?, type = ?, proco_id = ?, year = ?, added_on = ?, status = ? WHERE show_id = ?");

            // Plug in the parameters

            statement.setString(1, show.getTitle());
            statement.setInt(2, show.getGenre());
            statement.setDouble(3, show.getLength());
            statement.setString(4, show.getType());
            statement.setInt(5, show.getProcoId());
            statement.setInt(6, show.getYear());
            statement.setString(7, String.valueOf(show.getAddedOn()));
            statement.setInt(8, show.getStatus());
            statement.setInt(9, show.getId());

            // Insert the row

            statement.executeUpdate();

            return;

        } catch (Exception e) {

            System.out.println("PANIC: Error updating show");
            System.out.println("ERROR: " + e.getMessage());

        }

        DBConnection.closeConnection(con);

    }

}