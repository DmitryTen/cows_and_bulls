package game.logic.gamerNumberDefiner;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Windows on 18.12.2015.
 */
public class NumberInfo {

    private static final Logger log = Logger.getLogger(NumberInfo.class);
    private int num;
    private int cowsAmount;
    private int bullsAmount;
    private int stepNumber;

    private int index1;
    private int index2;
    private int index3;
    private int index4;

    public NumberInfo(int num, int index1, int index2, int index3, int index4, int stepNumber) {
        this.num = num;
        this.index1 = index1;
        this.index2 = index2;
        this.index3 = index3;
        this.index4 = index4;
        this.stepNumber=stepNumber;
    }

    public int getNum() {
        return num;
    }

    public int getCowsAmount() {
        return cowsAmount;
    }

    public void setCowsAmount(int cowsAmount) {
        this.cowsAmount = cowsAmount;
        log.debug("step: " + stepNumber + ", number: " + num + ", cows: " + cowsAmount);
    }

    public int getBullsAmount() {
        return bullsAmount;
    }

    public void setBullsAmount(int bullsAmount) {
        this.bullsAmount = bullsAmount;
        log.debug("step: " + stepNumber + ", number: " + num  + ", bulls: " + bullsAmount);
    }

    public int getResultSum() {
        return (cowsAmount + bullsAmount);
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public int getIndexOfPosition1() {
        return index1;
    }

    public int getIndexOfPosition2() {
        return index2;
    }

    public int getIndexOfPosition3() {
        return index3;
    }

    public int getIndexOfPosition4() {
        return index4;
    }

    public byte findPositionOfIndex(int searchingIndex){
        if (searchingIndex==index1) return (byte)0;
        if (searchingIndex==index2) return (byte)1;
        if (searchingIndex==index3) return (byte)2;
        if (searchingIndex==index4) return (byte)3;
        return -1;
    }

    public boolean doesIndexExistsInNumber(int searchingIndex){
        if (searchingIndex==index1) return true;
        if (searchingIndex==index2) return true;
        if (searchingIndex==index3) return true;
        if (searchingIndex==index4) return true;
        return false;
    }

    public ArrayList<Integer> getAllTrueIndexesFromCurrentNumber(HashMap<Integer, Boolean> definedIndexes){
        ArrayList<Integer> trueIndexesOfCurrentNumber = new ArrayList<>();
        for (Integer index: definedIndexes.keySet()){
            if(definedIndexes.get(index) == true && doesIndexExistsInNumber(index)){
                trueIndexesOfCurrentNumber.add(index);
            }
        }
        return trueIndexesOfCurrentNumber;
    }



}
