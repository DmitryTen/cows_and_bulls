package game.logic.computersNumber;

import game.db.entity.PlayerNumber;
import game.web.wicket.game.models.NumberModel;
import game.web.wicket.game.models.PageModel;
import org.apache.log4j.Logger;

/**
 * Created by Windows on 15.01.2016.
 *
 * Кратко: данный класс выдает быков и коров по полученному числу.
 *
 * Данный класс "загадывает" число при создании игры, а затем работает с числами, запрашиваемыми игроком: выдает игроку
 * количество быков и коров, относительно запрошенного им числа.
 */

public class CowBullDefiner {

    protected static final Logger log = Logger.getLogger(CowBullDefiner.class);
    //загаданное число
    private int proposedNumber;
    //отдельные цифры загаданного числа (обновляются с каждым запросом)
    private int proposedDigit1;
    private int proposedDigit2;
    private int proposedDigit3;
    private int proposedDigit4;

    //отдельные цифры запрашиваемого числа (обновляются с каждым запросом)
    private int requestedD1;
    private int requestedD2;
    private int requestedD3;
    private int requestedD4;

    private int step = 0;

    //результаты
    private int resultBulls;
    private int resultCows;


    public CowBullDefiner(){
        makeNumber();
    }

    public CowBullDefiner (int proposedNumber){
        this.proposedNumber = proposedNumber;
    }

    private void makeNumber(){
        proposedNumber = (int)(Math.random()*10000);
    }

    private void fillLocalTemporaryDigits(){
        proposedDigit1 = proposedNumber /1000%10;
        proposedDigit2 = proposedNumber /100%10;
        proposedDigit3 = proposedNumber /10%10;
        proposedDigit4 = proposedNumber /1%10;
    }

    private void defineDigitsFromRequestedNumber(int number){
        requestedD1 = number /1000%10;
        requestedD2 = number /100%10;
        requestedD3 = number /10%10;
        requestedD4 = number /1%10;
    }

    private void defineBulls(int number){
        defineDigitsFromRequestedNumber(number);
        fillLocalTemporaryDigits();

        //Сначала вызываем bulls, затем cows - НЕ наоборот.
        resultBulls = getBulls();
        resultCows = getCows();

        log.debug("Computers number: " + proposedNumber + ", asked number: " + number + ", bulls amount: " + resultBulls + ", cows amount: " + resultCows);
    }

    public void fillDBEntityByBullsAndCows(PlayerNumber playerNumber){
        int number = playerNumber.getNum();
        defineDigitsFromRequestedNumber(number);
        fillLocalTemporaryDigits();
        
        playerNumber.setBullsAmount(getBulls());
        playerNumber.setCowsAmount(getCows());
    }



    public NumberModel createNumberViewAndDefineCowBullAmount(PageModel pageModel){
        int number = pageModel.getPlayer_number();
        String playerName = pageModel.getPlayer_name();

        defineBulls(number);

        NumberModel numberModel = new NumberModel();
        numberModel.setStep(++step);
        numberModel.setPlayerName(playerName);
        numberModel.setNumber(number);
        numberModel.setCows(resultCows);
        numberModel.setBulls(resultBulls);


        return numberModel;
    }
    
     private int getBulls(){
        int bulls = 0;
        if (requestedD1 == proposedDigit1) {
            bulls++;
            proposedDigit1 = -1;
            requestedD1 = -2;
        }
        if (requestedD2 == proposedDigit2) {
            bulls++;
            proposedDigit2 = -1;
            requestedD2 = -2;
        }
        if (requestedD3 == proposedDigit3) {
            bulls++;
            proposedDigit3 = -1;
            requestedD3 = -2;
        }
        if (requestedD4 == proposedDigit4) {
            bulls++;
            proposedDigit4 = -1;
            requestedD4 = -2;
        }
        return bulls;
    }

    private int getCows(){
        int cows=0;
        if (proposedDigit1 == requestedD2 || proposedDigit1 == requestedD3 || proposedDigit1 == requestedD4){
            if(proposedDigit1 == requestedD2) requestedD2 = -2;
            if(proposedDigit1 == requestedD3) requestedD3 = -2;
            if(proposedDigit1 == requestedD4) requestedD4 = -2;
            cows++;
            proposedDigit1 =-1;
        }
        if (proposedDigit2 == requestedD1 || proposedDigit2 == requestedD3 || proposedDigit2 == requestedD4){
            if(proposedDigit2 == requestedD1) requestedD1 = -2;
            if(proposedDigit2 == requestedD3) requestedD3 = -2;
            if(proposedDigit2 == requestedD4) requestedD4 = -2;
            cows++;
            proposedDigit2 =-1;
        }
        if (proposedDigit3 == requestedD2 || proposedDigit3 == requestedD1 || proposedDigit3 == requestedD4){
            if(proposedDigit3 == requestedD2) requestedD2 = -2;
            if(proposedDigit3 == requestedD1) requestedD1 = -2;
            if(proposedDigit3 == requestedD4) requestedD4 = -2;
            cows++;
            proposedDigit3 =-1;
        }
        if (proposedDigit4 == requestedD2 || proposedDigit4 == requestedD3 || proposedDigit4 == requestedD1){
            if(proposedDigit4 == requestedD2) requestedD2 = -2;
            if(proposedDigit4 == requestedD3) requestedD3 = -2;
            if(proposedDigit4 == requestedD1) requestedD1 = -2;
            cows++;
            proposedDigit4 =-1;
        }
        return cows;
    }


    public String getProposedNumber() {
        String stringNum = String.valueOf(proposedNumber);
        while (stringNum.length() < 4){
            stringNum = "0" + stringNum;
        }return stringNum;
    }
}
