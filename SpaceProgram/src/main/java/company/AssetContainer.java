package company;

import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import operands.Operable;

//
public class AssetContainer<T extends @NotNull Asset> implements Operable<@NotNull AssetContainer<T>> {
    private final @NotNull List<T> assets;

    //from a list of assets
    public AssetContainer(@Nullable List<T> assets) {
        this.assets = Objects.requireNonNullElse(assets, new ArrayList<>());
    }

    //empty
    public AssetContainer() {
        this((List<T>) null);
    }

    //for copying
    private AssetContainer(@NotNull AssetContainer<T> assetContainer) {
        this(new ArrayList<>() {{
            addAll(assetContainer.getAssets());
        }});
    }

    //creates a new list of the same assets
    @Override
    public @NotNull AssetContainer<T> copy() {
        return new AssetContainer<>(this);
    }

    //
    public final @NotNull List<T> getAssets() {
        return assets;
    }

    //
    @Override
    public final void sum(@NotNull AssetContainer<T> addend) {
        //TODO: finish this
        throw new RuntimeException("Asset container summation not supported yet!");
    }

    //
    @Override
    public final void multiply(double multiplier) {
        //TODO: finish this
        throw new RuntimeException("Asset container multiplication not supported yet!");
    }

    //
    public final void addAsset(T asset) {
        assets.add(asset);
    }

    //for comparison
    public final @NotNull List<T> getMissingAssets(@NotNull AssetContainer<T> target) {
        @NotNull List<T>
                targetAssets = target.getAssets(),
                missingAssets = new ArrayList<>();
        for (T asset : targetAssets) {
            if (!assets.contains(asset)) {
                missingAssets.add(asset);
            }
        }
        return missingAssets;
    }
}