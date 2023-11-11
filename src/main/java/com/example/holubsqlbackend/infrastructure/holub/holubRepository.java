package com.example.holubsqlbackend.infrastructure.holub;

import com.example.holubsqlbackend.infrastructure.holub.database.Database;
import com.example.holubsqlbackend.infrastructure.holub.database.Table;
import com.example.holubsqlbackend.infrastructure.holub.text.ParseFailure;

import java.io.*;

public class holubRepository {
    Database database;


    public holubRepository(){
        try {
            database = new Database(new File("Dbase"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Table getTable(String sqlQuery){
        try {
            return database.execute(sqlQuery);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseFailure e) {
            throw new RuntimeException(e);
        }
    }

}
