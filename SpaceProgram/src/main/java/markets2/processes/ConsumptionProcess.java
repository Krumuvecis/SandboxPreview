package markets2.processes;

import org.jetbrains.annotations.NotNull;

import markets2.resources.ResourceSet;

//
public interface ConsumptionProcess extends ProcessInterface {
    //
    @NotNull ResourceSet getIngredients();
}