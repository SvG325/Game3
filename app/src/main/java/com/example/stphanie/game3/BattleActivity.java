package com.example.stphanie.game3;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.icu.text.RelativeDateTimeFormatter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Iterator;

import static java.lang.Thread.sleep;

public class BattleActivity extends AppCompatActivity {
    public final String PLAYER = "PLAYER";
    public final String QUEST = "QUEST";
    private Quest quest;
    private Character player;
    private Enemy enemy;
    private ImageView imgEnemy, imgPlayer;
    private Drawable drawPlayer, drawEnemy;
    private ImageView[] ivSkills = new ImageView[6];
    private TextView tvBattleLog, tvSkillDescription, tvTitle;
    private ScrollView scrollView;
    private int selectedSkillIndex;
    private Battle battle;
    private ProgressBar pbPlayerHP, pbEnemyHP;
    private boolean battleOver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        imgEnemy = findViewById(R.id.img_battleEnemy);
        imgPlayer = findViewById(R.id.img_battlePlayer);
        ivSkills[0] = findViewById(R.id.img_skill1);
        ivSkills[1] = findViewById(R.id.img_skill2);
        ivSkills[2] = findViewById(R.id.img_skill3);
        ivSkills[3] = findViewById(R.id.img_skill4);
        ivSkills[4] = findViewById(R.id.img_skill5);
        ivSkills[5] = findViewById(R.id.img_skill6);
        tvBattleLog = findViewById(R.id.tv_battleLog);
        tvSkillDescription = findViewById(R.id.tv_skillDescription);
        scrollView = findViewById(R.id.sv_battleLog);
        pbEnemyHP = findViewById(R.id.hpBarEnemy);
        pbPlayerHP = findViewById(R.id.hpBarPlayer);
        tvTitle = findViewById(R.id.tv_battleTitle);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        player = (Character) bundle.getSerializable(PLAYER);
        quest = (Quest) bundle.getSerializable(QUEST);
        selectedSkillIndex = -1;
        battleOver = false;

        initView();
        battle = new Battle(player, enemy, quest);

    }

    private void initView(){
        if(quest.getTypeA())
            enemy = Library.retrieveEnemy(quest.getEnemyA());
        else
            enemy = Library.retrieveEnemy(quest.getEnemyB());

        tvTitle.setText(quest.getName());
        tvBattleLog.setText(String.format(this.getString(R.string.battle_start), enemy.getName()));
        pbPlayerHP.setMax(player.getMaxHp());
        pbEnemyHP.setMax(enemy.getMaxHp());

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            drawPlayer = this.getDrawable(player.getImgID());
            drawEnemy = this.getDrawable(enemy.getImg());
        }else{
            drawPlayer = this.getResources().getDrawable(player.getImgID());
            drawEnemy = this.getResources().getDrawable(enemy.getImg());
        }
        imgPlayer.setImageDrawable(drawPlayer);
        imgEnemy.setImageDrawable(drawEnemy);

        updateViews();
    }

    private void clickedSkill(int skillNr){
        Skill skill;
        String description;
        if(skillNr == -1){
            description = "";
        }else{
            skill = player.getEquippedSkills().get(skillNr);
            description = skill.getDescription() + skill.getDmgMulti();
        }
        selectedSkillIndex = skillNr;
        tvSkillDescription.setText(description);
        updateViews();
    }

    private void setViewsAfterBattle(){
        battleOver = true;
        for( ImageView iv: ivSkills){
            iv.setVisibility(View.GONE);
        }
        tvSkillDescription.setVisibility(View.GONE);
        int delta = 0;
        delta += scrollView.getHeight();
        delta += tvBattleLog.getLineCount()*tvBattleLog.getLineHeight();
        scrollView.smoothScrollBy(0, delta);
    }
    private void setViewsForBattle(){
        if(quest.getTypeA())
            enemy = Library.retrieveEnemy(quest.getEnemyA());
        else
            enemy = Library.retrieveEnemy(quest.getEnemyB());
        tvBattleLog.setText(String.format(this.getString(R.string.battle_start), enemy.getName()));
        battleOver = false;
        for( ImageView iv: ivSkills){
            iv.setVisibility(View.VISIBLE);
        }
        tvSkillDescription.setVisibility(View.VISIBLE);
    }

    private void updateViews(){
        pbEnemyHP.setProgress(enemy.getCurrentHp());
        pbPlayerHP.setProgress(player.getCurrentHp());

        //Skills
        Iterator iterator = player.getEquippedSkills().iterator();
        for(int i = 0; i < 6; i++){
            final int index = i;
            Drawable drawSkill;
            if(iterator.hasNext()){
                Skill skill = (Skill) iterator.next();
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    if(i != selectedSkillIndex)
                        drawSkill = getDrawable(skill.getImgUnselected());
                    else
                        drawSkill = getDrawable(skill.getImgSelected());
                }else{
                    if(i != selectedSkillIndex)
                        drawSkill = getResources().getDrawable(skill.getImgUnselected());
                    else
                        drawSkill = getResources().getDrawable(skill.getImgSelected());
                }
                ivSkills[i].setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v) {
                        clickedSkill(index);
                    }
                });

            }else{
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    drawSkill = getDrawable(R.drawable.empty);
                }else{
                    drawSkill = getResources().getDrawable(R.drawable.empty);
                }
            }
            ivSkills[i].setImageDrawable(drawSkill);
        }
        scrollDown();
    }

    void scrollDown(){
        scrollView.post(new Runnable()
        {
            public void run()
            {
                try {
                    sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                scrollView.fullScroll(View.FOCUS_DOWN);
            }
        });
    }

    public void onGoClick(View v){
        if(battleOver){
            if(quest.isFinished() || quest.isAborted()){
                //Return to eventactivity
                Intent data = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable(PLAYER, player);
                bundle.putSerializable(QUEST, quest);
                data.putExtras(bundle);
                setResult(RESULT_OK, data);
                finish();
            }else{
                //Start the next fight
                setViewsForBattle();
                selectedSkillIndex = -1;
                battleOver = false;

                initView();
                battle = new Battle(player, enemy, quest);
            }
            return;
        }

        if(selectedSkillIndex == -1)
            return;

        battle.setSelectedSkill(selectedSkillIndex);
        battle.playRound(this);
        tvBattleLog.setText(battle.getBattlelog());
        if(battle.isOver()){
            setViewsAfterBattle();
            if(battle.isWon()){
                battle.collectRewards();
            }
            tvBattleLog.setText(battle.getBattlelog());
        }
        clickedSkill(-1);
    }

    @Override
    public void onBackPressed() {
        //TODO add a dialog if you want to forfeit/flee the fight
    }

}
