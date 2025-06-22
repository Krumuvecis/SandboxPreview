package markets2.processes.particularProcesses;

import org.jetbrains.annotations.NotNull;

import markets2.resources.ResourceSet;
import markets2.processes.AbstractProcess;
import markets2.processes.FullProcess;

//
final class ParticularFullProcess extends AbstractProcess implements FullProcess {
    private final @NotNull ResourceSet
            ingredients,
            products;

    //
    ParticularFullProcess(@NotNull String name, double duration,
                        @NotNull ResourceSet ingredients, @NotNull ResourceSet products) {
        super(name, duration);
        this.ingredients = ingredients;
        this.products = products;
    }

    //
    @Override
    public @NotNull ResourceSet getIngredients() {
        return ingredients;
    }

    //
    @Override
    public @NotNull ResourceSet getProducts() {
        return products;
    }
}