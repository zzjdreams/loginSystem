package com.example.pc.qqlogin;

/**
 * Created by draem on $(DATE)
 **/
public class UserPoke {
    private String username;
    private String password;
    private int power;
    private int speed;
    private int vitality;
    private int remain;

    public int getRemain() {
        return remain;
    }

    public void setRemain(int remain) {
        this.remain = remain;
    }

    public int getVitality() {
        return vitality;
    }

    public void setVitality(int vitality) {
        this.vitality = vitality;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserPoke() {
    }

    public UserPoke(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserPoke(int remain,int power, int speed, int vitality) {
        this.remain=remain;
        this.power = power;
        this.speed = speed;
        this.vitality = vitality;
    }

    public UserPoke(String username, String password, int remain, int power, int speed, int vitality) {
        this.username = username;
        this.password = password;
        this.power = power;
        this.speed = speed;
        this.vitality = vitality;
        this.remain = remain;
    }

//    @Override
//    public String toString() {
//        String powerText="力量:"+this.getPower();
//        String speedText="速度："+this.getSpeed();
//        String vitalityText="血量："+this.getVitality();
//        return powerText+speedText+vitalityText;
//    }
}
