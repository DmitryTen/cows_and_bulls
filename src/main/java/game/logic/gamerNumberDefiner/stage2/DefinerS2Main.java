package game.logic.gamerNumberDefiner.stage2;

import game.logic.gamerNumberDefiner.Definer;
import game.logic.gamerNumberDefiner.NumberDefiner;
import game.logic.gamerNumberDefiner.NumberInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by Windows on 26.12.2015.
 */
public class DefinerS2Main extends DefinerS2 implements Definer {

    private Analizator analizator;
    private DefinerS2Deep1 definerStage2;
    private NumberParametersS2 numberParametersS2;
    private boolean firstStep = true;
    private int stepNumber;

    private NumberDefiner numberDefiner;


    public DefinerS2Main(Integer[] rndSecuence, ArrayList<NumberInfo> numbersHistory, HashMap<Integer, Boolean> definedIndexes,
                         HashMap<Integer, CowInfo> cowContainer, HashMap<Byte, Integer> definedPositions, NumberDefiner numberDefiner) {
        super(rndSecuence, numbersHistory, definedIndexes, cowContainer, definedPositions);
        this.numberDefiner = numberDefiner;
    }

    @Override
    public int getNumber() {
        if (firstStep) {
            return analizeFirstStep();
        }
        else {
            return handleCurrentDefiner();
        }
    }

    private int analizeFirstStep(){
        firstStep = false;
        analizator = new Analizator(definedIndexes, numbersHistory, cowContainer, definedPositions);
        analizator.analizeDataOfStage1();
        return assembleNewNumber();
    }

    private int assembleNewNumber(){
        numberParametersS2 = new NumberParametersS2();

        //Вставляем быков - индексы, позиции которых уже точно определены
        fillWithBulls(numberParametersS2);

        //Вставляем корову, одну. Корова - индекс, который присутствует в числе, но его позиция пока не определена.
        fillWithOneCow(numberParametersS2);

        //Вставляем новый индекс, один. Новый индекс -индекс, о котором не изестно является ли он коровой или нет.
        fillWithOneNewIndex(numberParametersS2);

        //Оставшиеся пустые места наполняем пустыми индексами. Пустой индекс - индекс, о котором точно известно, что его не присутствует в числе.
        fillAllEmptyPositionsByFalseIndexes(numberParametersS2);

        stepNumber = numbersHistory.size();

        return assembleByCharacteristics(numberParametersS2);
    }

    private int handleCurrentDefiner(){

        if (getSumOfCowsAndBulls(stepNumber) == numberParametersS2.getCowsAndBullsAmountSum()){
            if (numberParametersS2.isNewIndexExists()) createNewFalseIndex();

            if(numberParametersS2.isCowExists() && getBulls(stepNumber) == numberParametersS2.getBullsAmount()){
                int cowIndex = numberParametersS2.getCowIndex();
                byte cowPosition = numberParametersS2.getCowPosition();
                cowContainer.get(cowIndex).setCowPositionAndAnalize(cowPosition);
            }

            if(numberParametersS2.isCowExists() && getBulls(stepNumber) == numberParametersS2.getBullsAmount() + 1){
                int cowIndex = numberParametersS2.getCowIndex();
                byte cowPosition = numberParametersS2.getCowPosition();
                cowContainer.get(cowIndex).setBullPosition(cowPosition);
            }
        }

        if (getSumOfCowsAndBulls(stepNumber) == numberParametersS2.getCowsAndBullsAmountSum() + 1) {
            createNewTrueIndex();

            if(numberParametersS2.isCowExists() && getBulls(stepNumber) == numberParametersS2.getBullsAmount()){
                int cowIndex = numberParametersS2.getCowIndex();
                byte cowPosition = numberParametersS2.getCowPosition();
                cowContainer.get(cowIndex).setCowPositionAndAnalize(cowPosition);

                int newIndex = numberParametersS2.getNewIndex();
                byte newIndexPosition = numberParametersS2.getNewIndexPosition();
                cowContainer.get(newIndex).setCowPositionAndAnalize(newIndexPosition);
            }

            if(!numberParametersS2.isCowExists() && getBulls(stepNumber) == numberParametersS2.getBullsAmount()){
                int newIndex = numberParametersS2.getNewIndex();
                byte newIndexPosition = numberParametersS2.getNewIndexPosition();
                cowContainer.get(newIndex).setCowPositionAndAnalize(newIndexPosition);
            }

            if(numberParametersS2.isCowExists() && getBulls(stepNumber) == numberParametersS2.getBullsAmount() + 1){
                if(definerStage2==null){
                    definerStage2 = new DefinerS2Deep1(rndSecuence, numbersHistory, definedIndexes, cowContainer, definedPositions);
                    definerStage2.setPreviousParameters(numberParametersS2);
                    return definerStage2.assembleNewNumber();
                }
                else {
                    definerStage2.handleCurrentDefiner();
                    definerStage2 = null;
                }
            }

            if(!numberParametersS2.isCowExists() && getBulls(stepNumber) == numberParametersS2.getBullsAmount() + 1){
                int newIndex = numberParametersS2.getNewIndex();
                byte newIndexPosition = numberParametersS2.getNewIndexPosition();
                cowContainer.get(newIndex).setBullPosition(newIndexPosition);
            }

            if (numberParametersS2.isCowExists() && getBulls(stepNumber) == numberParametersS2.getBullsAmount() + 2){
                int cowIndex = numberParametersS2.getCowIndex();
                byte cowPosition = numberParametersS2.getCowPosition();
                cowContainer.get(cowIndex).setBullPosition(cowPosition);

                int newIndex = numberParametersS2.getNewIndex();
                byte newIndexPosition = numberParametersS2.getNewIndexPosition();
                cowContainer.get(newIndex).setBullPosition(newIndexPosition);
            }
        }

        analizator.analizeStep2();

        if (definedIndexes.size()==10) {
            thirdStageStarted = true;
            checkIfThereIsTheOnlyCandidateToBeABull();
        }
        if (definedPositions.size()==4) {
            numberDefiner.setStage(3);
            return numberDefiner.getNumber();
        }
        else return assembleNewNumber();
    }

    private void createNewTrueIndex(){
        int newIndex = numberParametersS2.getNewIndex();
        if (cowContainer.get(newIndex) == null){
            definedIndexes.put(newIndex, true);
            cowContainer.put(newIndex, new CowInfo(newIndex, cowContainer, definedPositions));
            if (numbersHistory.get(0).doesIndexExistsInNumber(newIndex)) tryToFinishTheVeryFirstNumber();
            for (Byte position : definedPositions.keySet()){
                int index = definedPositions.get(position);
                cowContainer.get(index).setBullPosition(position);
            }
        }
    }

    private void createNewFalseIndex(){
        int newIndex = numberParametersS2.getNewIndex();
        if (cowContainer.get(newIndex) == null) definedIndexes.put(newIndex, false);
        else {
            cowContainer.get(newIndex).finishThisCow();
        }
    }

    private void tryToFinishTheVeryFirstNumber(){
        int amountOfCoinsidiences = 0;
        NumberInfo number0 = numbersHistory.get(0);
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

    private void checkIfThereIsTheOnlyCandidateToBeABull(){
        Set<Byte> positions = getNotDefinedPositions();

        for (Byte position : positions) {
            ArrayList<Integer> allIndexes = new ArrayList<>(cowContainer.keySet());
            for (Integer index : cowContainer.keySet()){
                if (cowContainer.get(index).isCowPositionOccupied(position)) allIndexes.remove(index);
            }
            if (allIndexes.size()==1) cowContainer.get(allIndexes.get(0)).setBullPosition(position);
        }
    }

}
