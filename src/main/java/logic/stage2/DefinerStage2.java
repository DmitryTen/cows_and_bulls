package logic.stage2;

import logic.NumberWithCowsAmount;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * Created by Windows on 26.12.2015.
 */
public abstract class DefinerStage2 {

    protected static final Logger log = Logger.getLogger(DefinerStage2.class);
    protected HashMap<Integer, Boolean> definedIndexes;
    protected Integer[] rndSecuence;
    protected ArrayList<NumberWithCowsAmount> numbersHistory;
    protected HashMap<Integer, CowInfo> cowContainer;
    protected HashMap<Byte, Integer> definedPositions;
    public enum IndexType {
        BULL, COW, NEW_INDEX, FALSE_INDEX
    }

    public DefinerStage2(Integer[] rndSecuence, ArrayList<NumberWithCowsAmount> numbersHistory, HashMap<Integer, Boolean> definedIndexes,
                         HashMap<Integer, CowInfo> cowContainer, HashMap<Byte, Integer> definedPositions) {
        this.rndSecuence = rndSecuence;
        this.numbersHistory = numbersHistory;
        this.definedIndexes = definedIndexes;
        this.cowContainer = cowContainer;
        this.definedPositions = definedPositions;
    }

    protected int getSumOfCowsAndBulls(int stepNumber){
        return numbersHistory.get(stepNumber).getResultSum();
    }

    protected int getCows(int stepNumber){
        return numbersHistory.get(stepNumber).getCowsAmount();
    }

    protected int getBulls(int stepNumber){
        return numbersHistory.get(stepNumber).getBullsAmount();
    }

    private int getAnyFalseIndex(){
        for (Integer index : definedIndexes.keySet() ){
            if (definedIndexes.get(index)==false) {
                return index;
            }
        }
        return -1;
    }

    protected void fillAllEmptyPositionsByFalseIndexes(NumberParameters numberParameters){
        int falseIndex = getAnyFalseIndex();

        if (numberParameters.getType((byte)0) == null)
            numberParameters.setPosition((byte)0, falseIndex, IndexType.FALSE_INDEX);
        if (numberParameters.getType((byte)1) == null)
            numberParameters.setPosition((byte)1, falseIndex, IndexType.FALSE_INDEX);
        if (numberParameters.getType((byte)2) == null)
            numberParameters.setPosition((byte)2, falseIndex, IndexType.FALSE_INDEX);
        if (numberParameters.getType((byte)3) == null)
            numberParameters.setPosition((byte)3, falseIndex, IndexType.FALSE_INDEX);
    }

    protected void fillWithOneFalseIndexIfThereWasNoNewIndex(NumberParameters numberParameters){
        int falseIndex = getAnyFalseIndex();
        fillByIndexWithSpecifiedType(numberParameters, falseIndex, IndexType.NEW_INDEX);
    }

    protected void fillWithOneNewIndex(NumberParameters numberParameters){
        int newIndex = defineNewOneIndex();
        fillByIndexWithSpecifiedType(numberParameters, newIndex, IndexType.NEW_INDEX);
    }


    protected void fillByIndexWithSpecifiedType (NumberParameters numberParameters, int index, IndexType type){
        if (index == -10) return;

        if (numberParameters.getType((byte)0) == null) {
            numberParameters.setPosition((byte)0, index, type);
            return;
        }
        if (numberParameters.getType((byte)1) == null) {
            numberParameters.setPosition((byte)1, index, type);
            return;
        }
        if (numberParameters.getType((byte)2) == null) {
            numberParameters.setPosition((byte)2, index, type);
            return;
        }
        if (numberParameters.getType((byte)3) == null) {
            numberParameters.setPosition((byte)3, index, type);
            return;
        }
    }

    protected void fillWithBulls(NumberParameters numberParameters){
        for (Byte position : definedPositions.keySet()){
            int index = definedPositions.get(position);
            numberParameters.setPosition(position, index, IndexType.BULL);
        }
    }

    protected void fillWithOneCowExceptBulls(NumberParameters numberParameters){
        Set<Integer> cows = getCowsExceptBulls();
        putCowPosition(numberParameters, cows, IndexType.COW);
    }

    protected void fillWithAnotherCowOrBull(NumberParameters numberParameters, int exceptIndex){
        Set<Integer> cowsAndBulls = new HashSet<>(cowContainer.keySet());
        cowsAndBulls.remove(exceptIndex);
        putCowPosition(numberParameters, cowsAndBulls, IndexType.NEW_INDEX);
    }

     private void putCowPosition(NumberParameters numberParameters, Set<Integer> cows, IndexType type){
        if (numberParameters.getType((byte)0) == null) {
            int cowIndex = getCowIndexWithSpecifiedPosition((byte)0, cows);
            if (cowIndex != -10){
                numberParameters.setPosition((byte)0, cowIndex, type);
                return;
            }
        }
        if (numberParameters.getType((byte)1) == null) {
            int cowIndex = getCowIndexWithSpecifiedPosition((byte)1, cows);
            if (cowIndex != -10){
                numberParameters.setPosition((byte)1, cowIndex, type);
                return;
            }
        }
        if (numberParameters.getType((byte)2) == null) {
            int cowIndex = getCowIndexWithSpecifiedPosition((byte)2, cows);
            if (cowIndex != -10){
                numberParameters.setPosition((byte)2, cowIndex, type);
                return;
            }
        }
        if (numberParameters.getType((byte)3) == null) {
            int cowIndex = getCowIndexWithSpecifiedPosition((byte)3, cows);
            if (cowIndex != -10){
                numberParameters.setPosition((byte)3, cowIndex, type);
                return;
            }
        }
    }

    protected int getCowIndexWithSpecifiedPosition(byte cowPosition, Set<Integer> cows){
        for (Integer cowIndex : cows){
            if (!cowContainer.get(cowIndex).isCowPositionOccupied(cowPosition))
                return cowIndex;
        }
        return -10;
    }

    private int defineNewOneIndex(){
        HashSet<Integer> secuenceOfDigits = new HashSet<>();
        for (int i=0; i<10; i++) secuenceOfDigits.add(i);
        secuenceOfDigits.removeAll(definedIndexes.keySet());

        Iterator<Integer> i = secuenceOfDigits.iterator();
        if (i.hasNext()) return i.next();
        else return -10;
    }

    private Set<Integer> getCowsExceptBulls(){
        Set<Integer> cowSet = new HashSet<>(cowContainer.keySet());
        for (Byte position : definedPositions.keySet()){
            cowSet.remove(definedPositions.get(position));
        }
      //  if (cowSet.size()==0) cowSet = cowContainer.keySet();
        return cowSet;
    }

    protected final int assembleByCharacteristics(NumberParameters numberParameters){
        int num = rndSecuence[numberParameters.getIndex((byte)0)]*1000 + rndSecuence[numberParameters.getIndex((byte)1)]*100
                + rndSecuence[numberParameters.getIndex((byte)2)]*10 + rndSecuence[numberParameters.getIndex((byte)3)];
        numbersHistory.add(new NumberWithCowsAmount(num, numberParameters.getIndex((byte)0), numberParameters.getIndex((byte)1),
                numberParameters.getIndex((byte)2), numberParameters.getIndex((byte)0), numbersHistory.size()));
        log.debug("Received indexes: " + numberParameters.getIndex((byte)0) + ", " + numberParameters.getIndex((byte)1) + ", "
                + numberParameters.getIndex((byte)2) + ", " + numberParameters.getIndex((byte)3) + ", Created number:" + num);
        return num;
    }


}
