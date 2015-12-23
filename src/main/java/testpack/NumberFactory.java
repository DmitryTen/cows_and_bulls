package testpack;


import org.apache.log4j.Logger;
import testpack.definers.Definer2;
import testpack.definers.DefinerBase;
import testpack.definers.DefinerException;
import testpack2.NumberWithCowsAmount;

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
    private int stage = 0;
    private static Integer[] rndSecuence = new Integer[10];
    private Definer2 definer;


    private static NumberFactory ourInstance = new NumberFactory();

    public static NumberFactory getInstance() {
        return ourInstance;
    }

    public static Integer[] getRndSecuence() {
        return rndSecuence;
    }

    private NumberFactory() {

        fillRndSecuence();
        ResultsKeeper.getInstance(numbersHistory, definedDigits);
    }

    private void fillRndSecuence(){
        ArrayList<Integer> byteList = new ArrayList<>();
        for (int b=0; b<10; b++) byteList.add(b);
        Collections.shuffle(byteList);
        rndSecuence = byteList.toArray(rndSecuence);
    }

    public int getARandomNumber() throws DefinerException {
        int num;
        if (stage==0) {
            stage = 1;
            definer = new DefinerBase();
            num = definer.getNumber();
            return num;
        }
        if (stage==1) {
            int resultOfLastStep = numbersHistory.get(numbersHistory.size()-1).getResultSum();
            if (resultOfLastStep == 0){
                stage = 2;
                putDefinedDigits(false);
                num = getARandomNumber();
                return num;
            }
            if (resultOfLastStep == 4){
                stage = 3;
                putDefinedDigits(true);
                num = getARandomNumber();
                return num;
            }
            return definer.getNumber();
        }





    }

    private void putDefinedDigits(boolean result){
        definedDigits.put(numbersHistory.get(numbersHistory.size()-1).getIndex1(), result);
        definedDigits.put(numbersHistory.get(numbersHistory.size()-1).getIndex2(), result);
        definedDigits.put(numbersHistory.get(numbersHistory.size()-1).getIndex3(), result);
        definedDigits.put(numbersHistory.get(numbersHistory.size()-1).getIndex4(), result);
    }


    public void setCowsAmount(int number, int cows, int bulls){
        NumberWithCowsAmount cowsAmount = numbersHistory.get(numbersHistory.size() - 1);
        if (cowsAmount.getNum() == number){
            cowsAmount.setCowsAmount(cows);
            cowsAmount.setBullsAmount(bulls);
            cowsAmount.setCowsAmountReceived(true);
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
