package space.routes;

import java.util.List;
import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;

import dimensions.distance.Distance;
import dimensions.time.Time;
import space.orbits.HohmannTransferOrbit;
import space.orbitalBodies.abstractOrbitalBody.NullParentException;
import space.orbitalBodies.abstractOrbitalBody.NullOrbitException;
import space.orbitalBodies.abstractOrbitalBody.AbstractOrbitalBody;
import space.orbitalBodies.MajorOrbitalBody;

//
public class ManeuverRoute {
    private final OutwardsHohmannRoute outwardsRoute;
    private final CommonRootHohmannElement commonRootElement;
    private final InwardsHohmannRoute inwardsRoute;

    //
    public ManeuverRoute(@NotNull List<@NotNull AbstractOrbitalBody> routeStartToCommonRoot,
                         @NotNull MajorOrbitalBody commonRoot,
                         @NotNull List<@NotNull AbstractOrbitalBody> routeCommonRootToTarget) {
        outwardsRoute = new OutwardsHohmannRoute(routeStartToCommonRoot);
        commonRootElement = new CommonRootHohmannElement(commonRoot, routeStartToCommonRoot.getLast(), routeCommonRootToTarget.getFirst());
        inwardsRoute = new InwardsHohmannRoute(routeCommonRootToTarget);
    }

    //
    public ManeuverRoute(@NotNull ObjectRoute route) {
        this(route.routeStartToCommonRoot, route.commonRoot, route.routeCommonRootToTarget);
    }

    //
    public List<HohmannRouteElement> getOutwardsRoute() {
        return outwardsRoute.hohmannRoute;
    }

    //
    public List<HohmannRouteElement> getCommonRootRoute() {
        return new ArrayList<>() {{add(commonRootElement.hohmannElement);}};
    }

    //
    public List<HohmannRouteElement> getInwardsRoute() {
        return inwardsRoute.hohmannRoute;
    }

    public List<HohmannRouteElement> getTotalRoute() {
        return new ArrayList<>() {{
            addAll(getOutwardsRoute());
            addAll(getCommonRootRoute());
            addAll(getInwardsRoute());
        }};
    }

    //in m/s, for total route
    public double getTotalDeltaV() {
        return getTotalDeltaV(getTotalRoute());
    }

    //in m/s
    private double getTotalDeltaV(List<HohmannRouteElement> route) {
        double sum = 0;
        for (HohmannRouteElement element : route) {
            double
                    specificGravity = element.parent.getSpecificGravity(),
                    dvp = element.transferOrbit.getDeltaVtoCircularAtPeriapsis(specificGravity),
                    dva = element.transferOrbit.getDeltaVtoCircularAtApoapsis(specificGravity);
            sum += dvp + dva;
        }
        return sum;
    }

    //in seconds, for total route
    public Time getTotalTransferTime() {
        return new Time(getTotalTransferTime(getTotalRoute()));
    }

    //in seconds
    private double getTotalTransferTime(List<HohmannRouteElement> route) {
        double sum = 0;
        for (HohmannRouteElement element : route) {
            sum += element.transferOrbit.getTransferTime(element.parent.getSpecificGravity()).getSI();
        }
        return sum;
    }

    public static class HohmannRouteElement {
        public final MajorOrbitalBody parent;
        public final HohmannTransferOrbit transferOrbit;
        public final boolean outwards;
        public final String startName, targetName;

        HohmannRouteElement(MajorOrbitalBody parent, HohmannTransferOrbit transferOrbit, boolean outwards,
                            String startName, String targetName) {
            this.parent = parent;
            this.transferOrbit = transferOrbit;
            this.outwards = outwards;
            this.startName = startName;
            this.targetName = targetName;
        }
    }

    static class OutwardsHohmannRoute {
        final List<HohmannRouteElement> hohmannRoute;

        //
        OutwardsHohmannRoute(@NotNull List<@NotNull AbstractOrbitalBody> routeStartToCommonRoot) {
            hohmannRoute = new ArrayList<>();
            //first element is start

            //i - parent's index, but is abstract - parent must be accessed through (i - 1).getParent()
            for (int i = 1; i < routeStartToCommonRoot.size(); i++) {
                AbstractOrbitalBody particularBody = routeStartToCommonRoot.get(i - 1);
                try {
                    MajorOrbitalBody parent = particularBody.getNonNullParent();
                    Distance
                            rp = getPeriapsis(particularBody),
                            ra = getApoapsis(parent);
                    String
                            startName = particularBody.getName(),
                            targetName = parent.getName() + "'s Hill sphere";
                    if (particularBody instanceof MajorOrbitalBody) {
                        startName += "'s Hill sphere";
                    }
                    hohmannRoute.add(new HohmannRouteElement(
                            parent, new HohmannTransferOrbit(rp, ra), true,
                            startName, targetName));
                } catch (@NotNull NullParentException | @NotNull NullOrbitException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        private Distance getPeriapsis(@NotNull AbstractOrbitalBody body) throws NullParentException, NullOrbitException {
            @NotNull Distance rp_0 = body.getNonNulSemiMajorAxis();

            Distance rp;
            if (body instanceof @NotNull MajorOrbitalBody major) {
                Distance rp_h = major.getHillRadiusAtSemiMajorAis();
                rp = new Distance(rp_0.getSI() + rp_h.getSI());
            } else {
                rp = rp_0;
            }
            return rp;
        }

        private Distance getApoapsis(@NotNull MajorOrbitalBody parent) throws NullParentException, NullOrbitException {
            return parent.getHillRadiusAtSemiMajorAis();
        }
    }

    static class InwardsHohmannRoute {
        final List<HohmannRouteElement> hohmannRoute;

        //
        InwardsHohmannRoute(@NotNull List<@NotNull AbstractOrbitalBody> routeCommonRootToTarget){
            hohmannRoute = new ArrayList<>();
            //last element is the target

            for (int i = 1; i < routeCommonRootToTarget.size(); i++) {
                AbstractOrbitalBody particularBody = routeCommonRootToTarget.get(i);
                try {
                    MajorOrbitalBody parent = particularBody.getNonNullParent();
                    Distance
                            rp = getPeriapsis(particularBody),
                            ra = getApoapsis(parent);
                    String
                            startName = parent.getName() + "'s Hill sphere",
                            targetName = particularBody.getName();
                    if (particularBody instanceof MajorOrbitalBody) {
                        targetName += "'s Hill sphere";
                    }
                    hohmannRoute.add(new HohmannRouteElement(
                            parent, new HohmannTransferOrbit(rp, ra), false,
                            startName, targetName));
                } catch (@NotNull NullParentException | @NotNull NullOrbitException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        private Distance getPeriapsis(@NotNull AbstractOrbitalBody body) throws NullParentException, NullOrbitException {
            @NotNull Distance rp_0 = body.getNonNulSemiMajorAxis();

            Distance rp;
            if (body instanceof @NotNull MajorOrbitalBody major) {
                Distance rp_h = major.getHillRadiusAtSemiMajorAis();
                rp = new Distance(rp_0.getSI() + rp_h.getSI());
            } else {
                rp = rp_0;
            }
            return rp;
        }

        private Distance getApoapsis(@NotNull MajorOrbitalBody parent) throws NullParentException, NullOrbitException {
            return parent.getHillRadiusAtSemiMajorAis();
        }
    }

    static class CommonRootHohmannElement {
        final HohmannRouteElement hohmannElement;

        //
        CommonRootHohmannElement(MajorOrbitalBody parent, AbstractOrbitalBody lastOutwardsBody, AbstractOrbitalBody firstInwardsBody) {
            Distance
                    a1 = lastOutwardsBody.getNonNulSemiMajorAxis(),
                    a2 = firstInwardsBody.getNonNulSemiMajorAxis(),
                    rh1,
                    rh2;
            String
                    startName,
                    targetName;
            try {
                if (lastOutwardsBody instanceof MajorOrbitalBody major) {
                    rh1 = major.getHillRadiusAtSemiMajorAis();
                    startName = major.getName() + "'s Hill sphere";
                } else {
                    rh1 = new Distance(0);
                    startName = lastOutwardsBody.getName();
                }
                if (firstInwardsBody instanceof MajorOrbitalBody major) {
                    rh2 = major.getHillRadiusAtSemiMajorAis();
                    targetName = major.getName() + "'s Hill sphere";
                } else {
                    rh2 = new Distance(0);
                    targetName = firstInwardsBody.getName();
                }
            } catch (@NotNull NullParentException | @NotNull NullOrbitException e) {
                throw new RuntimeException(e);
            }

            boolean outwards;
            Distance rp, ra;
            if (a1.getSI() + rh1.getSI() <= a2.getSI() - rh2.getSI()) {
                outwards = true;
                rp = new Distance(a1.getSI() + rh1.getSI());
                ra = new Distance(a2.getSI() - rh2.getSI());
            } else if (a1.getSI() - rh1.getSI() >= a2.getSI() + rh2.getSI()) {
                outwards = false;
                rp = new Distance(a2.getSI() + rh2.getSI());
                ra = new Distance(a1.getSI() - rh1.getSI());
            } else {
                throw new RuntimeException("Overlapping orbits not supported.");
            }

            HohmannTransferOrbit transferOrbit = new HohmannTransferOrbit(rp, ra);
            hohmannElement = new HohmannRouteElement(parent, transferOrbit, outwards, startName, targetName);
        }
    }
}