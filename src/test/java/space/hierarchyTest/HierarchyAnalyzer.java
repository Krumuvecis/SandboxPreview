package space.hierarchyTest;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static consoleUtils.SimplePrinting.printLine;

import dimensions.distance.DistanceUnit;
import dimensions.distance.Distance;
import dimensions.mass.Mass;
import space.orbits.OrbitInterface;
import space.orbitalBodies.MinorOrbitalBody;
import space.orbitalBodies.MajorOrbitalBody;
import space.orbitalBodies.PlanetaryParameters;
import space.orbitalBodies.Planetoid;

//
final class HierarchyAnalyzer {
    private static final @NotNull String
            INDENT = "  ",
            SEPARATOR_COMMA = ", ",
            NULL_VALUE_STRING = "Null";
    private final MajorOrbitalBody root;

    //
    HierarchyAnalyzer(MajorOrbitalBody root) {
        this.root = root;
    }

    //call this to print
    void print() {
        printMajor(root, 0);
    }

    private void printMajor(@NotNull MajorOrbitalBody body, int level) {
        printLine(getIndent(level) + body.getName() + " (major)");
        printLine(getIndent(level + 1) + getOrbitalInfo_ea(body.getOrbit()));
        printLine(getIndent(level + 1) + getOrbitalInfo_RpRa(body.getOrbit()));
        printLine(getIndent(level + 1) + getMassInfo(body.getMass()));
        if (body instanceof @NotNull Planetoid planetoid) {
            @NotNull PlanetaryParameters planetaryParameters = planetoid.planetaryParameters;
            printLine(getIndent(level + 1) + getRadiusInfo(planetaryParameters.getRadius()));
        }
        printLine("");
        for (@NotNull MajorOrbitalBody satellite : body.satellites.getMajorSatellites()) {
            printMajor(satellite, level + 1);
        }
        for (@NotNull MinorOrbitalBody satellite : body.satellites.getMinorSatellites()) {
            printMinor(satellite, level + 1);
        }
        printLine("");
    }

    private void printMinor(@NotNull MinorOrbitalBody body, int level) {
        printLine(getIndent(level) + body.getName() + " (minor)");
        printLine(getIndent(level + 1) + getOrbitalInfo_ea(body.getOrbit()));
        printLine(getIndent(level + 1) + getOrbitalInfo_RpRa(body.getOrbit()));
        @Nullable Distance parentRadius;
        if (body.getParent() instanceof @NotNull Planetoid planetoid) {
            parentRadius = planetoid.planetaryParameters.getRadius();
        } else {
            parentRadius = null;
        }
        printLine(getIndent(level + 1) + getOrbitalInfo_HpHa(body.getOrbit(), parentRadius, DistanceUnit.KM));
    }

    private static String getIndent(int level) {
        return INDENT.repeat(level);
    }

    private static String getOrbitalInfo_ea(@Nullable OrbitInterface orbit) {
        @NotNull String
                semiMajorAxisString = "a: ",
                eccentricityString = "e: ";
        if (orbit == null) {
            semiMajorAxisString += NULL_VALUE_STRING;
            eccentricityString += NULL_VALUE_STRING;
        } else {
            @Nullable Distance semiMajorAxis = orbit.getSemiMajorAxis();
            if (semiMajorAxis == null) {
                semiMajorAxisString += NULL_VALUE_STRING;
            } else {
                semiMajorAxisString += semiMajorAxis.getValueAndShortUnit();
            }

            double eccentricity = orbit.getEccentricity();
            eccentricityString += eccentricity;
        }
        return semiMajorAxisString + SEPARATOR_COMMA + eccentricityString;
    }

    private static String getOrbitalInfo_RpRa(@Nullable OrbitInterface orbit) {
        @NotNull String
                periapsisString = "Rp: ",
                apoapsisString = "Ra: ";
        if (orbit == null) {
            periapsisString += NULL_VALUE_STRING;
            apoapsisString += NULL_VALUE_STRING;
        } else {
            @Nullable Distance periapsis = orbit.getPeriapsis();
            if (periapsis == null) {
                periapsisString += NULL_VALUE_STRING;
            } else {
                periapsisString += periapsis.getValueAndShortUnit();
            }

            @Nullable Distance apoapsis = orbit.getApoapsis();
            if (apoapsis == null) {
                apoapsisString += NULL_VALUE_STRING;
            } else {
                apoapsisString += apoapsis.getValueAndShortUnit();
            }
        }
        return periapsisString + SEPARATOR_COMMA + apoapsisString;
    }

    private static String getOrbitalInfo_HpHa(@Nullable OrbitInterface orbit, @Nullable Distance radius, @NotNull DistanceUnit unit) {
        @NotNull String
                periapsisString = "Hp: ",
                apoapsisString = "Ha: ";
        if (orbit == null || radius == null) {
            periapsisString += NULL_VALUE_STRING;
            apoapsisString += NULL_VALUE_STRING;
        } else {
            double r0 = radius.get(unit);
            @Nullable Distance periapsis = orbit.getPeriapsis();
            if (periapsis == null) {
                periapsisString += NULL_VALUE_STRING;
            } else {
                double rp = periapsis.get(unit);
                @NotNull Distance hp = new Distance(rp - r0, unit);
                periapsisString += hp.getValueAndShortUnit();
            }

            @Nullable Distance apoapsis = orbit.getApoapsis();
            if (apoapsis == null) {
                apoapsisString += NULL_VALUE_STRING;
            } else {
                double ra = apoapsis.get(unit);
                @NotNull Distance ha = new Distance(ra - r0, unit);
                apoapsisString += ha.getValueAndShortUnit();
            }
        }
        return periapsisString + SEPARATOR_COMMA + apoapsisString;
    }

    private static @NotNull String getMassInfo(@NotNull Mass mass) {
        return "mass: " + mass.getValueAndShortUnit();
    }

    private static @NotNull String getRadiusInfo(@Nullable Distance radius) {
        @NotNull String radiusString = "radius: ";
        if (radius == null) {
            radiusString += NULL_VALUE_STRING;
        } else {
            radiusString += radius.getValueAndShortUnit();
        }
        return radiusString;
    }
}