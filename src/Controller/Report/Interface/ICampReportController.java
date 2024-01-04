package src.Controller.Report.Interface;

import src.Entity.Camp;

public interface ICampReportController extends IReportController{
    String generateReport(Camp c);
    String generateReport(Camp c,String filter);
}
