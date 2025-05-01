package company.facilities;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import company.Asset;
import company.resources.Resource;

//
public abstract class FacilityAsset extends Asset {
    private static final @NotNull String NAME_PREFIX = "Facility";

    //
    public FacilityAsset(@NotNull String shortName) {
        super(shortName);
    }

    //
    @Override
    public final @NotNull String getShortName() {
        return super.getShortName();
    }

    //
    @Override
    public final @NotNull String getLongName() {
        return NAME_PREFIX + " - " + getShortName();
    }

    //
    public abstract @NotNull List<@NotNull Resource<?>> getSetupCosts();

    //
    public abstract @NotNull List<@NotNull Resource<?>> getResellValue();
}