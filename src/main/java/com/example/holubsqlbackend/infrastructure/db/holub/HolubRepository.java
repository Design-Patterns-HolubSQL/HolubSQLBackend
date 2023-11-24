package com.example.holubsqlbackend.infrastructure.db.holub;

import com.example.holubsqlbackend.infrastructure.db.holub.database.Database;
import com.example.holubsqlbackend.infrastructure.db.holub.database.Table;
import com.example.holubsqlbackend.infrastructure.db.holub.text.ParseFailure;

import java.io.*;

public class HolubRepository {
    Database database;


    public HolubRepository(){
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
