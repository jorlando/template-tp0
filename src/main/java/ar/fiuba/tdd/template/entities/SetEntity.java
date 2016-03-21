package ar.fiuba.tdd.template.entities;

import ar.fiuba.tdd.template.enums.Multiplicity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jorlando on 19/03/16.
 */
public class SetEntity extends Entity {

    List listOfElements = new ArrayList<>();
    int endPosition;

    public SetEntity(int initPosition, int finalPosition) {
        this.position = initPosition;
        this.endPosition = finalPosition;
        this.multiplicity = Multiplicity.ONE;
    }

    public void setElements(String regularExp, String charEscape) {
        int pointerToRegExPosition = this.position + 1;
        while (pointerToRegExPosition < this.endPosition) {
            String element = String.valueOf(regularExp.charAt(pointerToRegExPosition));
            if (element.equals(charEscape)) {
                pointerToRegExPosition++;
                element = String.valueOf(regularExp.charAt(pointerToRegExPosition));
            }
            listOfElements.add(element);
            pointerToRegExPosition++;
        }
    }

    public String replaceUsedPosition(String regularExp, String charUsedPosition) {
        StringBuilder newRegularExp = new StringBuilder(regularExp);
        int pointerToRegExPosition = this.position;
        while (pointerToRegExPosition <= this.endPosition) {
            newRegularExp.setCharAt(pointerToRegExPosition, charUsedPosition.charAt(0));

            pointerToRegExPosition++;
        }
        if (multiplicity != Multiplicity.ONE) {
            newRegularExp.setCharAt(this.endPosition + 1, charUsedPosition.charAt(0));
        }
        return newRegularExp.toString();
    }

    public String generateSolution() {
        StringBuilder strGenerated = new StringBuilder();
        for (int idxGeneration = 0; idxGeneration < this.multiplicity.getMultiplicity(); idxGeneration++) {
            if (this.listOfElements.size() != 0) {
                int characterRandom = this.getRandomInt(this.listOfElements.size());
                strGenerated.append(this.listOfElements.get(characterRandom));
            }
        }
        return strGenerated.toString();
    }
}
