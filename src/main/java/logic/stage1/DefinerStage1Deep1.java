package logic.stage1;

import logic.NumberFactory;
import logic.NumberWithCowsAmount;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Windows on 22.12.2015.
 */
public class DefinerStage1Deep1 extends DefinerStage1 {

    private DefinerStage1 definerStage1Deep2;
    private boolean isDefinerInitialized;

    public DefinerStage1Deep1(Integer[] rndSecuence, ArrayList<NumberWithCowsAmount> numbersHistory, HashMap<Integer, Boolean> definedDigits, int stepNumber){
        super(rndSecuence, numbersHistory, definedDigits, stepNumber);
    }

    @Override
    public int getNumber() {
        if(stepNumber<=numbersHistory.size()-1) return handleResults();
        else return getNumberFromCurrentDefiner();
    }

    private int handleResults() {
        if (isDefinerInitialized) return definerStage1Deep2.getNumber();
        else {
            y2x1_X2[0] = getRes(stepNumber) - z1z2_Z2[0];
            x2y1_Y2[0] = x1x2_X1[0] + y1y2_Y1[0] + z1z2_Z1[0] - y2x1_X2[0] - z1z2_Z2[0];
            definerStage1Deep2 = new DefinerStage1Deep2(rndSecuence, numbersHistory, definedIndexes, numbersHistory.size());
            definerStage1Deep2.setVariables(x1x2_X1, y1y2_Y1, z1z2_Z1, y2x1_X2, x2y1_Y2, z1z2_Z2);

            isDefinerInitialized = true;
            return definerStage1Deep2.getNumber();
        }
    }

    private int getNumberFromCurrentDefiner() {
        if (x1x2_X1[0]==1 && y1y2_Y1[0]==1){
            return createNewNumber(x1x2_X1, y1y2_Y1, z1z2_Z1);
        }
        if (y1y2_Y1[0]==1 && z1z2_Z1[0]==1){
            return createNewNumber(y1y2_Y1, z1z2_Z1, x1x2_X1);
        }
        if (z1z2_Z1[0]==1 && x1x2_X1[0]==1){
            return createNewNumber(z1z2_Z1, x1x2_X1, y1y2_Y1);
        }
        if (x1x2_X1[0]==1){
            return createNewNumber(x1x2_X1, y1y2_Y1, z1z2_Z1);
        }
        if (y1y2_Y1[0]==1){
            return createNewNumber(y1y2_Y1, z1z2_Z1, x1x2_X1);
        }
        if (z1z2_Z1[0]==1){
            return createNewNumber(z1z2_Z1, x1x2_X1, y1y2_Y1);
        }

        return goToAnotherStage(x1x2_X1,y1y2_Y1,z1z2_Z1);
    }

    private int createNewNumber(int[] mixingX, int[] mixingY, int[] sameZ){
        y2x1_X2 = new int[3];
        x2y1_Y2 = new int[3];
        z1z2_Z2 = sameZ;
        y2x1_X2[1] = mixingY[2];
        y2x1_X2[2] = mixingX[1];
        x2y1_Y2[1] = mixingX[2];
        x2y1_Y2[2] = mixingY[1];
        return assembleByIndexes(mixingY[2], sameZ[1], sameZ[2], mixingX[1]);
    }

    private int goToAnotherStage(int[] x1x2_X1, int[] y1y2_Y1, int[] z1z2_Z1) {
        putIntoDefinedDigits(x1x2_X1);
        putIntoDefinedDigits(y1y2_Y1);
        putIntoDefinedDigits(z1z2_Z1);

        toLogResDefinedDigits("2");
        NumberFactory n = NumberFactory.getInstance();
        n.setStage(2);
        return n.getNumber();
    }

    private void putIntoDefinedDigits(int[] x){
        if (x[0]==0) {
            putDefinedDigit(x[1], false);
            putDefinedDigit(x[2], false);
        }
        if (x[0]==2) {
            putDefinedDigit(x[1], true);
            putDefinedDigit(x[2], true);
        }
    }
}
