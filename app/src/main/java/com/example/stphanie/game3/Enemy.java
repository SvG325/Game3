package com.example.stphanie.game3;

/**
 * Created by Stéphanie on 6-4-2018.
 */

public class Enemy {
    private String name;
    private int maxHp, currentHp;
    private int damage;
    private int img;

    public Enemy(String name, int maxHp, int damage, int img) {
        this.name = name;
        this.maxHp = maxHp;
        this.currentHp = maxHp;
        this.damage = damage;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public int getDamage() {
        return damage;
    }

    public int getImg(){
        return img;
    }

    public void changeHp(int delta){
        currentHp += delta;
    }

    public void resetEnemy(){
        currentHp = maxHp;
    }


}
