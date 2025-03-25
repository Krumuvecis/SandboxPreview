package space.orbitalBodies;

import dimensions.distance.DistanceUnit;
import dimensions.distance.Distance;
import space.orbits.CircularOrbit;
import space.orbitalBodies.particularMajors.*;
import space.orbitalBodies.particularMajors.earth.*;
import space.orbitalBodies.particularMajors.mars.*;
import space.orbitalBodies.particularMinors.*;

//a minimal standard Solar system
public final class StandardSolarSystem {
    //planetary distances sourced from: https://en.wikipedia.org/wiki/List_of_gravitationally_rounded_objects_of_the_Solar_System
    private static final Distance
            DISTANCE_SUN_MERCURY = new Distance(0.38709893, DistanceUnit.AU),
            ORBITAL_STATION_MERCURY_HEIGHT = new Distance(200, DistanceUnit.KM),
            DISTANCE_SUN_VENUS = new Distance(0.72333199, DistanceUnit.AU),
            DISTANCE_SUN_EARTH = new Distance(1.00000011, DistanceUnit.AU),
            DISTANCE_EARTH_MOON = new Distance(384400, DistanceUnit.KM),
            ORBITAL_STATION_LEO_HEIGHT = new Distance(400, DistanceUnit.KM),
            ORBITAL_STATION_MOON_HEIGHT = new Distance(100, DistanceUnit.KM),
            DISTANCE_SUN_MARS = new Distance(1.52366231, DistanceUnit.AU),
            DISTANCE_MARS_PHOBOS = new Distance(9376, DistanceUnit.KM),
            ORBITAL_STATION_MARS_HEIGHT = new Distance(200, DistanceUnit.KM),
            ORBITAL_STATION_PHOBOS_HEIGHT = new Distance(5, DistanceUnit.KM),
            DISTANCE_SUN_JUPITER = new Distance(5.20336301, DistanceUnit.AU),
            DISTANCE_SUN_SATURN = new Distance(9.53707032, DistanceUnit.AU),
            DISTANCE_SUN_URANUS = new Distance(19.19126393, DistanceUnit.AU),
            DISTANCE_SUN_NEPTUNE = new Distance(30.06896348, DistanceUnit.AU);
    public static final Planetoid
            SUN = new Sun(null, null),
            MERCURY = new Mercury(SUN, new CircularOrbit(DISTANCE_SUN_MERCURY)),
            VENUS = new Venus(SUN, new CircularOrbit(DISTANCE_SUN_VENUS)),
            EARTH = new Earth(SUN, new CircularOrbit(DISTANCE_SUN_EARTH)),
            MOON = new Moon(EARTH, new CircularOrbit(DISTANCE_EARTH_MOON)),
            MARS = new Mars(SUN, new CircularOrbit(DISTANCE_SUN_MARS)),
            PHOBOS = new Phobos(MARS, new CircularOrbit(DISTANCE_MARS_PHOBOS));
    public static final MinorOrbitalBody
            ORBITAL_STATION_MERCURY = new OrbitalStation("Mercury orbital station", MERCURY, new CircularOrbit(MERCURY, ORBITAL_STATION_MERCURY_HEIGHT)),
            ORBITAL_STATION_LEO = new OrbitalStation_LEO(EARTH, new CircularOrbit(EARTH, ORBITAL_STATION_LEO_HEIGHT)),
            ORBITAL_STATION_MOON = new OrbitalStation_Moon(MOON, new CircularOrbit(MOON, ORBITAL_STATION_MOON_HEIGHT)),
            ORBITAL_STATION_MARS = new OrbitalStation_Mars(MARS, new CircularOrbit(MARS, ORBITAL_STATION_MARS_HEIGHT)),
            ORBITAL_STATION_PHOBOS = new OrbitalStation("Phobos orbital station", PHOBOS, new CircularOrbit(PHOBOS, ORBITAL_STATION_PHOBOS_HEIGHT));
}