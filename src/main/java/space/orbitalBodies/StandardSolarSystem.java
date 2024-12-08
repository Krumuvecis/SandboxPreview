package space.orbitalBodies;

import dimensions.distance.DistanceUnit;
import dimensions.distance.Distance;
import space.Orbit;
import space.satelliteContainer.DuplicateSatelliteException;
import space.orbitalBodies.particularMajors.*;
import space.orbitalBodies.particularMinors.*;

//a minimal standard Solar system
public final class StandardSolarSystem {
    //planetary distances sourced from: https://en.wikipedia.org/wiki/List_of_gravitationally_rounded_objects_of_the_Solar_System
    private static final Distance
            DISTANCE_SUN_MERCURY = new Distance(0.38709893, DistanceUnit.AU),
            DISTANCE_SUN_VENUS = new Distance(0.72333199, DistanceUnit.AU),
            DISTANCE_SUN_EARTH = new Distance(1.00000011, DistanceUnit.AU),
            DISTANCE_EARTH_MOON = new Distance(1, DistanceUnit.KM),
            ORBITAL_STATION_LEO_HEIGHT = new Distance(400, DistanceUnit.KM),
            ORBITAL_STATION_MOON_HEIGHT = new Distance(100, DistanceUnit.KM),
            DISTANCE_SUN_MARS = new Distance(1.52366231, DistanceUnit.AU),
            DISTANCE_MARS_PHOBOS = new Distance(9376, DistanceUnit.KM),
            ORBITAL_STATION_MARS_HEIGHT = new Distance(200, DistanceUnit.KM),
            ORBITAL_STATION_PHOBOS_HEIGHT = new Distance(50, DistanceUnit.KM),
            DISTANCE_SUN_JUPITER = new Distance(5.20336301, DistanceUnit.AU),
            DISTANCE_SUN_SATURN = new Distance(9.53707032, DistanceUnit.AU),
            DISTANCE_SUN_URANUS = new Distance(19.19126393, DistanceUnit.AU),
            DISTANCE_SUN_NEPTUNE = new Distance(30.06896348, DistanceUnit.AU);
    public static final MajorOrbitalBody
            SUN = new Sun(null, null),
            EARTH = new Earth(SUN, Orbit.getCircularOrbit(DISTANCE_SUN_EARTH)),
            MOON = new Moon(EARTH, Orbit.getCircularOrbit(DISTANCE_EARTH_MOON)),
            MARS = new Mars(SUN, Orbit.getCircularOrbit(DISTANCE_SUN_MARS)),
            PHOBOS = new Phobos(MARS, Orbit.getCircularOrbit(DISTANCE_MARS_PHOBOS));
    public static final MinorOrbitalBody
            ORBITAL_STATION_LEO = new OrbitalStation_LEO(EARTH, Orbit.getCircularOrbit(EARTH, ORBITAL_STATION_LEO_HEIGHT)),
            ORBITAL_STATION_MOON = new OrbitalStation_Moon(MOON, Orbit.getCircularOrbit(MOON, ORBITAL_STATION_MOON_HEIGHT)),
            ORBITAL_STATION_MARS = new OrbitalStation_Mars(MARS, Orbit.getCircularOrbit(MARS, ORBITAL_STATION_MARS_HEIGHT)),
            ORBITAL_STATION_PHOBOS = new OrbitalStation("Phobos orbital station", PHOBOS, Orbit.getCircularOrbit(PHOBOS, ORBITAL_STATION_PHOBOS_HEIGHT));

    //call this before use to populate the hierarchy
    static {
        try {
            SUN.satellites.add(EARTH);
            EARTH.satellites.add(MOON);
            EARTH.satellites.add(ORBITAL_STATION_LEO);
            MOON.satellites.add(ORBITAL_STATION_MOON);

            SUN.satellites.add(MARS);
            MARS.satellites.add(PHOBOS);
            MARS.satellites.add(ORBITAL_STATION_MARS);
            PHOBOS.satellites.add(ORBITAL_STATION_PHOBOS);
        } catch (DuplicateSatelliteException e) {
            throw new RuntimeException(e);
        }
    }
}