package com.example.pc.qqlogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class registerActivity extends AppCompatActivity {


    private MyDatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private EditText username;
    private EditText password;
    private Button sure;
    private Button cancel;
    private EditText compare;
    private Boolean flag;
    private sqlOperater sqlOperater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dbHelper=new MyDatabaseHelper(this,"bookStore.db",null,1);

        //创建名为bookStore.db的数据库，版本号为1
        sure=(Button)findViewById(R.id.sure);
        cancel=(Button)findViewById(R.id.cancel);
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        compare=(EditText)findViewById(R.id.compare);

        flag=true;

        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=username.getText().toString();
                String firpass=password.getText().toString();
                String secpass=compare.getText().toString();
                if(!name.equals("")&&!firpass.equals("")&&!secpass.equals("")){
                    db=dbHelper.getWritableDatabase();
                    if(firpass.equals(secpass)){
                        Cursor cursor = db.query("user", new String[]{"username_sql"},
                                null, null, null, null, null);
                        while (cursor.moveToNext()){
                            String No = cursor.getString(cursor.getColumnIndex("username_sql"));
                            if(No.equals(name)){
                                flag=false;
                                Toast.makeText(registerActivity.this, "该用户已存在~", Toast.LENGTH_SHORT).show();
                            }else {
                                flag=true;
                            }
                        }
                        if(flag){
//                            sqlOperater=new sqlOperater();
//                            sqlOperater.InsertUser(name,firpass);
                            ContentValues values=new ContentValues();
                            values.put("username_sql",name);
                            values.put("password_sql",firpass);
                            values.put("remain_sql",1000);
                            values.put("power_sql",50);
                            values.put("speed_sql",50);
                            values.put("vitality_sql",50);
                            db.insert("user",null,values);
                            values.clear();
                            Toast.makeText(registerActivity.this, "创建成功~", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{
                        Toast.makeText(registerActivity.this, "密码不一致~", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(registerActivity.this, "填写完整内容~", Toast.LENGTH_SHORT).show();
                }






            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Toast.makeText(registerActivity.this, "exit~", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
