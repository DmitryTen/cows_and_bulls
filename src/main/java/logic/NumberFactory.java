package logic;


import logic.stage1.DefinerStage1Main;
import logic.stage2.CowInfo;
import logic.stage2.DefinerStage2Main;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
/**
 * Created by Windows on 18.12.2015.
 */
public class NumberFactory {

    private static final Logger log = Logger.getLogger(NumberFactory.class);
    private ArrayList<NumberWithCowsAmount> numbersHistory = new ArrayList<>();
    private HashMap<Integer, Boolean> definedIndexes = new HashMap<>();
    private Integer[] rndSecuence = new Integer[10];
    private int stage = 1;
    private HashMap<Integer, CowInfo> cowContainer = new HashMap<>();
    private HashMap<Byte, Integer> definedPositions = new HashMap<>();


    private Definer definer;

    private static NumberFactory ourInstance = new NumberFactory();

    public static NumberFactory getInstance() {
        return ourInstance;
    }

    private NumberFactory() {
        fillRndSecuence();
        definer = new DefinerStage1Main(rndSecuence, numbersHistory, definedIndexes);
    }

    public NumberFactory(Integer[] rndSecuence){
        this.rndSecuence = rndSecuence;
    }

    private void fillRndSecuence(){
        ArrayList<Integer> numbersArray = new ArrayList<>();
        for (int b=0; b<10; b++) numbersArray.add(b);
        Collections.shuffle(numbersArray);
        rndSecuence = numbersArray.toArray(rndSecuence);
    }

    public int getNumber(){
        if (stage==1) {
            return definer.getNumber();
        }
        if (stage==2) {
     //       return definer.getNumber();
        }
        if (stage==3) {
            return 1;
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
        for (int i=0; i<10; i++) System.out.println(n.getNumber());
        log.debug("Hello World");
        method();
    }

    private static void method(){
        log.info("Hello World 2");
    }*/

    public void setStage(int stage) {
        if (stage==2) definer = new DefinerStage2Main(rndSecuence, numbersHistory, definedIndexes, cowContainer, definedPositions);
        this.stage = stage;
    }
}
