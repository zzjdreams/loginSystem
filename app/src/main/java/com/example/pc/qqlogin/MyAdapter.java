package com.example.pc.qqlogin;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dream on 2019/5/19
 **/
public class MyAdapter extends BaseAdapter {

    private ArrayList<UserPoke> Pokes;
    private Context mContext;

    private TextView name;
    private TextView password;
    private TextView vitality;
    private TextView speed;
    private TextView power;
    private TextView remain;
    private ImageView tou;
    private int[] imgIds = new int[]{R.drawable.dialog_ico,R.drawable.qq_poke};
    private int j=0;

    public MyAdapter(ArrayList<UserPoke> pokes, Context mContext) {
        Pokes = pokes;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return Pokes.size();
    }

    @Override
    public Object getItem(int i) {
        return Pokes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        view = LayoutInflater.from(mContext).inflate(R.layout.list_item,viewGroup,false);

        name=(TextView) view.findViewById(R.id.name);
        password=(TextView) view.findViewById(R.id.password);
        vitality=(TextView) view.findViewById(R.id.vitality);
        speed=(TextView) view.findViewById(R.id.speed);
        power=(TextView) view.findViewById(R.id.power);
        remain=(TextView) view.findViewById(R.id.remain);
        tou=(ImageView)view.findViewById(R.id.imgtou) ;

        name.setText(Pokes.get(i).getUsername());
        password.setText(Pokes.get(i).getPassword());
        vitality.setText(String.valueOf(Pokes.get(i).getVitality()));
        speed.setText(String.valueOf(Pokes.get(i).getSpeed()));
        power.setText(String.valueOf(Pokes.get(i).getPower()));
        remain.setText(String.valueOf(Pokes.get(i).getRemain()));
        tou.setBackgroundResource(imgIds[j%2]);
        j++;

        return view;
    }
}
