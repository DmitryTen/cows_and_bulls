package testpack.definers;

import testpack.NumberWithCowsAmount;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by Windows on 20.12.2015.
 */
public abstract class Definer {

    protected static HashMap<Integer, Boolean> definedDigits = new HashMap<>();
    protected static Integer[] rndSecuence = new Integer[10];
    protected static ArrayList<NumberWithCowsAmount> numbersHistory = new ArrayList<>();
    protected int stepNumber;

    static {
        fillRndSecuence();
    }

    public Definer() {
    }

    private static void fillRndSecuence(){
        ArrayList<Integer> byteList = new ArrayList<>();
        for (int b=0; b<10; b++) byteList.add(b);
        Collections.shuffle(byteList);
        rndSecuence = byteList.toArray(rndSecuence);
    }

    protected final int assembleByIndexes(int index1, int index2, int index3, int index4){
        int num = rndSecuence[index1]*1000 + rndSecuence[index2]*100 + rndSecuence[index3]*10 + rndSecuence[index4];
        numbersHistory.add(new NumberWithCowsAmount(num, index1, index2, index3, index4));
        return num;
    }

    public abstract int getNumber() throws DefinerException;

    protected int getRes(int indexOfNumbersHistoryList){
        return numbersHistory.get(indexOfNumbersHistoryList).getResultSum();
    }

    public Integer[] getRndSecuence() {
        return rndSecuence;
    }

    public static void setRndSecuence(Integer[] rndSecuence) {
        Definer.rndSecuence = rndSecuence;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    public static ArrayList<NumberWithCowsAmount> getNumbersHistory() {
        return numbersHistory;
    }

    public static HashMap<Integer, Boolean> getDefinedDigits() {
        return definedDigits;
    }
}
