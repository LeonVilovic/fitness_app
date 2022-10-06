/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domain.OpstiDomenskiObjekat;

/**
 *
 * @author Marija
 */
public class SOIzmeniVezbu extends OpstaSistemskaOperacija{

    public SOIzmeniVezbu(OpstiDomenskiObjekat odo) {
        super(odo);
    }

    @Override
    public void izvrsenjeOperacije() throws Exception {
        dbbr.update(odo);
    }
    
}
