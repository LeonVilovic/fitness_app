/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import database.DatabaseBroker;
import domain.Korisnik;
import domain.OpstiDomenskiObjekat;
import domain.TezinaNaDan;
import exception.ValidationException;
import java.util.Calendar;
import java.util.List;
import validator.Validator;

/**
 *
 * @author Marija
 */
public class ValidatorZapamtiKorisnika implements Validator {

    List<TezinaNaDan> tezineNaDan;
    DatabaseBroker dbbr;

    public ValidatorZapamtiKorisnika(List<TezinaNaDan> tezineNaDan) {
        this.tezineNaDan = tezineNaDan;
    }

    @Override
    public void validate(Object value) throws ValidationException {
        try {
            Korisnik korisnik = (Korisnik) value;
            if (korisnik.getIme() == null || korisnik.getIme().matches("")) {
                throw new Exception("Polje Ime je obavezno!");
            }
            if (korisnik.getIme().length() < 2) {
                throw new Exception("Ime mora imati bar 2 slova");
            }
            if (korisnik.getIme().contains("\\") || korisnik.getIme().contains("?") || korisnik.getIme().contains("$") || korisnik.getIme().contains("#") || korisnik.getIme().contains("!") || korisnik.getIme().contains("&") || korisnik.getIme().contains("^")) {
                throw new Exception("Ime ne sme zadrzati sledece karaktere: ?&^%$#@!\\");
            }
            if (korisnik.getPrezime().contains("\\") || korisnik.getPrezime().contains("?") || korisnik.getPrezime().contains("&") || korisnik.getPrezime().contains("^") || korisnik.getPrezime().contains("%") || korisnik.getPrezime().contains("!") || korisnik.getPrezime().contains("&")) {
                throw new Exception("Prezime ne sme zadrzati sledece karaktere: ?&^%$#@!\\");
            }
            if (korisnik.getPrezime() == null || korisnik.getPrezime().matches("")) {
                throw new Exception("Polje Prezime je obavezno!");
            }
            if (korisnik.getUsername() == null || korisnik.getUsername().matches("")) {
                throw new Exception("Polje Username je obavezno!");
            }
            if (korisnik.getUsername().length() < 3) {
                throw new Exception("Username mora imati bar 3 slova");
            }
            if (korisnik.getPassword() == null || korisnik.getPassword().matches("")) {
                throw new Exception("Polje Password je obavezno!");
            }
            if (korisnik.getPassword().length() < 3) {
                throw new Exception("Password mora imati bar 3 slova");
            }
            if (!korisnik.getEmail().isEmpty()) {
                if (!korisnik.getEmail().contains("@")) {
                    throw new Exception("E-mail nije validan");
                }
            }
            if (korisnik.getVisina() > 270) {
                throw new Exception("Visna je prevelika");
            }
            if (korisnik.getVisina() <= 0) {
                throw new Exception("Visna mora biti pozitivan broj");
            }

            dbbr = new DatabaseBroker();
            dbbr.connect();
            List<OpstiDomenskiObjekat> sviKorisnici = dbbr.search(korisnik, "");
            dbbr.disconnect();

            for (OpstiDomenskiObjekat opstiDomenskiObjekat : sviKorisnici) {
                Korisnik k = (Korisnik) opstiDomenskiObjekat;
                if (k.getUsername().equals(korisnik.getUsername())) {
                    throw new Exception("U bazi vec postoji korisnik sa istim korisnickim imenom");
                }
            }

            for (int i = 0; i < tezineNaDan.size(); i++) {
                if (tezineNaDan.get(i).getTezina() <= 0) {
                    throw new Exception("Tezina mora biti pozitivan broj");
                }

                for (int j = i + 1; j < tezineNaDan.size(); j++) {
                    if (tezineNaDan.get(i).getDatum().compareTo(tezineNaDan.get(j).getDatum()) == 0) {
                        throw new Exception("Dva datuma tezina su ista");
                    }
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ValidationException(ex.getMessage());
        }

    }

}
