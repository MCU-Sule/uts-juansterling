package com.example.UTS_2072009.Controller;

import com.example.UTS_2072009.Dao.UserDao;
import com.example.UTS_2072009.Model.User;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class UserController {
    public TextField txtUserName;
    public TextField txtPassword;

    public void submit(){
        UserDao dao = new UserDao();
        if (txtUserName.getText() != null && txtPassword.getText() != null) {
            dao.addData(new User(0,txtUserName.getText(),txtPassword.getText()));
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Please Fill all the field", new ButtonType[]{ButtonType.OK});
            alert.showAndWait();
        }
    }
}
