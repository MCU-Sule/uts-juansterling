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

public class UserDao implements DaoInterface<User> {
    @Override
    public ObservableList<User> getData() {
        ObservableList<User> uList;
        uList = FXCollections.observableArrayList();
        Connection conn = MyConnection.getConnection();
        String query = "SELECT * FROM User";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(query);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                int id = result.getInt("idUser");
                String name = result.getString("UserName");
                String pass = result.getString("UserPassword");
                User u = new User(id,name,pass);
                uList.add(u);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return uList;
    }

    @Override
    public void addData(User data) {
        Connection conn = MyConnection.getConnection();
        String query = "INSERT INTO User(idUser,UserName,UserPassword) VALUES(?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, data.getIdUser());
            ps.setString(2, data.getUsername());
            ps.setString(3, data.getPassword());
            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("data berhasil ditambahkan");
            }

        } catch (SQLException var6) {
            throw new RuntimeException(var6);
        }
    }

    @Override
    public void deleteData(User data) {
        Connection conn = MyConnection.getConnection();
        String query = "DELETE FROM User WHERE idUser = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, data.getIdUser());
            int result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("data berhasil dihapus");
            }

        } catch (SQLException var6) {
            throw new RuntimeException(var6);
        }
    }
    public int updateData(User data) {
        Connection conn = MyConnection.getConnection();
        String query = "UPDATE User SET UserName = ?, UserPassword = ? WHERE idUser = ?";
        PreparedStatement ps;
        int result;
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, data.getUsername());
            ps.setString(2, data.getPassword());
            ps.setInt(3, data.getIdUser());
            result = ps.executeUpdate();
            if (result > 0) {
                System.out.println("data berhasil diupdate");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
