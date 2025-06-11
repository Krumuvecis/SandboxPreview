package markets2.graphics.mainPanel;

import java.awt.Graphics;

import org.jetbrains.annotations.NotNull;

import static consoleUtils.stringTools.NumberFormatter.doubleToString;

import markets2.resources.ParticularResources;
import markets2.granary.Granary;
import markets2.World;

//
class GranaryInfoPainter implements TextPainterInterface {
    private final @NotNull World world;

    //
    GranaryInfoPainter(@NotNull World world) {
        this.world = world;
    }

    //
    @SuppressWarnings("SameParameterValue")
    void paint(@NotNull Graphics g, int drawX, int drawY) {
        int
                indent = 20,
                columnSeparation = 120;
        double
                colorCriteria_lowDA = Granary.TARGET_DA_RATIO,
                colorCriteria_highDA = Granary.CRITICAL_DA_RATIO,
                colorCriteria_lowLiquidity = Granary.TARGET_LIQUIDITY_LOW,
                colorCriteria_highLiquidity = Granary.TARGET_LIQUIDITY_HIGH;

        @NotNull Granary granary = world.getGranary();
        g.setColor(TEXT_COLOR);
        g.drawString("Granary info:", drawX, drawY);

        double
                food = granary.getInventory().getContinuousResourceAmount(ParticularResources.FOOD),
                foodPrice = granary.getReferencePrice(), //instasell value?
                foodValue = food * foodPrice;
        g.drawString("Food: " + doubleToString(food, 1),
                drawX + indent,
                drawY + TEXT_HEIGHT);
        g.drawString("Value: " + doubleToString(foodValue, 3),
                drawX + indent + columnSeparation,
                drawY + TEXT_HEIGHT);
        g.drawString("At price: " + doubleToString(foodPrice, 3),
                drawX + indent + columnSeparation * 2,
                drawY + TEXT_HEIGHT);

        g.drawString("Money: " + doubleToString(granary.getWallet().getMoney(), 3),
                drawX + indent,
                drawY + TEXT_HEIGHT * 2);
        double liquidity = granary.getLiquidity();
        drawColoredString(g, determineColor(liquidity, colorCriteria_highLiquidity, colorCriteria_lowLiquidity),
                "Liquidity: " + doubleToString(liquidity * 100, 1) + " %",
                drawX + indent + columnSeparation,
                drawY + TEXT_HEIGHT * 2);

        double
                totalAssets = granary.getTotalAssets(),
                debt = granary.getDebt(),
                debtToAssetRatio = granary.getDebtToAssetRatio();
        drawColoredString(g, null,
                "Assets: " + doubleToString(totalAssets, 3),
                drawX + indent,
                drawY + TEXT_HEIGHT * 3);
        drawColoredString(g, null,
                "Debt: " + doubleToString(debt, 3),
                drawX + indent + columnSeparation,
                drawY + TEXT_HEIGHT * 3);
        drawColoredString(g, determineColor(debtToAssetRatio, colorCriteria_highDA, colorCriteria_lowDA),
                "D/A: " + doubleToString(debtToAssetRatio * 100, 1) + " %",
                drawX + indent + columnSeparation * 2,
                drawY + TEXT_HEIGHT * 3);
    }
}