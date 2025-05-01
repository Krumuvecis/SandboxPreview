package chemistry;

import java.util.Set;
import java.util.List;

import org.jetbrains.annotations.NotNull;

//for containing elements
public interface SuperElementalInterface extends AtomicInterface {
    //returns 0, if element not found
    double getElementMolarFraction(@NotNull Element element);

    //returns 0, if element not found
    double getElementMassFraction(@NotNull Element element);

    //
    @NotNull Set<@NotNull Element> getAllElements();

    //
    @NotNull List<@NotNull ElementFraction> getElementsByMolarFraction();

    //
    @NotNull List<@NotNull ElementFraction> getElementsByMassFraction();

    //
    record ElementFraction(@NotNull Element element, double fraction) {}
}