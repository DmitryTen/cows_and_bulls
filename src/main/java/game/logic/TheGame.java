package game.logic;

import game.entity.ComputerNumber;
import game.entity.Game;
import game.entity.Player;
import game.entity.PlayerNumber;
import game.logic.computersNumber.CowBullDefiner;
import game.logic.gamerNumberDefiner.NumberDefiner;
import game.logic.gamerNumberDefiner.NumberInfo;

/**
 * Created by Windows on 18.01.2016.
 */
public class TheGame {
    private CowBullDefiner comp;
    private NumberDefiner numberDefiner;
    private Game game;

    private int playersStep;
    private boolean theLastStep;

    private byte winner = -1;


    public TheGame(Player player) {
        this.comp = new CowBullDefiner();
        this.numberDefiner = new NumberDefiner();
        this.game = new Game(player);
        game.setRndSecuence(numberDefiner.getRndSecuense());
    }

    public PlayerNumber handlePlayersNumberReturnDBEntity(int number){
        playersStep++;
        if(number>=10000) number = number%10000;
        PlayerNumber playerNumber = new PlayerNumber(number, game, playersStep);
        comp.fillDBEntityByBullsAndCows(playerNumber);
        if (playerNumber.getBullsAmount()==4) theLastStep = true;
        return playerNumber;
    }

    public int getNumberFromDefiner(){
        return numberDefiner.getNumber();
    }

    public ComputerNumber handleComputerNumberReturnDBEntity(int number, int cows, int bulls){
        NumberInfo numberInfo = numberDefiner.setCowsAndGetNumberInfo(number, cows, bulls);
        ComputerNumber computerNumber = new ComputerNumber(numberInfo, game);

        checkWinner(numberInfo);

        return computerNumber;
    }

    public Game getGame(){
        return game;
    }

    public void setIdIntoGame(long id){
        game.setGameId(id);
    }

    private void checkWinner(NumberInfo numberInfo){
        if(theLastStep || numberInfo.getBullsAmount()==4){
            if(theLastStep && numberInfo.getBullsAmount() != 4) winner=1; // player Wins
            if(!theLastStep && numberInfo.getBullsAmount() == 4) winner=2; // computer Wins
            if(theLastStep && numberInfo.getBullsAmount() == 4) winner=3; // friendship Wins
            getNumberFromDefiner(); //эта операция позволит обработать полученных на предыдущем шаге быков и коров
        }
    }

    public byte getWinner(){
        return winner;
    }

    public String revealNumProposedByPlayer(){
        return numberDefiner.revealNumProposedByPlayer();
    }

    public int revealNumProposedByComputer(){
        return comp.getProposedNumber();
    }
}
