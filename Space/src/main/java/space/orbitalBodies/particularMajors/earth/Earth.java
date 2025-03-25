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
public final class Earth extends Planetoid {
    private static final @NotNull String NAME = "Earth";
    private static final @NotNull Mass MASS = new Mass(1, MassUnit.EARTH_MASS);
    private static final @NotNull Distance RADIUS = new Distance(6378.1366, DistanceUnit.KM); //equatorial
    private static final @NotNull Time ROTATION_PERIOD = new Time(0.99726968, TimeUnit.DAY);

    //
    public Earth(@Nullable MajorOrbitalBody parent, @Nullable OrbitInterface orbit) {
        super(NAME, parent, orbit, MASS, RADIUS, ROTATION_PERIOD);
    }
}