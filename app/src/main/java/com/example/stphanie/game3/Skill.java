package com.example.stphanie.game3;

import java.io.Serializable;

/**
 * Created by Stéphanie on 7-4-2018.
 */

public class Skill implements Serializable{
    private String name;
    private int currLevel;
    private int maxLevel;
    private double dmgMulti;
    private int imgUnselected;
    private int imgSelected;
    private String description;
    private MagicType magicType;

    public Skill(String name, int maxLevel, double dmgMulti, int imgUnselected, int imgSelected,
                 int imgDisabled, String description, MagicType magicType){
        this.name = name;
        this.currLevel = 1;
        this.maxLevel = maxLevel;
        this.dmgMulti = dmgMulti;
        this.imgUnselected = imgUnselected;
        this.imgSelected = imgSelected;
        this.imgDisabled = imgDisabled;
        this.description = description;
        this.magicType = magicType;
    }

    private int imgDisabled;

    public String getName() {
        return name;
    }

    public int getCurrLevel() {
        return currLevel;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public double getDmgMulti() {
        return dmgMulti;
    }

    public int getImgUnselected() {
        return imgUnselected;
    }

    public int getImgSelected() {
        return imgSelected;
    }

    public int getImgDisabled() {
        return imgDisabled;
    }

    public String getDescription(){
        return  description;
    }

    public MagicType getMagicType(){
        return magicType;
    }
}
