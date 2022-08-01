package com.example.UTS_2072009.Dao;

import com.example.UTS_2072009.Model.Movie;
import com.example.UTS_2072009.Model.User;
import com.example.UTS_2072009.Model.WatchList;
import com.example.UTS_2072009.Util.MyConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WatchListDao implements DaoInterface<WatchList> {
    @Override
    public ObservableList<WatchList> getData() {
        ObservableList<WatchList> wList;
        wList = FXCollections.observableArrayList();
        Connection conn = MyConnection.getConnection();
        String query = "SELECT * FROM watchlist w JOIN movie m ON w.Movie_idMovie = m.idMovie JOIN user u ON w.User_idUser = u.idUser";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(query);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                int idWatch = result.getInt("idWatchList");
                int last = result.getInt("LastWatch");
                int fav = result.getInt("Favorite");

                int idMovie = result.getInt("idMovie");
                String title = result.getString("Title");
                String genre = result.getString("Genre");
                int durasi = result.getInt("Durasi");

                int idUser = result.getInt("idUser");
                String username = result.getString("UserName");
                String userpass = result.getString("UserPassword");
                Movie m = new Movie(idMovie, title, genre, durasi);
                User u = new User(idUser, username, userpass);
                WatchList w = new WatchList(idWatch, last, fav, m, u);
                wList.add(w);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return wList;

    }

    @Override
    public void addData(WatchList data) {
        Connection conn = MyConnection.getConnection();
        String query = "INSERT INTO User(idWatchList,LastWatch,Favorite,Movie_idMovie,User_iduser) VALUES(?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, data.getIdwatchlist());
            ps.setInt(2, data.getLastwatch());
            ps.setInt(3, data.getFavourite());
            ps.setInt(4, data.getFavourite());
            ps.setInt(5, data.getFavourite());
            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("data berhasil ditambahkan");
            }

        } catch (SQLException var6) {
            throw new RuntimeException(var6);
        }
    }

    @Override
    public void deleteData(WatchList data) {
        Connection conn = MyConnection.getConnection();
        String query = "DELETE FROM WatchList WHERE idWatchList = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, data.getIdwatchlist());
            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("data berhasil dihapus");
            }

        } catch (SQLException var6) {
            throw new RuntimeException(var6);
        }
    }
    public int updateData(WatchList data) {
        Connection conn = MyConnection.getConnection();
        String query = "UPDATE WatchList SET LastWatch = ?, Favorite = ?, Movie_idMovie = ?, User_idUser = ? WHERE idWatchList = ?";
        PreparedStatement ps;
        int result;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, data.getLastwatch());
            ps.setInt(2, data.getFavourite());
            ps.setInt(3, data.getMovie_idmovie().getIdMovie());
            ps.setInt(4, data.getUser_iduser().getIdUser());
            ps.setInt(5, data.getIdwatchlist());
            result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("data berhasil diupdate");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    public ObservableList<WatchList> filterData(User userSelect) {
        ObservableList<WatchList> wList;
        wList = FXCollections.observableArrayList();
        Connection conn = MyConnection.getConnection();
        String query = "SELECT * FROM watchlist w JOIN movie m ON w.Movie_idMovie = m.idMovie JOIN user u ON w.User_idUser = u.idUser WHERE w.User_idUser = ?";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, userSelect.getIdUser());
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                int idWatch = result.getInt("idWatchList");
                int last = result.getInt("LastWatch");
                int fav = result.getInt("Favorite");

                int idMovie = result.getInt("idMovie");
                String title = result.getString("Title");
                String genre = result.getString("Genre");
                int durasi = result.getInt("Durasi");

                int idUser = result.getInt("idUser");
                String username = result.getString("UserName");
                String userpass = result.getString("UserPassword");
                Movie m = new Movie(idMovie, title, genre, durasi);
                User u = new User(idUser, username, userpass);
                WatchList w = new WatchList(idWatch, last, fav, m, u);
                wList.add(w);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return wList;
    }
}
