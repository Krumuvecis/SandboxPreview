package space.ships;

import dimensions.distance.Distance;
import dimensions.mass.Mass;
import dimensions.time.Time;
import dimensions.time.TimeUnit;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

//
class ShipAccommodations implements MassiveShipPart {
    final @NotNull Mass food, water, luggage;
    final @NotNull RotatingHabitatSystem rotatingHabitats;

    //
    ShipAccommodations(Time tripTime, ShipCrew crew, double staffArea, int rotorCount, int floorCount) {
        food = new Mass(tripTime.get(TimeUnit.DAY) * crew.getDailyFoodConsumption().getSI());
        water = new Mass(tripTime.get(TimeUnit.DAY) * crew.getDailyWaterConsumption().getSI());
        luggage = crew.getTotalLuggage();
        rotatingHabitats = new RotatingHabitatSystem(crew, staffArea, rotorCount, floorCount);
    }

    @Override
    public @NotNull Mass getMass() {
        return new Mass(food.getSI() + water.getSI() + luggage.getSI() + rotatingHabitats.getMass().getSI());
    }

    static class RotatingHabitatSystem implements MassiveShipPart {
        List<RotatingHabitat> habitats;

        //
        RotatingHabitatSystem(ShipCrew crew, double staffArea, int rotorCount, int floorCount) {
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
        @Override
        public @NotNull Mass getMass() {
            double sum = 0;
            for (RotatingHabitat habitat : habitats) {
                sum += habitat.getMass().getSI();
            }
            return new Mass(sum);
        }
    }

    static class RotatingHabitat implements MassiveShipPart {
        private static final double
                DENSITY_STRUCTURAL_LAYER = 3000,
                DENSITY_RADIATION_ISOLATION = 2000;
        private static final Distance
                THICKNESS_STRUCTURAL_LAYER = new Distance(0.5),
                THICKNESS_RADIATION_ISOLATION = new Distance(2);
        private static final int CAPSULES_PER_HABITAT = 2;
        String habitatName;
        double ceilingHeight;
        int floorCount;
        double areaPerCapsule;
        Mass furniture;

        //
        RotatingHabitat(String habitatName, double areaPerHabitat,
                        double ceilingHeight, int floorCount,
                        double furniturePerArea) {
            this.habitatName = habitatName;
            areaPerCapsule = areaPerHabitat / CAPSULES_PER_HABITAT;
            this.ceilingHeight = ceilingHeight;
            this.floorCount = floorCount;
            furniture = new Mass(areaPerHabitat * furniturePerArea);
        }

        //assumes square shape
        double getSurfaceAreaOfSingleCapsule() {
            double
                    a2 = areaPerCapsule / floorCount,
                    a = Math.sqrt(a2);
            return a2 * 2 + a * ceilingHeight * floorCount * 4;
        }

        //
        @Override
        public @NotNull Mass getMass() {
            double totalSurfaceArea = CAPSULES_PER_HABITAT * getSurfaceAreaOfSingleCapsule();
            return new Mass(getStructuralLayerMass(totalSurfaceArea)
                    + getRadiationIsolationMass(totalSurfaceArea)
                    + furniture.getSI());
        }

        private static double getStructuralLayerMass(double totalSurfaceArea) {
            return totalSurfaceArea * THICKNESS_STRUCTURAL_LAYER.getSI() * DENSITY_STRUCTURAL_LAYER;
        }

        private static double getRadiationIsolationMass(double totalSurfaceArea) {
            return totalSurfaceArea * THICKNESS_RADIATION_ISOLATION.getSI() * DENSITY_RADIATION_ISOLATION;
        }
    }
}