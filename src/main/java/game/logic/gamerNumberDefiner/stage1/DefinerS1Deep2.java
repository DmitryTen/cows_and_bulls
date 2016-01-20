package game.logic.gamerNumberDefiner.stage1;

import game.logic.gamerNumberDefiner.NumberDefiner;
import game.logic.gamerNumberDefiner.NumberInfo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Windows on 22.12.2015.
 */
public class DefinerS1Deep2 extends DefinerS1 {

    private DefinerS1 definerS1Deep3;
    private boolean isDefinerInitialized;

    public DefinerS1Deep2(Integer[] rndSecuence, ArrayList<NumberInfo> numbersHistory, HashMap<Integer, Boolean> definedDigits, int stepNumber, NumberDefiner numberDefiner){
        super(rndSecuence, numbersHistory, definedDigits, stepNumber, numberDefiner);
    }

    @Override
    public int getNumber() {
        if(stepNumber<=numbersHistory.size()-1) return handleResults();
        else return getNumberFromCurrentDefiner();
    }

    private int handleResults() {
        if (isDefinerInitialized) return definerS1Deep3.getNumber();
        else {
            x2z1_X3[0] = getRes(stepNumber) - y1y2_Z3[0];
            z2x1_Y3[0] = x1x2_X1[0] + y1y2_Y1[0] + z1z2_Z1[0] - x2z1_X3[0] - y1y2_Z3[0];
            definerS1Deep3 = new DefinerS1Deep3(rndSecuence, numbersHistory, definedIndexes, numbersHistory.size(), numberDefiner);
            definerS1Deep3.setVariables(x1x2_X1, y1y2_Y1, z1z2_Z1, y2x1_X2, x2y1_Y2, z1z2_Z2, x2z1_X3, z2x1_Y3, y1y2_Z3);
            isDefinerInitialized = true;
            return definerS1Deep3.getNumber();
        }
    }

    private int getNumberFromCurrentDefiner() {
        if (x1x2_X1[0]==1 && y1y2_Y1[0]==1){
            if (z1z2_Z1[0]==1) return defineResults111(x1x2_X1, y1y2_Y1, z1z2_Z1);
            return defineResults11(x1x2_X1, y1y2_Y1, z1z2_Z1);
        }

        if (y1y2_Y1[0]==1 && z1z2_Z1[0]==1){
            return defineResults11(y1y2_Y1, z1z2_Z1, x1x2_X1);
        }

        if (z1z2_Z1[0]==1 && x1x2_X1[0]==1){
            return defineResults11(z1z2_Z1, x1x2_X1, y1y2_Y1);
        }

        if (x1x2_X1[0]==1){
            return defineResults1(x1x2_X1, y1y2_Y1, z1z2_Z1);
        }

        if (y1y2_Y1[0]==1) {
            return defineResults1(y1y2_Y1, z1z2_Z1, x1x2_X1);
        }

        if (z1z2_Z1[0]==1){
            return defineResults1(z1z2_Z1, x1x2_X1, y1y2_Y1);
        }
        return -10;
    }
    private int defineResults1(int[] xx, int[] yy, int[] zz) {
        putIntoDefinedDigits(yy);
        putIntoDefinedDigits(zz);

        if (yy[0]==2){
            if (y2x1_X2[0] - 1 == 1) {
                putDefinedDigit(xx[1], true);
                putDefinedDigit(xx[2], false);
            }
            if (y2x1_X2[0] - 1 == 0) {
                putDefinedDigit(xx[1], false);
                putDefinedDigit(xx[2], true);
            }
        }

        if (yy[0]==0){
            if (y2x1_X2[0] - 0 == 1) {
                putDefinedDigit(xx[1], true);
                putDefinedDigit(xx[2], false);
            }
            if (y2x1_X2[0] - 0 == 0) {
                putDefinedDigit(xx[1], false);
                putDefinedDigit(xx[2], true);
            }
        }

        toLogResDefinedDigits("4");
        numberDefiner.setStage(2);
        return numberDefiner.getNumber();
    }

    private int defineResults11(int[] xx, int[] yy, int[] zz) {
        if (y2x1_X2[0]== 0) {
            putIntoDefinedDigits(y2x1_X2);
            putIntoDefinedDigits(x2y1_Y2);
            putIntoDefinedDigits(z1z2_Z2);
        }
        if (y2x1_X2[0]==2) {
            putIntoDefinedDigits(y2x1_X2);
            putIntoDefinedDigits(x2y1_Y2);
            putIntoDefinedDigits(z1z2_Z2);
        }
        if (y2x1_X2[0]==1) {
            return createNewNumber(zz, xx, yy);
        }

        toLogResDefinedDigits("3");
        numberDefiner.setStage(2);
        return numberDefiner.getNumber();
    }

    private int defineResults111(int[] xx, int[] yy, int[] zz) {
        return createNewNumber(zz, xx, yy);
    }

    private int createNewNumber(int[] equals1X, int[] equals1Y, int[] someZ){
        x2z1_X3 = new int[3];
        z2x1_Y3 = new int[3];
        y1y2_Z3 = someZ;
        x2z1_X3[1] = equals1Y[2];
        x2z1_X3[2] = equals1X[1];
        z2x1_Y3[1] = equals1X[2];
        z2x1_Y3[2] = equals1Y[1];
        return assembleByIndexes(equals1Y[2], someZ[1], someZ[2], equals1X[1]);
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
