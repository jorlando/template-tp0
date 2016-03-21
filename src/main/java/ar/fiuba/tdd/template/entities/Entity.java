package ar.fiuba.tdd.template.entities;

import ar.fiuba.tdd.template.enums.Multiplicity;

import java.util.Random;

/**
 * Created by jorlando on 20/03/16.
 */
public class Entity{

    Multiplicity multiplicity;
    int position;

    public void setMultiplicity(Multiplicity newMultiplicity) {
        this.multiplicity = newMultiplicity;
    }

    public String replaceUsedPosition(String regularExp, String charUsedPosition) {
        StringBuilder newRegularExp = new StringBuilder(regularExp);
        newRegularExp.setCharAt(this.position, charUsedPosition.charAt(0));
        if (multiplicity != Multiplicity.ONE) {
            newRegularExp.setCharAt(this.position + 1, charUsedPosition.charAt(0));
        }
        return newRegularExp.toString();
    }

    public int getPosition() {
        return this.position;
    }

    public String generateSolution() {
        return "";
    }

    public int getRandomInt(int maxNumber) {
        Random random = new Random();
        return random.nextInt(maxNumber);
    }
}
