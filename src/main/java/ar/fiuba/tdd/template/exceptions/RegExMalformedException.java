package ar.fiuba.tdd.template.exceptions;

/**
 * Created by jorlando on 20/03/16.
 */
public class RegExMalformedException extends RuntimeException {

    public RegExMalformedException() {
        super();
    }

    public RegExMalformedException(String msg) {
        super(msg);
    }
}
