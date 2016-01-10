package logic.stage2;

import logic.Definer;
import logic.NumberFactory;
import logic.NumberWithCowsAmount;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Windows on 26.12.2015.
 */
public class DefinerStage2Main extends DefinerStage2 implements Definer {

    private Analizator analizator;
    private DefinerStage2Deep1 definerStage2;
    private NumberParameters numberParameters;
    private boolean firstStep = true;
    private int stepNumber;

    public DefinerStage2Main(Integer[] rndSecuence, ArrayList<NumberWithCowsAmount> numbersHistory, HashMap<Integer, Boolean> definedIndexes,
                             HashMap<Integer, CowInfo> cowContainer, HashMap<Byte, Integer> definedPositions) {
        super(rndSecuence, numbersHistory, definedIndexes, cowContainer, definedPositions);
    }

    @Override
    public int getNumber() {
        if (firstStep) {
            analizator = new Analizator(definedIndexes, numbersHistory, cowContainer, definedPositions);
            analizator.analizeDataOfPreviousSteps();
            cowContainer.size();
            return assembleNewNumber();
        }
        else {
            return handleCurrentDefiner();
        }
    }

    private int assembleNewNumber(){
        numberParameters = new NumberParameters();

        fillWithBulls(numberParameters);

        fillWithOneCowExceptBulls(numberParameters);

        fillWithOneNewIndex(numberParameters);

        fillAllEmptyPositionsByFalseIndexes(numberParameters);

        firstStep = false;
        stepNumber = numbersHistory.size();

        return assembleByCharacteristics(numberParameters);
    }

    private int handleCurrentDefiner(){

        if (getSumOfCowsAndBulls(stepNumber) == numberParameters.getCowsAndBullsAmountSum()){
            if (numberParameters.isNewIndexExists()) createNewFalseIndex();

            if(numberParameters.isCowExists() && getBulls(stepNumber) == numberParameters.getBullsAmount()){
                int cowIndex = numberParameters.getCowIndex();
                byte cowPosition = numberParameters.getCowPosition();
                cowContainer.get(cowIndex).setCowPositionAndAnalize(cowPosition);
            }

            if(numberParameters.isCowExists() && getBulls(stepNumber) == numberParameters.getBullsAmount() + 1){
                int cowIndex = numberParameters.getCowIndex();
                byte cowPosition = numberParameters.getCowPosition();
                cowContainer.get(cowIndex).setBullPosition(cowPosition);
            }
        }

        if (getSumOfCowsAndBulls(stepNumber) == numberParameters.getCowsAndBullsAmountSum() + 1) {
            createNewTrueIndex();

            if(numberParameters.isCowExists() && getBulls(stepNumber) == numberParameters.getBullsAmount()){
                int cowIndex = numberParameters.getCowIndex();
                byte cowPosition = numberParameters.getCowPosition();
                cowContainer.get(cowIndex).setCowPositionAndAnalize(cowPosition);

                int newIndex = numberParameters.getNewIndex();
                byte newIndexPosition = numberParameters.getNewIndexPosition();
                cowContainer.get(newIndex).setCowPositionAndAnalize(newIndexPosition);
            }

            if(!numberParameters.isCowExists() && getBulls(stepNumber) == numberParameters.getBullsAmount()){
                int newIndex = numberParameters.getNewIndex();
                byte newIndexPosition = numberParameters.getNewIndexPosition();
                cowContainer.get(newIndex).setCowPositionAndAnalize(newIndexPosition);
            }

            if(numberParameters.isCowExists() && getBulls(stepNumber) == numberParameters.getBullsAmount() + 1){
                if(definerStage2==null){
                    definerStage2 = new DefinerStage2Deep1(rndSecuence, numbersHistory, definedIndexes, cowContainer, definedPositions);
                    definerStage2.setPreviousParameters(numberParameters);
                    return definerStage2.assembleNewNumber();
                }
                else {
                    definerStage2.handleCurrentDefiner();
                    definerStage2 = null;
                }
            }

            if(!numberParameters.isCowExists() && getBulls(stepNumber) == numberParameters.getBullsAmount() + 1){
                int newIndex = numberParameters.getNewIndex();
                byte newIndexPosition = numberParameters.getNewIndexPosition();
                cowContainer.get(newIndex).setBullPosition(newIndexPosition);
            }

            if (numberParameters.isCowExists() && getBulls(stepNumber) == numberParameters.getBullsAmount() + 2){
                int cowIndex = numberParameters.getCowIndex();
                byte cowPosition = numberParameters.getCowPosition();
                cowContainer.get(cowIndex).setBullPosition(cowPosition);

                int newIndex = numberParameters.getNewIndex();
                byte newIndexPosition = numberParameters.getNewIndexPosition();
                cowContainer.get(newIndex).setBullPosition(newIndexPosition);
            }
        }

        analizator.analizeStep2();

        if (definedIndexes.size()==10 || cowContainer.size()==4) {
            NumberFactory.getInstance().setStage(3);
            return NumberFactory.getInstance().getNumber();
        }
        else return assembleNewNumber();
    }

    private void createNewTrueIndex(){
        int newIndex = numberParameters.getNewIndex();
        definedIndexes.put(newIndex, true);
        cowContainer.put(newIndex, new CowInfo(newIndex, cowContainer, definedPositions));
        if (numbersHistory.get(0).doesIndexExistsInNumber(newIndex)) tryToFinishTheVeryFirstNumber();
        for (Byte position : definedPositions.keySet()){
            int index = definedPositions.get(position);
            cowContainer.get(index).setBullPosition(position);
        }
    }

    private void createNewFalseIndex(){
        int newIndex = numberParameters.getNewIndex();
        definedIndexes.put(newIndex, false);
    }

    private void tryToFinishTheVeryFirstNumber(){
        int amountOfCoinsidiences = 0;
        NumberWithCowsAmount number0 = numbersHistory.get(0);
        for (Integer trueIndex : cowContainer.keySet()){
            if (number0.doesIndexExistsInNumber(trueIndex)) amountOfCoinsidiences++;
        }
        if (number0.getResultSum() == amountOfCoinsidiences) {
            if (cowContainer.get(number0.getIndexOfPosition1()) == null) definedIndexes.put(number0.getIndexOfPosition1(), false);
            if (cowContainer.get(number0.getIndexOfPosition2()) == null) definedIndexes.put(number0.getIndexOfPosition2(), false);
            if (cowContainer.get(number0.getIndexOfPosition3()) == null) definedIndexes.put(number0.getIndexOfPosition3(), false);
            if (cowContainer.get(number0.getIndexOfPosition4()) == null) definedIndexes.put(number0.getIndexOfPosition4(), false);
        }
    }

}
