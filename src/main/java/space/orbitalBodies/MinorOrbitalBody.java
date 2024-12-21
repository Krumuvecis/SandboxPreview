package space.orbitalBodies;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import space.orbits.OrbitInterface;
import space.orbitalBodies.abstractOrbitalBody.AbstractOrbitalBody;

//
public class MinorOrbitalBody extends AbstractOrbitalBody {
    //
    public MinorOrbitalBody(@NotNull String name, @Nullable MajorOrbitalBody parent, @Nullable OrbitInterface orbit) {
        super(name, parent, orbit);
    }
}