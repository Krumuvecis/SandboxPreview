package markets2.resources;

import org.jetbrains.annotations.NotNull;

import utils.Copyable;

//
public abstract class ResourceAmount<T extends @NotNull ResourceInterface, V extends @NotNull Number>
        implements Copyable<@NotNull ResourceAmount<T, V>> {
    private final T resource;
    private V amount;

    //
    ResourceAmount(T resource, V amount) {
        this.resource = resource;
        this.amount = amount;
    }

    //
    public final T getResource() {
        return resource;
    }

    //
    final V getAmount() {
        return amount;
    }

    //
    public abstract double getMass();

    //
    public abstract double getVolume();

    //for internal use
    final void setAmount(V amount) {
        this.amount = amount;
    }

    //
    public abstract void add(V addend);

    //returns unsubtracted remainder, if insufficient
    public abstract V subtract(V subtrahend);

    //
    public static final class DiscreteResourceAmount extends ResourceAmount<@NotNull DiscreteResource, @NotNull Integer> {
        //
        public DiscreteResourceAmount(@NotNull DiscreteResource resource, @NotNull Integer amount) {
            super(resource, amount);
        }

        //
        @Override
        public @NotNull DiscreteResourceAmount copy() {
            return new DiscreteResourceAmount(getResource(), getAmount());
        }

        //
        public int getCount() {
            return getAmount();
        }

        //
        @Override
        public double getMass() {
            return getCount() * getResource().getMass();
        }

        //
        @Override
        public double getVolume() {
            return getCount() * getResource().getVolume();
        }

        //supports only non-negative
        @Override
        public void add(@NotNull Integer addend) {
            if (addend > 0) {
                setAmount(getAmount() + addend);
            }
        }

        //returns unsubtracted remainder, if insufficient
        @Override
        public @NotNull Integer subtract(@NotNull Integer subtrahend) {
            int
                    minuend = getAmount(),
                    cappedDelta = Math.max(0, Math.min(minuend, subtrahend));
            setAmount(minuend - cappedDelta); //result
            return subtrahend - cappedDelta; //remainder
        }
    }

    //
    public static final class ContinuousResourceAmount extends ResourceAmount<@NotNull ContinuousResource, @NotNull Double> {
        //
        public ContinuousResourceAmount(@NotNull ContinuousResource resource, @NotNull Double amount) {
            super(resource, amount);
        }

        //
        @Override
        public @NotNull ContinuousResourceAmount copy() {
            return new ContinuousResourceAmount(getResource(), getAmount());
        }

        //
        @Override
        public double getMass() {
            return getAmount();
        }

        //
        @Override
        public double getVolume() {
            return getResource().getVolume(getMass());
        }

        //supports only non-negative
        @Override
        public void add(@NotNull Double addend) {
            if (addend > 0) {
                setAmount(getAmount() + addend);
            }
        }

        //returns unsubtracted remainder, if insufficient
        @Override
        public @NotNull Double subtract(@NotNull Double subtrahend) {
            double
                    minuend = getAmount(),
                    cappedDelta = Math.max(0, Math.min(minuend, subtrahend));
            setAmount(minuend - cappedDelta); //result
            return subtrahend - cappedDelta; //remainder
        }
    }
}