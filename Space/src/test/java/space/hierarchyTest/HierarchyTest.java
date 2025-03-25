package space.hierarchyTest;

import static consoleUtils.SimplePrinting.printLine;

import space.orbitalBodies.MajorOrbitalBody;
import space.orbitalBodies.StandardSolarSystem;

//
public class HierarchyTest {
    //
    public static void main(String[] args) {
        analyze(StandardSolarSystem.SUN);
        analyze(StandardSolarSystem.EARTH);
        analyze(StandardSolarSystem.MOON);
    }

    private static void analyze(MajorOrbitalBody body) {
        printLine("Hierarchy below " + body.getName() + ":");
        (new HierarchyAnalyzer(body)).print();
        printLine("");
    }
}