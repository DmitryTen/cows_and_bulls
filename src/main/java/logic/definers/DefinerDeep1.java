package logic.definers;

import logic.NumberFactory;
import logic.NumberWithCowsAmount;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Windows on 22.12.2015.
 */
public class DefinerDeep1 extends Definer {

    private Definer definerDeep2;
    private boolean isDefinerInitialized;

    public DefinerDeep1(Integer[] rndSecuence, ArrayList<NumberWithCowsAmount> numbersHistory, HashMap<Integer, Boolean> definedDigits, int stepNumber){
        super(rndSecuence, numbersHistory, definedDigits, stepNumber);
    }

    @Override
    public int getNumber() throws DefinerException{
       // log.debug(stepNumber + " " + (numbersHistory.size()-1));
        if(stepNumber<=numbersHistory.size()-1) return handleResults();
        else return getNumberFromCurrentDefiner();
    }

    private int handleResults() throws DefinerException{
        if (isDefinerInitialized) return definerDeep2.getNumber();
        else {
            y2x1_X2[0] = getRes(stepNumber) - z1z2_Z2[0];
            x2y1_Y2[0] = x1x2_X1[0] + y1y2_Y1[0] + z1z2_Z1[0] - y2x1_X2[0] - z1z2_Z2[0];
            definerDeep2 = new DefinerDeep2(rndSecuence, numbersHistory, definedDigits, numbersHistory.size());
            definerDeep2.setVariables(x1x2_X1, y1y2_Y1, z1z2_Z1, y2x1_X2, x2y1_Y2, z1z2_Z2);

            isDefinerInitialized = true;
            return definerDeep2.getNumber();
        }

    }

    private int getNumberFromCurrentDefiner() throws DefinerException{
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

        putIntoDefinedDigits(x1x2_X1);
        putIntoDefinedDigits(y1y2_Y1);
        putIntoDefinedDigits(z1z2_Z1);

        toLogResDefinedDigits("2");
        NumberFactory n = NumberFactory.getInstance();
        n.setStage(2);
        return n.getARandomNumber();
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
