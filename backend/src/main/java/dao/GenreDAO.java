package dao;

import models.Genre;
import models.ProductionCompany;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GenreDAO {

    public static Genre get(Integer id) {

        Connection con = DBConnection.createConnection();
        Genre genre = new Genre();

        try {

            PreparedStatement statement = con.prepareStatement("SELECT * FROM genre WHERE genre_id = ?");
            statement.setInt(1, id);
            ResultSet res = statement.executeQuery();

            if (res.next()) {

                String genreName = res.getString("genre_name");

                genre.setId(id);
                genre.setName(genreName);

            }

        } catch (Exception e) {

            System.out.println("PANIC: Failed to get genre");
            System.out.println("ERROR: " + e.getMessage());

        }

        return genre;

    }

    public static Genre get(String name) {

        Connection con = DBConnection.createConnection();
        Genre genre = new Genre();

        try {

            PreparedStatement statement = con.prepareStatement("SELECT * FROM genre WHERE genre_name = ?");
            statement.setString(1, name);
            ResultSet res = statement.executeQuery();

            if (res.next()) {

                int genreId = res.getInt("genre_id");
                String genreName = res.getString("genre_name");

                genre.setId(genreId);
                genre.setName(genreName);

            }

        } catch (Exception e) {

            System.out.println("PANIC: Failed to get genre");
            System.out.println("ERROR: " + e.getMessage());

        }

        return genre;

    }

    public static ArrayList<Genre> getAll() {

        Connection con = DBConnection.createConnection();
        ArrayList<Genre> genres = new ArrayList<Genre>();

        try {

            PreparedStatement statement = con.prepareStatement("SELECT * FROM genre");
            ResultSet res = statement.executeQuery();

            while(res.next()) {

                Genre genre = new Genre();

                int genreId = res.getInt("genre_id");
                String genreName = res.getString("genre_name");

                genre.setId(genreId);
                genre.setName(genreName);

                genres.add(genre);

            }

        } catch (Exception e) {

            System.out.println("PANIC: Failed to get all genres");
            System.out.println("ERROR: " + e.getMessage());

        }

        return genres;

    }

}
