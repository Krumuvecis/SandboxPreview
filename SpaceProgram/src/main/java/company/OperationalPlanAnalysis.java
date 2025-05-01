package company;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import company.resources.Resource;
import company.resources.particularResources.Money;
import company.facilities.ProductionFacility;
import company.facilities.ImportFacility;
import company.facilities.ExportFacility;

//
public class OperationalPlanAnalysis {
    private final @NotNull List<@NotNull Resource<?>>
            operationalInput,
            operationalOutput;
    private final @NotNull Money
            operationalExpenses,
            operationalRevenue,
            operationalProfit;
    private final double operationalROI;

    //
    public OperationalPlanAnalysis(@NotNull OperationalPlan plan) {
        @NotNull ProductionFacility productionFacility = plan.getProductionFacility();
        operationalInput = productionFacility.getOperationalCosts(); //TODO: improve this
        operationalOutput = productionFacility.getOperationalProducts(); //TODO: improve this
        operationalExpenses = calculateExpenses(plan.getImportFacility());
        operationalRevenue = calculateRevenue(plan.getExportFacility());
        operationalProfit = new Money(operationalRevenue.getAmount() - operationalExpenses.getAmount());
        operationalROI = operationalProfit.getAmount() / operationalExpenses.getAmount();
    }

    private @NotNull Money calculateExpenses(@NotNull ImportFacility importFacility) {
        double expenses = 0;
        for (@NotNull Resource<?> resource : operationalInput) {
            double resourcePrice = importFacility.getPrice(resource);
            expenses += resourcePrice;
        }
        return new Money(expenses);
    }

    private @NotNull Money calculateRevenue(@NotNull ExportFacility exportFacility) {
        double revenue = 0;
        for (@NotNull Resource<?> resource : operationalOutput) {
            double resourcePrice = exportFacility.getPrice(resource);
            revenue += resourcePrice;
        }
        return new Money(revenue);
    }

    //
    public final @NotNull List<@NotNull Resource<?>> getOperationalInput() {
        return operationalInput;
    }

    //
    public final @NotNull List<@NotNull Resource<?>> getOperationalOutput() {
        return operationalOutput;
    }

    //
    public final @NotNull Money getOperationalExpenses() {
        return operationalExpenses;
    }

    //
    public final @NotNull Money getOperationalRevenue() {
        return operationalRevenue;
    }

    //
    public final @NotNull Money getOperationalProfit() {
        return operationalProfit;
    }

    //
    public final double getOperationalROI() {
        return operationalROI;
    }
}