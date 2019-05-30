package com.example.pc.qqlogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

/**
 * Created by draem on 2019/5/2
 **/
public class sqlOperater {

    private Context mContext;
    private MyDatabaseHelper dbHelper;
    private SQLiteDatabase db;

    private String usernameInput;
    private String passwordInput;
    private int power;
    private int speed;
    private int vitality;
    private int remain;



    public void InsertUser(String usernameInput,String passwordInput){
//        db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("username_sql",usernameInput);
        values.put("password_sql",passwordInput);
        values.put("remain_sql",1000);
        values.put("power_sql",50);
        values.put("speed_sql",50);
        values.put("vitality_sql",50);
        db.insert("user",null,values);
        values.clear();
    }

    public void deleteUser(String usernameInput){
        db=dbHelper.getWritableDatabase();
        db.delete("user", "username_sql = ?", new String[]{"usernameInput"});
    }

    public void updateUser(String usernameInput,int num,String changeWord){
        db=dbHelper.getWritableDatabase();
        ContentValues values2 = new ContentValues();
        switch (num){
            case 1:
                Toast.makeText(mContext, "用户名不可修改~", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                values2.put("password_sql",changeWord);
                db.update("user",values2,"username_sql = ?",new String[]{"usernameInput"});
                Toast.makeText(mContext, "密码修改成功~", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                values2.put("remain_sql",Integer.getInteger(changeWord));
                db.update("user",values2,"username_sql = ?",new String[]{"usernameInput"});
                Toast.makeText(mContext, "密码修改成功~", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                values2.put("power_sql",Integer.getInteger(changeWord));
                db.update("user",values2,"username_sql = ?",new String[]{"usernameInput"});
                Toast.makeText(mContext, "力量值修改成功~", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                values2.put("speed_sql",Integer.getInteger(changeWord));
                db.update("user",values2,"username_sql = ?",new String[]{"usernameInput"});
                Toast.makeText(mContext, "速度值修改成功~", Toast.LENGTH_SHORT).show();
                break;
            case 6:
                values2.put("vitality_sql",Integer.getInteger(changeWord));
                db.update("user",values2,"username_sql = ?",new String[]{"usernameInput"});
                Toast.makeText(mContext, "生命值修改成功~", Toast.LENGTH_SHORT).show();
                break;

        }

    }

    public void queryUser(String usernameInput){
        db=dbHelper.getWritableDatabase();
        Cursor cursor = db.query("user", null, null, null, null, null, null);
        if (cursor.moveToFirst()){
            do {
                String username_sql=cursor.getString(cursor.getColumnIndex("username_sql"));
                String password_sql=cursor.getString(cursor.getColumnIndex("password_sql"));
                String remain_sql=cursor.getString(cursor.getColumnIndex("remain_sql"));
                String power_sql=cursor.getString(cursor.getColumnIndex("power_sql"));
                String speed_sql=cursor.getString(cursor.getColumnIndex("speed_sql"));
                String vitality_sql=cursor.getString(cursor.getColumnIndex("vitality_sql"));
            } while (cursor.moveToNext());
        }
        cursor.close();
    }




}
