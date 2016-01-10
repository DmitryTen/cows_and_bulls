package logic.stage2;

import logic.NumberWithCowsAmount;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Windows on 02.01.2016.
 */
public class DefinerStage2Deep1 extends DefinerStage2 {

    private int stepNumber;
    private NumberParameters previousParameters;
    private NumberParameters currentParameters;

    public DefinerStage2Deep1(Integer[] rndSecuence, ArrayList<NumberWithCowsAmount> numbersHistory, HashMap<Integer, Boolean> definedIndexes,
                              HashMap<Integer, CowInfo> cowContainer, HashMap<Byte, Integer> definedPositions) {
        super(rndSecuence, numbersHistory, definedIndexes, cowContainer, definedPositions);
    }

    public void setPreviousParameters(NumberParameters previousParameters) {
        this.previousParameters = previousParameters;
    }


    public int assembleNewNumber(){
        currentParameters = new NumberParameters(previousParameters);

        if (cowContainer.size() != 4){
            if (definedIndexes.size() != 10) replaceNewIndexByAnotherNewIndex(currentParameters);
            else replaceNewIndexByAnyCowOrBullIndex(currentParameters);
        }
        else replaceNewIndexByAnyCowOrBullIndex(currentParameters);

        stepNumber = numbersHistory.size();
        return assembleByCharacteristics(currentParameters);
    }

    public void handleCurrentDefiner(){
                if (getBulls(stepNumber) == currentParameters.getBullsAmount() + 1 && getCows(stepNumber) == 0) {
                    case1();
                }
                if (getBulls(stepNumber) == currentParameters.getBullsAmount() && getCows(stepNumber) == 1){
                    case2();
                }
                if (getBulls(stepNumber) == currentParameters.getBullsAmount() + 1 && getCows(stepNumber) == 1){
                    case3();
                }
                if (getBulls(stepNumber) == currentParameters.getBullsAmount() + 2 && getCows(stepNumber) == 0){
                    case4();
                }
                if (getBulls(stepNumber) == currentParameters.getBullsAmount() && getCows(stepNumber) == 2){
                    case5();
                }
    }

    private void replaceNewIndexByAnotherNewIndex(NumberParameters numberParameters){
        byte previousNewIndexPosition = numberParameters.getNewIndexPosition();
        numberParameters.setPosition(previousNewIndexPosition, -1, null);
        fillWithOneNewIndex(numberParameters);
        fillWithOneFalseIndexIfThereWasNoNewIndex(numberParameters);
    }

    private void replaceNewIndexByAnyCowOrBullIndex(NumberParameters numberParameters){
        int previousNewIndex = numberParameters.getNewIndex();
        byte positionToReplace = numberParameters.getNewIndexPosition();
        numberParameters.setPosition(positionToReplace, -1, null);

        fillWithAnotherCowOrBull(numberParameters, previousNewIndex);
        fillWithOneFalseIndexIfThereWasNoNewIndex(numberParameters);
    }

    private void case1() {
        int currentCowIndex = currentParameters.getCowIndex();
        byte currentCowPosition = currentParameters.getCowPosition();
        cowContainer.get(currentCowIndex).setBullPosition(currentCowPosition);

        int currentNewIndex = currentParameters.getNewIndex();
        if (cowContainer.get(currentNewIndex) != null) {
            cowContainer.get(currentNewIndex).finishThisCow();
        }
        else definedIndexes.put(currentNewIndex, false);

        int previousNewIndex = previousParameters.getNewIndex();
        byte previousNewIndexPosition = currentParameters.getNewIndexPosition();
        cowContainer.get(previousNewIndex).setCowPositionAndAnalize(previousNewIndexPosition);
    }

    private void case2() {
        int currentCowIndex = currentParameters.getCowIndex();
        byte currentCowPosition = currentParameters.getCowPosition();
        cowContainer.get(currentCowIndex).setCowPositionAndAnalize(currentCowPosition);

        int currentNewIndex = currentParameters.getNewIndex();
        if (cowContainer.get(currentNewIndex) != null) {

            if (currentNewIndex == currentParameters.getCowIndex()){
                byte currentNewIndexPosition = currentParameters.getNewIndexPosition();
                cowContainer.get(currentNewIndex).setCowPositionAndAnalize(currentNewIndexPosition);
            }
            else cowContainer.get(currentNewIndex).finishThisCow();

        }
        else definedIndexes.put(currentNewIndex, false);

        int previousNewIndex = previousParameters.getNewIndex();
        byte previousNewIndexPosition = currentParameters.getNewIndexPosition();
        cowContainer.get(previousNewIndex).setBullPosition(previousNewIndexPosition);
    }

    private void case3() {
        int currentCowIndex = currentParameters.getCowIndex();
        byte currentCowPosition = currentParameters.getCowPosition();
        cowContainer.get(currentCowIndex).setBullPosition(currentCowPosition);

        int currentNewIndex = currentParameters.getNewIndex();
        if (cowContainer.get(currentNewIndex) != null) {
            byte currentNewIndexPosition = currentParameters.getNewIndexPosition();
            cowContainer.get(currentNewIndex).setCowPositionAndAnalize(currentNewIndexPosition);
        }
        else {
            createNewTrueIndex(currentParameters);
            byte currentNewIndexPosition = currentParameters.getNewIndexPosition();
            cowContainer.get(currentNewIndex).setCowPositionAndAnalize(currentNewIndexPosition);
        }

        int previousNewIndex = previousParameters.getNewIndex();
        byte previousNewIndexPosition = currentParameters.getNewIndexPosition();
        cowContainer.get(previousNewIndex).setCowPositionAndAnalize(previousNewIndexPosition);
    }

    private void case4(){
        int currentCowIndex = currentParameters.getCowIndex();
        byte currentCowPosition = currentParameters.getCowPosition();
        cowContainer.get(currentCowIndex).setBullPosition(currentCowPosition);

        int currentNewIndex = currentParameters.getNewIndex();
        if (cowContainer.get(currentNewIndex) != null) {
            byte currentNewIndexPosition = currentParameters.getNewIndexPosition();
            cowContainer.get(currentNewIndex).setBullPosition(currentNewIndexPosition);
        }
        else {
            createNewTrueIndex(currentParameters);
            byte currentNewIndexPosition = currentParameters.getNewIndexPosition();
            cowContainer.get(currentNewIndex).setBullPosition(currentNewIndexPosition);
        }

        int previousNewIndex = previousParameters.getNewIndex();
        byte previousNewIndexPosition = currentParameters.getNewIndexPosition();
        cowContainer.get(previousNewIndex).setCowPositionAndAnalize(previousNewIndexPosition);
    }

    private void case5(){
        int currentCowIndex = currentParameters.getCowIndex();
        byte currentCowPosition = currentParameters.getCowPosition();
        cowContainer.get(currentCowIndex).setCowPositionAndAnalize(currentCowPosition);

        int currentNewIndex = currentParameters.getNewIndex();
        if (cowContainer.get(currentNewIndex) != null) {
            byte currentNewIndexPosition = currentParameters.getNewIndexPosition();
            cowContainer.get(currentNewIndex).setCowPositionAndAnalize(currentNewIndexPosition);
        }
        else {
            createNewTrueIndex(currentParameters);
            byte currentNewIndexPosition = currentParameters.getNewIndexPosition();
            cowContainer.get(currentNewIndex).setCowPositionAndAnalize(currentNewIndexPosition);
        }

        int previousNewIndex = previousParameters.getNewIndex();
        byte previousNewIndexPosition = currentParameters.getNewIndexPosition();
        cowContainer.get(previousNewIndex).setBullPosition(previousNewIndexPosition);
    }


    private void createNewTrueIndex(NumberParameters numberParameters){
        int newIndex = numberParameters.getNewIndex();
        definedIndexes.put(newIndex, true);
        cowContainer.put(newIndex, new CowInfo(newIndex, cowContainer, definedPositions));
        for (Byte position : definedPositions.keySet()){
            int index = definedPositions.get(position);
            cowContainer.get(index).setBullPosition(position);
        }
    }
}
