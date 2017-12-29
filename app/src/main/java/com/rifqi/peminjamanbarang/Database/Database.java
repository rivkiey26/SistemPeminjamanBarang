package com.rifqi.peminjamanbarang.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import com.rifqi.peminjamanbarang.Model.Alat;
import com.rifqi.peminjamanbarang.Model.Pinjam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 28/12/2017.
 */

public class Database extends SQLiteAssetHelper {
    private static final String DB_NAME = "PinjamDB.db";
    private static final int DB_VER = 1;
    public Database(Context context) {
        super(context,DB_NAME,null,DB_VER);


    }
    public List<Pinjam> getCarts()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();


        String[] sqlSelect = {"ProductName","ProductId","Quantity"};
        String sqlTable="PinjamDetail";

        qb.setTables(sqlTable);

        Cursor c = qb.query(db,sqlSelect,null,null,null,null,null);

        final List<Pinjam> result = new ArrayList<>();
        if (c.moveToFirst())
        {
            do {
                result.add(new Pinjam(c.getString(c.getColumnIndex("ProductId")),
                        c.getString(c.getColumnIndex("ProductName")),
                        c.getString(c.getColumnIndex("Quantity"))
                ));
            }while (c.moveToNext());
        }
        return result;
    }
    public void addToCart(Pinjam pinjam)
    {
        SQLiteDatabase db = getReadableDatabase();
        String query =  String.format("INSERT INTO PinjamDetail(ProductId,ProductName,Quantity) VALUES ('%s','%s','%s');",
                pinjam.getProductId(),
                pinjam.getProductName(),
                pinjam.getQuantity());
        db.execSQL(query);
    }

    public void cleanCart()
    {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM PinjamDetail");
        db.execSQL(query);
    }
}
