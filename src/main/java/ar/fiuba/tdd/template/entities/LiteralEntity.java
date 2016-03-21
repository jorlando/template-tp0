package ar.fiuba.tdd.template.entities;

import ar.fiuba.tdd.template.enums.Multiplicity;

/**
 * Created by jorlando on 19/03/16.
 */
public class LiteralEntity extends Entity{

    String literal;

    public LiteralEntity(String newLiteral, int newPosition) {
        this.literal = newLiteral;
        this.position = newPosition;
    }

    public String getLiteral() {
        return this.literal;
    }

    public String generateSolution() {
        StringBuilder strGenerated = new StringBuilder();
        for (int idxGeneration = 0; idxGeneration < this.multiplicity.getMultiplicity(); idxGeneration++) {
            strGenerated.append(this.literal);
        }
        return strGenerated.toString();
    }
}
