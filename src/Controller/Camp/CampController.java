package src.Controller.Camp;

import src.Controller.Camp.Data.CampDataHandler;

public class CampController {
    CampDataHandler campdatahandler;
    
    public CampController()
    {
    }

    public CampController(CampDataHandler campdatahandler)
    {
        this.campdatahandler = campdatahandler;
    }
}
