package game.web.wicket.models;

import game.logic.gamerNumberDefiner.NumberInfo;

/**
 * Created by Windows on 15.01.2016.
 */
public class NumberModel {

    private int step;
    private int number;
    private int cows;
    private int bulls;
    private String playerName;

    public NumberModel(){}

    public NumberModel(NumberInfo numberInfo, String playerName){
        this.playerName = playerName;
        this.step = numberInfo.getStepNumber();
        this.number = numberInfo.getNum();
        this.cows = numberInfo.getCowsAmount();
        this.bulls = numberInfo.getBullsAmount();
    }


    private String addZeroOnTheBegginingOfNumber(String stringNumber){
        while (stringNumber.length() < 4){
            stringNumber = "0" + stringNumber;
        }
        return stringNumber;
    }



    public int getNumber() {
        return number;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getStringNumber() {
        String stringNumber = String.valueOf(number);
        return addZeroOnTheBegginingOfNumber(stringNumber);
    }

    public void setNumber(int intNumber) {
        this.number = intNumber;
    }

    public int getCows() {
        return cows;
    }

    public void setCows(int cows) {
        this.cows = cows;
    }

    public int getBulls() {
        return bulls;
    }

    public void setBulls(int bulls) {
        this.bulls = bulls;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
