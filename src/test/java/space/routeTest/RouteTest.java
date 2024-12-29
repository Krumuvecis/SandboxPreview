package space.routeTest;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import static consoleUtils.SimplePrinting.printLine;

import dimensions.distance.Distance;
import dimensions.time.TimeUnit;
import space.orbitalBodies.abstractOrbitalBody.NullParentException;
import space.orbitalBodies.abstractOrbitalBody.AbstractOrbitalBody;
import space.orbitalBodies.StandardSolarSystem;
import space.routes.NotSameRootException;
import space.routes.ObjectRoute;
import space.routes.ManeuverRoute;
import space.routes.ManeuverRoute.HohmannRouteElement;

//route test 2
public class RouteTest {
    private static final String INDENT = "  ";

    //
    @SuppressWarnings("InstantiationOfUtilityClass")
    public static void main(String[] args) {
        try {
            //new RouteTest2(StandardSolarSystem.ORBITAL_STATION_MOON, StandardSolarSystem.ORBITAL_STATION_LEO);
            new RouteTest(StandardSolarSystem.ORBITAL_STATION_LEO, StandardSolarSystem.ORBITAL_STATION_MOON);
            //new RouteTest2(StandardSolarSystem.ORBITAL_STATION_LEO, StandardSolarSystem.ORBITAL_STATION_MARS);
            new RouteTest(StandardSolarSystem.ORBITAL_STATION_MOON, StandardSolarSystem.ORBITAL_STATION_PHOBOS);
            new RouteTest(StandardSolarSystem.ORBITAL_STATION_MOON, StandardSolarSystem.ORBITAL_STATION_MERCURY);
        } catch (@NotNull NullParentException | NotSameRootException e) {
            throw new RuntimeException(e);
        }
    }

    //
    RouteTest(@NotNull AbstractOrbitalBody start, @NotNull AbstractOrbitalBody target) throws NullParentException, NotSameRootException {
        printIntro(start, target);
        if (start.getParent() == null || target.getParent() == null) {
            throw new NullParentException();
        }
        ObjectRoute commonRootRoute = new ObjectRoute(start, target);
        printLine(INDENT + "Common root: " + commonRootRoute.commonRoot.getName());
        printRoute("Route from start (" + start.getName() + ") to common root", commonRootRoute.routeStartToCommonRoot);
        printRoute("Route from common root to target (" + target.getName() + ")", commonRootRoute.routeCommonRootToTarget);

        ManeuverRoute maneuverRoute = new ManeuverRoute(commonRootRoute);
        /*
        printLine("");
        printHohmannRoute("Outwards Hohmann route", maneuverRoute.getOutwardsRoute());
        printLine("");
        printHohmannRoute("Common root Hohmann route", maneuverRoute.getCommonRootRoute());
        printLine("");
        printHohmannRoute("Inwards Hohmann route", maneuverRoute.getInwardsRoute());
        */
        printLine("");
        printHohmannRoute("Total Hohmann route", maneuverRoute.getTotalRoute());
        printLine("");
        printLine(INDENT
                + "Total delta-v: " + maneuverRoute.getTotalDeltaV() + " m/s"
                + ", total transfer time: " + maneuverRoute.getTotalTransferTime().getValueAndShortUnit(TimeUnit.DAY));
        printLine("");
    }

    private static void printIntro(@NotNull AbstractOrbitalBody start, @NotNull AbstractOrbitalBody target) {
        printLine("----------");
        printLine("");
        printLine("Testing route: " + start.getName() + " - " + target.getName());
    }

    private static void printRoute(@NotNull String routeName, @NotNull List<@NotNull AbstractOrbitalBody> route) {
        printLine(INDENT + routeName + ":");
        StringBuilder routeString = new StringBuilder(route.get(0).getName());
        for (int i = 1; i < route.size(); i++) {
            routeString.append(" - ");
            routeString.append(route.get(i).getName());
        }
        printLine(INDENT.repeat(2) + routeString);
    }

    @SuppressWarnings("SameParameterValue")
    private static void printHohmannRoute(@NotNull String routeName, @NotNull List<@NotNull HohmannRouteElement> route) {
        printLine(INDENT + routeName + ":");
        for (@NotNull HohmannRouteElement element : route) {
            boolean outwards = element.outwards;
            printLine(INDENT.repeat(2) + getDirectionString(outwards) + " transfer at " + element.parent.getName());
            double
                    specificGravity = element.parent.getSpecificGravity(),
                    dvp = element.transferOrbit.getDeltaVtoCircularAtPeriapsis(specificGravity),
                    dva = element.transferOrbit.getDeltaVtoCircularAtApoapsis(specificGravity);
            Distance
                    periapsis = element.transferOrbit.getPeriapsis(),
                    apoapsis = element.transferOrbit.getApoapsis();
            if (outwards) {
                printLine(INDENT.repeat(3)
                        + "Periapsis at: " + element.startName
                        + ", R: " + periapsis.getValueAndShortUnit()
                        + ", delta-v: " + dvp + " m/s");
                printLine(INDENT.repeat(3)
                        + "Apoapsis at: " + element.targetName
                        + ", R: " + apoapsis.getValueAndShortUnit()
                        + ", delta-v: " + dva + " m/s");
            } else {
                printLine(INDENT.repeat(3)
                        + "Apoapsis at: " + element.startName
                        + ", R: " + apoapsis.getValueAndShortUnit()
                        + ", delta-v: " + dva + " m/s");
                printLine(INDENT.repeat(3)
                        + "Periapsis at: " + element.targetName
                        + ", R: " + periapsis.getValueAndShortUnit()
                        + ", delta-v: " + dvp + " m/s");
            }
            double dvTotal = dvp + dva;
            printLine(INDENT.repeat(3)
                    + "Sub-total delta-v: " + dvTotal + " m/s"
                    + ", transfer time: " + element.transferOrbit.getTransferTime(specificGravity).getValueAndShortUnit(TimeUnit.DAY));
        }
    }

    private static @NotNull String getDirectionString(boolean outwards) {
        if (outwards) {
            return "Outwards";
        } else {
            return "Inwards";
        }
    }
}