package space.orbitalBodies.particularMajors.earth;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import dimensions.distance.DistanceUnit;
import dimensions.distance.Distance;
import dimensions.mass.MassUnit;
import dimensions.mass.Mass;
import dimensions.time.TimeUnit;
import dimensions.time.Time;
import space.orbits.OrbitInterface;
import space.orbitalBodies.MajorOrbitalBody;
import space.orbitalBodies.Planetoid;

//
public final class Moon extends Planetoid {
    private static final @NotNull String NAME = "Moon";
    private static final @NotNull Mass MASS = new Mass(7.346 * Math.pow(10, 22), MassUnit.KG);
    private static final @NotNull Distance RADIUS = new Distance(1738.1, DistanceUnit.KM); //equatorial
    private static final @NotNull Time ROTATION_PERIOD = new Time(27.321582, TimeUnit.DAY);

    //
    public Moon(@Nullable MajorOrbitalBody parent, @Nullable OrbitInterface orbit) {
        super(NAME, parent, orbit, MASS, RADIUS, ROTATION_PERIOD);
    }
}