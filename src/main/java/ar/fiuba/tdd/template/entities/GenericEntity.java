package ar.fiuba.tdd.template.entities;

/**
 * Created by jorlando on 19/03/16.
 */
public class GenericEntity extends Entity{

    public static final int MAX_ASCII_CODE = 256;

    public GenericEntity(int newPosition) {
        this.position = newPosition;
    }

    public String generateSolution() {
        StringBuilder strGenerated = new StringBuilder();
        for (int idxGeneration = 0; idxGeneration < this.multiplicity.getMultiplicity(); idxGeneration++) {
            int characterRandom = this.getRandomInt(MAX_ASCII_CODE);
            strGenerated.append(Character.toString((char) characterRandom));
        }
        return strGenerated.toString();
    }
}
