package space.hierarchyTest;

import static consoleUtils.SimplePrinting.printLine;

import dimensions.distance.Distance;
import dimensions.mass.Mass;
import space.Orbit;
import space.orbitalBodies.PlanetaryParameters;
import space.orbitalBodies.MajorOrbitalBody;
import space.orbitalBodies.MinorOrbitalBody;

//
class HierarchyAnalyzer {
    private static final String
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

    private void printMajor(MajorOrbitalBody body, int level) {
        String majorString = getIndent(level) + body.getName() + " (major) " + getOrbitalInfo(body.orbit) + SEPARATOR_COMMA;
        PlanetaryParameters planetaryParameters = body.planetaryParameters;
        if (planetaryParameters == null) {
            printLine(majorString + "planetary parameters: " + NULL_VALUE_STRING);
        } else {
            printLine(majorString + getMassInfo(body.getMass()) + SEPARATOR_COMMA + getRadiusInfo(planetaryParameters.getRadius()));
        }

        for (MajorOrbitalBody satellite : body.satellites.getMajorSatellites()) {
            printMajor(satellite, level + 1);
        }
        for (MinorOrbitalBody satellite : body.satellites.getMinorSatellites()) {
            printMinor(satellite, level + 1);
        }
    }

    private void printMinor(MinorOrbitalBody body, int level) {
        printLine(getIndent(level) + body.getName() + " (minor) " + getOrbitalInfo(body.orbit));
    }

    private static String getIndent(int level) {
        return INDENT.repeat(level);
    }

    private static String getOrbitalInfo(Orbit orbit) {
        String
                semiMajorAxisString = "a: ",
                eccentricityString = "e: ";
        if (orbit == null) {
            semiMajorAxisString += NULL_VALUE_STRING;
            eccentricityString += NULL_VALUE_STRING;
        } else {
            Distance semiMajorAxis = orbit.semiMajorAxis;
            if (semiMajorAxis == null) {
                semiMajorAxisString += NULL_VALUE_STRING;
            } else {
                semiMajorAxisString += semiMajorAxis.getValueAndShortUnit();
            }
            double eccentricity = orbit.eccentricity;
            eccentricityString += eccentricity;
        }
        return semiMajorAxisString + SEPARATOR_COMMA + eccentricityString;
    }

    private static String getMassInfo(Mass mass) {
        String massString = "mass: ";
        if (mass == null) {
            massString += NULL_VALUE_STRING;
        } else {
            massString += mass.getValueAndShortUnit();
        }
        return massString;
    }

    private static String getRadiusInfo(Distance radius) {
        String radiusString = "radius: ";
        if (radius == null) {
            radiusString += NULL_VALUE_STRING;
        } else {
            radiusString += radius.getValueAndShortUnit();
        }
        return radiusString;
    }
}