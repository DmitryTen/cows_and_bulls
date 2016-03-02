package game.web.wicket.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Windows on 19.01.2016.
 */
public class PageModel {

    private String player_name;
    private int player_number;      //число, которое запрашивает игрок у компьютера
    private int computer_number;    //число, которое сгенерировал компьютер и запрашивает у игрока

    private int cow_group;
    private int bull_group;

    private List<NumberModel> player_list = new ArrayList<>(); //табличный список
    private List<NumberModel> computer_list = new ArrayList<>();



    private String winner;
    private String proposedPlayerNumber; // итоговое число, загаданное игроком, вычисляемое компьютером
    private String proposedComputerNumber; // итоговое число, загаданное компьютером, вычисляемое игроком.

    public PageModel(){}

    public PageModel(String player_name){
        this.player_name = player_name;
    }

    private String addZeroOnTheBegginingOfNumber(int computer_number){
        String stringNum = String.valueOf(computer_number);
        while (stringNum.length() < 4){
            stringNum = "0" + stringNum;
        }
        return stringNum;
    }
    public String getString_computer_number(){
        return addZeroOnTheBegginingOfNumber(computer_number);
    }

    public void addToPlayerList(NumberModel numberModel){
        player_list.add(numberModel);
    }

    public void addToComputerList(NumberModel numberModel){
        computer_list.add(numberModel);
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public int getPlayer_number() {
        return player_number;
    }

    public void setPlayer_number(int player_number) {
        this.player_number = player_number % 10000;
    }

    public int getComputer_number() {
        return computer_number;
    }

    public void setComputer_number(int computer_number) {
        this.computer_number = computer_number;
    }

    public int getCow_group() {
        return cow_group;
    }

    public void setCow_group(int cow_group) {
        this.cow_group = cow_group;
    }

    public int getBull_group() {
        return bull_group;
    }

    public void setBull_group(int bull_group) {
        this.bull_group = bull_group;
    }

    public List<NumberModel> getPlayer_list() {
        return player_list;
    }

    public void setPlayer_list(List<NumberModel> player_list) {
        this.player_list = player_list;
    }

    public List<NumberModel> getComputer_list() {
        return computer_list;
    }

    public void setComputer_list(List<NumberModel> computer_list) {
        this.computer_list = computer_list;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getProposedPlayerNumber() {
        return proposedPlayerNumber;
    }

    public void setProposedPlayerNumber(String proposedPlayerNumber) {
        this.proposedPlayerNumber = proposedPlayerNumber;
    }

    public String getProposedComputerNumber() {
        return proposedComputerNumber;
    }

    public void setProposedComputerNumber(String proposedComputerNumber) {
        this.proposedComputerNumber = proposedComputerNumber;
    }

    public void makeZero(){
        player_number = 0;
        computer_number = 0;
        cow_group = 0;
        bull_group = 0;
        winner = null;
        proposedComputerNumber = null;
        proposedPlayerNumber = null;
        player_list= new ArrayList<>();
        computer_list = new ArrayList<>();
    }

    /*

    private List<NumberModel> playerList;
    private List<NumberModel> computerList;
    private String stringNumber; // запрашиваемое у игрока число в формате String, чтобы не съедались нули в начале.








    public List<NumberModel> getPlayerList() {
        return playerList;
    }

    public List<NumberModel> getComputerList() {
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

    public String getProposedPlayerNumber() {
        return proposedPlayerNumber;
    }

    public void setProposedPlayerNumber(String proposedPlayerNumber) {
        this.proposedPlayerNumber = proposedPlayerNumber;
    }

    public String getProposedComputerNumber() {
        return proposedComputerNumber;
    }

    public void setProposedComputerNumber(int computersNumberInteger) {
        this.proposedComputerNumber = String.valueOf(computersNumberInteger);
        if (proposedComputerNumber.length()<4) proposedComputerNumber = addZeroOnTheBegginingOfNumber(proposedComputerNumber);
    }*/

}

