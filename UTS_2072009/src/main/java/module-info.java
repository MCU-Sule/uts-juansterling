module com.example.UTS_2072009 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jasperreports;


    opens com.example.UTS_2072009 to javafx.fxml;
    exports com.example.UTS_2072009;

    opens com.example.UTS_2072009.Controller to javafx.fxml;
    exports com.example.UTS_2072009.Controller;

    opens com.example.UTS_2072009.Dao to javafx.fxml;
    exports com.example.UTS_2072009.Dao;

    opens com.example.UTS_2072009.Model to javafx.fxml;
    exports com.example.UTS_2072009.Model;

    opens com.example.UTS_2072009.Util to javafx.fxml;
    exports com.example.UTS_2072009.Util;
}