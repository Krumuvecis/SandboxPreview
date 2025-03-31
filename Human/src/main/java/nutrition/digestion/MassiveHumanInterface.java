package nutrition.digestion;

import org.jetbrains.annotations.NotNull;

import dimensions.mass.Mass;

//
public interface MassiveHumanInterface {
    //
    @NotNull Mass getBodyMass();

    //
    void setBodyMass(@NotNull Mass bodyMass);
}