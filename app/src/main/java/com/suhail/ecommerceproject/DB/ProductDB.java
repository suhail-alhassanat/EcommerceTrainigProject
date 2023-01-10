package com.suhail.ecommerceproject.DB;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import com.suhail.ecommerceproject.models.Product;
import com.suhail.ecommerceproject.models.User;

import java.util.ArrayList;

public class ProductDB extends SQLiteOpenHelper {
    
    public static final String DB_NAME="products_db";
    public static final String USERS_TABLE_NAME="users";
    public static final String PRODUCTS_TABLE_NAME="products";
    public static final String USERS_COL_USER_NAME="userName";
    public static final String USERS_COL_PASSWORD="passowrd";
    public static final String PRODUCTS_COL_ID="id";
    public static final String PRODUCTS_COL_NAME="name";
    public static final String PRODUCTS_COL_PRICE="price";
    public static final String PRODUCTS_COL_DETAILS="details";
    public final static int DB_VERSION=1;


    public ProductDB(@Nullable Context context ) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+USERS_TABLE_NAME+"("+USERS_COL_USER_NAME+" text primary key, "+USERS_COL_PASSWORD+" text)");
        db.execSQL("create table "+PRODUCTS_TABLE_NAME+"("+PRODUCTS_COL_ID+" integer primary key autoincrement, "+PRODUCTS_COL_NAME+" text, "+PRODUCTS_COL_PRICE+" real, "+PRODUCTS_COL_DETAILS+" text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table "+USERS_TABLE_NAME);
        db.execSQL("drop table "+PRODUCTS_TABLE_NAME);
        onCreate(db);
    }
    public boolean createUser(User user){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERS_COL_USER_NAME,user.getUserName());
        values.put(USERS_COL_PASSWORD,user.getPassword());
        long result = db.insert(USERS_TABLE_NAME,null,values);
        return result != -1;
    }
    public boolean login(User user){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from "+USERS_TABLE_NAME+" where "+USERS_COL_USER_NAME+" = ? and "+USERS_COL_PASSWORD+" = ?",new String[]{user.getUserName(),user.getPassword()});
        if(c.getCount()>0)
            return true;
        else
            return false;
    }
    public boolean insertProduct(Product product){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PRODUCTS_COL_NAME,product.getName());
        values.put(PRODUCTS_COL_PRICE,product.getPrice());
        values.put(PRODUCTS_COL_DETAILS,product.getDetails());
        long result = db.insert(PRODUCTS_TABLE_NAME,null,values);
        return result != -1;
    }
    public boolean updateProduct(Product product){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PRODUCTS_COL_NAME,product.getName());
        values.put(PRODUCTS_COL_PRICE,product.getPrice());
        values.put(PRODUCTS_COL_NAME,product.getDetails());
        long result = db.update(PRODUCTS_TABLE_NAME,values,PRODUCTS_COL_ID+" = ?",new String[]{String.valueOf(product.getId())});
        return result != -1;
    }
    @SuppressLint("Range")
    public ArrayList<Product> getAllProducts(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Product> products = new ArrayList<>();
        Cursor c = db.rawQuery("select * from "+PRODUCTS_TABLE_NAME,null);
        while (c.moveToNext()){
            Product p = new Product();
            p.setId(c.getInt(c.getColumnIndex(PRODUCTS_COL_ID)));
            p.setName(c.getString(c.getColumnIndex(PRODUCTS_COL_NAME)));
            p.setDetails(c.getString(c.getColumnIndex(PRODUCTS_COL_DETAILS)));
            p.setPrice(c.getDouble(c.getColumnIndex(PRODUCTS_COL_PRICE)));
            products.add(p);
        }
        return products;
    }
    @SuppressLint("Range")
    public Product getProductById(int id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from "+PRODUCTS_TABLE_NAME+" where "+PRODUCTS_COL_ID+" = ?",new String[]{String.valueOf(id)});
        Product p = new Product();
        if(c.getCount()>0){
            p.setId(c.getInt(c.getColumnIndex(PRODUCTS_COL_ID)));
            p.setName(c.getString(c.getColumnIndex(PRODUCTS_COL_NAME)));
            p.setDetails(c.getString(c.getColumnIndex(PRODUCTS_COL_DETAILS)));
            p.setPrice(c.getDouble(c.getColumnIndex(PRODUCTS_COL_PRICE)));
        }
        return p;
    }
    public boolean deleteProduct(int id){
        SQLiteDatabase db = getWritableDatabase();
        long result = db.delete(PRODUCTS_TABLE_NAME,PRODUCTS_COL_ID+" = ?",new String[]{String.valueOf(id)});
        return result != -1;
    }
}
