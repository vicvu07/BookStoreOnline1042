package application.model.viewmodel.admin;

import application.model.viewmodel.common.ChartVM;
import application.model.viewmodel.common.LayoutHeaderAdminVM;

public class AdminChartVM {
    private LayoutHeaderAdminVM layoutHeaderAdminVM;
    private ChartVM countBookByCategory;
    private ChartVM sumBookByCategory;

    public ChartVM getSumBookByCategory() {
        return sumBookByCategory;
    }

    public void setSumBookByCategory(ChartVM sumBookByCategory) {
        this.sumBookByCategory = sumBookByCategory;
    }

    public LayoutHeaderAdminVM getLayoutHeaderAdminVM() {
        return layoutHeaderAdminVM;
    }

    public void setLayoutHeaderAdminVM(LayoutHeaderAdminVM layoutHeaderAdminVM) {
        this.layoutHeaderAdminVM = layoutHeaderAdminVM;
    }

    public ChartVM getCountBookByCategory() {
        return countBookByCategory;
    }

    public void setCountBookByCategory(ChartVM countBookByCategory) {
        this.countBookByCategory = countBookByCategory;
    }
}
