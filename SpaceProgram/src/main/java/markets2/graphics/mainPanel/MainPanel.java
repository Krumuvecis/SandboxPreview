package markets2.graphics.mainPanel;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

import org.jetbrains.annotations.NotNull;

import markets2.World;

//
public final class MainPanel extends JPanel implements TextPainterInterface {
    private static final @NotNull Color BACKGROUND_COLOR = new Color(0, 0, 0, 0);
    private static final int TEXT_HEIGHT = 15;
    private final @NotNull WorldInfoPainter worldInfoPainter;
    private final @NotNull GranaryInfoPainter granaryInfoPainter;
    private final @NotNull MarketInfoPainter marketInfoPainter;
    private final @NotNull PeopleInfoPainter peopleInfoPainter;

    //
    public MainPanel(@NotNull World world) {
        super();
        setBackground(BACKGROUND_COLOR);
        worldInfoPainter = new WorldInfoPainter(world);
        granaryInfoPainter = new GranaryInfoPainter(world);
        marketInfoPainter = new MarketInfoPainter(world.getMarket());
        peopleInfoPainter = new PeopleInfoPainter(world);
    }

    //
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int
                leftOffset = 30,
                topOffset = 30,
                worldInfoLines = 4,
                granaryInfoLines = 5,
                leftSideSize = 530;
        worldInfoPainter.paint(g, leftOffset, topOffset);
        granaryInfoPainter.paint(g, leftOffset, topOffset + worldInfoLines * TEXT_HEIGHT);
        marketInfoPainter.paint(g, leftOffset, topOffset + (worldInfoLines + granaryInfoLines) * TEXT_HEIGHT);
        peopleInfoPainter.paint(g, leftOffset + leftSideSize, topOffset);
    }
}