package space.orbitalBodies.particularMajors.mars;

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
public final class Mars extends Planetoid {
    private static final @NotNull String NAME = "Mars";
    private static final @NotNull Mass MASS = new Mass(6.4191 * Math.pow(10, 23), MassUnit.KG);
    private static final @NotNull Distance RADIUS = new Distance(3396.19, DistanceUnit.KM); //equatorial
    private static final @NotNull Time ROTATION_PERIOD = new Time(1.02595675, TimeUnit.DAY);

    //
    public Mars(@Nullable MajorOrbitalBody parent, @Nullable OrbitInterface orbit) {
        super(NAME, parent, orbit, MASS, RADIUS, ROTATION_PERIOD);
    }
}