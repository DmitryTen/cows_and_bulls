package game.view;

import java.util.List;

/**
 * Created by Windows on 19.01.2016.
 */
public class PageView {

    private List<NumberView> playerList;
    private List<NumberView> computerList;
    private String stringNumber; // запрашиваемое у игрока число в формате String, чтобы не съедались нули в начале.


    private String winner;
    private String playerName;
    private String playersNumber; // итоговое число, загаданное игроком, вычисляемое компьютером
    private String computersNumber; // итоговое число, загаданное компьютером, вычисляемое игроком.

    public PageView(List<NumberView> playerList, List<NumberView> computerList, int number) {
        this.playerList = playerList;
        this.computerList = computerList;
        stringNumber = String.valueOf(number);

        if (stringNumber.length()<4) stringNumber = addZeroOnTheBegginingOfNumber(stringNumber);
    }

    public PageView(List<NumberView> playerList, List<NumberView> computerList) {
        this.playerList = playerList;
        this.computerList = computerList;
    }

    private String addZeroOnTheBegginingOfNumber(String stringNum){
        while (stringNum.length() < 4){
            stringNum = "0" + stringNum;
        }
        return stringNum;
    }

    public List<NumberView> getPlayerList() {
        return playerList;
    }

    public List<NumberView> getComputerList() {
        return computerList;
    }

    public String getStringNumber() {
        return stringNumber;
    }


    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayersNumber() {
        return playersNumber;
    }

    public void setPlayersNumber(String playersNumber) {
        this.playersNumber = playersNumber;
    }

    public String getComputersNumber() {
        return computersNumber;
    }

    public void setComputersNumber(int computersNumberInteger) {
        this.computersNumber = String.valueOf(computersNumberInteger);
        if (computersNumber.length()<4) computersNumber = addZeroOnTheBegginingOfNumber(computersNumber);
    }

}

