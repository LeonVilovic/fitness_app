/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;


import domain.OpstiDomenskiObjekat;
import domain.StavkaPlanaVezbanja;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marija
 */
public class SOIzmeniPlanVezbanja extends OpstaSistemskaOperacija{

    List<OpstiDomenskiObjekat>listaStavki;
    
    public SOIzmeniPlanVezbanja(OpstiDomenskiObjekat odo,List<OpstiDomenskiObjekat> listaStavki) {
        super(odo);
        this.listaStavki = listaStavki;
        
    }

    @Override
    public void izvrsenjeOperacije() throws Exception {
        List<OpstiDomenskiObjekat>listaStarihlistaStavki = dbbr.search(new StavkaPlanaVezbanja(), odo.getID());
        
        for (OpstiDomenskiObjekat opstiDomenskiObjekat : listaStarihlistaStavki) {
            StavkaPlanaVezbanja spv = (StavkaPlanaVezbanja)opstiDomenskiObjekat;
            List<Long> complexKey = new ArrayList<>();
            complexKey.add(spv.getiDPlanVezbanja().getiDPlanVezbanja());
            complexKey.add(spv.getiDVezba().getiDVezba());
            complexKey.add((long)spv.getRedniBroj());
            dbbr.deleteUsingCoplexKey(opstiDomenskiObjekat, complexKey);
        }
        
        for (OpstiDomenskiObjekat opstiDomenskiObjekat : listaStavki) {
            dbbr.insert(opstiDomenskiObjekat);
        }
        
        dbbr.update(odo);
      
        
    }
    
    
    
}
