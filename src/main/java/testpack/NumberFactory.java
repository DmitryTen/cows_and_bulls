package testpack;


import org.apache.log4j.Logger;
import testpack.definers.Definer;
import testpack.definers.DefinerMain;
import testpack.definers.DefinerException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
/**
 * Created by Windows on 18.12.2015.
 */
public class NumberFactory {

    private static final Logger log = Logger.getLogger(NumberFactory.class);
    private ArrayList<NumberWithCowsAmount> numbersHistory = Definer.getNumbersHistory();
    private HashMap<Integer, Boolean> definedDigits = Definer.getDefinedDigits();
    private int stage = 1;

    private Definer definer;

    private static NumberFactory ourInstance = new NumberFactory();

    public static NumberFactory getInstance() {
        return ourInstance;
    }

    private NumberFactory() {
        definer = new DefinerMain();

    }

    public int getARandomNumber() throws DefinerException {
        if (stage==1) {
            return definer.getNumber();
        }
        if (stage==2) {

        }




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
