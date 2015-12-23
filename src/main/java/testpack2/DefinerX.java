package testpack2;

import testpack2.NumberWithCowsAmount;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Windows on 19.12.2015.
 */
public class DefinerX extends NumDefiner{
    private HashMap<Byte, Boolean> definedDigits;
    private Byte[] rndSecuence;
    private ArrayList<NumberWithCowsAmount> numbersHistory;
    private byte firstStep;
    private byte secondStep;
    private byte thirdStep;

    public DefinerX(ArrayList<NumberWithCowsAmount> numbersHistory, HashMap<Byte, Boolean> definedDigits, Byte[] rndSecuence) {
        this.definedDigits = definedDigits;
        this.rndSecuence = rndSecuence;
        firstStep = numbersHistory.get(0).getResultSum();
        secondStep = numbersHistory.get(1).getResultSum();
        thirdStep = numbersHistory.get(2).getResultSum();
        definedDigits.put(rndSecuence[9], false);
        definedDigits.put(rndSecuence[0], false);
    }

    @Override
    public int getNumberStep4() {
        if (thirdStep==1) return assembleByIndexes(6,7,9,0);


        return 0;
    }

    private int assembleByIndexes(int index1, int index2, int index3, int index4){
        int num = rndSecuence[index1]*1000 + rndSecuence[index2]*100 + rndSecuence[index3]*10 + rndSecuence[index4];
        numbersHistory.add(new NumberWithCowsAmount(num, index1, index2, index3, index4));
        return num;
    }
}
