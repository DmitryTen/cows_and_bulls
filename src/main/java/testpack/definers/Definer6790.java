package testpack.definers;

/**
 * Created by Windows on 21.12.2015.
 */
public class Definer6790 extends Definer {
    private Definer definer;
    private int num;

    public Definer6790 (Integer[] rndSecuence) {
        super(rndSecuence);
        num = assembleByIndexes(6,7,9,0);
    }

    @Override
    public int getNumber() {
        if (definer != null) return definer.getNumber();
        if (isCowsAmountReceived(stepNumber)) putDefinedDigits();

        return num;
    }

    private void putDefinedDigits(){
        if (stepNumber==3){
            if (getRes(stepNumber)==3){
                definedDigits.put(rndSecuence[5], false);
                definedDigits.put(rndSecuence[6], true);
                definedDigits.put(rndSecuence[7], false);
                definedDigits.put(rndSecuence[8], false);
                definedDigits.put(rndSecuence[9], true);
                definedDigits.put(rndSecuence[0], true);
            }
            if (getRes(stepNumber)==3){
                definedDigits.put(rndSecuence[5], true);
                definedDigits.put(rndSecuence[6], false);
                definedDigits.put(rndSecuence[7], false);
                definedDigits.put(rndSecuence[8], false);
                definedDigits.put(rndSecuence[9], true);
                definedDigits.put(rndSecuence[0], true);
            }
        }

        if (stepNumber==4){
            if (getRes(stepNumber - 1)==3){
                if (getRes(stepNumber)==2){
                    definedDigits.put(rndSecuence[5], false);
                    definedDigits.put(rndSecuence[6], false);
                    definedDigits.put(rndSecuence[7], false);
                    definedDigits.put(rndSecuence[8], true);
                    definedDigits.put(rndSecuence[9], true);
                    definedDigits.put(rndSecuence[0], true);
                }
                if (getRes(stepNumber)==3){
                    definedDigits.put(rndSecuence[5], false);
                    definedDigits.put(rndSecuence[6], false);
                    definedDigits.put(rndSecuence[7], true);
                    definedDigits.put(rndSecuence[8], false);
                    definedDigits.put(rndSecuence[9], true);
                    definedDigits.put(rndSecuence[0], true);
                }
            }
        }

    }
}
