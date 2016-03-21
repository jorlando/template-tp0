package ar.fiuba.tdd.template.tp0;

import java.util.List;

public class RegExGenerator {

    public RegExGenerator() {
    }

    public List<String> generate(String regEx, int numberOfResults) {
        RegExParser newRegExParser = new RegExParser(regEx);
        RegExVector newRegexVector = newRegExParser.parse();
        return newRegexVector.generateSolutions(numberOfResults);
    }
}