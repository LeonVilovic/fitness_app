/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import domain.Korisnik;
import domain.OpstiDomenskiObjekat;
import domain.PlanVezbanja;
import domain.StavkaPlanaVezbanja;
import domain.TezinaNaDan;
import domain.TezinaVezbe;
import domain.Vezba;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marija
 */
public class DatabaseBroker {

    Connection connection;

    public DatabaseBroker() {
    }

    public void connect() throws Exception {
        try {
            FileInputStream in = new FileInputStream("src\\dbsettings.properties");
            Properties props = new Properties();
            props.load(in);
            String driver = props.getProperty("driver");
            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");

            connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(false);
            System.out.println("Database Broker: succesfull connection!");
            in.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Database Broker: connection error!\n" + ex.getMessage());
        }
    }

    public void disconnect() throws Exception {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new Exception("Greska prilikom raskidanja konekcije: " + ex.getMessage());
            }
        }
    }

    public void commit() throws Exception {
        if (connection != null) {
            try {
                connection.commit();
            } catch (SQLException ex) {
                throw new Exception("Greska prilikom potvrdjivanja transakcije: " + ex.getMessage());
            }
        }
    }

    public void rollback() throws Exception {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new Exception("Greska prilikom ponistavanja transakcije: " + ex.getMessage());
            }
        }
    }

    //  Primarni kljuc u opstem domenskom objektu mora biti tipa long
    public OpstiDomenskiObjekat insert(OpstiDomenskiObjekat o) throws SQLException {
        try {
            String upit = "INSERT INTO " + o.dajNazivTabele() + " (" + o.dajNaziveAtributa() + ") VALUES (" + o.dajVrednostiAtributa() + ")";
            System.out.println(upit);
            Statement statement = connection.createStatement();

            statement.executeUpdate(upit, Statement.RETURN_GENERATED_KEYS);
            if (o.isAutoincrement()) {
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    Long id = rs.getLong(1);
                    o.setId(id);
                }
                rs.close();
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return o;
    }

    public OpstiDomenskiObjekat delete(OpstiDomenskiObjekat o) throws SQLException {
        String upit = "DELETE FROM " + o.dajNazivTabele() + " WHERE (" + o.dajNazivPrimarnogKljuca() + " = " + o.getID() + ")";
        System.out.println(upit);
        Statement statement = connection.createStatement();

        statement.executeUpdate(upit);

        return o;
    }
        public OpstiDomenskiObjekat deleteUsingCoplexKey(OpstiDomenskiObjekat o, List<Long> complexKey) throws SQLException {
        String upit = "DELETE FROM " + o.dajNazivTabele() + " WHERE";
        for (int i = 0; i < o.dajKompleksniKljuc().size(); i++) {
            upit = upit + " (" + o.dajKompleksniKljuc().get(i) + "= " + complexKey.get(i) + ")";
            if (i + 1 < o.dajKompleksniKljuc().size()) {
                upit = upit + " AND";
            }
        }
            
            
            
        System.out.println(upit);
        Statement statement = connection.createStatement();

        statement.executeUpdate(upit);

        return o;
    }

    public OpstiDomenskiObjekat update(OpstiDomenskiObjekat o) throws SQLException {
        String upit = "UPDATE " + o.dajNazivTabele() + " SET " + o.postaviVrednostiAtributa() + " WHERE (" + o.dajNazivPrimarnogKljuca() + "=" + o.getID() + ")";
        System.out.println(upit);
        Statement statement = connection.createStatement();

        statement.executeUpdate(upit);

        return o;
    }

    public List<OpstiDomenskiObjekat> classConverter(ResultSet rs) throws SQLException {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        ResultSetMetaData meta = rs.getMetaData();
        if ("iDKorisnik".equals(meta.getColumnName(1)) && "ime".equals(meta.getColumnName(2)) && "prezime".equals(meta.getColumnName(3)) && "email".equals(meta.getColumnName(4)) && "username".equals(meta.getColumnName(5)) && "password".equals(meta.getColumnName(6)) && "visina".equals(meta.getColumnName(7))) {
            while (rs.next()) {
                Korisnik k = new Korisnik(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDouble(7));
                lista.add(k);
            }
        }
        if ("iDTezinaNaDan".equals(meta.getColumnName(1)) && "datum".equals(meta.getColumnName(2)) && "tezina".equals(meta.getColumnName(3)) && "iDkorisnik".equals(meta.getColumnName(4))) {
            while (rs.next()) {
                Korisnik k = (Korisnik) getODOUsingPK(new Korisnik(), rs.getLong(4));
                TezinaNaDan t = new TezinaNaDan(rs.getLong(1), rs.getDate(2), rs.getDouble(3), k);
                lista.add(t);
            }
        }
        if ("iDVezba".equals(meta.getColumnName(1)) && "naziv".equals(meta.getColumnName(2)) && "opis".equals(meta.getColumnName(3)) && "moguceSamoUTeretani".equals(meta.getColumnName(4)) && "putDoFajla".equals(meta.getColumnName(5)) && "tezinaVezbe".equals(meta.getColumnName(6))) {
            while (rs.next()) {

                TezinaVezbe t;
                String s = rs.getString(6);
                switch (s) {
                    case "Laka":
                        t = TezinaVezbe.Laka;
                        break;
                    case "Srednja":
                        t = TezinaVezbe.Srednja;
                        break;
                    case "Teska":
                        t = TezinaVezbe.Teska;
                        break;
                    default:
                        t = TezinaVezbe.Srednja;
                }

                Vezba v = new Vezba(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getString(5), t);
                lista.add(v);
            }
        }
        if ("iDPlanVezbanja".equals(meta.getColumnName(1)) && "naziv".equals(meta.getColumnName(2)) && "iDKorisnik".equals(meta.getColumnName(3))) {
            while (rs.next()) {
                Korisnik k = (Korisnik) getODOUsingPK(new Korisnik(), rs.getLong(3));
                PlanVezbanja p = new PlanVezbanja(rs.getLong(1), rs.getString(2), k);
                lista.add(p);
            }
        }

        if ("iDPlanVezbanja".equals(meta.getColumnName(1)) && "iDVezba".equals(meta.getColumnName(2)) && "redniBroj".equals(meta.getColumnName(3))) {
            while (rs.next()) {
                PlanVezbanja p = (PlanVezbanja) getODOUsingPK(new PlanVezbanja(), rs.getLong(1));
                Vezba v = (Vezba) getODOUsingPK(new Vezba(), rs.getLong(2));
                StavkaPlanaVezbanja s = new StavkaPlanaVezbanja(p, v, rs.getInt(3));

                lista.add(s);
            }
        }
        
        return lista;
    }

    public OpstiDomenskiObjekat getODOUsingPK(OpstiDomenskiObjekat o, Long primaryKey) throws SQLException {
        String upit = "SELECT * FROM " + o.dajNazivTabele() + " WHERE (" + o.dajNazivPrimarnogKljuca() + "= " + primaryKey + ")";
        System.out.println(upit);
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(upit);
        List<OpstiDomenskiObjekat> lista;
        lista = classConverter(rs);
        if (lista.isEmpty()) {
            return null;
        } else {
            return lista.get(0);
        }
    }

    public OpstiDomenskiObjekat getODOUsingComplexkey(OpstiDomenskiObjekat o, List<Long> complexKey) throws SQLException {

        String upit = "SELECT * FROM " + o.dajNazivTabele() + " WHERE";
        for (int i = 0; i < o.dajKompleksniKljuc().size(); i++) {
            upit = upit + " (" + o.dajKompleksniKljuc().get(i) + "= " + complexKey.get(i) + ")";
            if (i + 1 < o.dajKompleksniKljuc().size()) {
                upit = upit + " AND";
            }
        }
        System.out.println(upit);
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(upit);
        List<OpstiDomenskiObjekat> lista;
        lista = classConverter(rs);
        if (lista.isEmpty()) {
            return null;
        } else {
            return lista.get(0);
        }
    }

    public List<OpstiDomenskiObjekat> search(OpstiDomenskiObjekat o, String kriterijumPretrage) throws SQLException {
        List<String> listaAtributaZaPretragu = o.dajAtributeZaPretragu();
        String upit = "SELECT * FROM " + o.dajNazivTabele() + " WHERE";
        for (int i = 0; i < listaAtributaZaPretragu.size(); i++) {
            upit = upit + " (UPPER(" + listaAtributaZaPretragu.get(i) + ") LIKE UPPER('" + kriterijumPretrage + "%'))";
            if (i + 1 < listaAtributaZaPretragu.size()) {
                upit = upit + " OR";
            }
        }
        System.out.println(upit);
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(upit);
        return classConverter(rs);
    }


}
