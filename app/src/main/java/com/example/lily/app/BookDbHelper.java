package com.example.lily.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by gwoo on 2014. 6. 12..
 */
public class BookDbHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "book.db";

    private static final int DATABASE_VERSION = 1;


    private RuntimeExceptionDao<Book, Integer> bookDao = null;

    public BookDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

        try {
            TableUtils.createTable(connectionSource, Book.class);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

        try {
            TableUtils.dropTable(connectionSource, Book.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {

        bookDao = null;

        super.close();
    }


    public RuntimeExceptionDao<Book, Integer> getBookDao() {
        if (bookDao == null) {
            bookDao = getRuntimeExceptionDao(Book.class);
        }
        return bookDao;
    }

}
