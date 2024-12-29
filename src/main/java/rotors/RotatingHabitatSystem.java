package rotors;

import java.util.List;
import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;

import dimensions.mass.Mass;
import human.ShipCrew;

//
// total angular momentum = 0
public class RotatingHabitatSystem {
    //
    List<RotatingHabitat> habitats;

    //
    public RotatingHabitatSystem(ShipCrew crew, double staffArea, int rotorCount, int floorCount) {
        double
                livingArea = crew.getRequiredArea() + staffArea,
                areaPerHabitat = livingArea / rotorCount;
        habitats = new ArrayList<>();
        double ceilingHeight = 2.5, furniturePerArea = 1000;
        for (int i = 0; i < rotorCount; i++) {
            String habitatName = "Habitat-" + i;
            habitats.add(new RotatingHabitat(habitatName, areaPerHabitat, ceilingHeight, floorCount, furniturePerArea));
        }
    }

    //
    public @NotNull Mass getMass() {
        double sum = 0;
        for (RotatingHabitat habitat : habitats) {
            sum += habitat.getMass().getSI();
        }
        return new Mass(sum);
    }

}