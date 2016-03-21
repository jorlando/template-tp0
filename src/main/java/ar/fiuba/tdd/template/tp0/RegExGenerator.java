package ar.fiuba.tdd.template.tp0;

import java.util.ArrayList;
import java.util.List;

public class RegExGenerator {
    // TODO: Uncomment this field
    //private int maxLength;

    //public RegExGenerator(int maxLength) {
    //    this.maxLength = maxLength;
    //}

    public List<String> generate(String regEx, int numberOfResults) {
        RegExParser newRegExParser = new RegExParser(regEx);
        RegExVector newRegexVector = newRegExParser.parse();
        return newRegexVector.generateSolutions(numberOfResults);
    }
}