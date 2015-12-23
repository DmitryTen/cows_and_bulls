package testpack.definers;

import testpack.NumberFactory;
import testpack.ResultsKeeper;
import testpack2.NumberWithCowsAmount;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Windows on 20.12.2015.
 */
public abstract class Definer {

    protected HashMap<Integer, Boolean> definedDigits;
    protected Integer[] rndSecuence = NumberFactory.getRndSecuence();
    protected ArrayList<NumberWithCowsAmount> numbersHistory;
    protected int stepNumber;


    public Definer() {
        numbersHistory = ResultsKeeper.getInstance().getNumbersHistory();
        definedDigits =  ResultsKeeper.getInstance().getDefinedDigits();
    }

    public abstract int getNumber ();

    protected final int assembleByIndexes(int index1, int index2, int index3, int index4){
        int num = rndSecuence[index1]*1000 + rndSecuence[index2]*100 + rndSecuence[index3]*10 + rndSecuence[index4];
        numbersHistory.add(new NumberWithCowsAmount(num, index1, index2, index3, index4));
        return num;
    }

    protected int getRes(int indexOfNumbersHistoryList){
        return numbersHistory.get(indexOfNumbersHistoryList).getResultSum();
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    public boolean isCowsAmountReceived(int stepNumber) {
        return numbersHistory.get(stepNumber).isCowsAmountReceived();
    }

}
