package testpack2;

import java.util.ArrayList;

/**
 * Created by Windows on 18.12.2015.
 */
public class Stage1 {
    private ArrayList<NumberWithCowsAmount> numbersHistory;
    Byte[] rndSecuence;

    private static Stage1 ourInstance;
    private Stage1(ArrayList<NumberWithCowsAmount> numbersHistory, Byte[] rndSecuence) {
        this.numbersHistory = numbersHistory;
        this.rndSecuence = rndSecuence;
    }
    public static Stage1 getInstance(ArrayList<NumberWithCowsAmount> numbersHistory, Byte[] rndSecuence) {
        if (ourInstance == null)
            return new Stage1(numbersHistory, rndSecuence);
        return ourInstance;
    }



  /*  public int getNumberInCase1Coinsidience(){

    }*/
}
