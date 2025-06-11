package markets2.person.actions;

import java.util.Map;
import java.util.HashMap;

import org.jetbrains.annotations.NotNull;

import common.NamedInterface;
import markets2.resources.ResourceInterface;
import markets2.resources.ContinuousResource;
import markets2.resources.DiscreteResource;
import markets2.resources.ParticularResources;
import markets2.person.Person;

//TODO: finish this
public abstract class PersonActionTemplate implements NamedInterface {
    public static final @NotNull PersonActionTemplate
            GATHER_FOOD = new ResourceProducingActionTemplate_continuous("Gather food") {
                //
                @Override
                public @NotNull Map<@NotNull ContinuousResource, @NotNull Double> getBaseProducts() {
                    return new HashMap<>() {{
                        put(ParticularResources.FOOD, );
                    }};
                }

                //
                @Override
                public @NotNull Double adjustProduct(@NotNull Double product, @NotNull Person person) {
                    return;
                }

                //
                @Override
                public @NotNull PersonAction getNewAction(@NotNull Person person) {
                    return new ProductiveAction.ProductiveAction_continuous.GatherAction_food(this, person);
                }
            },
            GATHER_STICKS = new ResourceProducingActionTemplate_continuous("Gather sticks") {
                //
                @Override
                public @NotNull Map<@NotNull ContinuousResource, @NotNull Double> getBaseProducts() {
                    return new HashMap<>() {{
                        put(ParticularResources.STICKS, );
                    }};
                }

                //
                @Override
                public @NotNull Double adjustProduct(@NotNull Double product, @NotNull Person person) {
                    return;
                }

                //
                @Override
                public @NotNull PersonAction getNewAction(@NotNull Person person) {
                    return new ProductiveAction.ProductiveAction_continuous.GatherAction_sticks(this, person);
                }
            },
            CRAFT_BASKET = new ResourceProducingActionTemplate_discrete("Craft basket") {
                //
                @Override
                public @NotNull Map<@NotNull DiscreteResource, @NotNull Integer> getBaseProducts() {
                    return new HashMap<>() {{
                        put(ParticularResources.BASKET, 1);
                    }};
                }

                //
                @Override
                public @NotNull Integer adjustProduct(@NotNull Integer product, @NotNull Person person) {
                    return product;
                }

                //
                @Override
                public @NotNull PersonAction getNewAction(@NotNull Person person) {
                    return new ProductiveAction.ProductiveAction_discrete.CraftAction_basket(this, person);
                }
            },
            BREED = new PersonActionTemplate("Breed") {
                //
                @Override
                public @NotNull PersonAction getNewAction(@NotNull Person person) {
                    return new BreedAction(this, person);
                }
            };
    private final @NotNull String actionName;

    //
    private PersonActionTemplate(@NotNull String actionName) {
        this.actionName = actionName;
    }

    //
    @Override
    public final @NotNull String getName() {
        return actionName;
    }

    //
    public abstract @NotNull PersonAction getNewAction(@NotNull Person person);

    //
    public static abstract class ResourceProducingActionTemplate<K extends @NotNull ResourceInterface, V extends @NotNull Number>
            extends PersonActionTemplate implements ResourceProducingActionInterface<K, V> {
        //
        ResourceProducingActionTemplate(@NotNull String actionName) {
            super(actionName);
        }

        //TODO: finish this
    }

    //
    public static abstract class ResourceProducingActionTemplate_continuous
            extends ResourceProducingActionTemplate<@NotNull ContinuousResource, @NotNull Double> {
        //
        ResourceProducingActionTemplate_continuous(@NotNull String actionName) {
            super(actionName);
        }

        //TODO: finish this
    }

    //
    public static abstract class ResourceProducingActionTemplate_discrete
            extends ResourceProducingActionTemplate<@NotNull DiscreteResource, @NotNull Integer> {
        //
        ResourceProducingActionTemplate_discrete(@NotNull String actionName) {
            super(actionName);
        }

        //TODO: finish this
    }
}