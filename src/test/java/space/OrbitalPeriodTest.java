package space;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static consoleUtils.SimplePrinting.printLine;

import dimensions.time.TimeUnit;
import dimensions.time.Time;
import space.orbitalBodies.OrbitalBody;
import space.orbitalBodies.MajorOrbitalBody;
import space.orbitalBodies.StandardSolarSystem;

//
final class OrbitalPeriodTest {
    private static final String INDENT = "  ";
    //
    @SuppressWarnings("InstantiationOfUtilityClass")
    public static void main(String[] args) {
        printLine("Testing orbital periods:");
        new OrbitalPeriodTest(StandardSolarSystem.EARTH, TimeUnit.YEAR);
        new OrbitalPeriodTest(StandardSolarSystem.MOON, TimeUnit.DAY);
        new OrbitalPeriodTest(StandardSolarSystem.MARS, TimeUnit.YEAR);
        new OrbitalPeriodTest(StandardSolarSystem.ORBITAL_STATION_LEO, TimeUnit.MIN);
        new OrbitalPeriodTest(StandardSolarSystem.ORBITAL_STATION_MOON, TimeUnit.MIN);
        new OrbitalPeriodTest(StandardSolarSystem.ORBITAL_STATION_MARS, TimeUnit.MIN);
        new OrbitalPeriodTest(StandardSolarSystem.ORBITAL_STATION_PHOBOS, TimeUnit.H);
        printLine("");
        printLine("Testing orbital alignment:");
        new OrbitalAlignmentTest(StandardSolarSystem.EARTH, StandardSolarSystem.MARS, TimeUnit.YEAR);
        new OrbitalAlignmentTest(StandardSolarSystem.ORBITAL_STATION_LEO, StandardSolarSystem.MOON, TimeUnit.MIN);
    }

    private OrbitalPeriodTest(@NotNull OrbitalBody body, TimeUnit unit) {
        printLine(INDENT + body.getName() + ": " + body.getOrbitalPeriod().getValueAndShortUnit(unit));
    }

    private static final class OrbitalAlignmentTest {
        //
        OrbitalAlignmentTest(@NotNull OrbitalBody body1, @NotNull OrbitalBody body2, @NotNull TimeUnit timeUnit) {
            @Nullable MajorOrbitalBody
                    parent = body1.getParent(),
                    parent2 = body2.getParent();
            if (parent != parent2) {
                throw new RuntimeException(new NotSameParentException(parent, parent2));
            }
            if (parent == null) {
                throw new RuntimeException(new NullParentException());
            }
            double
                    omega1 = body1.getAngularVelocity(),
                    omega2 = body2.getAngularVelocity(),
                    dOmega = Math.abs(omega1 - omega2);
            @NotNull Time synchTime = new Time(2 * Math.PI / dOmega, TimeUnit.S);
            printLine(INDENT + body1.getName() + "-" + body2.getName() + " alignment time: " + synchTime.getValueAndShortUnit(timeUnit));
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

        private static final class NullParentException extends Exception {
            //
            NullParentException() {
                super("Null parent exception.");
            }
        }
    }
}