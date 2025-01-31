package rotors.modularHabitat;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import dimensions.distance.Distance;

//contains a stack of modules
public final class HabitatSectionCell {
    public static final @NotNull Distance CELL_SIZE = new Distance(1.5);
    private @Nullable HabitatModule module;

    //
    public HabitatSectionCell() {}

    //gets called, when the corresponding parent section changes its index
    void incrementSectionIndex() {
        //TODO: finish this
    }

    //gets called, when the corresponding parent section changes its index
    void decrementSectionIndex() {
        //TODO: finish this
    }

    //
    public @Nullable HabitatModule getModule() {
        return module;
    }

    //
    public void setModule(@Nullable HabitatModule module) throws CellTakenException {
        if (module != null && this.module != null) {
            throw new CellTakenException();
        } else {
            this.module = module;
        }
    }

    //
    public void removeModule() {
        try {
            setModule(null);
        } catch (@NotNull CellTakenException ignored) {}
    }

    //
    public static final class CellTakenException extends Exception {
        CellTakenException() {
            super("Cell taken");
        }
    }
}