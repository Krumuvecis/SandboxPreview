package chemistry.molecules;

import java.util.Map;
import java.util.EnumMap;

import org.jetbrains.annotations.NotNull;

import chemistry.Element;

//
@SuppressWarnings("unused")
public class ElementalMolecules {
    private static final @NotNull Map<@NotNull Element, @NotNull Integer> polyatomicMolecules = new EnumMap<>(Element.class) {{
        put(Element.HYDROGEN, 2);

        put(Element.NITROGEN, 2);
        put(Element.OXYGEN, 2);

        put(Element.PHOSPHORUS, 4);
        put(Element.SULFUR, 8);
        put(Element.CHLORINE, 2);
    }};

    private static final @NotNull Map<@NotNull Element, @NotNull HomonuclearMolecule> elementalMolecules = new EnumMap<>(Element.class) {{
        for (@NotNull Element element : polyatomicMolecules.keySet()) {
            put(element, new HomonuclearMolecule(element, polyatomicMolecules.get(element)));
        }
        for (@NotNull Element element : Element.values()) {
            if (!containsKey(element)) {
                put(element, new MonatomicMolecule(element));
            }
        }
    }};

    //
    public static @NotNull HomonuclearMolecule getElementalMolecule(@NotNull Element element) {
        return elementalMolecules.get(element);
    }

    //
    public static class HomonuclearMolecule extends Molecule {
        public HomonuclearMolecule(@NotNull Element element, int atomCount) {
            super("Elemental " + element.getName(), new EnumMap<>(Element.class) {{
                put(element, atomCount);
            }});
        }
    }

    //
    public static class MonatomicMolecule extends HomonuclearMolecule {
        //
        public MonatomicMolecule(@NotNull Element element) {
            super(element, 1);
        }
    }
}