package logic;


import org.apache.log4j.Logger;
import logic.definers.Definer;
import logic.definers.DefinerMain;
import logic.definers.DefinerException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
/**
 * Created by Windows on 18.12.2015.
 */
public class NumberFactory {

    private static final Logger log = Logger.getLogger(NumberFactory.class);
    private ArrayList<NumberWithCowsAmount> numbersHistory = new ArrayList<>();
    private HashMap<Integer, Boolean> definedDigits = new HashMap<>();
    private Integer[] rndSecuence = new Integer[10];
    private int stage = 1;


    private Definer definer;

    private static NumberFactory ourInstance = new NumberFactory();

    public static NumberFactory getInstance() {
        return ourInstance;
    }

    private NumberFactory() {
        fillRndSecuence();
        definer = new DefinerMain(rndSecuence, numbersHistory, definedDigits);
    }

    private void fillRndSecuence(){
        ArrayList<Integer> numbersArray = new ArrayList<>();
        for (int b=0; b<10; b++) numbersArray.add(b);
        Collections.shuffle(numbersArray);
        rndSecuence = numbersArray.toArray(rndSecuence);
    }

    public int getARandomNumber() throws DefinerException {
        if (stage==1) {
            return definer.getNumber();
        }
        if (stage==2) {

        }
        return 0;
    }



    public void setCowsAmount(int number, int cows, int bulls){
        NumberWithCowsAmount cowsAmount = numbersHistory.get(numbersHistory.size() - 1);
        if (cowsAmount.getNum() == number){
            cowsAmount.setCowsAmount(cows);
            cowsAmount.setBullsAmount(bulls);
        }
    }

  /*  public static void main(String[] args){
        NumberFactory n = getInstance();
        for (int i=0; i<10; i++) System.out.println(n.getARandomNumber());
        log.debug("Hello World");
        method();
    }

    private static void method(){
        log.info("Hello World 2");
    }*/

    public void setStage(int stage) {
        this.stage = stage;
    }
}
