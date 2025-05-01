package company.facilities;

import java.util.List;
import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;

import company.resources.Resource;
import company.resources.particularResources.Money;

//
class SimpleFacility extends FacilityAsset {
    private final @NotNull List<@NotNull Resource<?>>
            setupCosts,
            resellValue;

    //
    SimpleFacility(@NotNull String shortName, double setupPrice, double resellValueRatio) {
        super(shortName);
        setupCosts = new ArrayList<>() {{
            add(new Money(setupPrice));
        }};
        resellValue = new ArrayList<>() {{
            add(new Money(setupPrice * resellValueRatio));
        }};
    }

    //
    @Override
    public final @NotNull List<@NotNull Resource<?>> getSetupCosts() {
        return setupCosts;
    }

    //
    @Override
    public final @NotNull List<@NotNull Resource<?>> getResellValue() {
        return resellValue;
    }
}