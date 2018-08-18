package com.example.tiago.mymovies.dao.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tiago.mymovies.dao.CategoryDao;
import com.example.tiago.mymovies.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryDaoDb implements CategoryDao {

    private DbOpenHelper dbSqlite;

    public CategoryDaoDb(Context context) {
        this.dbSqlite = new DbOpenHelper(context);
    }

    @Override
    public List<Category> listAll() {

        SQLiteDatabase db =  this.dbSqlite.getReadableDatabase();

        Cursor cursor = db.query("category",
                new String[]{"id","name"},
                null,null,null,null,"name");

        List<Category> categoriesList = new ArrayList<>();

        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));

            Category category = new Category(id, name);
            categoriesList.add(category);
        }
        return categoriesList;
    }
}
