package com.example.stphanie.game3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class EventActivity extends AppCompatActivity {
    public final String PLAYER = "PLAYER";
    public final String QUEST = "QUEST";
    public final String Q_ID = "QUEST_ID";
    static final int BATTLE_EVENT = 1;
    private Button btOptionA, btOptionB, btOptionC;
    private TextView tvReqA, tvReqB, tvTitle, tvConversation;
    private Character player;
    private Quest quest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        btOptionA = findViewById(R.id.bt_questOptionA);
        btOptionB = findViewById(R.id.bt_questOptionB);
        btOptionC = findViewById(R.id.bt_questOptionC);
        tvReqA = findViewById(R.id.tv_reqOptionA);
        tvReqB = findViewById(R.id.tv_reqOptionB);
        tvTitle = findViewById(R.id.tv_questTitle);
        tvConversation = findViewById(R.id.tv_npcConversation);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        player = (Character) bundle.getSerializable(PLAYER);
        int qID = bundle.getInt(Q_ID);
        quest = Library.quests.get(qID);

        tvTitle.setText(quest.getName());
        tvConversation.setText(quest.getStrStart());
        btOptionA.setText(quest.getStrOptionA());
        btOptionB.setText(quest.getStrOptionB());
        tvReqA.setText(quest.getReqTypeA().getName() + ": " + quest.getReqLevelA());
        tvReqB.setText(quest.getReqTypeB().getName() + ": " + quest.getReqLevelB());
    }

    private void endActivity(){
        Intent data = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable(PLAYER, player);
        data.putExtras(bundle);
        setResult(RESULT_OK, data);
        finish();
    }

    public void onLeaveClick(View v){
        if(quest.isAccepted()){
            Intent startBattle = new Intent(this, BattleActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(PLAYER, player);
            bundle.putSerializable(QUEST, quest);
            startBattle.putExtras(bundle);

            startActivityForResult(startBattle, BATTLE_EVENT);
        }else
            finish();
    }

    private void hideOptions(){
        btOptionA.setVisibility(View.GONE);
        btOptionB.setVisibility(View.GONE);
        tvReqA.setVisibility(View.GONE);
        tvReqB.setVisibility(View.GONE);
        btOptionC.setText(R.string.quest_accepted);
    }


    /**
     * TODO implement checkRequirements
     * @param mt
     * @param reqLevel
     * @return
     */
    private boolean checkRequirements(MagicType mt, int reqLevel){
        return true;
    }

    public void onOptionAClick(View v){
        hideOptions();
        checkRequirements(MagicType.Alchemy, 1);

        String conversation = quest.getStrStart();
        conversation += "\n" + quest.getStrOptionA();
        conversation += "\n" + quest.getStrResponseA();
        conversation += "\n\n" + quest.getStrObjectiveA();
        tvConversation.setText(conversation);
        quest.acceptQuest(true);
    }

    public void onOptionBClick(View v){
        hideOptions();
        checkRequirements(MagicType.Alchemy, 1);

        String conversation = quest.getStrStart();
        conversation += "\n" + quest.getStrOptionB();
        conversation += "\n" + quest.getStrResponseB();
        conversation += "\n\n" + quest.getStrObjectiveB();
        tvConversation.setText(conversation);
        quest.acceptQuest(false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BATTLE_EVENT) {

            if (resultCode == RESULT_OK) {
                //Yaay we won, and the player got some xp/items/quest progress so retrieve new player object
                Bundle bundle = data.getExtras();
                player = (Character) bundle.getSerializable(PLAYER);
                quest = (Quest) bundle.getSerializable(QUEST);

                if(quest.isFinished()){
                    //TODO dialog and rewards for finishing the quests
                    // exit back to map
                    endActivity();
                }else{
                    if(quest.isAborted()){
                        // exit back to map
                        endActivity();
                    }else{
                        //Start next battle
                        onLeaveClick(null);
                    }
                }
            }
        }
    }


}
