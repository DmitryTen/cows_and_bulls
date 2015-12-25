package testpack2;

import logic.NumberWithCowsAmount;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Windows on 20.12.2015.
 */
public class ResultsKeeper {
    private static ResultsKeeper ourInstance;
    private ArrayList<NumberWithCowsAmount> numbersHistory;
    private HashMap<Integer, Boolean> definedDigits;

    public static ResultsKeeper getInstance(ArrayList<NumberWithCowsAmount> numbersHistory, HashMap<Integer, Boolean> definedDigits) {
        if (ourInstance == null) return new ResultsKeeper(numbersHistory, definedDigits);
        return ourInstance;
    }

    public static ResultsKeeper getInstance() {
        return ourInstance;
    }

    private ResultsKeeper(ArrayList<NumberWithCowsAmount> numbersHistory, HashMap<Integer, Boolean> definedDigits) {
        this.definedDigits = definedDigits;
        this.numbersHistory = numbersHistory;
    }

    public ArrayList<NumberWithCowsAmount> getNumbersHistory() {
        return numbersHistory;
    }

    public HashMap<Integer, Boolean> getDefinedDigits() {
        return definedDigits;
    }


}
