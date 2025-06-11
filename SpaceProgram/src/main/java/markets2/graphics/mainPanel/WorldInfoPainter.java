package markets2.graphics.mainPanel;

import java.awt.Graphics;

import org.jetbrains.annotations.NotNull;

import static consoleUtils.stringTools.NumberFormatter.doubleToString;

import markets2.World;

//
class WorldInfoPainter implements TextPainterInterface {
    private final @NotNull World world;

    //
    WorldInfoPainter(@NotNull World world) {
        this.world = world;
    }

    //
    @SuppressWarnings("SameParameterValue")
    void paint(@NotNull Graphics g, int drawX, int drawY) {
        int
                columnSeparation = 150,
                yearLength = 40,
                elapsedTime = world.getElapsedTime(),
                years = Math.floorDiv(elapsedTime, yearLength),
                days = elapsedTime - years * yearLength;
        drawColoredString(g, null, "Elapsed time: " + world.getElapsedTime(), drawX, drawY);
        drawColoredString(g, null, "Y: " + years + ", D: " + days, drawX + columnSeparation, drawY);

        double weatherFertility = world.getWeatherFertility();
        drawColoredString(g, determineColor(weatherFertility, 0.2, 0.8),
                "Weather fertility: " + doubleToString(weatherFertility, 2),
                drawX,
                drawY + TEXT_HEIGHT);
        drawColoredString(g, null,
                "Land per person: " + doubleToString(world.getLandPerPerson(), 1),
                drawX + columnSeparation,
                drawY + TEXT_HEIGHT);
    }
}