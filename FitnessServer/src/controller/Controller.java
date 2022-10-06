/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Korisnik;
import domain.OpstiDomenskiObjekat;
import domain.PlanVezbanja;
import domain.TezinaNaDan;
import domain.Vezba;
import java.util.ArrayList;
import java.util.List;
import so.OpstaSistemskaOperacija;
import so.SOBrisiKorisnika;
import so.SOBrisiPlanVezbe;
import so.SOBrisiVezbu;
import so.SOIzmeniKorisnika;
import so.SOIzmeniPlanVezbanja;
import so.SOIzmeniVezbu;
import so.SOPretraziPlanoveVezbanja;
import so.SOPretraziVezbe;
import so.SOUcitajKorisnika;
import so.SOUcitajPlanVezbi;
import so.SOUcitajVezbu;
import so.SOUlogujKorisnika;
import so.SOZapamtiKorisnika;
import so.SOZapamtiPlanVezbanja;
import so.SOZapamtiVezbu;
import validator.ValidatorAzurirajKorisnika;
import validator.ValidatorAzurirajVezbu;
import validator.ValidatorUlogujKorisnka;
import validator.ValidatorZapamtiKorisnika;
import validator.ValidatorZapamtiVezbu;

/**
 *
 * @author Marija
 */
public class Controller {

    private static Controller instance;

    private Controller() {

    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public Korisnik logIn(Korisnik korisnik) throws Exception {
        OpstaSistemskaOperacija so = new SOUlogujKorisnika(korisnik);
        so.setValidator(new ValidatorUlogujKorisnka());
        so.opsteIzvrsenje();
        return (Korisnik) so.getResponse();
    }

    public List<OpstiDomenskiObjekat> getKorisnik(Korisnik korisnik) throws Exception {
        OpstaSistemskaOperacija so = new SOUcitajKorisnika(korisnik);
        so.opsteIzvrsenje();
        return (List<OpstiDomenskiObjekat>) so.getResponse();
    }

    public void saveKorisnik(Korisnik korisnik, TezinaNaDan tezinaNaDan) throws Exception {
        OpstaSistemskaOperacija so = new SOZapamtiKorisnika(korisnik, tezinaNaDan);
        List<TezinaNaDan> tezineNaDan = new ArrayList<>();
        tezineNaDan.add(tezinaNaDan);
        so.setValidator(new ValidatorZapamtiKorisnika(tezineNaDan));
        so.opsteIzvrsenje();
    }

    public void deleteKorisnik(Korisnik korisnik) throws Exception {
        OpstaSistemskaOperacija so = new SOBrisiKorisnika(korisnik);
        so.opsteIzvrsenje();
    }

    public List<OpstiDomenskiObjekat> searchKorisnik(Korisnik korisnik, String kriterijumPretrage) throws Exception {
        OpstaSistemskaOperacija so = new SOPretraziVezbe(korisnik, kriterijumPretrage);
        so.opsteIzvrsenje();
        return (List<OpstiDomenskiObjekat>) so.getResponse();
    }

    public void updateKorisnik(Korisnik korisnik, List<OpstiDomenskiObjekat> listaTezinaNaDan) throws Exception {
        OpstaSistemskaOperacija so = new SOIzmeniKorisnika(korisnik, listaTezinaNaDan);
        List<TezinaNaDan> listaTezinaNaDan2 = new ArrayList<>();
        for (OpstiDomenskiObjekat opstiDomenskiObjekat : listaTezinaNaDan) {
            TezinaNaDan t = (TezinaNaDan) opstiDomenskiObjekat;
            listaTezinaNaDan2.add(t);
        }
        so.setValidator(new ValidatorAzurirajKorisnika(listaTezinaNaDan2));
        so.opsteIzvrsenje();
    }

    public List<OpstiDomenskiObjekat> searchVezba(Vezba vezba, String kriterijumPretrage) throws Exception {
        OpstaSistemskaOperacija so = new SOPretraziVezbe(vezba, kriterijumPretrage);
        so.opsteIzvrsenje();
        return (List<OpstiDomenskiObjekat>) so.getResponse();
    }

    public void saveVezba(Vezba vezba) throws Exception {
        OpstaSistemskaOperacija so = new SOZapamtiVezbu(vezba);
        so.setValidator(new ValidatorZapamtiVezbu());
        so.opsteIzvrsenje();
    }

    public Vezba getVezba(Vezba vezba) throws Exception {
        OpstaSistemskaOperacija so = new SOUcitajVezbu(vezba);
        so.opsteIzvrsenje();
        return (Vezba) so.getResponse();
    }

    public void updateVezba(Vezba vezba) throws Exception {
        OpstaSistemskaOperacija so = new SOIzmeniVezbu(vezba);
        so.setValidator(new ValidatorAzurirajVezbu());
        so.opsteIzvrsenje();
    }

    public void deleteVezba(Vezba vezba) throws Exception {
        OpstaSistemskaOperacija so = new SOBrisiVezbu(vezba);
        so.opsteIzvrsenje();
    }

    public List<OpstiDomenskiObjekat> searchPlanVezbanja(PlanVezbanja planVezbanja, String kriterijumPretrage) throws Exception {
        OpstaSistemskaOperacija so = new SOPretraziPlanoveVezbanja(planVezbanja, kriterijumPretrage);
        so.opsteIzvrsenje();
        return (List<OpstiDomenskiObjekat>) so.getResponse();
    }

    public List<OpstiDomenskiObjekat> getPlanVezbanja(PlanVezbanja planVezbanja) throws Exception {
        OpstaSistemskaOperacija so = new SOUcitajPlanVezbi(planVezbanja);
        so.opsteIzvrsenje();
        return (List<OpstiDomenskiObjekat>) so.getResponse();
    }

    public void deletePlanVezbanja(PlanVezbanja planVezbanja) throws Exception {
        OpstaSistemskaOperacija so = new SOBrisiPlanVezbe(planVezbanja);
        so.opsteIzvrsenje();
    }
    
    public void updatePlanVezbanja(PlanVezbanja planVezbanja, List<OpstiDomenskiObjekat> listaStavki) throws Exception {
        OpstaSistemskaOperacija so = new SOIzmeniPlanVezbanja(planVezbanja, listaStavki);   
        so.opsteIzvrsenje();
    }
    
        public void savePlanVezbanja(PlanVezbanja planVezbanja) throws Exception {
        OpstaSistemskaOperacija so = new SOZapamtiPlanVezbanja(planVezbanja);
     //   so.setValidator(new ValidatorZapamtiPlanVezbanja());
        so.opsteIzvrsenje();
    }
    
}
