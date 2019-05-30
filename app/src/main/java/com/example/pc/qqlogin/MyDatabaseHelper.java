package com.example.pc.qqlogin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by draem on $(DATE)
 **/
public class MyDatabaseHelper extends SQLiteOpenHelper {


    //向数据库中加用户名（key）、密码、余额、力量值、速度值、生命值

    private Context mContext;

    public MyDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user(username_sql VARCHAR(20) NOT NULL,password_sql VARCHAR(20) NOT NULL,remain_sql integer," +
                "power_sql integer,speed_sql integer,vitality_sql integer,PRIMARY KEY (username_sql))");
        Toast.makeText(mContext,"注册成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
