package space.orbitalBodies;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import dimensions.distance.Distance;
import dimensions.mass.Mass;
import space.orbitalBodies.abstractOrbitalBody.NullOrbitException;
import space.orbits.OrbitInterface;
import space.orbitalBodies.abstractOrbitalBody.AbstractOrbitalBody;
import space.orbitalBodies.abstractOrbitalBody.NullParentException;
import space.orbitalBodies.satelliteContainer.SatelliteContainer;

//
public class MajorOrbitalBody extends AbstractOrbitalBody {
    private static final double GRAVITATIONAL_CONSTANT = 6.67430 * Math.pow(10, -11);
    public final @NotNull SatelliteContainer satellites; //access satellites through this container
    private final @NotNull Mass mass;
    private final double specificGravity;

    //
    public MajorOrbitalBody(@NotNull String name, @Nullable MajorOrbitalBody parent, @Nullable OrbitInterface orbit,
                            @NotNull Mass mass) {
        super(name, parent, orbit);
        satellites = new SatelliteContainer();
        this.mass = mass;
        specificGravity = calculateSpecificGravity(mass);
    }

    //
    public @NotNull Mass getMass() {
        return mass;
    }

    private static double calculateSpecificGravity(@NotNull Mass mass) {
        return GRAVITATIONAL_CONSTANT * mass.getSI();
    }

    //
    public final double getSpecificGravity() {
        return specificGravity;
    }

    //
    public Distance getHillRadiusAtPeriapsis() throws NullParentException, NullOrbitException {
        return getHillRadius(getNonNullOrbit().getPeriapsis());
    }

    //
    public Distance getHillRadiusAtApoapsis() throws NullParentException, NullOrbitException {
        return getHillRadius(getNonNullOrbit().getApoapsis());
    }

    //
    public Distance getHillRadiusAtSemiMajorAis() throws NullParentException, NullOrbitException {
        return getHillRadius(getNonNullOrbit().getSemiMajorAxis());
    }

    private Distance getHillRadius(Distance radius) throws NullParentException {
        //From wiki:
        //  Rh = x * (m2 / (3 * (m1 + m2)))^(1/3)
        //  x - instantaneous distance to parent
        //  m1 - parent
        //  m2 - child

        //After some conversions:
        //  M - parent's mass
        //  m - child's mass
        //  child's mass fraction - w = m / (M + m)
        //  R - instantaneous distance
        //  Rh = R * (w / 3)^(1/3)

        double childMassFraction = getChildMassFraction();
        return new Distance(radius.getSI() * getCubeRoot(childMassFraction / 3));
    }

    private double getChildMassFraction() throws NullParentException {
        double childMass = mass.getSI();
        return childMass / (childMass + getParentMass().getSI());
    }

    private static double getCubeRoot(double number) {
        return Math.pow(number, 1.0 / 3);
    }
}