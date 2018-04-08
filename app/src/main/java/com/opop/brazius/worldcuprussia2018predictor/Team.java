package com.opop.brazius.worldcuprussia2018predictor;

import android.os.Parcelable;

/**
 * Created by Juozas on 2018.03.26.
 */

class Team {


    public boolean isHeader() {
        return isHeader;
    }



    public String getRound16Winner2() {
        return round16Winner2;
    }

    public void setRound16Winner2(String round16Winner2) {
        this.round16Winner2 = round16Winner2;
    }

    private String round16Winner2 = "";
    private String round8Winner2 = "";
    private int round8Winner2Res;
    private int semiFinalWinnerRes;
    private int semiFinalWinner2Res;
    private String semiFinalWinner2 = "";
    private String semiFinalWinner = "";
    private int finalWinnerRes;

    public int getSemiFinalWinnerRes() {
        return semiFinalWinnerRes;
    }

    public void setSemiFinalWinnerRes(int semiFinalWinnerRes) {
        this.semiFinalWinnerRes = semiFinalWinnerRes;
    }

    public int getSemiFinalWinner2Res() {
        return semiFinalWinner2Res;
    }

    public void setSemiFinalWinner2Res(int semiFinalWinner2Res) {
        this.semiFinalWinner2Res = semiFinalWinner2Res;
    }

    public String getSemiFinalWinner2() {
        return semiFinalWinner2;
    }

    public void setSemiFinalWinner2(String semiFinalWinner2) {
        this.semiFinalWinner2 = semiFinalWinner2;
    }

    public String getSemiFinalWinner() {
        return semiFinalWinner;
    }

    public void setSemiFinalWinner(String semiFinalWinner) {
        this.semiFinalWinner = semiFinalWinner;
    }

    public int getFinalWinnerRes() {
        return finalWinnerRes;
    }

    public void setFinalWinnerRes(int finalWinnerRes) {
        this.finalWinnerRes = finalWinnerRes;
    }

    public String getFinalWinner() {
        return finalWinner;
    }

    public void setFinalWinner(String finalWinner) {
        this.finalWinner = finalWinner;
    }

    private String finalWinner = "";
    public int getRound8Winner2Res() {
        return round8Winner2Res;
    }

    public void setRound8Winner2Res(int round8Winner2Res) {
        this.round8Winner2Res = round8Winner2Res;
    }

    public int getRound8WinnerRes() {
        return round8WinnerRes;
    }

    public void setRound8WinnerRes(int round8WinnerRes) {
        this.round8WinnerRes = round8WinnerRes;
    }

    private int round8WinnerRes;


    public String getRound8Winner2() {
        return round8Winner2;
    }

    public void setRound8Winner2(String round8Winner2) {
        this.round8Winner2 = round8Winner2;
    }

    public String getRound8Winner() {
        return round8Winner;
    }

    public void setRound8Winner(String round8Winner) {
        this.round8Winner = round8Winner;
    }

    private String round8Winner = "";

    public String getRound16Winner() {
        return round16Winner;
    }

    public void setRound16Winner(String round16Winner) {
        this.round16Winner = round16Winner;
    }

    private boolean isHeader = false;
    String group = "";

    String name1;

    @Override
    public String toString() {
        String strToReturn = "";
        if(!round16Winner.isEmpty() && !round16Winner2.isEmpty() ){
            strToReturn = "\nRound 16 " +  "\n" +
                     name1 +" & "+name2+" winner - "+ round16Winner+ "\n" +
                    name3 +" & "+name4+" winner - "+ round16Winner2+ "\n" ;
            return strToReturn;
        }
        if(!round8Winner.isEmpty() && !round8Winner2.isEmpty() ){
            strToReturn = "\nQuarter-Finals " + "\n" +
                    name1 +" & "+name2+" winner - "+ round8Winner+ "\n" +
                    name3 +" & "+name4+" winner - "+ round8Winner2+ "\n" ;
            return strToReturn;
        }
        if(!semiFinalWinner.isEmpty() && !semiFinalWinner2.isEmpty() ){
            strToReturn = "\nSemi-Final " + "\n" +
                    name1 +" & "+name2+" winner - "+ semiFinalWinner+ "\n" +
                    name3 +" & "+name4+" winner - "+ semiFinalWinner2+ "\n" ;
            return strToReturn;
        }
        if(!finalWinner.isEmpty()){
            strToReturn = "Final " + "\n" +
                    name1 +" & "+name2+" winner - "+ finalWinner;
            return strToReturn;
        }
        return "";
    }
    private String round16Winner = "";

    private int round16WinnerRes;
    private int round16Winner2Res;

    public int getRound16WinnerRes() {
        return round16WinnerRes;
    }

    public void setRound16WinnerRes(int round16WinnerRes) {
        this.round16WinnerRes = round16WinnerRes;
    }

    public int getRound16Winner2Res() {
        return round16Winner2Res;
    }

    public void setRound16Winner2Res(int round16Winner2Res) {
        this.round16Winner2Res = round16Winner2Res;
    }

    String name2;
    String name3;
    String name4;

    int flagRes1;
    int flagRes2;
    int flagRes3;
    int flagRes4;

    String firstPlace = "";

    String secondPlace = "";
    int firstPlaceRes;

    public int getFirstPlaceRes() {
        return firstPlaceRes;
    }

    public void setFirstPlaceRes(int firstPlaceRes) {
        this.firstPlaceRes = firstPlaceRes;
    }

    public int getSecondPlaceRes() {
        return secondPlaceRes;
    }

    public void setSecondPlaceRes(int secondPlaceRes) {
        this.secondPlaceRes = secondPlaceRes;
    }

    int secondPlaceRes;
    boolean isDone = false;

    public String getFirstPlace() {
        return firstPlace;
    }

    public void setFirstPlace(String firstPlace) {
        this.firstPlace = firstPlace;
    }

    public String getSecondPlace() {
        return secondPlace;
    }

    public void setSecondPlace(String secondPlace) {
        this.secondPlace = secondPlace;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getGroup() {
        return group;
    }

    public String getName1() {
        return name1;
    }

    public String getName2() {
        return name2;
    }

    public String getName3() {
        return name3;
    }

    public String getName4() {
        return name4;
    }

    public int getFlagRes1() {
        return flagRes1;
    }

    public int getFlagRes2() {
        return flagRes2;
    }

    public int getFlagRes3() {
        return flagRes3;
    }

    public int getFlagRes4() {
        return flagRes4;
    }

    public Team() {
        isHeader = true;
    }
    public Team(String group, String name1, int flagRes1) {
        this.group = group;
        this.name1 = name1;
        this.flagRes1 = flagRes1;
    }

    public Team(String group, String name1, String name2, int flagRes1, int flagRes2) {
        this.group = group;
        this.name1 = name1;
        this.name2 = name2;
        this.flagRes1 = flagRes1;
        this.flagRes2 = flagRes2;
    }

    public Team(String group, String name1, String name2, String name3, String name4, int flagRes1, int flagRes2, int flagRes3, int flagRes4) {

        this.group = group;
        this.name1 = name1;
        this.name2 = name2;
        this.name3 = name3;
        this.name4 = name4;
        this.flagRes1 = flagRes1;
        this.flagRes2 = flagRes2;
        this.flagRes3 = flagRes3;
        this.flagRes4 = flagRes4;
    }
}
