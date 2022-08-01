package com.example.UTS_2072009.Controller;

import com.example.UTS_2072009.Dao.MovieDao;
import com.example.UTS_2072009.Dao.UserDao;
import com.example.UTS_2072009.Dao.WatchListDao;
import com.example.UTS_2072009.HelloApplication;
import com.example.UTS_2072009.Model.Movie;
import com.example.UTS_2072009.Model.User;
import com.example.UTS_2072009.Model.WatchList;
import com.example.UTS_2072009.Util.MyConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class MovieController {

    public TableView<Movie> table1;
    public TableView<WatchList> table2;
    public TableColumn<String, Movie> judul;
    public TableColumn<String, Movie> genre;
    public TableColumn<Integer, Movie> durasi;
    public TableColumn<Integer, WatchList> judwatch;
    public TableColumn<Integer, WatchList> lastwatch;
    public TableColumn<Integer, WatchList> isfav;
    public ListView<User> lvUser;
    public ComboBox<String> cmbGenre;
    private Stage stage;
    ObservableList<Movie> listmovie,listmovie2;
    ObservableList<User> listuser;
    ObservableList<WatchList> listwatch;
    ObservableList<String> listgenre;

    public void initialize() throws IOException {
        this.stage = new Stage();
        listmovie = FXCollections.observableArrayList();
        MovieDao mDao = new MovieDao();
        listgenre = FXCollections.observableArrayList(
                "All","Action","Musical","Comedy","Animated","Fantasy","Drama","Mystery","Thriller","Horror"
        );
        cmbGenre.setItems(listgenre);
        cmbGenre.getSelectionModel().select(0);
        this.showData();
    }
    public void showData() {
        MovieDao mdao = new MovieDao();
        this.listmovie = mdao.getData();

        this.table1.setItems(this.listmovie);
        this.judul.setCellValueFactory(new PropertyValueFactory("title"));
        this.genre.setCellValueFactory(new PropertyValueFactory("genre"));
        this.durasi.setCellValueFactory(new PropertyValueFactory("durasi"));

        UserDao udao = new UserDao();
        listuser = udao.getData();
        lvUser.setItems(listuser);

    }
    public void changeCombo(){
        MovieDao mdao = new MovieDao();
        listmovie.clear();
        if (!cmbGenre.getSelectionModel().getSelectedItem().equals("All")) {
            listmovie.addAll(mdao.filterData(cmbGenre.getSelectionModel().getSelectedItem()));
        } else {
            listmovie.addAll(mdao.getData());
        }
    }
    public void AddUserAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("UTSSecondPage.fxml"));
        Scene scene = new Scene((Parent)fxmlLoader.load(), 600.0, 300.0);
        UserController usercontroller = (UserController) fxmlLoader.getController();
        this.stage.setTitle("Tambah");
        this.stage.setScene(scene);
        this.stage.showAndWait();
        showData();
    }
    public void DelUserAction(){
        UserDao dao = new UserDao();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.OK, ButtonType.CANCEL);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            for (User u: lvUser.getSelectionModel().getSelectedItems()) {
                dao.deleteData(u);
            }
        }
        showData();
    }
    public void printReport(){
        JasperPrint jp;
        Connection conn = MyConnection.getConnection();
        Map param = new HashMap();
        try {
            jp = JasperFillManager.fillReport("report/Movie.jasper", param, conn);
            JasperViewer viewer = new JasperViewer(jp, false);
            viewer.setTitle("Menu Report Filtered");
            viewer.setVisible(true);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }
    public void fav(){
        WatchListDao wdao = new WatchListDao();
        this.listwatch = wdao.filterData(lvUser.getSelectionModel().getSelectedItem());

        this.table2.setItems(this.listwatch);
        this.judwatch.setCellValueFactory(new PropertyValueFactory("movie_idmovie"));
        this.lastwatch.setCellValueFactory(new PropertyValueFactory("Durasi"));
        this.isfav.setCellValueFactory(new PropertyValueFactory("BoolFav"));
    }
}
