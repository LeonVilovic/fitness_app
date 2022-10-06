/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domain.PlanVezbanja;
import domain.Vezba;

/**
 *
 * @author Marija
 */
public class SOZapamtiPlanVezbanja extends OpstaSistemskaOperacija {

    public SOZapamtiPlanVezbanja(PlanVezbanja planVezbanja) {
        super(planVezbanja);
    }

    @Override
    public void izvrsenjeOperacije() throws Exception {
        dbbr.insert(odo);
    }
}
