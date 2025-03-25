package space;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static consoleUtils.SimplePrinting.printLine;

import dimensions.time.TimeUnit;
import dimensions.time.Time;
import space.orbitalBodies.abstractOrbitalBody.NullParentException;
import space.orbitalBodies.abstractOrbitalBody.NullOrbitException;
import space.orbitalBodies.abstractOrbitalBody.AbstractOrbitalBody;
import space.orbitalBodies.MajorOrbitalBody;
import space.orbitalBodies.StandardSolarSystem;

//
final class OrbitalAlignmentTest {
    private static final @NotNull String
            INDENT = "  ",
            NULL_VALUE_STRING = "Null";

    //Main method of this test, run to start
    @SuppressWarnings("InstantiationOfUtilityClass")
    public static void main(String[] args) {
        new OrbitalAlignmentTest(StandardSolarSystem.EARTH, StandardSolarSystem.MARS, TimeUnit.YEAR);
        new OrbitalAlignmentTest(StandardSolarSystem.EARTH, StandardSolarSystem.VENUS, TimeUnit.YEAR);
        new OrbitalAlignmentTest(StandardSolarSystem.ORBITAL_STATION_LEO, StandardSolarSystem.MOON, TimeUnit.MIN);
    }

    //
    private OrbitalAlignmentTest(@NotNull AbstractOrbitalBody body1, @NotNull AbstractOrbitalBody body2, @NotNull TimeUnit timeUnit) {
        printLine("Testing orbital alignment: " + body1.getName() + " - " + body2.getName());
        printOrbitalPeriod(body1, timeUnit);
        printOrbitalPeriod(body2, timeUnit);
        printAlignmentPeriod(body1, body2, timeUnit);
        printLine("");
    }

    private static void printOrbitalPeriod(@NotNull AbstractOrbitalBody body, @NotNull TimeUnit unit) {
        printLine(INDENT + "Orbital period of " + body.getName() + ": " + getOrbitalPeriodString(body, unit));
    }

    private static @NotNull String getOrbitalPeriodString(@NotNull AbstractOrbitalBody body, @NotNull TimeUnit unit) {
        try {
            @NotNull Time orbitalTime = body.getOrbitalPeriod();
            return orbitalTime.getValueAndShortUnit(unit);
        } catch (@NotNull NullParentException | @NotNull NullOrbitException ignored) {
            return NULL_VALUE_STRING;
        }
    }

    private static void printAlignmentPeriod(@NotNull AbstractOrbitalBody body1, @NotNull AbstractOrbitalBody body2, @NotNull TimeUnit timeUnit) {
        @Nullable MajorOrbitalBody
                parent = body1.getParent(),
                parent2 = body2.getParent();
        if (parent != parent2) {
            throw new RuntimeException(new NotSameParentException(parent, parent2));
        }
        try {
            @NotNull Time alignmentTime = getAlignmentTime(body1, body2);
            printLine(INDENT + body1.getName() + "-" + body2.getName() + " alignment time: " + alignmentTime.getValueAndShortUnit(timeUnit));
        } catch (@NotNull NullParentException | @NotNull NullOrbitException e) {
            throw new RuntimeException(e);
        }
    }

    private static @NotNull Time getAlignmentTime(@NotNull AbstractOrbitalBody body1, @NotNull AbstractOrbitalBody body2) throws NullParentException, NullOrbitException {
        double
                omega1 = body1.getAverageAngularVelocity(),
                omega2 = body2.getAverageAngularVelocity(),
                dOmega = Math.abs(omega1 - omega2);
        return new Time(2 * Math.PI / dOmega, TimeUnit.S);
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