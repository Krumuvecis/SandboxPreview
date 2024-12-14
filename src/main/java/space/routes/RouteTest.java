package space.routes;

import java.util.List;
import java.util.ArrayList;

import static consoleUtils.SimplePrinting.printLine;

import dimensions.distance.Distance;
import space.Orbit;
import space.orbitalBodies.OrbitalBody;
import space.orbitalBodies.StandardSolarSystem;

//
public class RouteTest {
    private static final String INDENT = "  ";

    //
    public static void main(String[] args) {
        testRoute(StandardSolarSystem.ORBITAL_STATION_MOON, StandardSolarSystem.ORBITAL_STATION_LEO);
        testRoute(StandardSolarSystem.ORBITAL_STATION_LEO, StandardSolarSystem.ORBITAL_STATION_MOON);
        testRoute(StandardSolarSystem.ORBITAL_STATION_LEO, StandardSolarSystem.ORBITAL_STATION_MOON);

        testRoute(StandardSolarSystem.ORBITAL_STATION_LEO, StandardSolarSystem.ORBITAL_STATION_MARS);
        testRoute(StandardSolarSystem.ORBITAL_STATION_MOON, StandardSolarSystem.ORBITAL_STATION_PHOBOS);
    }

    private static void testRoute(OrbitalBody start, OrbitalBody target) {
        printLine("Testing route from " + start.getName() + " to " + target.getName() + ":");

        //route from start to absolute root
        List<OrbitalBody> routeToRoot_start = findRouteToRoot(start);
        printRoute(routeToRoot_start, start.getName(), "absolute root");

        //route from target to absolute root
        List<OrbitalBody> routeToRoot_target = findRouteToRoot(target);
        printRoute(routeToRoot_target, target.getName(), "absolute root");

        //common root
        int commonRootIndexReversed = getCommonRootIndexReversed(routeToRoot_start, routeToRoot_target);
        OrbitalBody commonRoot = routeToRoot_start.reversed().get(commonRootIndexReversed);
        printLine(INDENT + "Common root: " + commonRoot.getName());

        //route from start to common root
        List<OrbitalBody> routeToCommonRoot_start = getRouteToCommonRoot(routeToRoot_start, commonRootIndexReversed);
        printRoute(routeToCommonRoot_start, start.getName(), "common root");
        if (routeToCommonRoot_start.isEmpty()) {
            return;
        }

        //route from target to common root
        List<OrbitalBody> routeToCommonRoot_target = getRouteToCommonRoot(routeToRoot_target, commonRootIndexReversed);
        printRoute(routeToCommonRoot_target, target.getName(), "common root");
        if (routeToCommonRoot_target.isEmpty()) {
            return;
        }

        //
        RootTriangleManeuver rootTriangle = new RootTriangleManeuver(
                commonRoot,
                routeToCommonRoot_start.getLast(),
                routeToCommonRoot_target.getLast());
        List<OrbitalBody>
                routeToCommonRootReversed_start = routeToCommonRoot_start.reversed(), //without the common root itself
                routeToCommonRootReversed_target = routeToCommonRoot_target.reversed(); //without the common root itself

        List<RouteManeuver> maneuversToCommonRootReversed = new ArrayList<>();
        for (int i = 1; i < routeToCommonRootReversed_start.size(); i++) {
            maneuversToCommonRootReversed.add(new SimpleManeuver(routeToCommonRootReversed_start.get(i), routeToCommonRootReversed_start.get(i - 1), ManeuverDirection.OUTWARDS));
        }
        List<RouteManeuver> maneuvers = maneuversToCommonRootReversed.reversed();
        maneuvers.add(rootTriangle);
        for (int i = 1; i < routeToCommonRootReversed_target.size(); i++) {
            maneuvers.add(new SimpleManeuver(routeToCommonRootReversed_target.get(i - 1), routeToCommonRootReversed_target.get(i), ManeuverDirection.INWARDS));
        }

        printLine(INDENT + "Maneuvers:");
        for (RouteManeuver maneuver : maneuvers) {
            printLine(INDENT.repeat(2) + maneuver.getDirection() + " maneuver from " + maneuver.start.getName() + " to " + maneuver.target.getName());
        }


        //

        printLine("");
    }

    private static List<OrbitalBody> findRouteToRoot(OrbitalBody body) {
        OrbitalBody child = body;
        List<OrbitalBody> route = new ArrayList<>();
        while (true) {
            route.add(child);
            OrbitalBody parent = child.getParent();
            if (parent == null) {
                break;
            }
            child = parent;
        }
        return route;
    }

    private static void printRoute(List<OrbitalBody> route, String startName, String finishName) {
        printLine(INDENT + "Route from " + startName + " to " + finishName + ":");
        if (route.isEmpty()) {
            printLine(INDENT.repeat(2) + "Route not found.");
        } else {
            printLine(INDENT.repeat(2) + getRouteString(route));
        }
    }

    private static String getRouteString(List<OrbitalBody> route) {
        String separator = " -> ";
        StringBuilder routeString = new StringBuilder();
        for (int i = 0; i < route.size(); i++) {
            if (i > 0) {
                routeString.append(separator);
            }
            routeString.append(route.get(i).getName());
        }
        return routeString.toString();
    }

    //get the index from behind for the lowest common root
    private static int getCommonRootIndexReversed(List<OrbitalBody> startRoute, List<OrbitalBody> targetRoute) {
        int
                startRouteSize = startRoute.size(),
                targetRouteSize = targetRoute.size(),
                maxSteps = Math.min(startRouteSize, targetRouteSize),
                commonIndexReversed = 0;
        List<OrbitalBody>
                startRouteReversed = startRoute.reversed(),
                targetRouteReversed = targetRoute.reversed();
        for (int i = 0; i < maxSteps; i++) {
            if (startRouteReversed.get(i) != targetRouteReversed.get(i)) {
                commonIndexReversed = i - 1;
                break;
            }
        }
        return commonIndexReversed;
    }

    //gets the route to the common root, without the common root itself
    private static List<OrbitalBody> getRouteToCommonRoot(List<OrbitalBody> routeToAbsoluteRoot, int commonRootIndexReversed) {
        List<OrbitalBody>
                routeToAbsoluteRootReversed = routeToAbsoluteRoot.reversed(),
                routeToCommonRootReversed = new ArrayList<>();
        for (int i = commonRootIndexReversed + 1; i < routeToAbsoluteRootReversed.size(); i++) {
            routeToCommonRootReversed.add(routeToAbsoluteRootReversed.get(i));
        }
        return routeToCommonRootReversed.reversed();
    }

    enum ManeuverDirection {
        INWARDS,
        OUTWARDS
    }

    static abstract class RouteManeuver {
        OrbitalBody start, target;
        private final ManeuverDirection direction;

        RouteManeuver(OrbitalBody start, OrbitalBody target, ManeuverDirection direction) {
            this.direction = direction;
            this.start = start;
            this.target = target;
        }

        public ManeuverDirection getDirection() {
            return direction;
        }

        //Hohmann
        public abstract LocationAndOrbit getTransferOrbit();
    }

    static class RootTriangleManeuver extends RouteManeuver {
        OrbitalBody commonRoot;

        RootTriangleManeuver(OrbitalBody commonRoot, OrbitalBody start, OrbitalBody target) {
            super(start, target, getDirection(start, target));
            this.commonRoot = commonRoot;
        }

        private static ManeuverDirection getDirection(OrbitalBody start, OrbitalBody target) {
            ManeuverDirection direction;

            //if (start.orbit.semiMajorAxis)

            /*return direction;*/
            return null;
        }

        //Hohmann
        @Override
        public LocationAndOrbit getTransferOrbit() {
            //TODO: account for Hill's sphere and eccentricity, etc.

            Distance
                    startDistance, targetDistance,
                    periapsis, apoapsis;


            /*Orbit transferOrbit = Orbit.getEllipticOrbit(periapsis, apoapsis);
            return new LocationAndOrbit(commonRoot, transferOrbit);*/
            return null;
        }
    }

    static class SimpleManeuver extends RouteManeuver {
        SimpleManeuver(OrbitalBody start, OrbitalBody target, ManeuverDirection direction) {
            super(start, target, direction);
        }

        //Hohmann
        //  if moon->earth, then moon
        //  if earth->moon, then earth
        //  thus always start as location
        @Override
        public LocationAndOrbit getTransferOrbit() {

            Orbit transferOrbit;

            /*return new LocationAndOrbit(start, transferOrbit);*/
            return null;
        }
    }

    @SuppressWarnings("ClassCanBeRecord")
    static class LocationAndOrbit {
        private final OrbitalBody body;
        private final Orbit orbit;

        LocationAndOrbit(OrbitalBody body, Orbit orbit) {
            this.body = body;
            this.orbit = orbit;
        }

        public OrbitalBody getBody() {
            return body;
        }

        public Orbit getOrbit() {
            return orbit;
        }
    }
}