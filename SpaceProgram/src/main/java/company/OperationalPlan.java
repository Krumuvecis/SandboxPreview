package company;

import org.jetbrains.annotations.NotNull;

import dimensions.time.Time;
import company.facilities.ProductionFacility;
import company.facilities.ImportFacility;
import company.facilities.ExportFacility;

//
public class OperationalPlan {
    private final @NotNull ProductionFacility productionFacility;
    private final @NotNull ImportFacility importFacility;
    private final @NotNull ExportFacility exportFacility;
    private final @NotNull Time operationalTime;
    private final @NotNull OperationalPlanAnalysis analysis;

    //
    public OperationalPlan(@NotNull ProductionFacility productionFacility,
                           @NotNull ImportFacility importFacility,
                           @NotNull ExportFacility exportFacility) {
        this.productionFacility = productionFacility;
        this.importFacility = importFacility;
        this.exportFacility = exportFacility;

        operationalTime = productionFacility.getOperationalTime();
        analysis = new OperationalPlanAnalysis(this);
    }

    //
    public final @NotNull ProductionFacility getProductionFacility() {
        return productionFacility;
    }

    //
    public final @NotNull ImportFacility getImportFacility() {
        return importFacility;
    }

    //
    public final @NotNull ExportFacility getExportFacility() {
        return exportFacility;
    }

    //
    public final @NotNull Time getOperationalTime() {
        return operationalTime;
    }

    //
    public final @NotNull OperationalPlanAnalysis getAnalysis() {
        return analysis;
    }
}