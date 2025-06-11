package markets2.graphics.mainPanel;

import java.util.List;
import java.awt.Graphics;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static consoleUtils.stringTools.NumberFormatter.doubleToString;

import markets2.resources.ContinuousResource;
import markets2.resources.DiscreteResource;
import markets2.resources.ParticularResources;
import markets2.market.MarketHistoryDataPoint;
import markets2.market.MarketHistory;
import markets2.market.MarketOrder.MarketOrderContinuous;
import markets2.market.MarketOrder.MarketOrderDiscrete;
import markets2.market.SingleResourceMarket.SingleResourceMarketDiscrete;
import markets2.market.SingleResourceMarket.SingleResourceMarketContinuous;
import markets2.market.MultiMarket;

//
class MarketInfoPainter implements TextPainterInterface {
    private static final int
            INDENT = 20,
            INDENT_P = 80,
            INDENT_V = 70,
            COLUMN_SEPARATION = INDENT + INDENT_P + INDENT_V + 80,
            INTER_MARKET_VERTICAL_SEPARATION = 200;
    private final @NotNull MultiMarket multiMarket;

    //
    MarketInfoPainter(@NotNull MultiMarket multiMarket) {
        this.multiMarket = multiMarket;
    }

    //
    @SuppressWarnings("SameParameterValue")
    void paint(@NotNull Graphics g, int drawX, int drawY) {
        paintMarket_food(g, drawX, drawY);
        paintMarket_sticks(g, drawX, drawY + INTER_MARKET_VERTICAL_SEPARATION);
        paintMarket_basket(g, drawX, drawY + INTER_MARKET_VERTICAL_SEPARATION * 2);
    }

    private void paintMarket_food(@NotNull Graphics g, int drawX, int drawY) {
        paintMarket_continuous(g, drawX, drawY, ParticularResources.FOOD);
    }

    private void paintMarket_sticks(@NotNull Graphics g, int drawX, int drawY) {
        paintMarket_continuous(g, drawX, drawY, ParticularResources.STICKS);
    }

    private void paintMarket_basket(@NotNull Graphics g, int drawX, int drawY) {
        paintMarket_discrete(g, drawX, drawY, ParticularResources.BASKET);
    }

    private void paintMarket_continuous(@NotNull Graphics g, int drawX, int drawY,
                                        @NotNull ContinuousResource resource) {
        g.setColor(TEXT_COLOR);
        g.drawString(resource.getName() + " market:", drawX, drawY);

        @NotNull SingleResourceMarketContinuous market = multiMarket.getMarket(resource);

        //print last day's info here (could throw null pointer on first day)
        @NotNull MarketHistory<@NotNull Double> history = market.getHistory();
        @Nullable MarketHistoryDataPoint<@NotNull Double> lastPositiveVolume = history.getLastPositiveVolumeData();
        @NotNull String
                lastPriceString = "Last price: ",
                lastVolumeString = "Last volume: ";
        if (lastPositiveVolume == null) {
            lastPriceString += "-";
            lastVolumeString += "0";
        } else {
            lastPriceString += doubleToString(lastPositiveVolume.getPriceVWA(), 3);
            lastVolumeString += doubleToString(lastPositiveVolume.getVolume(), 1);
        }
        g.drawString(lastPriceString, drawX, drawY + TEXT_HEIGHT);
        g.drawString(lastVolumeString, drawX, drawY + TEXT_HEIGHT * 2);
        int startingLineCount = 4;

        //sell orders
        @NotNull List<@NotNull MarketOrderContinuous> sellOrders = market.getSortedSellOrders();
        g.drawString("Sell:", drawX + INDENT, drawY + TEXT_HEIGHT * startingLineCount);
        int lineCount = startingLineCount + 1;
        for (@NotNull MarketOrderContinuous order : sellOrders) {
            g.drawString(order.getTrader().getName(),
                    drawX + INDENT * 2, drawY + TEXT_HEIGHT * lineCount);
            g.drawString("P: " + doubleToString(order.getPrice(), 3),
                    drawX + INDENT * 2 + INDENT_P, drawY + TEXT_HEIGHT * lineCount);
            g.drawString("V: " + doubleToString(order.getVolume(), 1),
                    drawX + INDENT * 2 + INDENT_P + INDENT_V, drawY + TEXT_HEIGHT * lineCount);
            lineCount ++;
        }

        //buy orders
        @NotNull List<@NotNull MarketOrderContinuous> buyOrders = market.getSortedBuyOrders();
        g.drawString("Buy:", drawX + INDENT + COLUMN_SEPARATION, drawY + TEXT_HEIGHT * startingLineCount);
        lineCount = startingLineCount + 1;
        for (@NotNull MarketOrderContinuous order : buyOrders) {
            g.drawString(order.getTrader().getName(),
                    drawX + COLUMN_SEPARATION + INDENT * 2, drawY + TEXT_HEIGHT * lineCount);
            g.drawString("P: " + doubleToString(order.getPrice(), 3),
                    drawX + COLUMN_SEPARATION + INDENT * 2 + INDENT_P, drawY + TEXT_HEIGHT * lineCount);
            g.drawString("V: " + doubleToString(order.getVolume(), 1),
                    drawX + COLUMN_SEPARATION + INDENT * 2 + INDENT_P + INDENT_V, drawY + TEXT_HEIGHT * lineCount);
            lineCount ++;
        }
    }

    @SuppressWarnings("SameParameterValue")
    private void paintMarket_discrete(@NotNull Graphics g, int drawX, int drawY,
                                      @NotNull DiscreteResource resource) {
        g.setColor(TEXT_COLOR);
        g.drawString(resource.getName() + " market:", drawX, drawY);

        @NotNull SingleResourceMarketDiscrete market = multiMarket.getMarket(resource);

        //print last positive-volume info here (could throw null pointer on first day)
        @NotNull MarketHistory<@NotNull Integer> history = market.getHistory();
        @Nullable MarketHistoryDataPoint<@NotNull Integer> lastPositiveVolume = history.getLastPositiveVolumeData();
        @NotNull String
                lastPriceString = "Last positive price: ",
                lastVolumeString = "Last positive volume: ";
        if (lastPositiveVolume == null) {
            lastPriceString += "-";
            lastVolumeString += "0";
        } else {
            lastPriceString += doubleToString(lastPositiveVolume.getPriceVWA(), 3);
            lastVolumeString += lastPositiveVolume.getVolume();
        }
        g.drawString(lastPriceString, drawX, drawY + TEXT_HEIGHT);
        g.drawString(lastVolumeString, drawX, drawY + TEXT_HEIGHT * 2);
        int startingLineCount = 4;

        //sell orders
        @NotNull List<@NotNull MarketOrderDiscrete> sellOrders = market.getSortedSellOrders();
        g.drawString("Sell:", drawX + INDENT, drawY + TEXT_HEIGHT * startingLineCount);
        int lineCount = startingLineCount + 1;
        for (@NotNull MarketOrderDiscrete order : sellOrders) {
            g.drawString(order.getTrader().getName(),
                    drawX + INDENT * 2, drawY + TEXT_HEIGHT * lineCount);
            g.drawString("P: " + doubleToString(order.getPrice(), 3),
                    drawX + INDENT * 2 + INDENT_P, drawY + TEXT_HEIGHT * lineCount);
            g.drawString("V: " + doubleToString(order.getVolume(), 1),
                    drawX + INDENT * 2 + INDENT_P + INDENT_V, drawY + TEXT_HEIGHT * lineCount);
            lineCount ++;
        }

        //buy orders
        @NotNull List<@NotNull MarketOrderDiscrete> buyOrders = market.getSortedBuyOrders();
        g.drawString("Buy:", drawX + INDENT + COLUMN_SEPARATION, drawY + TEXT_HEIGHT * startingLineCount);
        lineCount = startingLineCount + 1;
        for (@NotNull MarketOrderDiscrete order : buyOrders) {
            g.drawString(order.getTrader().getName(),
                    drawX + COLUMN_SEPARATION + INDENT * 2, drawY + TEXT_HEIGHT * lineCount);
            g.drawString("P: " + doubleToString(order.getPrice(), 3),
                    drawX + COLUMN_SEPARATION + INDENT * 2 + INDENT_P, drawY + TEXT_HEIGHT * lineCount);
            g.drawString("V: " + doubleToString(order.getVolume(), 1),
                    drawX + COLUMN_SEPARATION + INDENT * 2 + INDENT_P + INDENT_V, drawY + TEXT_HEIGHT * lineCount);
            lineCount ++;
        }
    }
}