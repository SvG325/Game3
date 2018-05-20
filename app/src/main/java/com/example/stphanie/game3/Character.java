package com.example.stphanie.game3;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Stéphanie on 6-4-2018.
 */

public class Character implements Serializable{
    private String name;
    private int maxHp, currentHp;
    private int alchemyLevel, elementalLevel, arcaneLevel;
    private int alchemyXp, elementalXp, arcaneXp;
    private ArrayList<Skill> equippedSkills;
    private int imgID;

    public Character(String name){
        this.name = name;
        maxHp = 20;
        currentHp = maxHp;
        alchemyLevel = 3;
        elementalLevel = 3;
        arcaneLevel = 3;
        alchemyXp = 0;
        elementalXp = 0;
        arcaneXp = 0;
        this.imgID = R.drawable.player;
        equippedSkills = new ArrayList<>();
        getEquippedSkills().add(Library.skills.get(1)); //Start out with 1 skill
        getEquippedSkills().add(Library.skills.get(2)); // This one is just for testing
    }

    public void incAlchemyXp(int delta){
        alchemyXp += delta;
        if(alchemyXp >= calcMaxXp(alchemyLevel)){
            alchemyXp = 0;
            alchemyLevel++;
        }
    }

    public int calcMaxXp(int currLevel){
        return currLevel*20;
    }

    public void incElementalXp(int delta){
        elementalXp += delta;
        if(elementalXp >= calcMaxXp(elementalLevel)){
            elementalXp = 0;
            elementalLevel++;
        }
    }

    public void incArcaneXp(int delta){
        arcaneXp += delta;
        if(arcaneXp >= calcMaxXp(arcaneLevel)){
            arcaneXp = 0;
            arcaneLevel++;
        }

    }

    public int getImgID(){
        return imgID;
    }

    public ArrayList<Skill> getEquippedSkills() {
        return equippedSkills;
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

    public int getAlchemyLevel() {
        return alchemyLevel;
    }

    public int getElementalLevel() {
        return elementalLevel;
    }

    public int getArcaneLevel() {
        return arcaneLevel;
    }

    public int getAlchemyXp(){
        return alchemyXp;
    }

    public int getElementalXp(){
        return elementalXp;
    }

    public int getArcaneXp(){
        return arcaneXp;
    }


    public int getTypeLevel(MagicType magicType){
        if(magicType == MagicType.Elemental)
            return elementalLevel;
        else if(magicType == MagicType.Arcane)
            return arcaneLevel;
        else if (magicType == MagicType.Alchemy)
            return alchemyLevel;
        else
            return 0;
    }

    public void changeHp(int delta){
        currentHp += delta;
    }
}
