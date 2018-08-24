package com.example.tiago.mymovies.dao.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tiago.mymovies.dao.WatchedWhereDao;
import com.example.tiago.mymovies.model.WatchedWhere;

import java.util.ArrayList;
import java.util.List;

public class WatchedWhereDb implements WatchedWhereDao {

    private DbOpenHelper dbSqlite;

    public WatchedWhereDb(Context context) {
        this.dbSqlite = new DbOpenHelper(context);
    }

    @Override
    public List<WatchedWhere> listAll() {

        SQLiteDatabase db =  this.dbSqlite.getReadableDatabase();

        Cursor cursor = db.query("watched_where",
                new String[]{"id","name"},
                null,null,null,null,"name");

        List<WatchedWhere> watchedWhereList = new ArrayList<>();

        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));

            WatchedWhere watchedWhere = new WatchedWhere(id, name);
            watchedWhereList.add(watchedWhere);
        }
        return watchedWhereList;
    }
}
