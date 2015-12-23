package testpack.definers;

/**
 * Created by Windows on 20.12.2015.
 */
public class Definer7890 extends Definer {
    private Definer definer;
    private int num;

    public Definer7890(Integer[] rndSecuence) {
        super(rndSecuence);
        num = assembleByIndexes(7,8,9,0);
    }

    @Override
    public int getNumber() {
        if (definer != null) return definer.getNumber();
        if (isCowsAmountReceived(stepNumber)) putDefinedDigits();
        definer = new Definer6790(rndSecuence);
        definer.setStepNumber(4);
        return num;
    }

    private void putDefinedDigits(){

        if (getRes(stepNumber) == 2) {
            definedDigits.put(rndSecuence[5], false);
            definedDigits.put(rndSecuence[6], false);
        }
        if (getRes(stepNumber) == 1) {
            definedDigits.put(rndSecuence[7], false);
            definedDigits.put(rndSecuence[8], false);
        }
        if (getRes(stepNumber) == 3) {
            definedDigits.put(rndSecuence[5], false);
            definedDigits.put(rndSecuence[6], false);
            definedDigits.put(rndSecuence[9], true);
            definedDigits.put(rndSecuence[0], true);
        }
    }



}
