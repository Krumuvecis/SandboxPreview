package chemistry.substance;

import java.util.HashMap;

import org.jetbrains.annotations.NotNull;

import chemistry.Element;
import chemistry.molecules.ElementalMolecules;
import chemistry.molecules.ParticularMolecules;

//
@SuppressWarnings("unused")
public final class ParticularSubstances {
    public static final @NotNull Substance
            HYDROGEN_GAS = new Substance("Hydrogen gas", new HashMap<>() {{
                put(ElementalMolecules.getElementalMolecule(Element.HYDROGEN), 1.0);
            }}, SubstancePhase.GAS),
            OXYGEN_GAS = new Substance("Oxygen gas", new HashMap<>() {{
                put(ElementalMolecules.getElementalMolecule(Element.OXYGEN), 1.0);
            }}, SubstancePhase.GAS),
            WATER_VAPOR = new Substance("Water vapor (gas)", new HashMap<>() {{
                put(ParticularMolecules.WATER, 1.0);
            }}, SubstancePhase.GAS),
            WATER_DISTILLED = new Substance("Distilled water", new HashMap<>() {{
                put(ParticularMolecules.WATER, 1.0);
            }}, SubstancePhase.LIQUID);
}