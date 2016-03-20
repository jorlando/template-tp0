package ar.fiuba.tdd.template.tp0;

import ar.fiuba.tdd.template.entities.GenericEntity;
import ar.fiuba.tdd.template.entities.LiteralEntity;
import ar.fiuba.tdd.template.entities.SetEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jorlando on 19/03/16.
 */

public class RegExVector {

    List listEntities;

    public RegExVector() {
        listEntities = new ArrayList<>();
    }

    public void addEntity(LiteralEntity entity) {
        listEntities.add(entity);
    }

    public void addEntity(SetEntity entity) {
        listEntities.add(entity);
    }

    public void addEntity(GenericEntity entity) {
        listEntities.add(entity);
    }
}
