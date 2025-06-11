package markets2.resources;

import org.jetbrains.annotations.NotNull;

//
@SuppressWarnings("unused")
public final class ParticularResources {
    public static final @NotNull ContinuousResource
            FOOD = new ContinuousResource("Food"),
            STICKS = new ContinuousResource("Sticks");
    public static final @NotNull DiscreteResource
            BASKET = new DiscreteResource("Basket", 1);
}