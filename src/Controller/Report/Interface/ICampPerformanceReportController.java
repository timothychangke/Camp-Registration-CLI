package src.Controller.Report.Interface;

import src.Entity.Camp;

public interface ICampPerformanceReportController extends IReportController{
    String generateReport(Camp c);
}
