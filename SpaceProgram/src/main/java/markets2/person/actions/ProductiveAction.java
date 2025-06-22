package markets2.person.actions;

import java.util.Random;

import markets2.person.actions.PersonActionTemplate.ResourceProducingActionTemplate;
import markets2.person.actions.PersonActionTemplate.ResourceProducingActionTemplate_continuous;
import markets2.person.actions.PersonActionTemplate.ResourceProducingActionTemplate_discrete;
import markets2.resources.ResourceInterface;
import org.jetbrains.annotations.NotNull;

import markets2.resources.ContinuousResource;
import markets2.resources.DiscreteResource;
import markets2.resources.ParticularResources;
import markets2.skills.SkillType;
import markets2.skills.collections.PersonSkills;
import markets2.person.Person;

//
public abstract class ProductiveAction<
            K extends @NotNull ResourceInterface,
            V extends @NotNull Number,
            T extends @NotNull ResourceProducingActionTemplate<K, V>>
        extends PersonAction<T> implements ResourceProducingActionInterface<K, V> {
    //
    public ProductiveAction(T actionTemplate, @NotNull Person person, int duration) {
        super(actionTemplate, person, duration);
    }

    //continuous feedstock, continuous products
    public static abstract class ProductiveAction_continuous extends ProductiveAction<
            @NotNull ContinuousResource, @NotNull Double, @NotNull ResourceProducingActionTemplate_continuous> {
        //
        public ProductiveAction_continuous(@NotNull ResourceProducingActionTemplate_continuous actionTemplate,
                                           @NotNull Person person, int duration) {
            super(actionTemplate, person, duration);
        }

        //TODO: finish this
        public static final class GatherAction_food extends ProductiveAction_continuous {
            private static final @NotNull Random RANDOM = new Random();
            private static final double
                    RANDOM_INEFFICIENCY_RATE = 0.2,
                    BASKET_BREAK_CHANCE = 0.2;
            public static final double ESTIMATED_BASKET_LIFETIME = 1 / BASKET_BREAK_CHANCE;

            //
            public GatherAction_food(@NotNull ResourceProducingActionTemplate_continuous actionTemplate,
                                     @NotNull Person person) {
                super(actionTemplate, person, 1);
            }

            //
            @Override
            public void action() {
                @NotNull Person person = getPerson();
                @NotNull PersonSkills skills = person.getSkills();
                double
                        //skill = skills.getSkill_gatherFood(),
                        maximumYield = person.maximumYield_gatherFood, //already contains skill
                        actualYield = maximumYield * (1 - RANDOM_INEFFICIENCY_RATE * RANDOM.nextDouble());
                person.getInventory().addContinuousResource(ParticularResources.FOOD, actualYield);

                skills.learnSkill(SkillType.GATHERING);

                if (person.hasBasket() && RANDOM.nextDouble() < BASKET_BREAK_CHANCE) {
                    person.getInventory().subtractDiscreteResource(ParticularResources.BASKET, 1);
                }
            }
        }

        //TODO: finish this
        public static final class GatherAction_sticks extends ProductiveAction_continuous {
            private static final @NotNull Random RANDOM = new Random();
            private static final double RANDOM_INEFFICIENCY_RATE = 0.2;

            //
            public GatherAction_sticks(@NotNull ResourceProducingActionTemplate_continuous actionTemplate,
                                       @NotNull Person person) {
                super(actionTemplate, person, 1);
            }

            //
            @Override
            public void action() {
                @NotNull Person person = getPerson();
                @NotNull PersonSkills skills = person.getSkills();
                double
                        //skill = skills.getSkill_gatherSticks(),
                        maximumYield = person.maximumYield_gatherSticks, //already contains skill
                        actualYield = maximumYield * (1 - RANDOM_INEFFICIENCY_RATE * RANDOM.nextDouble());
                person.getInventory().addContinuousResource(ParticularResources.STICKS, actualYield);

                skills.learnSkill(SkillType.GATHERING);
            }
        }
    }

    //continuous feedstock, discrete products
    public static abstract class ProductiveAction_discrete extends ProductiveAction<
            @NotNull DiscreteResource, @NotNull Integer, @NotNull ResourceProducingActionTemplate_discrete> {
        //
        public ProductiveAction_discrete(@NotNull ResourceProducingActionTemplate_discrete actionTemplate,
                                         @NotNull Person person, int duration) {
            super(actionTemplate, person, duration);
        }

        //TODO: finish this
        public static final class CraftAction_basket extends ProductiveAction_discrete implements ResourceDepletingActionInterface<@NotNull ContinuousResource, @NotNull Double> {
            //
            public CraftAction_basket(@NotNull ResourceProducingActionTemplate_discrete actionTemplate, @NotNull Person person) {
                super(actionTemplate, person, 1);
            }

            //
            @Override
            public void action() {
                @NotNull Person person = getPerson();
                @NotNull PersonSkills skills = person.getSkills();

                //TODO: finish this below

                double
                        //skill = skills.getSkill_craftBasket(),
                        sticksConsumption = person.maximumYield_gatherSticks; //already contains skill
                int yield = 1;

                person.getInventory().subtractContinuousResource(ParticularResources.STICKS, sticksConsumption);
                person.getInventory().addDiscreteResource(ParticularResources.BASKET, yield);

                //TODO: finish this above

                skills.learnSkill(SkillType.CRAFTING);
            }
        }
    }
}