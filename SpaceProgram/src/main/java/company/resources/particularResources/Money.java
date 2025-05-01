package company.resources.particularResources;

import company.resources.Resource;
import org.jetbrains.annotations.NotNull;

//
public class Money extends Resource<@NotNull Money> {
    //
    public Money(double amount) {
        super("Money (€)", amount);
    }

    //
    @Override
    public @NotNull Money copy() {
        return new Money(getAmount());
    }
}