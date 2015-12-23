package testpack2;

import testpack.NumberWithCowsAmount;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Windows on 19.12.2015.
 */
public class DefinerXX extends NumDefiner{
    private HashMap<Byte, Boolean> definedDigits;
    private Byte[] rndSecuence;
    private ArrayList<NumberWithCowsAmount> numbersHistory;
    private byte step1;
    private byte step2;
    private byte step3;
    private byte step4;

    public DefinerXX(ArrayList<NumberWithCowsAmount> numbersHistory, HashMap<Byte, Boolean> definedDigits, Byte[] rndSecuence) {
        this.definedDigits = definedDigits;
        this.rndSecuence = rndSecuence;
        step1 = numbersHistory.get(0).getResultSum();
        step2 = numbersHistory.get(1).getResultSum();
        step3 = numbersHistory.get(2).getResultSum();
    }

    @Override
    public int getStep4() {
        if (step3 ==1 || step3 ==2) return get7890();
        if (step3 ==3) return get6790();

        return 0;
    }

    public int getStep5() {
        step4 = numbersHistory.get(3).getResultSum();
        if (step3 == 1 &&  step4 == 0)
    }

    private int get7890(){
        return assembleByIndexes(7,8,9,0);
    }

    private int get6790(){
        return assembleByIndexes(6,7,9,0);
    }

    private int assembleByIndexes(int index1, int index2, int index3, int index4){
        int num = rndSecuence[index1]*1000 + rndSecuence[index2]*100 + rndSecuence[index3]*10 + rndSecuence[index4];
        numbersHistory.add(new NumberWithCowsAmount(num, index1, index2, index3, index4));
        return num;
    }
}
