package ar.fiuba.tdd.template.tp0;

/**
 * Created by jorlando on 19/03/16.
 */
import ar.fiuba.tdd.template.entities.GenericEntity;
import ar.fiuba.tdd.template.entities.LiteralEntity;
import ar.fiuba.tdd.template.entities.SetEntity;
import ar.fiuba.tdd.template.enums.Multiplicity;
import ar.fiuba.tdd.template.exceptions.RegExMalformedException;

import java.lang.String;
import java.util.Arrays;
import java.util.List;

public class RegExParser {

    public static final String CHAR_USED_POSITION = "_";
    public static final String CHAR_INIT_SET = "[";
    public static final String CHAR_END_SET = "]";
    public static final String CHAR_ESCAPE = "\\";
    public static final String CHAR_GENERIC = ".";
    public static final int NEXT_POSITION = 1;

    String originalRegularExpr = "";
    RegExVector regExVector;

    public RegExParser(String regEx) {
        this.originalRegularExpr = regEx;
        this.regExVector = new RegExVector();
    }

    public void checkValidSet(int initPosition) {
        if (this.getEndPositionSet(initPosition) < 0) {
            throw new RegExMalformedException();
        }
    }

    public int getEndPositionSet(int initPosition) {
        int startPositionForSearch = initPosition;
        while (true) {
            int endPosition = originalRegularExpr.indexOf(this.CHAR_END_SET, startPositionForSearch + this.NEXT_POSITION);
            if (endPosition < 0) {
                return endPosition;
            }
            if (this.isCharacterEscaped(endPosition)) {
                startPositionForSearch = endPosition;
            } else {
                return endPosition;
            }
        }
    }

    public void addSet(int initPosition) {
        this.checkValidSet(initPosition);
        int endPositionSet = this.getEndPositionSet(initPosition);
        SetEntity newSet = new SetEntity(initPosition, endPositionSet);
        newSet.setElements(this.originalRegularExpr, this.CHAR_ESCAPE);
        newSet.setMultiplicity(this.getMultiplicity(endPositionSet));
        this.regExVector.addEntity(newSet);
        this.originalRegularExpr = newSet.replaceUsedPosition(this.originalRegularExpr, this.CHAR_USED_POSITION);
    }

    public void addLiteral(String literalToAdd, int position) {
        LiteralEntity newLiteral = new LiteralEntity(literalToAdd, position);
        newLiteral.setMultiplicity(this.getMultiplicity(position));
        this.regExVector.addEntity(newLiteral);
        this.originalRegularExpr = newLiteral.replaceUsedPosition(this.originalRegularExpr, this.CHAR_USED_POSITION);
        if (isCharacterEscaped(position)) {
            this.originalRegularExpr = this.replaceCharacterEscaped(position);
        } else if (!this.isValidLiteralWithoutEscape(literalToAdd)) {
            throw new RegExMalformedException();
        }
    }

    public boolean isValidLiteralWithoutEscape(String literalToAnalize) {
        List<String> invalidLiteralList = Arrays.asList("*", "?", "+", CHAR_INIT_SET, CHAR_END_SET);
        for (String invalidLiteral : invalidLiteralList) {
            if (invalidLiteral.equals(literalToAnalize)) {
                return false;
            }
        }
        return true;

    }

    public void findCharUsedPosition() {
        int indexCharUsed = originalRegularExpr.indexOf(this.CHAR_USED_POSITION);
        while (indexCharUsed >= 0) {
            this.addLiteral(this.CHAR_USED_POSITION,indexCharUsed);
            indexCharUsed = originalRegularExpr.indexOf(this.CHAR_USED_POSITION, indexCharUsed + NEXT_POSITION);
        }
    }

    public void findLiterals() {
        boolean escapedFlag = false;
        for (int positionIndex = 0; positionIndex < this.originalRegularExpr.length(); positionIndex++) {
            String charToAnalize = this.getStringInPosition(this.originalRegularExpr,positionIndex);
            if ( !charToAnalize.equals(CHAR_USED_POSITION)) {
                if (charToAnalize.equals(CHAR_ESCAPE) && !escapedFlag) {
                    escapedFlag = true;
                } else {
                    this.addLiteral(charToAnalize, positionIndex);
                }
            }
        }
    }

    public void findGenerics() {
        this.find(this.CHAR_GENERIC);
    }

    public void findSets() {
        this.find(this.CHAR_INIT_SET);
    }

    public void find(String character) {
        int indexInit = this.originalRegularExpr.indexOf(character);
        while (indexInit >= 0) {
            if (!this.isCharacterEscaped(indexInit)) {
                if (character.equals(this.CHAR_INIT_SET)) {
                    this.addSet(indexInit);
                } else {
                    this.addGeneric(indexInit);
                }
            } else {
                this.addLiteral(character, indexInit);
            }
            indexInit = originalRegularExpr.indexOf(character);
        }
    }

    public void addGeneric(int position) {
        GenericEntity newGeneric = new GenericEntity(position);
        newGeneric.setMultiplicity(this.getMultiplicity(position));
        this.regExVector.addEntity(newGeneric);
        this.originalRegularExpr = newGeneric.replaceUsedPosition(this.originalRegularExpr, this.CHAR_USED_POSITION);
    }

    public RegExVector parse() {
        this.findCharUsedPosition();
        this.findSets();
        this.findGenerics();
        this.findLiterals();
        return regExVector;
    }

    public Multiplicity getMultiplicity(int indexOfCharacter) {
        if (indexOfCharacter == this.originalRegularExpr.length() - 1) {
            return Multiplicity.ONE;
        }
        String charMultiplicity = this.getStringInPosition(this.originalRegularExpr, indexOfCharacter + this.NEXT_POSITION);
        return Multiplicity.getMultiplicityOfChar(charMultiplicity);
    }

    public String getStringInPosition(String primaryString, int position) {
        return String.valueOf(primaryString.charAt(position));
    }

    public boolean isCharacterEscaped(int position) {
        return position != 0 && this.getStringInPosition(this.originalRegularExpr,position - 1).equals(this.CHAR_ESCAPE);
    }

    public String replaceCharacterEscaped(int position) {
        StringBuilder newRegularExp = new StringBuilder(this.originalRegularExpr);
        newRegularExp.setCharAt(position - 1, this.CHAR_USED_POSITION.charAt(0));
        return newRegularExp.toString();
    }
}