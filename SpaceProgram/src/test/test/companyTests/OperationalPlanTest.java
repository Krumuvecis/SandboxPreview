package companyTests;

import company.OperationalPlan;
import company.OperationalPlanAnalysis;
import company.facilities.*;
import dimensions.time.TimeUnit;
import org.jetbrains.annotations.NotNull;

import static consoleUtils.SimplePrinting.printLine;

//
public class OperationalPlanTest {
    private static final @NotNull String INDENT = "  ";

    //
    public static void main(String[] args) {
        new OperationalPlanTest(new OperationalPlan(
                new ProductionFacility.Factory(),
                new ImportFacility.ImportCenter(),
                new ExportFacility.ExportCenter()));
    }

    private OperationalPlanTest(@NotNull OperationalPlan plan) {
        printLine("Testing operational plan!");
        printFacilities(plan, 1);
        printLine("");
        printIndentedLine(1, "Operational time: " + plan.getOperationalTime().getValueAndShortUnit(TimeUnit.MONTH));
        printLine("");
        printAnalysis(plan.getAnalysis(), 1);
    }

    @SuppressWarnings("SameParameterValue")
    private void printFacilities(@NotNull OperationalPlan plan, int indent) {
        printIndentedLine(indent, "Production facility: " + ((FacilityAsset) (plan.getProductionFacility())).getLongName());
        printIndentedLine(indent, "Import facility: " + ((FacilityAsset) (plan.getImportFacility())).getLongName());
        printIndentedLine(indent, "Export facility: " + ((FacilityAsset) (plan.getExportFacility())).getLongName());
    }

    @SuppressWarnings("SameParameterValue")
    private void printAnalysis(@NotNull OperationalPlanAnalysis analysis, int indent) {
        printIndentedLine(indent, "Analysis:");
        printIndentedLine(indent + 1, "Expenses: " + analysis.getOperationalExpenses().getAmount());
        printIndentedLine(indent + 1, "Revenue: " + analysis.getOperationalRevenue().getAmount());
        printIndentedLine(indent + 1, "Profit: " + analysis.getOperationalProfit().getAmount() + ", ROI: " + ((int) (100 * 100 * analysis.getOperationalROI())) * 0.01 + " %");
    }

    private void printIndentedLine(int indent, @NotNull String line) {
        printLine(INDENT.repeat(indent) + line);
    }
}