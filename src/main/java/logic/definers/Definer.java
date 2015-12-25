package logic.definers;

import logic.NumberWithCowsAmount;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Windows on 20.12.2015.
 */
public abstract class Definer {

    protected static final Logger log = Logger.getLogger(Definer.class);
    protected HashMap<Integer, Boolean> definedDigits;
    protected Integer[] rndSecuence;
    protected ArrayList<NumberWithCowsAmount> numbersHistory;
    protected int stepNumber;

    public Definer(Integer[] rndSecuence, ArrayList<NumberWithCowsAmount> numbersHistory, HashMap<Integer, Boolean> definedDigits, int stepNumber) {
        this.rndSecuence = rndSecuence;
        this.numbersHistory = numbersHistory;
        this.definedDigits = definedDigits;
        this.stepNumber = stepNumber;
    }

    protected int x1x2_X1[];
    protected int y1y2_Y1[];
    protected int z1z2_Z1[];

    protected int y2x1_X2[];
    protected int x2y1_Y2[];
    protected int z1z2_Z2[];

    protected int x2z1_X3[];
    protected int z2x1_Y3[];
    protected int y1y2_Z3[];

    public void setVariables(int[] x1x2_X1, int[] y1y2_Y1, int[] z1z2_Z1){
        this.x1x2_X1 = x1x2_X1;
        this.y1y2_Y1 = y1y2_Y1;
        this.z1z2_Z1 = z1z2_Z1;
    }

    public void setVariables(int[] x1x2_X1, int[] y1y2_Y1, int[] z1z2_Z1, int[] y2x1_X2, int[] x2y1_Y2, int[] z1z2_Z2){
        this.x1x2_X1 = x1x2_X1;
        this.y1y2_Y1 = y1y2_Y1;
        this.z1z2_Z1 = z1z2_Z1;
        this.y2x1_X2 = y2x1_X2;
        this.x2y1_Y2 = x2y1_Y2;
        this.z1z2_Z2 = z1z2_Z2;
    }

    public void setVariables(int[] x1x2_X1, int[] y1y2_Y1, int[] z1z2_Z1, int[] y2x1_X2, int[] x2y1_Y2, int[] z1z2_Z2, int[] x2z1_X3, int[] z2x1_Y3, int[] y1y2_Z3){
        this.x1x2_X1 = x1x2_X1;
        this.y1y2_Y1 = y1y2_Y1;
        this.z1z2_Z1 = z1z2_Z1;
        this.y2x1_X2 = y2x1_X2;
        this.x2y1_Y2 = x2y1_Y2;
        this.z1z2_Z2 = z1z2_Z2;
        this.x2z1_X3 = x2z1_X3;
        this.z2x1_Y3 = z2x1_Y3;
        this.y1y2_Z3 = y1y2_Z3;
    }

    protected final int assembleByIndexes(int index1, int index2, int index3, int index4){
        int num = rndSecuence[index1]*1000 + rndSecuence[index2]*100 + rndSecuence[index3]*10 + rndSecuence[index4];
        numbersHistory.add(new NumberWithCowsAmount(num, index1, index2, index3, index4, numbersHistory.size()));
        log.debug("Received indexes: " + index1 + ", " + index2 + ", " + index3 + ", " + index4 + ", Created number:" + num);
        return num;
    }

    public abstract int getNumber() throws DefinerException;

    protected int getRes(int indexOfNumbersHistoryList){
        return numbersHistory.get(indexOfNumbersHistoryList).getResultSum();
    }

    public ArrayList<NumberWithCowsAmount> getNumbersHistory() {
        return numbersHistory;
    }

    public HashMap<Integer, Boolean> getDefinedDigits() {
        return definedDigits;
    }

    public void toLogResDefinedDigits(String logID){
        for (Integer i : definedDigits.keySet()){
            log.debug("Result of stage1: digit " + i +  " is: " + definedDigits.get(i) + " logId: " + logID);
        }
    }

    protected int getAmountOfAssembledNumbers(){
        return numbersHistory.size();
    }
}
