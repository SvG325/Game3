package com.example.stphanie.game3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

public class MapActivity extends AppCompatActivity {
    public final String PLAYER = "PLAYER";
    public final String Q_ID = "QUEST_ID";
    public final int TO_EVENT = 1;
    public final int TO_HOME = 2;
    private Character player;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Library.initEnemies(this);
        Library.initSkills(this);
        Library.initQuests(this);

        if(player == null)
            player = new Character("ItisI");
    }

    public void onEvent1Click(View v){
        Intent toEvent = new Intent(this, EventActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable(PLAYER, player);
        bundle.putInt(Q_ID, 1);

        toEvent.putExtras(bundle);
        startActivityForResult(toEvent, TO_EVENT);
    }

    public void onHomeClick(View v){
        Intent toHome = new Intent(this, HomeActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable(PLAYER, player);
        toHome.putExtras(bundle);
        startActivityForResult(toHome, TO_HOME);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            // After both an event or visiting home, retrieve the updated player object
            Bundle bundle = data.getExtras();
            player = (Character) bundle.getSerializable(PLAYER);
        }

    }




}
