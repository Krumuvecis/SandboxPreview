package basicParts.cables;

import org.jetbrains.annotations.NotNull;

//
public enum CableMaterial {
    STEEL_COMMON("Common steel (structural/stainless)", 7800, getPascals(250)),
    STEEL_HIGH_STRENGTH("High-strength steel", 7800, getPascals(900)),
    ALUMINIUM_ALLOY("Aluminium alloy", 2800, getPascals(400)),
    STEEL_MARAGING("Maraging steel", 8100, getPascals(2400)),
    NYLON("Nylon", 1130, getPascals(900)),
    FIBERGLASS_S_CLASS("Fiberglass S-class", 2460, getPascals(2445)),
    ZYLON("Zylon", 1560, getPascals(2700)),
    KEVLAR("Kevlar", 1440, getPascals(3620)),
    CARBON_FIBER("Carbon fiber", 1750, getPascals(4100));

    private final @NotNull String name;
    private final double
            density, // in kg/m3
            maxStress; // yield strength, in Pa

    //
    CableMaterial(@NotNull String name, double density, double maxStress) {
        this.name = name;
        this.density = density;
        this.maxStress = maxStress;
    }

    //
    public final @NotNull String getName() {
        return name;
    }

    //
    public final double getDensity() {
        return density;
    }

    //
    public final double getMaxStress() {
        return maxStress;
    }

    private static double getPascals(double megaPascals) {
        return megaPascals * Math.pow(10, 6);
    }
}