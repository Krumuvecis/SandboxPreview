package company.resources;

import org.jetbrains.annotations.NotNull;

public class Materials {
    //
    public static class Iron extends Resource<@NotNull Iron> {
        //
        public Iron(double amount) {
            super("Iron (kg)", amount);
        }

        //
        @Override
        public @NotNull Iron copy() {
            return new Iron(getAmount());
        }
    }

    //
    public static class Steel extends Resource<@NotNull Steel> {
        //
        public Steel(double amount) {
            super("Steel (kg)", amount);
        }

        //
        @Override
        public @NotNull Steel copy() {
            return new Steel(getAmount());
        }
    }

    //
    public static class Slag extends Resource<@NotNull Slag> {
        //
        public Slag(double amount) {
            super("Slag (kg)", amount);
        }

        //
        @Override
        public @NotNull Slag copy() {
            return new Slag(getAmount());
        }
    }
}