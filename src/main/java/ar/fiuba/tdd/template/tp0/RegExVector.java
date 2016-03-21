package ar.fiuba.tdd.template.tp0;

import ar.fiuba.tdd.template.entities.Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by jorlando on 19/03/16.
 */

public class RegExVector{

    List<Entity> listEntities;

    public RegExVector() {
        listEntities = new ArrayList<>();
    }

    public void addEntity(Entity entity) {
        listEntities.add(entity);
    }

    public List<String> generateSolutions(int numberOfSolutions) {
        List solutions = new ArrayList<>();
        this.sortEntities();
        for (int idxGenerateSolutions = 0; idxGenerateSolutions < numberOfSolutions; idxGenerateSolutions++) {
            solutions.add(this.generateStringSolution());
        }
        return solutions;
    }

    public String generateStringSolution() {
        StringBuffer solution = new StringBuffer();
        for (Entity entity : this.listEntities) {
            solution.append(entity.generateSolution());
        }
        return solution.toString();
    }

    private void sortEntities() {
        Collections.sort(listEntities, new Comparator<Entity>() {
            public int compare(Entity entityA, Entity entityB) {
                return entityA.getPosition() - entityB.getPosition();
            }
        });
    }
}
