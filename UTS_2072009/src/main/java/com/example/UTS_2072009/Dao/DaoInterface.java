package com.example.UTS_2072009.Dao;

import javafx.collections.ObservableList;

public interface DaoInterface<T> {
    ObservableList<T> getData();
    void addData(T data);
    void deleteData(T data);
}
