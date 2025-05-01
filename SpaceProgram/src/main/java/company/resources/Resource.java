package company.resources;

import org.jetbrains.annotations.NotNull;

import utils.Copyable;
import company.Asset;

//
public abstract class Resource<T extends @NotNull Resource<T>> extends Asset implements Copyable<T> {
    private static final @NotNull String NAME_PREFIX = "Resource";
    private final double amount;

    //
    public Resource(@NotNull String shortName, double amount) {
        super(shortName);
        this.amount = amount;
    }

    //
    @Override
    public final @NotNull String getShortName() {
        return super.getShortName() + ": " + amount;
    }

    //
    @Override
    public @NotNull String getLongName() {
        return NAME_PREFIX + " - " + getShortName();
    }

    //
    public final double getAmount() {
        return amount;
    }
}