package com.example.stphanie.game3;

import android.content.Context;

/**
 * Created by Stéphanie on 13-4-2018.
 */

public class Battle {
    private Character player;
    private Enemy enemy;
    private Quest quest;
    private String battlelog;
    private int selectedSkill;
    private int difficulty;
    private boolean over, won;

    public Battle(Character player, Enemy enemy, Quest quest){
        this.player = player;
        this.enemy = enemy;
        this.quest = quest;
        over = false;
        won = false;
        battlelog = "";
        difficulty = quest.getReqLevel();
    }

    public void playRound(Context context){
        Skill skill = player.getEquippedSkills().get(selectedSkill);

        int damage = (int) Math.round(skill.getDmgMulti() * player.getTypeLevel(skill.getMagicType()));
        enemy.changeHp(-damage);
        battlelog += "\n" + String.format(context.getString(R.string.battle_dmgenemy), enemy.getName(),
                skill.getName(), damage);
        if(enemy.getCurrentHp() <= 0){
            battlelog += "\n\n" +  String.format(context.getString(R.string.battle_enemydies), enemy.getName());
            over = true;
            won = true;
        }else{
            damage = enemy.getDamage();
            player.changeHp(-damage);
            battlelog +=  String.format(context.getString(R.string.battle_dmgplayer), enemy.getName(), damage);
        }

        if(player.getCurrentHp() <= 0 ){
            battlelog += "You died. Tap to return." ;
            quest.setAborted(true);
            over = true;
            won = false;
        }

    }

    public void setSelectedSkill(int selectedSkill){
        this.selectedSkill = selectedSkill;
    }

    public boolean isOver() {
        return over;
    }

    public boolean isWon(){
        return won;
    }

    public void collectRewards(){
        quest.incItemsGathered();
        battlelog += "\n\nYou receive 1 " + quest.getStrQuestItem() + "\n";
        if(quest.getMagicType() == MagicType.Alchemy){
            player.incAlchemyXp(difficulty);
            battlelog += "You gain " + difficulty + " " + quest.getMagicType().getName() + " xp.";
        }else if(quest.getMagicType() == MagicType.Elemental){
            player.incElementalXp(difficulty);
            battlelog += "You gain " + difficulty + " " + quest.getMagicType().getName() + " xp.";
        }else if (quest.getMagicType() == MagicType.Arcane) {
            player.incArcaneXp(difficulty);
            battlelog += "You gain " + difficulty + " " + quest.getMagicType().getName() + " xp.";
        }
    }

    public String getBattlelog(){
        return battlelog;
    }
}
