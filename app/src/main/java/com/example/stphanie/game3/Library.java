package com.example.stphanie.game3;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stéphanie on 7-4-2018.
 */

public class Library {
    public static Map<Integer, Quest> quests;
    public static Map<Integer, Skill> skills;
    public static Map<Integer, Enemy> enemies;


    public static void initQuests(Context context){
        quests = new HashMap<>();

        Quest temp = new Quest(context.getString(R.string.quest1_title), MagicType.Elemental, MagicType.Alchemy,
                10, 1, 1, 1, context.getString(R.string.quest1_startConversation),
                context.getString(R.string.quest1_revenge), context.getString(R.string.quest1_help),
                context.getString(R.string.quest1_responseA), context.getString(R.string.quest1_responseB),
                context.getString(R.string.quest1_reqA), context.getString(R.string.quest1_reqB),
                context.getString(R.string.quest_item1),context.getString(R.string.quest_item2));
        quests.put(1, temp);

    }

    public static void initSkills(Context context){
        skills = new HashMap<>();

        Skill temp = new Skill(context.getString(R.string.skill_name1), 5, 0.8,
                R.drawable.rawforce, R.drawable.ac_rawforce, R.drawable.in_rawforce,
                context.getString(R.string.skill_description1), MagicType.Arcane);
        skills.put(1, temp);
        temp = new Skill(context.getString(R.string.skill_name2), 5, 1.3,
                R.drawable.fire, R.drawable.ac_fire, R.drawable.in_fire,
                context.getString(R.string.skill_description2), MagicType.Elemental);
        skills.put(2, temp);
    }

    public static void initEnemies(Context context){
        enemies = new HashMap<>();

        // Enemy(String name, int maxHp, int damage, int[] imgs_idle, int[] imgs_attack, int[] imgs_hurt, int[] imgs_dieing)
        Enemy temp = new Enemy("Fairy", 12, 2, R.drawable.fairy_c);
        enemies.put(1, temp);

    }

    public static Enemy retrieveEnemy(int enemyID){
        Enemy enemy = enemies.get(enemyID);
        enemy.resetEnemy();
        return  enemy;
    }
}
