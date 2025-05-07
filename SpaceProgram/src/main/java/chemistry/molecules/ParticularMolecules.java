package chemistry.molecules;

import java.util.EnumMap;
import java.util.Map;

import org.jetbrains.annotations.NotNull;

import chemistry.Element;
import org.jetbrains.annotations.Nullable;

//
@SuppressWarnings("unused")
public final class ParticularMolecules {
    public static final @NotNull Molecule
            WATER = new Molecule("Water", new EnumMap<>(Element.class) {{
                put(Element.HYDROGEN, 2);
                put(Element.OXYGEN, 1);
            }}),
            TEST_SALT_1 = new Molecule(new EnumMap<>(Element.class) {{
                put(Element.URANIUM, 1);
                put(Element.FLUORINE, 6);
            }});
    private static final @NotNull Molecule
            HYDROGEN_CHLORIDE = new Molecule("Hydrogen chloride", new EnumMap<>(Element.class) {{
                put(Element.HYDROGEN, 1);
                put(Element.CHLORINE, 1);
            }});
    private static final @NotNull Map<@NotNull Element, @NotNull Molecule>
            OXIDES = new EnumMap<>(Element.class) {{
                /*put(Element.CARBON, new Molecule("Carbon monoxide", new EnumMap<>(Element.class) {{
                    put(Element.CARBON, 1);
                    put(Element.OXYGEN, 1);
                }}));*/
                //TODO: somehow add monoxides/pentoxides, etc
                put(Element.CARBON, new Molecule("Carbon dioxide", new EnumMap<>(Element.class) {{
                    put(Element.CARBON, 1);
                    put(Element.OXYGEN, 2);
                }}));
                put(Element.NITROGEN, new Molecule("Nitrogen dioxide", new EnumMap<>(Element.class) {{
                    put(Element.NITROGEN, 1);
                    put(Element.OXYGEN, 2);
                }}));
                put(Element.SODIUM, new Molecule("Sodium oxide", new EnumMap<>(Element.class) {{
                    put(Element.SODIUM, 2);
                    put(Element.OXYGEN, 1);
                }}));
                //add more oxides here
            }},
            HYDRIDES = new EnumMap<>(Element.class) {{
                put(Element.CARBON, new Molecule("Methane", new EnumMap<>(Element.class) {{
                    put(Element.CARBON, 1);
                    put(Element.HYDROGEN, 4);
                }}));
                put(Element.NITROGEN, new Molecule("Ammonia", new EnumMap<>(Element.class) {{
                    put(Element.NITROGEN, 1);
                    put(Element.HYDROGEN, 3);
                }}));
                put(Element.CHLORINE, HYDROGEN_CHLORIDE);
                //add more hydrides here
            }},
            CHLORIDES = new EnumMap<>(Element.class) {{
                put(Element.HYDROGEN, HYDROGEN_CHLORIDE);
                put(Element.SODIUM, new Molecule("Sodium chloride", new EnumMap<>(Element.class) {{
                    put(Element.SODIUM, 1);
                    put(Element.CHLORINE, 1);
                }}));
                //add more chlorides here
            }},
            NITRIDES = new EnumMap<>(Element.class) {{
                //add nitrides here
            }};

    //returns null, if such molecule not defined
    public static @Nullable Molecule getOxide(@NotNull Element element) {
        return OXIDES.get(element);
    }

    //returns null, if such molecule not defined
    public static @Nullable Molecule getHydride(@NotNull Element element) {
        return HYDRIDES.get(element);
    }

    //returns null, if such molecule not defined
    public static @Nullable Molecule getChloride(@NotNull Element element) {
        return CHLORIDES.get(element);
    }

    //returns null, if such molecule not defined
    public static @Nullable Molecule getNitride(@NotNull Element element) {
        return NITRIDES.get(element);
    }
}