package markets2.resources;

import org.jetbrains.annotations.NotNull;

//
public final class ParticularResources {
    public static final @NotNull ContinuousResource
            FOOD = new ContinuousResource("Food", 1),
            STICKS = new ContinuousResource("Sticks", 1);
    public static final @NotNull DiscreteResource
            BASKET = new DiscreteResource("Basket", 1, 1);
}