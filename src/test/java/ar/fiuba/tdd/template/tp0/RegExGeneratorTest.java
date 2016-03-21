package ar.fiuba.tdd.template.tp0;

import org.junit.Test;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegExGeneratorTest {

    private boolean validate(String regEx, int numberOfResults) {
        RegExGenerator generator = new RegExGenerator();
        // TODO: Uncomment parameters
        List<String> results = generator.generate(regEx, numberOfResults);
        // force matching the beginning and the end of the strings
        Pattern pattern = Pattern.compile("^" + regEx + "$");
        return results
                .stream()
                .reduce(true,
                    (acc, item) -> {
                        Matcher matcher = pattern.matcher(item);
                        return acc && matcher.find();
                    },
                    (item1, item2) -> item1 && item2);
    }

    //TODO: Uncomment these tests

    @Test
    public void testAnyCharacter() {
        assertTrue(validate(".", 1));
    }

   /* @Test
    public void testAnyCharacterDebug() {
        System.out.println("START");
        RegExGenerator generator = new RegExGenerator();
        String regEx = "a\\?.";
        int numberOfResults = 3;
        List<String> results = generator.generate(regEx, numberOfResults);
        for (String str : results) {
            System.out.println(str);
        }
        assertTrue(validate(".", 1));
    }*/

    @Test
    public void testCharUsedInOriginalRegEx() {
        RegExGenerator generator = new RegExGenerator();
        int numberOfResults = 3;
        List<String> results = generator.generate("_", numberOfResults);
        for (int x = 0; x < numberOfResults; x++) {
            assertEquals(results.get(x), "_");
        }
    }

/*
    @Test
    public void testMultipleCharacters() {
        assertTrue(validate("...", 1));
    }

    @Test
    public void testLiteral() {
        assertTrue(validate("\\@", 1));
    }

    @Test
    public void testLiteralDotCharacter() {
        assertTrue(validate("\\@..", 1));
    }

    @Test
    public void testZeroOrOneCharacter() {
        assertTrue(validate("\\@.h?", 1));
    }

    @Test
    public void testCharacterSet() {
        assertTrue(validate("[abc]", 1));
    }

    @Test
    public void testCharacterSetWithQuantifiers() {
        assertTrue(validate("[abc]+", 1));
    }
    */
    // TODO: Add more tests!!!
}
