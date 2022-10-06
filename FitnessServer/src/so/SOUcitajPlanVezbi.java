/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domain.OpstiDomenskiObjekat;
import domain.PlanVezbanja;
import domain.StavkaPlanaVezbanja;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marija
 */
public class SOUcitajPlanVezbi extends OpstaSistemskaOperacija{

    public SOUcitajPlanVezbi(OpstiDomenskiObjekat odo) {
        super(odo);
    }

    
    @Override
    public void izvrsenjeOperacije() throws Exception {
        PlanVezbanja p = (PlanVezbanja) odo;
        StavkaPlanaVezbanja s = new StavkaPlanaVezbanja();
        
        List<OpstiDomenskiObjekat> listaObjekataZaVracanje = new ArrayList<>();
        listaObjekataZaVracanje.add(dbbr.getODOUsingPK(odo, p.getiDPlanVezbanja()));
        listaObjekataZaVracanje.addAll(dbbr.search(s, odo.getID()));
        response = listaObjekataZaVracanje;
        //prvi objekat je tipa PlanVezbanja svi ostali su tipa StavkaPlanaVezbanja
    }
    
}
