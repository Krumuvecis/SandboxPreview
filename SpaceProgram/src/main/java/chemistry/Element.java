package chemistry;

import org.jetbrains.annotations.NotNull;

import dimensions.mass.MassUnit;
import dimensions.mass.Mass;

//
@SuppressWarnings("unused")
public enum Element implements AtomicInterface {
    //period 1, s-type
    HYDROGEN(1, "H", "Hydrogen", new Mass(1.0080, MassUnit.G)),
    HELIUM(2, "He", "Helium", new Mass(4.0026, MassUnit.G)),

    //period 2, s-type
    LITHIUM(3, "Li", "Lithium", new Mass(6.94, MassUnit.G)),
    BERYLLIUM(4, "Be", "Beryllium", new Mass(9.0122, MassUnit.G)),

    //period 2, p-type
    BORON(5, "B", "Boron", new Mass(10.81, MassUnit.G)),
    CARBON(6, "C", "Carbon", new Mass(12.011, MassUnit.G)),
    NITROGEN(7, "N", "Nitrogen", new Mass(14.007, MassUnit.G)),
    OXYGEN(8, "O", "Oxygen", new Mass(15.999, MassUnit.G)),
    FLUORINE(9, "F", "Fluorine", new Mass(18.998, MassUnit.G)),
    NEON(10, "Ne", "Neon", new Mass(20.180, MassUnit.G)),

    //period 3, s-type
    SODIUM(11, "Na", "Sodium", new Mass(22.990, MassUnit.G)),
    MAGNESIUM(12, "Mg", "Magnesium", new Mass(24.305, MassUnit.G)),

    //period 3, p-type
    ALUMINIUM(13, "Al", "Aluminium", new Mass(26.982, MassUnit.G)),
    Si(14, "Si", "Silicon", new Mass(28.085, MassUnit.G)),
    PHOSPHORUS(15, "P", "Phosphorus", new Mass(30.974, MassUnit.G)),
    SULFUR(16, "S", "Sulfur", new Mass(32.06, MassUnit.G)),
    CHLORINE(17, "Cl", "Chlorine", new Mass(35.45, MassUnit.G)),
    ARGON(18, "Ar", "Argon", new Mass(39.95, MassUnit.G)),

    //period 4, s-type
    POTASSIUM(19, "K", "Potassium", new Mass(39.098, MassUnit.G)),
    CALCIUM(20, "Ca", "Calcium", new Mass(40.078, MassUnit.G)),

    //period 4, d-type
    SCANDIUM(21, "Sc", "Scandium", new Mass(44.956, MassUnit.G)),
    TITANIUM(22, "Ti", "Titanium", new Mass(47.867, MassUnit.G)),
    VANADIUM(23, "V", "Vanadium", new Mass(50.942, MassUnit.G)),
    CHROMIUM(24, "Cr", "Chromium", new Mass(51.996, MassUnit.G)),
    MANGANESE(25, "Mn", "Manganese", new Mass(54.938, MassUnit.G)),
    IRON(26, "Fe", "Iron", new Mass(55.845, MassUnit.G)),
    COBALT(27, "Co", "Cobalt", new Mass(58.933, MassUnit.G)),
    NICKEL(28, "Ni", "Nickel", new Mass(58.693, MassUnit.G)),
    COPPER(29, "Cu", "Copper", new Mass(63.546, MassUnit.G)),
    ZINC(30, "Zn", "Zic", new Mass(65.38, MassUnit.G)),

    //period 4, p-type
    GALLIUM(31, "Ga", "Gallium", new Mass(69.723, MassUnit.G)),
    GERMANIUM(32, "Ge", "Germanium", new Mass(72.630, MassUnit.G)),
    ARSENIC(33, "As", "Arsenic", new Mass(74.922, MassUnit.G)),
    SELENIUM(34, "Se", "Selenium", new Mass(78.971, MassUnit.G)),
    BROMINE(35, "Br", "Bromine", new Mass(79.904, MassUnit.G)),
    KRYPTON(36, "Kr", "Krypton", new Mass(83.798, MassUnit.G)),

    //period 5, s-type
    RUBIDIUM(37, "Rb", "Rubidium", new Mass(85.468, MassUnit.G)),
    STRONTIUM(38, "Sr", "Strontium", new Mass(87.62, MassUnit.G)),

    //period 5, d-type
    YTTRIUM(39, "Y", "Yttrium", new Mass(88.906, MassUnit.G)),
    ZIRCONIUM(40, "Zr", "Zirconium", new Mass(91.224, MassUnit.G)),
    NIOBIUM(41, "Nb", "Niobium", new Mass(92.906, MassUnit.G)),
    MOLYBDENUM(42, "Mo", "Molybdenum", new Mass(95.95, MassUnit.G)),
    TECHNETIUM(43, "Tc", "Technetium", new Mass(97, MassUnit.G)),
    RUTHENIUM(44, "Ru", "Ruthenium", new Mass(101.07, MassUnit.G)),
    RHODIUM(45, "Rh", "Rhodium", new Mass(102.91, MassUnit.G)),
    PALLADIUM(46, "Pd", "Palladium", new Mass(106.42, MassUnit.G)),
    SILVER(47, "Ag", "Silver", new Mass(107.87, MassUnit.G)),
    CADMIUM(48, "Cd", "Cadmium", new Mass(112.41, MassUnit.G)),

    //period 5, p-type
    INDIUM(49, "In", "Indium", new Mass(114.82, MassUnit.G)),
    TIN(50, "Sn", "Tin", new Mass(118.71, MassUnit.G)),
    ANTIMONY(51, "Sb", "Antimony", new Mass(121.76, MassUnit.G)),
    TELLURIUM(52, "Te", "Tellurium", new Mass(127.60, MassUnit.G)),
    IODINE(53, "I", "Iodine", new Mass(126.90, MassUnit.G)),
    XENON(54, "Xe", "Xenon", new Mass(131.29, MassUnit.G)),

    //period 6, s-type
    CAESIUM(55, "Cs", "Caesium", new Mass(132.91, MassUnit.G)),
    BARIUM(56, "Ba", "Barium", new Mass(137.33, MassUnit.G)),

    //period 6, f-type, lanthanides
    LANTHANUM(57, "La", "Lanthanum", new Mass(138.91, MassUnit.G)),
    CERIUM(58, "Ce", "Cerium", new Mass(140.12, MassUnit.G)),
    PRASEODYMIUM(59, "Pr", "Praseodymium", new Mass(140.91, MassUnit.G)),
    NEODYMIUM(60, "Nd", "Neodymium", new Mass(144.24, MassUnit.G)),
    PROMETHIUM(61, "Pm", "Promethium", new Mass(145, MassUnit.G)),
    SAMARIUM(62, "Sm", "Samarium", new Mass(150.36, MassUnit.G)),
    EUROPIUM(63, "Eu", "Europium", new Mass(151.96, MassUnit.G)),
    GADOLINIUM(64, "Gd", "Gadolinium", new Mass(157.25, MassUnit.G)),
    TERBIUM(65, "Tb", "Terbium", new Mass(158.93, MassUnit.G)),
    DYSPROSIUM(66, "Dy", "Dysprosium", new Mass(162.50, MassUnit.G)),
    HOLMIUM(67, "Ho", "Holmium", new Mass(164.93, MassUnit.G)),
    ERBIUM(68, "Er", "Erbium", new Mass(167.26, MassUnit.G)),
    THULIUM(69, "Tm", "Thulium", new Mass(168.93, MassUnit.G)),
    YTTERBIUM(70, "Yb", "Ytterbium", new Mass(173.05, MassUnit.G)),

    //period 6, d-type
    LUTETIUM(71, "Lu", "Lutetium", new Mass(174.97, MassUnit.G)),
    HAFNIUM(72, "Hf", "Hafnium", new Mass(178.49, MassUnit.G)),
    TANTALUM(73, "Ta", "Tantalum", new Mass(180.95, MassUnit.G)),
    TUNGSTEN(74, "W", "Tungsten", new Mass(183.84, MassUnit.G)),
    RHENIUM(75, "Re", "Rhenium", new Mass(186.21, MassUnit.G)),
    OSMIUM(76, "Os", "Osmium", new Mass(190.23, MassUnit.G)),
    IRIDIUM(77, "Ir", "Iridium", new Mass(192.22, MassUnit.G)),
    PLATINUM(78, "Pt", "Platinum", new Mass(195.08, MassUnit.G)),
    GOLD(79, "Au", "Gold", new Mass(196.97, MassUnit.G)),
    MERCURY(80, "Hg", "Mercury", new Mass(200.59, MassUnit.G)),

    //period 6, p-type
    THALLIUM(81, "Tl", "Thallium", new Mass(204.38, MassUnit.G)),
    LEAD(82, "Pb", "Lead", new Mass(207.2, MassUnit.G)),
    BISMUTH(83, "Bi", "Bismuth", new Mass(208.98, MassUnit.G)),
    POLONIUM(84, "Po", "Polonium", new Mass(209, MassUnit.G)),
    ASTATINE(85, "At", "Astatine", new Mass(210, MassUnit.G)),
    RADON(86, "Ra", "Radon", new Mass(222, MassUnit.G)),

    //period 7, s-type
    FRANCIUM(87, "Fr", "Francium", new Mass(223, MassUnit.G)),
    RADIUM(88, "Ra", "Radium", new Mass(226, MassUnit.G)),

    //period 7, f-type, actinides
    ACTINIUM(89, "Ac", "Actinium", new Mass(227, MassUnit.G)),
    THORIUM(90, "Th", "Thorium", new Mass(232.04, MassUnit.G)),
    PROTACTINIUM(91, "Pa", "Protactinium", new Mass(231.04, MassUnit.G)),
    URANIUM(92, "U", "Uranium", new Mass(238.03, MassUnit.G)),
    NEPTUNIUM(93, "Np", "Neptunium", new Mass(237, MassUnit.G)),
    PLUTONIUM(94, "Pu", "Plutonium", new Mass(244, MassUnit.G)),
    AMERICIUM(95, "Am", "Americium", new Mass(243, MassUnit.G)),
    CURIUM(96, "Cm", "Curium", new Mass(247, MassUnit.G)),
    BERKELIUM(97, "Bk", "Berkelium", new Mass(247, MassUnit.G)),
    CALIFORNIUM(98, "Cf", "Californium", new Mass(251, MassUnit.G)),
    EINSTEINIUM(99, "Es", "Einsteinium", new Mass(252, MassUnit.G)),
    FERMIUM(100, "Fm", "Fermium", new Mass(257, MassUnit.G)),
    MENDELEVIUM(101, "Md", "Mendelevium", new Mass(258, MassUnit.G)),
    NOBELIUM(102, "No", "Nobelium", new Mass(259, MassUnit.G)),

    //period 7, d-type
    LAWRENCIUM(103, "Lr", "Lawrencium", new Mass(266, MassUnit.G)),
    RUTHERFORDIUM(104, "Rf", "Rutherfordium", new Mass(267, MassUnit.G)),
    DUBNIUM(105, "Db", "Dubnium", new Mass(268, MassUnit.G)),
    SEABORGIUM(106, "Sg", "Seaborgium", new Mass(269, MassUnit.G)),
    BOHRIUM(107, "Bh", "Bohrium", new Mass(270, MassUnit.G)),
    HASSIUM(108, "Hs", "Hassium", new Mass(271, MassUnit.G)),
    MEITNERIUM(109, "Mt", "Meitnerium", new Mass(278, MassUnit.G)),
    DARMSTADTIUM(110, "Ds", "Darmstadtium", new Mass(281, MassUnit.G)),
    ROENTGENIUM(111, "Rg", "Roentgenium", new Mass(282, MassUnit.G)),
    COPERNICIUM(112, "Cn", "Copernicium", new Mass(285, MassUnit.G)),

    //period 7, p-type
    NIHONIUM(113, "Nh", "Nihonium", new Mass(286, MassUnit.G)),
    FLEROVIUM(114, "Fl", "Flerovium", new Mass(289, MassUnit.G)),
    MOSCOVIUM(115, "Mc", "Moscovium", new Mass(290, MassUnit.G)),
    LIVERMORIUM(116, "Lv", "Livermorium", new Mass(293, MassUnit.G)),
    TENNESSINE(117, "Ts", "Tennessine", new Mass(294, MassUnit.G)),
    OGANESSON(118, "Og", "Oganesson", new Mass(294, MassUnit.G));

    private final int atomicNumber;
    private final @NotNull String
            symbol,
            name;
    private final @NotNull Mass atomicMass; //per mole; average

    Element(int atomicNumber, @NotNull String symbol, @NotNull String name, @NotNull Mass atomicMass) {
        this.atomicNumber = atomicNumber;
        this.symbol = symbol;
        this.name = name;
        this.atomicMass = atomicMass;
    }

    //
    public int getAtomicNumber() {
        return atomicNumber;
    }

    //
    public final @NotNull String getSymbol() {
        return symbol;
    }

    //
    @Override
    public final @NotNull String getName() {
        return name;
    }

    //
    @Override
    public final @NotNull Mass getAtomicMass() {
        return atomicMass;
    }
}