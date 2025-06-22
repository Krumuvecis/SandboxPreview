package markets2.processes.particularProcesses;

import org.jetbrains.annotations.NotNull;

import markets2.resources.ParticularResources;
import markets2.resources.ResourceAmount.DiscreteResourceAmount;
import markets2.resources.ResourceAmount.ContinuousResourceAmount;
import markets2.resources.ResourceSet;
import markets2.processes.ProductionProcess;
import markets2.processes.FullProcess;

//
public final class ParticularProcesses {
    public static final @NotNull ProductionProcess
            GATHER_FOOD = new ParticularProductionProcess("Gather food", 1, new ResourceSet() {{
                put(new ContinuousResourceAmount(ParticularResources.FOOD, 1.0));
            }}),
            GATHER_STICKS = new ParticularProductionProcess("Gather sticks", 1, new ResourceSet() {{
                put(new ContinuousResourceAmount(ParticularResources.STICKS, 1.0));
            }}),
            GATHER_ALL = new ParticularProductionProcess("Gather all", 1, new ResourceSet() {{
                put(new ContinuousResourceAmount(ParticularResources.FOOD, 1.0));
                put(new ContinuousResourceAmount(ParticularResources.STICKS, 1.0));
            }});
    public static final @NotNull FullProcess
            CRAFT_BASKET = new ParticularFullProcess("Craft basket", 1,
                    new ResourceSet() {{
                        put(new ContinuousResourceAmount(ParticularResources.STICKS, 1.0));
                    }},
                    new ResourceSet() {{
                        put(new DiscreteResourceAmount(ParticularResources.BASKET, 1));
                    }});
}