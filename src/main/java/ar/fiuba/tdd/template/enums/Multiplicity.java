package ar.fiuba.tdd.template.enums;

import java.util.Random;

/**
 * Created by jorlando on 19/03/16.
 */
public enum Multiplicity {

    ONE("",1),
    ONE_OR_MORE("+",1),
    ZERO_OR_ONE("?",0),
    ZERO_OR_MORE("*",0);

    public static final int MAX_REPETITIONS = 20;

    private String charRepresentation;
    private int minRepetition;


    Multiplicity(String representation, int min) {
        this.charRepresentation = representation;
        this.minRepetition = min;
    }

    public String getCharRepresentation() {
        return this.charRepresentation;
    }

    public static Multiplicity getMultiplicityOfChar(String charToAnalize) {
        for (Multiplicity e : Multiplicity.values()) {
            if (e.getCharRepresentation().equals(charToAnalize)) {
                return e;
            }
        }
        return ONE;
    }

    public int getMultiplicity() {
        Random random = new Random();
        int maxRandom = 1;
        if (this.equals(Multiplicity.ONE_OR_MORE) || this.equals(Multiplicity.ZERO_OR_MORE)) {
            maxRandom = MAX_REPETITIONS;
        }
        return random.nextInt(maxRandom - minRepetition + 1) + minRepetition;
    }

}
