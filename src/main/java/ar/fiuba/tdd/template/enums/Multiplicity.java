package ar.fiuba.tdd.template.enums;

/**
 * Created by jorlando on 19/03/16.
 */
public enum Multiplicity {
    ONE(""),
    ONE_OR_MORE("+"),
    ZERO_OR_ONE("?"),
    ZERO_OR_MORE("*");

    private String charRepresentation;

    Multiplicity(String representation) {
        this.charRepresentation = representation;
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

}
