package space.routes;

import java.util.List;
import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;

import org.jetbrains.annotations.Nullable;
import space.orbitalBodies.abstractOrbitalBody.AbstractOrbitalBody;
import space.orbitalBodies.MajorOrbitalBody;

//
public class ObjectRoute {
    public final @NotNull MajorOrbitalBody commonRoot;
    public final @NotNull List<@NotNull AbstractOrbitalBody> routeStartToCommonRoot, routeCommonRootToTarget;

    //
    public ObjectRoute(@NotNull List<@NotNull AbstractOrbitalBody> routeStartToAbsoluteRoot,
                       @NotNull List<@NotNull AbstractOrbitalBody> routeTargetToAbsoluteRoot)
            throws NotSameRootException {
        @NotNull List<@NotNull AbstractOrbitalBody>
                routeStartToAbsoluteRootReversed = routeStartToAbsoluteRoot.reversed(),
                routeTargetToAbsoluteRootReversed = routeTargetToAbsoluteRoot.reversed();
        int commonRootIndex = 0;
        for (int i = 0; i < Math.min(routeStartToAbsoluteRootReversed.size(), routeTargetToAbsoluteRootReversed.size()); i++) {
            if (routeStartToAbsoluteRootReversed.get(i) != routeTargetToAbsoluteRootReversed.get(i)) {
                if (i == 0) {
                    throw new NotSameRootException();
                }
                break;
            }
            commonRootIndex = i;
        }
        commonRoot = (MajorOrbitalBody) routeStartToAbsoluteRootReversed.get(commonRootIndex);

        @NotNull List<@NotNull AbstractOrbitalBody> routeStartToCommonRootReversed = new ArrayList<>();
        for (int i = commonRootIndex + 1; i < routeStartToAbsoluteRootReversed.size(); i++) {
            routeStartToCommonRootReversed.add(routeStartToAbsoluteRootReversed.get(i));
        }
        routeStartToCommonRoot = routeStartToCommonRootReversed.reversed();
        routeCommonRootToTarget = new ArrayList<>();
        for (int i = commonRootIndex + 1; i < routeTargetToAbsoluteRootReversed.size(); i++) {
            routeCommonRootToTarget.add(routeTargetToAbsoluteRootReversed.get(i));
        }
    }

    public ObjectRoute(@NotNull AbstractOrbitalBody start, @NotNull AbstractOrbitalBody target)
            throws NotSameRootException {
        this(getRouteToAbsoluteRoot(start), getRouteToAbsoluteRoot(target));
    }

    static @NotNull List<@NotNull AbstractOrbitalBody> getRouteToAbsoluteRoot(@NotNull AbstractOrbitalBody body) {
        @NotNull List<@NotNull AbstractOrbitalBody> route = new ArrayList<>();
        route.add(body);
        @Nullable MajorOrbitalBody parent = body.getParent();
        while (parent != null) {
            route.add(parent);
            parent = parent.getParent();
        }
        return route;
    }
}