package com.example.UTS_2072009.Dao;

import com.example.UTS_2072009.Model.Movie;
import com.example.UTS_2072009.Util.MyConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieDao implements DaoInterface<Movie> {
    @Override
    public ObservableList<Movie> getData() {
        ObservableList<Movie> mList;
        mList = FXCollections.observableArrayList();
        Connection conn = MyConnection.getConnection();
        String query = "SELECT * FROM Movie";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(query);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                int id = result.getInt("idMovie");
                String judul = result.getString("Title");
                String genre = result.getString("Genre");
                int durasi = result.getInt("Durasi");
                Movie m = new Movie(id,judul,genre,durasi);
                mList.add(m);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return mList;
    }

    @Override
    public void addData(Movie data) {
        Connection conn = MyConnection.getConnection();
        String query = "INSERT INTO Movie(idMovie,Title,Genre,Durasi) VALUES(?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, data.getIdMovie());
            ps.setString(2, data.getTitle());
            ps.setString(3, data.getGenre());
            ps.setInt(4, data.getDurasi());
            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("data berhasil ditambahkan");
            }

        } catch (SQLException var6) {
            throw new RuntimeException(var6);
        }
    }

    @Override
    public void deleteData(Movie data) {
        Connection conn = MyConnection.getConnection();
        String query = "DELETE FROM Movie WHERE idMovie = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, data.getIdMovie());
            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("data berhasil dihapus");
            }

        } catch (SQLException var6) {
            throw new RuntimeException(var6);
        }
    }
    public ObservableList<Movie> filterData(String data) {
        ObservableList<Movie> mList;
        mList = FXCollections.observableArrayList();
        Connection conn = MyConnection.getConnection();
        String query = "SELECT * FROM Movie  WHERE Genre Like '%' ? '%'";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1,data);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                int id = result.getInt("idMovie");
                String judul = result.getString("Title");
                String genre = result.getString("Genre");
                int durasi = result.getInt("Durasi");
                Movie m = new Movie(id,judul,genre,durasi);
                mList.add(m);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return mList;
    }
    public int updateData(Movie data) {
        int result;
        Connection conn = MyConnection.getConnection();
        String query = "UPDATE Movie SET Title=?,Genre=?,Durasi=? WHERE idMovie = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, data.getTitle());
            ps.setString(2, data.getGenre());
            ps.setInt(3, data.getDurasi());
            ps.setInt(4, data.getIdMovie());

            result = ps.executeUpdate();

        } catch (SQLException var6) {
            throw new RuntimeException(var6);
        }
        return result;
    }
}
