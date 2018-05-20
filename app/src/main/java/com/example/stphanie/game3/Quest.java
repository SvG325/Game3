package com.example.stphanie.game3;

import java.io.Serializable;

/**
 * Created by Stéphanie on 6-4-2018.
 */

public class Quest implements Serializable{
    private String name;
    private MagicType reqTypeA, reqTypeB;
    private int reqLevelA;
    private int reqLevelB;
    private int enemyA, enemyB;
    private int numItemsRequired;
    private int numItemsGathered;
    private String strStart;
    private String strOptionA;
    private String strOptionB;
    private String strResponseA;
    private String strResponseB;
    private String strObjectiveA;
    private String strObjectiveB;
    private String strQuestItemA;
    private String strQuestItemB;
    private String strComplete;
    private boolean accepted;
    private boolean finished;
    private boolean typeA;

    private boolean aborted;

    public Quest(String name, MagicType reqTypeA, MagicType reqTypeB, int reqLevelA, int reqLevelB,
                 int enemyA, int enemyB, String strStart, String strOptionA, String strOptionB,
                 String strResponseA, String strResponseB, String strObjectiveA, String strObjectiveB,
                 String strQuestItemA, String strQuestItemB) {
        this.name = name;
        this.reqTypeA = reqTypeA;
        this.reqTypeB = reqTypeB;
        this.reqLevelA = reqLevelA;
        this.reqLevelB = reqLevelB;
        this.enemyA = enemyA;
        this.enemyB = enemyB;
        this.strStart = strStart;
        this.strOptionA = strOptionA;
        this.strOptionB = strOptionB;
        this.strResponseA = strResponseA;
        this.strResponseB = strResponseB;
        this.strObjectiveA = strObjectiveA;
        this.strObjectiveB = strObjectiveB;
        this.strQuestItemA = strQuestItemA;
        this.strQuestItemB = strQuestItemB;
        numItemsGathered = 0;
        numItemsRequired = 3;
        this.accepted = false;
        this.finished = false;
        this.aborted = false;
    }

    public void resetQuest(){
        this.accepted = false;
        this.finished = false;
        numItemsGathered = 0;
    }

    public void acceptQuest(boolean optionA){
        typeA = optionA;
        accepted = true;
    }

    public String getStrStart(){
        return strStart;
    }

    public String getStrOptionA() {
        return strOptionA;
    }

    public String getStrOptionB() {
        return strOptionB;
    }

    public String getStrResponseA() {
        return strResponseA;
    }

    public String getStrResponseB() {
        return strResponseB;
    }

    public MagicType getMagicType(){
        if (typeA)
            return reqTypeA;
        else
            return reqTypeB;
    }

    public MagicType getReqTypeA(){
        return reqTypeA;
    }

    public MagicType getReqTypeB(){
        return reqTypeB;
    }

    public String getName() {
        return name;
    }

    public int getReqLevelA() {
        return reqLevelA;
    }

    public int getReqLevelB() {
        return reqLevelB;
    }

    public int getReqLevel(){
        if(typeA)
            return reqLevelA;
        else
            return reqLevelB;
    }

    public String getStrObjectiveA(){
        return strObjectiveA;
    }

    public String getStrObjectiveB(){
        return strObjectiveB;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public int getNumItemsRequired() {
        return numItemsRequired;
    }

    public int getNumItemsGathered() {
        return numItemsGathered;
    }

    public int getEnemyA(){
        return enemyA;
    }

    public int getEnemyB(){
        return enemyB;
    }

    public boolean getTypeA(){
        return typeA;
    }

    public String getStrQuestItem(){
        if(typeA)
            return strQuestItemA;
        else
            return strQuestItemB;
    }

    public void incItemsGathered(){
        numItemsGathered++;
        if(numItemsGathered >= numItemsRequired)
            finished = true;
    }

    public boolean isAborted() {
        return aborted;
    }

    public void setAborted(boolean aborted) {
        this.aborted = aborted;
    }

}
