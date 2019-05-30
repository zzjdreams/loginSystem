package com.example.pc.qqlogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class User_infoActivity extends AppCompatActivity {

    private ListView lv;
    private ArrayList<UserPoke> userPokes;
    private Context mContext;
    private MyDatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private MyAdapter adapter;
    private Button change;
    private Button delete;
    private EditText in_name;
    private EditText in_no;
    private EditText in_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        dbHelper=new MyDatabaseHelper(this,"bookStore.db",null,1);
        db=dbHelper.getWritableDatabase();

        lv = (ListView) findViewById(R.id.userList);
        change=(Button)findViewById(R.id.change_btn);
        delete=(Button)findViewById(R.id.dele_btn);

        in_name=(EditText)findViewById(R.id.in_name);
        in_data=(EditText)findViewById(R.id.in_data);
        in_no=(EditText)findViewById(R.id.in_no);


        userPokes = new ArrayList<>();
        Cursor cursor = db.query("user", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String username_sql = cursor.getString(cursor.getColumnIndex("username_sql"));
                String password_sql = cursor.getString(cursor.getColumnIndex("password_sql"));
                int remain_sql = cursor.getInt(cursor.getColumnIndex("remain_sql"));
                int power_sql = cursor.getInt(cursor.getColumnIndex("power_sql"));
                int speed_sql = cursor.getInt(cursor.getColumnIndex("speed_sql"));
                int vitality_sql = cursor.getInt(cursor.getColumnIndex("vitality_sql"));
                UserPoke uc = new UserPoke(username_sql, password_sql,remain_sql,power_sql,speed_sql,vitality_sql);
                userPokes.add(uc);

            } while (cursor.moveToNext());
        }
        cursor.close();
        adapter = new MyAdapter(userPokes,User_infoActivity.this);
        lv.setAdapter(adapter);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUser(in_name.getText().toString(),in_no.getText().toString(),in_data.getText().toString());
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteUser(in_name.getText().toString());
            }
        });
    }
    public void updateUser(String usernameInput,String num,String changeWord){
        db=dbHelper.getWritableDatabase();
        ContentValues values2 = new ContentValues();
        switch (Integer.valueOf(num)){
            case 1:
                Toast.makeText(User_infoActivity.this, "用户名不可修改~", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                values2.put("password_sql",changeWord);
                db.update("user",values2,"username_sql = ?",new String[]{usernameInput});
                Toast.makeText(User_infoActivity.this, "密码"+changeWord+"修改成功~", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                values2.put("remain_sql",Integer.valueOf(changeWord));
                db.update("user",values2,"username_sql = ?",new String[]{usernameInput});
                Toast.makeText(User_infoActivity.this, "余额"+changeWord+"修改成功~", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                values2.put("power_sql",Integer.valueOf(changeWord));
                db.update("user",values2,"username_sql = ?",new String[]{usernameInput});
                Toast.makeText(User_infoActivity.this, "力量值"+changeWord+"修改成功~", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                values2.put("speed_sql",Integer.valueOf(changeWord));
                db.update("user",values2,"username_sql = ?",new String[]{usernameInput});
                Toast.makeText(User_infoActivity.this, "速度值"+changeWord+"修改成功~", Toast.LENGTH_SHORT).show();
                break;
            case 6:
                values2.put("vitality_sql",Integer.valueOf(changeWord));
                db.update("user",values2,"username_sql = ?",new String[]{usernameInput});
                Toast.makeText(User_infoActivity.this, "生命值"+changeWord+"修改成功~", Toast.LENGTH_SHORT).show();
                break;

        }

    }
    public void deleteUser(String usernameInput){
        db=dbHelper.getWritableDatabase();
        db.delete("user", "username_sql = ?", new String[]{usernameInput});
        Toast.makeText(User_infoActivity.this, "删除成功~", Toast.LENGTH_SHORT).show();
    }
}
