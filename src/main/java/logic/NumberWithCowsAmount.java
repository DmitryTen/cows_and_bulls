package logic;

import org.apache.log4j.Logger;

/**
 * Created by Windows on 18.12.2015.
 */
public class NumberWithCowsAmount {

    private static final Logger log = Logger.getLogger(NumberWithCowsAmount.class);
    private int num;
    private int cowsAmount;
    private int bullsAmount;
    private int stepNumber;
    private int index1;
    private int index2;
    private int index3;
    private int index4;


    public int getNum() {
        return num;
    }

    public NumberWithCowsAmount(int num, int index1, int index2, int index3, int index4, int stepNumber) {
        this.num = num;
        this.index1 = index1;
        this.index2 = index2;
        this.index3 = index3;
        this.index4 = index4;
        this.stepNumber=stepNumber;

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

    public int getIndex1() {
        return index1;
    }

    public int getIndex2() {
        return index2;
    }

    public int getIndex3() {
        return index3;
    }

    public int getIndex4() {
        return index4;
    }


}
