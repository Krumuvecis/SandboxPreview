package space;

import org.jetbrains.annotations.NotNull;

import static consoleUtils.SimplePrinting.printLine;

import dimensions.time.TimeUnit;
import dimensions.time.Time;
import space.orbitalBodies.abstractOrbitalBody.NullParentException;
import space.orbitalBodies.abstractOrbitalBody.NullOrbitException;
import space.orbitalBodies.abstractOrbitalBody.AbstractOrbitalBody;
import space.orbitalBodies.StandardSolarSystem;

//For testing orbital periods
final class OrbitalPeriodTest {
    private static final @NotNull String
            INDENT = "  ",
            NULL_VALUE_STRING = "Null";

    //Main method of this test, run to start
    @SuppressWarnings("InstantiationOfUtilityClass")
    public static void main(String[] args) {
        printLine("Testing orbital periods:");
        new OrbitalPeriodTest(StandardSolarSystem.EARTH, TimeUnit.YEAR);
        new OrbitalPeriodTest(StandardSolarSystem.MOON, TimeUnit.DAY);
        new OrbitalPeriodTest(StandardSolarSystem.MARS, TimeUnit.YEAR);
        new OrbitalPeriodTest(StandardSolarSystem.VENUS, TimeUnit.YEAR);
        new OrbitalPeriodTest(StandardSolarSystem.ORBITAL_STATION_LEO, TimeUnit.MIN);
        new OrbitalPeriodTest(StandardSolarSystem.ORBITAL_STATION_MOON, TimeUnit.MIN);
        new OrbitalPeriodTest(StandardSolarSystem.ORBITAL_STATION_MARS, TimeUnit.MIN);
        new OrbitalPeriodTest(StandardSolarSystem.ORBITAL_STATION_PHOBOS, TimeUnit.H);
    }

    private OrbitalPeriodTest(@NotNull AbstractOrbitalBody body, @NotNull TimeUnit unit) {
        printLine(INDENT + body.getName() + ": " + getOrbitalPeriodString(body, unit));
    }

    private static @NotNull String getOrbitalPeriodString(@NotNull AbstractOrbitalBody body, @NotNull TimeUnit unit) {
        try {
            @NotNull Time orbitalTime = body.getOrbitalPeriod();
            return orbitalTime.getValueAndShortUnit(unit);
        } catch (@NotNull NullParentException | @NotNull NullOrbitException ignored) {
            return NULL_VALUE_STRING;
        }
    }
}