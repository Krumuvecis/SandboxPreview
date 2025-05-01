package chemistry.molecules;

import java.util.EnumMap;

import chemistry.Element;

//
@SuppressWarnings("unused")
public final class ParticularMolecules {
    //
    public static final class DiHydrogen extends Molecule {
        //
        public DiHydrogen() {
            super("Di-hydrogen", new EnumMap<>(Element.class) {{
                put(Element.HYDROGEN, 2);
            }});
        }
    }

    //
    public static final class DiOxygen extends Molecule {
        //
        public DiOxygen() {
            super("Di-oxygen", new EnumMap<>(Element.class) {{
                put(Element.OXYGEN, 2);
            }});
        }
    }

    //
    public static final class Water extends Molecule {
        //
        public Water() {
            super("Water", new EnumMap<>(Element.class) {{
                put(Element.HYDROGEN, 2);
                put(Element.OXYGEN, 1);
            }});
        }
    }

    //
    public static final class SodiumChloride extends Molecule {
        //
        public SodiumChloride() {
            super("Sodium chloride", new EnumMap<>(Element.class) {{
                put(Element.SODIUM, 1);
                put(Element.CHLORINE, 1);
            }});
        }
    }

    //unnamed test molecule
    public static final class TestMolecule extends Molecule {
        //
        public TestMolecule() {
            super(new EnumMap<>(Element.class) {{
                put(Element.URANIUM, 1);
                put(Element.FLUORINE, 6);
            }});
        }
    }
}