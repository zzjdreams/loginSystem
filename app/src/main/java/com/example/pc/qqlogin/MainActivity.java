package com.example.pc.qqlogin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    private EditText ed1;
    private EditText ed2;
    private Button register;
    private Button admin;
    private Button btn;
    private CheckBox cb;
    private SharedPreferences.Editor editor;
    private SharedPreferences pref;
    private Context mContext;
    private MyDatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private static final String TAG = "MainActivity";
    private Boolean flag;
    private UserPoke up;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper=new MyDatabaseHelper(this,"bookStore.db",null,1);
        pref= PreferenceManager.getDefaultSharedPreferences(this);
        ed1=(EditText)findViewById(R.id.username);
        ed2=(EditText)findViewById(R.id.password);
        btn=(Button)findViewById(R.id.btn);
        register=(Button)findViewById(R.id.register);
        admin=(Button)findViewById(R.id.admin);
        cb=(CheckBox)findViewById(R.id.cb);
        up=new UserPoke();

        boolean isRemember=pref.getBoolean("cb",false);
        if(isRemember){
            String username=pref.getString("username","");
            String password=pref.getString("password","");
            //保存账号和密码
            ed1.setText(username);
            ed2.setText(password);

            cb.setChecked(true);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = ed1.getText().toString();
                String password = ed2.getText().toString();
                db = dbHelper.getWritableDatabase();
                if (username.equals("") || password.equals("")){
                    Toast.makeText(MainActivity.this, "请输入账号或密码", Toast.LENGTH_SHORT).show();
                }else {
                    Cursor cursor = db.query("user", new String[]{"username_sql", "password_sql"}, null,
                            null, null, null, null);
                    boolean login = false;//账号密码是否匹配
                    //从数据库中匹配账号密码
                    while (cursor.moveToNext()) {
                        if (username.equals(cursor.getString(cursor.getColumnIndex("username_sql")))
                                && password.equals(cursor.getString(cursor.getColumnIndex("password_sql")))) {
                            up.setUsername(username);

                            login = true;
                            break;
                        }
                    }
                    if(login){
                        shareSave(username,password);
                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                        intent.putExtra("username",username);
                        startActivity(intent);
                    }else {
                        Toast.makeText(MainActivity.this, "账号与密码不匹配", Toast.LENGTH_SHORT).show();
                    }

                }
            }

        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,registerActivity.class);
                startActivity(intent);
            }
        });
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = ed1.getText().toString();
                String password = ed2.getText().toString();
                if (username.equals("dream") && password.equals("123456")) {
                    shareSave(username,password);
                    Toast.makeText(MainActivity.this, "用户名和密码保存，登陆成功",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, User_infoActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "用户名和密码未保存，登陆失败",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void shareSave( String username,String password){
        editor = pref.edit();
        if (cb.isChecked()) {
            editor.putString("username", username);
            editor.putString("password", password);
            editor.putBoolean("cb", true);

        } else {
            editor.clear();
        }
        editor.commit();
    }
    protected void onStart(){
        super.onStart();
        Log.d(TAG,"onStart()");

    }
    protected void onResume(){
        super.onResume();
        Log.d(TAG,"onResume()");
    }
    protected void onStop(){
        super.onStop();
        Log.d(TAG,"onStop()");
    }
    protected void onPause(){
        super.onPause();
        Log.d(TAG,"onPause()");
    }
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"onDestroy()");
    }

}
