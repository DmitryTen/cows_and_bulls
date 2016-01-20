package game.logic.gamerNumberDefiner.stage2;

import game.logic.gamerNumberDefiner.NumberInfo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Windows on 02.01.2016.
 */
public class DefinerS2Deep1 extends DefinerS2 {

    private int stepNumber;
    private NumberParametersS2 previousParameters;
    private NumberParametersS2 currentParameters;

    public DefinerS2Deep1(Integer[] rndSecuence, ArrayList<NumberInfo> numbersHistory, HashMap<Integer, Boolean> definedIndexes,
                          HashMap<Integer, CowInfo> cowContainer, HashMap<Byte, Integer> definedPositions) {
        super(rndSecuence, numbersHistory, definedIndexes, cowContainer, definedPositions);
    }

    public void setPreviousParameters(NumberParametersS2 previousParameters) {
        this.previousParameters = previousParameters;
    }


    public int assembleNewNumber(){
        currentParameters = new NumberParametersS2(previousParameters);

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

    private void replaceNewIndexByAnotherNewIndex(NumberParametersS2 numberParametersS2){
        byte previousNewIndexPosition = numberParametersS2.getNewIndexPosition();
        numberParametersS2.setPosition(previousNewIndexPosition, -1, null);
        fillWithOneNewIndex(numberParametersS2);
        fillWithOneFalseIndexIfThereWasNoNewIndex(numberParametersS2);
    }

    private void replaceNewIndexByAnyCowOrBullIndex(NumberParametersS2 numberParametersS2){
        int previousNewIndex = numberParametersS2.getNewIndex();
        byte positionToReplace = numberParametersS2.getNewIndexPosition();
        numberParametersS2.setPosition(positionToReplace, -1, null);

        fillWithAnotherCowOrBull(numberParametersS2, previousNewIndex);
        fillWithOneFalseIndexIfThereWasNoNewIndex(numberParametersS2);
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


    private void createNewTrueIndex(NumberParametersS2 numberParametersS2){
        int newIndex = numberParametersS2.getNewIndex();
        definedIndexes.put(newIndex, true);
        cowContainer.put(newIndex, new CowInfo(newIndex, cowContainer, definedPositions));
        for (Byte position : definedPositions.keySet()){
            int index = definedPositions.get(position);
            cowContainer.get(index).setBullPosition(position);
        }
    }
}
