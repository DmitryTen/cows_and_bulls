package logic.definers;

import logic.NumberFactory;
import logic.NumberWithCowsAmount;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Windows on 22.12.2015.
 */
public class DefinerDeep2 extends Definer {

    private Definer definerDeep3;
    private boolean isDefinerInitialized;

    public DefinerDeep2(Integer[] rndSecuence, ArrayList<NumberWithCowsAmount> numbersHistory, HashMap<Integer, Boolean> definedDigits, int stepNumber){
        super(rndSecuence, numbersHistory, definedDigits, stepNumber);
    }

    @Override
    public int getNumber() throws DefinerException{
     //   log.debug(stepNumber+ " " + (numbersHistory.size()-1));
        if(stepNumber<=numbersHistory.size()-1) return handleResults();
        else return getNumberFromCurrentDefiner();
    }

    private int handleResults() throws DefinerException{
        if (isDefinerInitialized) return definerDeep3.getNumber();
        else {
            x2z1_X3[0] = getRes(stepNumber) - y1y2_Z3[0];
            z2x1_Y3[0] = x1x2_X1[0] + y1y2_Y1[0] + z1z2_Z1[0] - x2z1_X3[0] - y1y2_Z3[0];
            definerDeep3 = new DefinerDeep3(rndSecuence, numbersHistory, definedDigits, numbersHistory.size());
            definerDeep3.setVariables(x1x2_X1, y1y2_Y1, z1z2_Z1, y2x1_X2, x2y1_Y2, z1z2_Z2, x2z1_X3, z2x1_Y3, y1y2_Z3);
            isDefinerInitialized = true;
            return definerDeep3.getNumber();
        }
    }

    private int getNumberFromCurrentDefiner() throws DefinerException{
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
            putIntoDefinedDigits(y1y2_Y1);
            putIntoDefinedDigits(z1z2_Z1);
            return defineResults1(x1x2_X1, y1y2_Y1, z1z2_Z1);
        }

        if (y1y2_Y1[0]==1) {
            putIntoDefinedDigits(x1x2_X1);
            putIntoDefinedDigits(z1z2_Z1);
            return defineResults1(y1y2_Y1, z1z2_Z1, x1x2_X1);
        }

        if (z1z2_Z1[0]==1){
            putIntoDefinedDigits(x1x2_X1);
            putIntoDefinedDigits(y1y2_Y1);
            return defineResults1(z1z2_Z1, x1x2_X1, y1y2_Y1);
        }
        return -10;
    }
    private int defineResults1(int[] xx, int[] yy, int[] zz) throws DefinerException{
        if (yy[0]==2){
            if (y2x1_X2[0] - 1 == 1) {
                definedDigits.put(xx[1], true);
                definedDigits.put(xx[2], false);
            }
            if (y2x1_X2[0] - 1 == 0) {
                definedDigits.put(xx[1], false);
                definedDigits.put(xx[2], true);
            }
        }
        if (yy[0]==0){
            if (y2x1_X2[0] - 0 == 1) {
                definedDigits.put(xx[1], true);
                definedDigits.put(xx[2], false);
            }
            if (y2x1_X2[0] - 0 == 0) {
                definedDigits.put(xx[1], false);
                definedDigits.put(xx[2], true);
            }
        }

        toLogResDefinedDigits("4");
        NumberFactory n = NumberFactory.getInstance();
        n.setStage(2);
        return n.getARandomNumber();
    }

    private int defineResults11(int[] xx, int[] yy, int[] zz) throws DefinerException{
        if (y2x1_X2[0]== 0) {
            log.debug("Я здесь! " +  x2y1_Y2[0]);
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
        NumberFactory n = NumberFactory.getInstance();
        n.setStage(2);
        return n.getARandomNumber();
    }

    private int defineResults111(int[] xx, int[] yy, int[] zz) throws DefinerException{

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
            definedDigits.put(x[1], false);
            definedDigits.put(x[2], false);
        }
        if (x[0]==2) {
            definedDigits.put(x[1], true);
            definedDigits.put(x[2], true);
        }
    }
}
