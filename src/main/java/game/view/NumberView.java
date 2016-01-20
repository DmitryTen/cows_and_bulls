package game.view;

import game.entity.ComputerNumber;
import game.entity.PlayerNumber;

/**
 * Created by Windows on 15.01.2016.
 */
public class NumberView {

    private int step;
    private String number;
    private int cows;
    private int bulls;
    private String playerName;

    public NumberView(){}

    public NumberView(PlayerNumber playerNumberDBEntity){
        this.playerName = playerNumberDBEntity.getGame().getPlayer().getPlayerName();
        this.step = playerNumberDBEntity.getStepNumber();
        this.cows = playerNumberDBEntity.getCowsAmount();
        this.bulls = playerNumberDBEntity.getBullsAmount();
        this.number = String.valueOf(playerNumberDBEntity.getNum());

        if (number.length()<4) addZeroOnTheBegginingOfNumber();
    }

    public NumberView(ComputerNumber computerNumberDBEntity){
        this.step = computerNumberDBEntity.getStepNumber();
        this.bulls = computerNumberDBEntity.getBullsAmount();
        this.cows = computerNumberDBEntity.getCowsAmount();
        this.playerName = "Computer";
        this.number = String.valueOf(computerNumberDBEntity.getNum());

        if (number.length()<4) addZeroOnTheBegginingOfNumber();
    }

    private void addZeroOnTheBegginingOfNumber(){
        while (number.length() < 4){
            number = "0" + number;
        }
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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
