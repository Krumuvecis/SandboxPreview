package humanGroup;

import org.jetbrains.annotations.NotNull;

import humanRequirements.HumanRequirementInterface;
import humanRequirements.HumanGroupRequirements;

//
public class HumanGroup {
    private @NotNull HumanRequirementInterface humanRequirements;

    //
    HumanGroup() {
        humanRequirements = new HumanGroupRequirements(this);
    }
}