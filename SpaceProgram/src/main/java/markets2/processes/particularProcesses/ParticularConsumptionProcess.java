package markets2.processes.particularProcesses;

import org.jetbrains.annotations.NotNull;

import markets2.resources.ResourceSet;
import markets2.processes.AbstractProcess;
import markets2.processes.ConsumptionProcess;

//
@SuppressWarnings("unused")
final class ParticularConsumptionProcess extends AbstractProcess implements ConsumptionProcess {
    private final @NotNull ResourceSet ingredients;

    //
    ParticularConsumptionProcess(@NotNull String name, double duration, @NotNull ResourceSet ingredients) {
        super(name, duration);
        this.ingredients = ingredients;
    }

    //
    @Override
    public @NotNull ResourceSet getIngredients() {
        return ingredients;
    }
}