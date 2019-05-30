package com.example.pc.qqlogin;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PokeActivity extends AppCompatActivity implements View.OnClickListener {


    private Button boxing;
    private Button armour;
    private Button shoe;
    private Button adventure;
    private Button upload;

    private ProgressBar vitality_Prog;
    private ProgressBar power_Prog;
    private ProgressBar speed_Prog;

   // private TextView remain;

    private ImageView pokeIMG;

    private String username;

    UserPoke userPoke=new UserPoke();

    private static final String TAG = "PokeActivity";

    private MyDatabaseHelper dbHelper;
    private SQLiteDatabase db;


    int alphaVitality=155;
    int alphaPower=155;
    int alphaSpeed=155;
    int power_num;
    int vitality_num;
    int speed_num;
    int reamin_num;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke);

        dbHelper=new MyDatabaseHelper(this,"bookStore.db",null,1);
        db=dbHelper.getWritableDatabase();

        Intent intent2 = getIntent();
        username = intent2.getStringExtra("username1");
        Toast.makeText(PokeActivity.this, "username-->"+username, Toast.LENGTH_SHORT).show();

        sqlFind();
        bindView();
        initView();
        judgeMent();

    }

    public void sqlFind(){
        Cursor cursor = db.query("user",null, null,
                null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                if(username.equals(cursor.getString(cursor.getColumnIndex("username_sql")))){
                    power_num=cursor.getInt(cursor.getColumnIndex("power_sql"));
                    vitality_num=cursor.getInt(cursor.getColumnIndex("vitality_sql"));
                    speed_num=cursor.getInt(cursor.getColumnIndex("speed_sql"));
                    reamin_num=cursor.getInt(cursor.getColumnIndex("remain_sql"));
//                    UserPoke uc = new UserPoke(reamin_num,power_num,speed_num,vitality_num);
                    showNormalDialog("居然成功","数据加载成功！！");
                }else {
                    showNormalDialog("fail","数据加载失败！！");
                }
//
//                userPoke.setRemain(cursor.getInt(cursor.getColumnIndex("remain_sql")));
//                userPoke.setPower(cursor.getInt(cursor.getColumnIndex("power_sql")));
//                userPoke.setSpeed(cursor.getInt(cursor.getColumnIndex("speed_sql")));
//                userPoke.setVitality(cursor.getInt(cursor.getColumnIndex("vitality_sql")));

            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    public void bindView(){
        boxing=(Button)findViewById(R.id.boxing);
        armour=(Button)findViewById(R.id.armour);
        shoe=(Button)findViewById(R.id.shoe);
        adventure=(Button)findViewById(R.id.adventure);
        upload=(Button)findViewById(R.id.upload);

        vitality_Prog=(ProgressBar)findViewById(R.id.vitality_Prog);
        power_Prog=(ProgressBar)findViewById(R.id.power_Prog);
        speed_Prog=(ProgressBar)findViewById(R.id.speed_Prog);

        //remain=(TextView)findViewById(R.id.remain);

        pokeIMG=(ImageView)findViewById(R.id.pokeIMG);

        boxing.setOnClickListener(this);
        armour.setOnClickListener(this);
        shoe.setOnClickListener(this);
        adventure.setOnClickListener(this);
        pokeIMG.setOnClickListener(this);
        upload.setOnClickListener(this);

    }

    @SuppressLint("ResourceType")
    public void initView(){
        boxing.getBackground().setAlpha(125);
        armour.getBackground().setAlpha(125);
        shoe.getBackground().setAlpha(125);
        upload.getBackground().setAlpha(150);


        userPoke.setRemain(reamin_num);
        userPoke.setPower(power_num);
        userPoke.setSpeed(speed_num);
        userPoke.setVitality(vitality_num);


        vitality_Prog.setProgress(userPoke.getVitality());
        power_Prog.setProgress(userPoke.getPower());
        speed_Prog.setProgress(userPoke.getSpeed());

    }       //初始化




    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.boxing:            //点击拳套按钮实现增加5点力量，并扣除100金币,加10透明度

                if(userPoke.getRemain()>0){

                    if(alphaPower<255){
                        reamin_num=userPoke.getRemain()- 100;
                        //remain.setText(reamin_num);
                        userPoke.setRemain(reamin_num);
                        alphaPower+=10;
                        power_num=userPoke.getPower()+5;
                        power_Prog.setProgress(power_num);
                        userPoke.setPower(power_num);
                        boxing.getBackground().setAlpha(alphaPower);

                    }
                    else{
                        showNormalDialog("装备已达到十级","别点我了，去升其他的吧");
                        alphaPower=255;
                        //vitality_Prog.setProgress(alpha);
                        power_Prog.setProgress(255);
                        //speed_Prog.setProgress(alpha);
                    }
                }
                else{
                    showNormalDialog("余额不足","您的余额不足，请充值！！");
                }

                break;
            case R.id.armour:             //点击铠甲按钮实现增加5点生命值，并扣除100金币,加10透明度

                if(userPoke.getRemain()>0){

                    if(alphaVitality<255){
                        reamin_num=userPoke.getRemain()- 100;
                        // remain.setText(reamin_num);
                        userPoke.setRemain(reamin_num);
                        alphaVitality+=10;
                        vitality_num=userPoke.getVitality()+5;
                        vitality_Prog.setProgress(vitality_num);
                        userPoke.setVitality(vitality_num);
                        armour.getBackground().setAlpha(alphaVitality);

                    }
                    else{
                        showNormalDialog("装备已达到十级","别点我了，去升其他的吧");
                        alphaVitality=255;
                        vitality_Prog.setProgress(255);
                    }

                }
                else{
                    showNormalDialog("余额不足","您的余额不足，请充值！！");
                }


                break;
            case R.id.shoe:               //点击鞋子按钮实现增加5点速度，并扣除100金币,加10透明度

                if(userPoke.getRemain()>0){

                    if(alphaSpeed<255){

                        reamin_num=userPoke.getRemain()- 100;
                        // remain.setText(reamin_num);
                        userPoke.setRemain(reamin_num);

                        alphaSpeed+=10;
                        speed_num=userPoke.getSpeed()+5;
                        speed_Prog.setProgress(speed_num);
                        userPoke.setSpeed(speed_num);
                        shoe.getBackground().setAlpha(alphaSpeed);

                    }
                    else{
                        showNormalDialog("装备已达到十级","别点我了，去升其他的吧");
                        alphaSpeed=255;
                        speed_Prog.setProgress(255);
                    }

                }
                else{
                    showNormalDialog("余额不足","您的余额不足，请充值！！");

                }


                break;
            case R.id.adventure:        //点击探险按钮实现探险功能
                adventureText();
                break;

            case R.id.pokeIMG:          //点击宠物弹出对话框进行充值
                showInputDialog();

                break;
            case R.id.upload:
                ContentValues values2 = new ContentValues();
                values2.put("remain_sql", userPoke.getRemain());
                values2.put("power_sql", userPoke.getPower());
                values2.put("speed_sql", userPoke.getSpeed());
                values2.put("vitality_sql", userPoke.getVitality());
                //参数依次是表名，修改后的值，where条件，以及约束，如果不指定三四两个参数，会更改所有行
                db.update("user", values2, "username_sql = ?", new String[]{username});
                Toast.makeText(PokeActivity.this, username+"上传完毕~", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void showNormalDialog(String title,String message){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
         AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(PokeActivity.this);
        normalDialog.setIcon(R.drawable.dialog_ico);
        normalDialog.setTitle(title);
        normalDialog.setMessage(message);
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        // 显示
        normalDialog.show();
    }

    private void showInputDialog() {
        /*@setView 装入一个EditView
         */
        final EditText editText = new EditText(PokeActivity.this);
        AlertDialog.Builder inputDialog =
                new AlertDialog.Builder(PokeActivity.this);
        inputDialog.setTitle("请输入要充值的金额（整数位）~~").setView(editText);
        inputDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if(editText.getText().equals("")){
                            Toast.makeText(PokeActivity.this,
                                    "请输入要充值的金额",
                                    Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(PokeActivity.this,
                                    "已充值成功："+editText.getText()+"金币\n充值0金币显示余额",
                                    Toast.LENGTH_SHORT).show();

                            String s=editText.getText().toString();
                            Log.d(TAG,s);
                            userPoke.setRemain(Integer.valueOf(s)+userPoke.getRemain());

                            if(Integer.valueOf(s)==0){
                                Toast.makeText(PokeActivity.this,
                                        "余额："+userPoke.getRemain()+"金币",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }




                    }
                }).show();



    }

    private boolean judgePower(){
        if(userPoke.getPower()>=100){
            userPoke.setPower(100);
            return true;
        }else if(userPoke.getPower()<=0){
            userPoke.setPower(0);
            return false;
        }else {
            return true;
        }
    }

    private boolean judgeVitality(){
        if(userPoke.getVitality()>=100){
            userPoke.setVitality(100);
            return true;
        }else if(userPoke.getVitality()<=0){
            userPoke.setVitality(0);
            return false;
        }else {
            return true;
        }
    }

    private boolean judgeSpeed(){
        if(userPoke.getSpeed()>=100){
            userPoke.setSpeed(100);
            return true;
        }else if(userPoke.getSpeed()<=0){
            userPoke.setSpeed(0);
            return false;
        }else {
            return true;
        }
    }

    private void judgeMent(){
        boolean gameover=judgePower()|judgeVitality()|judgeSpeed();
        boolean congratulation=judgePower()&judgeVitality()&judgeSpeed();
        if(!gameover){
            showNormalDialog("gameover","很遗憾，游戏失败，充一下钱试试");
        }
        if(congratulation){
            if(userPoke.getSpeed()==100 && userPoke.getVitality()==100 && userPoke.getPower()==100){
                showNormalDialog("congratulation","恭喜你完成挑战");
            }
           else{
                Log.d(TAG,"");
            }
        }
    }

    private void adventureText(){
        String adventureOne="唉~好渴啊~瞧，那里有装着鲜血的瓶子，捡起瓶子一口闷吞——血量加";
        String adventureTwo="啊，这地上的毒荆棘真多，刺得我满身是血——血量减";
        String adventureThree="耶，前面有稻草人，正好手痒练练拳——力量加";
        String adventureFore="哎呀，我的手抽筋了——力量减";
        String adventureFive="真棒，又有新鞋子穿了——速度加";
        String adventureSix="妈呀，谁这么缺德，居然在地上撒钉子——速度减";

        final double randNum = Math.random();
        final double randAttr = Math.random();
        int adventureRand=(int)(randNum*6);
        int attrRand=(int)(randAttr*10+1);

        switch (adventureRand){
            case 0:
                showNormalDialog("",adventureOne+attrRand);
                int attr1=userPoke.getVitality()+attrRand;
                userPoke.setVitality(attr1);
                vitality_Prog.setProgress(attr1);
                break;
            case 1:
                showNormalDialog("",adventureTwo+attrRand);
                int attr2=userPoke.getVitality()-attrRand;
                userPoke.setVitality(attr2);
                vitality_Prog.setProgress(attr2);
                break;
            case 2:
                showNormalDialog("",adventureThree+attrRand);
                int attr3=userPoke.getPower()+attrRand;
                userPoke.setPower(attr3);
                power_Prog.setProgress(attr3);
                break;
            case 3:
                showNormalDialog("",adventureFore+attrRand);
                int attr4=userPoke.getPower()-attrRand;
                userPoke.setPower(attr4);
                power_Prog.setProgress(attr4);
                break;
            case 4:
                showNormalDialog("",adventureFive+attrRand);
                int attr5=userPoke.getSpeed()+attrRand;
                userPoke.setSpeed(attr5);
                speed_Prog.setProgress(attr5);
                break;
            case 5:
                showNormalDialog("",adventureSix+attrRand);
                int attr6=userPoke.getSpeed()-attrRand;
                userPoke.setSpeed(attr6);
                speed_Prog.setProgress(attr6);
                break;
        }

    }

}
