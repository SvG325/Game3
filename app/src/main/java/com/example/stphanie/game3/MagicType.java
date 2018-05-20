package com.example.stphanie.game3;

import java.io.Serializable;

/**
 * Created by Stéphanie on 6-4-2018.
 */

public enum MagicType implements Serializable{
    Alchemy(1),
    Elemental(2),
    Arcane(3);

    int type;
    String name;

    private MagicType(int type){
        this.type = type;
        if(type == 1)
            name = "Alchemy";
        else if(type == 2)
            name = "Elemental";
        else
            name = "Arcane";
    }

    public String getName(){
        return name;
    }

}
