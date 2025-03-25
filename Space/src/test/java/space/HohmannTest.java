package space;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static consoleUtils.SimplePrinting.printLine;

import dimensions.distance.DistanceUnit;
import dimensions.distance.Distance;
import dimensions.time.TimeUnit;
import space.orbits.HohmannTransferOrbit;
import space.orbitalBodies.abstractOrbitalBody.NullParentException;
import space.orbitalBodies.abstractOrbitalBody.AbstractOrbitalBody;
import space.orbitalBodies.MajorOrbitalBody;
import space.orbitalBodies.StandardSolarSystem;

//
final class HohmannTest {
    private static final @NotNull String INDENT = "  ";
    private static final @NotNull DistanceUnit DEFAULT_DISTANCE_UNIT = DistanceUnit.KM;
    private static final @NotNull TimeUnit DEFAULT_TIME_UNIT = TimeUnit.DAY;
    private final @NotNull HohmannTransferOrbit transferOrbit;
    private final double specificGravity;

    //
    public static void main(String[] args) {
        new HohmannTest(
                StandardSolarSystem.ORBITAL_STATION_LEO, StandardSolarSystem.MOON,
                DEFAULT_DISTANCE_UNIT, TimeUnit.DAY);
        new HohmannTest(StandardSolarSystem.EARTH, StandardSolarSystem.MARS);
        new HohmannTest(StandardSolarSystem.EARTH, StandardSolarSystem.VENUS);
    }

    private HohmannTest(@NotNull AbstractOrbitalBody body1, @NotNull AbstractOrbitalBody body2,
                        @NotNull DistanceUnit distanceUnit, @NotNull TimeUnit timeUnit) {
        printLine("Testing Hohmann maneuver: " + body1.getName() + " - " + body2.getName());
        @Nullable MajorOrbitalBody
                parent1 = body1.getParent(),
                parent2 = body2.getParent();
        if (parent1 != parent2) {
            throw new RuntimeException(new NotSameParentException(parent1, parent2));
        }
        try {
            specificGravity = body1.getParentSpecificGravity();
        } catch (@NotNull NullParentException e) {
            throw new RuntimeException(e);
        }

        @NotNull Distance @NotNull [] apsis = getApsis(body1, body2, distanceUnit);
        transferOrbit = new HohmannTransferOrbit(apsis[0], apsis[1]);
        printResults(distanceUnit, timeUnit);
    }

    private HohmannTest(@NotNull AbstractOrbitalBody body1, @NotNull AbstractOrbitalBody body2) {
        this(body1, body2, DEFAULT_DISTANCE_UNIT, DEFAULT_TIME_UNIT);
    }

    private static @NotNull Distance @NotNull [] getApsis(@NotNull AbstractOrbitalBody body1, @NotNull AbstractOrbitalBody body2, @NotNull DistanceUnit unit) {
        double
                a_1 = body1.getNonNulSemiMajorAxis().get(unit),
                a_2 = body2.getNonNulSemiMajorAxis().get(unit);
        @NotNull Distance @NotNull [] apsis = new Distance[2];
        if (a_1 <= a_2) {
            apsis[0] = new Distance(a_1, unit);
            apsis[1] = new Distance(a_2, unit);
        } else {
            apsis[0] = new Distance(a_2, unit);
            apsis[1] = new Distance(a_1, unit);
        }
        return apsis;
    }

    private void printResults(@NotNull DistanceUnit distanceUnit, @NotNull TimeUnit timeUnit) {
        printLine(INDENT + "Periapsis: " + transferOrbit.getPeriapsis().getValueAndShortUnit(distanceUnit));
        double periapsisDeltaV = transferOrbit.getDeltaVtoCircularAtPeriapsis(specificGravity);
        printLine(INDENT.repeat(2) + "Delta-v: " + periapsisDeltaV + " m/s");
        printLine("");
        printLine(INDENT + "Apoapsis: " + transferOrbit.getApoapsis().getValueAndShortUnit(distanceUnit));
        double apoapsisDeltaV = transferOrbit.getDeltaVtoCircularAtApoapsis(specificGravity);
        printLine(INDENT.repeat(2) + "Delta-v: " + apoapsisDeltaV + " m/s");
        printLine("");
        double totalDeltaV = periapsisDeltaV + apoapsisDeltaV;
        printLine(INDENT + "Total delta-v: " + totalDeltaV + " m/s");
        printLine(INDENT + "Transfer time: " + transferOrbit.getTransferTime(specificGravity).getValueAndShortUnit(timeUnit));
        printLine("");
    }

    private static final class NotSameParentException extends Exception {
        private static final @NotNull String NULL_VALUE_STRING = "Null";

        //
        NotSameParentException(@Nullable MajorOrbitalBody parent1, @Nullable MajorOrbitalBody parent2) {
            super("Not the same parent exception. Parent1 = " + getParentName(parent1) + ", parent2 = " + getParentName(parent2));
        }

        private static @NotNull String getParentName(@Nullable MajorOrbitalBody parent) {
            if (parent == null) {
                return NULL_VALUE_STRING;
            } else {
                return parent.getName();
            }
        }
    }
}