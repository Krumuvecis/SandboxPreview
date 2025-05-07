package markets2;

import org.jetbrains.annotations.NotNull;

import common.NamedInterface;
import markets2.person.PersonInventory;

//
public interface TraderInterface extends NamedInterface {
    //
    @NotNull PersonInventory getInventory();
}