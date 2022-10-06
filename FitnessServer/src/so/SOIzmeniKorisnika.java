/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domain.Korisnik;
import domain.OpstiDomenskiObjekat;
import domain.TezinaNaDan;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marija
 */
public class SOIzmeniKorisnika extends OpstaSistemskaOperacija{

    List<OpstiDomenskiObjekat>listaNovihTezinaNaDan;
    
    public SOIzmeniKorisnika(OpstiDomenskiObjekat odo,List<OpstiDomenskiObjekat> listaTezinaNaDan) {
        super(odo);
        this.listaNovihTezinaNaDan = listaTezinaNaDan;
        
    }

    @Override
    public void izvrsenjeOperacije() throws Exception {
        List<OpstiDomenskiObjekat>listaStarihTezinaNaDan = dbbr.search(new TezinaNaDan(), odo.getID());
        
        List<Long>iDLista = new ArrayList<>();
        List<Long>iDForDeletionLista = new ArrayList<>();
        
        for (OpstiDomenskiObjekat opstiDomenskiObjekat : listaStarihTezinaNaDan) {
                TezinaNaDan t = (TezinaNaDan)opstiDomenskiObjekat;
                iDLista.add(t.getiDTezinaNaDan());
                iDForDeletionLista.add(t.getiDTezinaNaDan());
            }
        
        for (OpstiDomenskiObjekat opstiDomenskiObjekat : listaNovihTezinaNaDan) {
            TezinaNaDan t = (TezinaNaDan)opstiDomenskiObjekat;
            if (t.getiDTezinaNaDan()!=null) {
                for (Long long1 : iDLista) {
                    if (long1.equals(t.getiDTezinaNaDan())) {
                        iDForDeletionLista.remove(long1);
                    }
                }
            } else{
                dbbr.insert(t);
            } 
        }
        for (Long long1 : iDForDeletionLista) {
            TezinaNaDan t = new TezinaNaDan();
            t.setiDTezinaNaDan(long1);
            dbbr.delete(t);
        }
        Korisnik noviKorisnik = (Korisnik) odo;
        Korisnik stariKorisnik = (Korisnik) dbbr.getODOUsingPK(odo, noviKorisnik.getIDKorisnik());
        
        if (!(noviKorisnik.getID().equals(stariKorisnik.getID()) && noviKorisnik.getIme().equals(stariKorisnik.getIme())&& noviKorisnik.getPrezime().equals(stariKorisnik.getPrezime())&& noviKorisnik.getEmail().equals(stariKorisnik.getEmail())&& noviKorisnik.getUsername().equals(stariKorisnik.getUsername()) && noviKorisnik.getPassword().equals(stariKorisnik.getPassword())&& Double.compare(noviKorisnik.getVisina(), stariKorisnik.getVisina())==0)) {
            dbbr.update(odo);
        }
        
    }
    
    
    
}
