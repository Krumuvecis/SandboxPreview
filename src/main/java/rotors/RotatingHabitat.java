package rotors;

import human.rotationalLimits.RotationalLimits;
import human.rotationalLimits.particularRotationalLimits.CivilianRotationalLimits;
import org.jetbrains.annotations.NotNull;

import dimensions.distance.Distance;
import dimensions.mass.Mass;

//
class RotatingHabitat {
    private static final double
            DENSITY_STRUCTURAL_LAYER = 3000,
            DENSITY_RADIATION_ISOLATION = 2000;
    private static final Distance
            THICKNESS_STRUCTURAL_LAYER = new Distance(0.5),
            THICKNESS_RADIATION_ISOLATION = new Distance(2);
    private static final int CAPSULES_PER_HABITAT = 2;

    String habitatName;
    RotorGeometry rotorGeometry;

    //old
    double areaPerCapsule;
    Mass furniture;

    //
    RotatingHabitat(String habitatName, double areaPerHabitat,
                    double ceilingHeight, int floorCount,
                    double furniturePerArea) {
        this.habitatName = habitatName;
        RotationalLimits rotationalLimits = new CivilianRotationalLimits();
        rotorGeometry = new RotorGeometry.RotorGeometryGenerator(rotationalLimits).getGeometry();


        areaPerCapsule = areaPerHabitat / CAPSULES_PER_HABITAT;
        //this.ceilingHeight = ceilingHeight;
        //this.floorCount = floorCount;
        furniture = new Mass(areaPerHabitat * furniturePerArea);
    }

    //assumes square shape
    double getSurfaceAreaOfSingleCapsule() {
        /*double
                a2 = areaPerCapsule / floorCount,
                a = Math.sqrt(a2);
        return a2 * 2 + a * ceilingHeight * floorCount * 4;*/
        return 0;
    }

    //
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