/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import controller.Controller;
import domain.Korisnik;
import domain.OpstiDomenskiObjekat;
import domain.PlanVezbanja;
import domain.TezinaNaDan;
import domain.Vezba;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import transfer.RequestObject;
import transfer.ResponseObject;
import util.Operation;

/**
 *
 * @author student1
 */
public class ClientHandlerThread extends Thread {

    private Socket socket;
    private Korisnik loggedUser;

    public ClientHandlerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (!socket.isClosed()) {
            try {
                RequestObject request = receiveRequest();
                ResponseObject response = null;
                switch (request.getOperation()) {
                    case Operation.OPERATION_LOGIN:
                        response = logIn(request);
                        break;
                    case Operation.OPERATION_SAVE_KORISNIK:
                        response = saveKorisnik(request);
                        break;
                    case Operation.OPERATION_SEARCH_KORISNIK:
                        response = searchKorisnik(request);
                        break;
                    case Operation.OPERATION_GET_KORISNIK:
                        response = getKorisnik(request);
                        break;
                    case Operation.OPERATION_DELETE_KORISNIK:
                        response = deleteKorisnik(request);
                        break;
                    case Operation.OPERATION_UPDATE_KORISNIK:
                        response = updateKorisnik(request);
                        break;
                    case Operation.OPERATION_SEARCH_VEZBA:
                        response = searchVezba(request);
                        break;
                    case Operation.OPERATION_SAVE_VEZBA:
                        response = saveVezba(request);
                        break;
                    case Operation.OPERATION_GET_VEZBA:
                        response = getVezba(request);
                        break;
                    case Operation.OPERATION_UPDATE_VEZBA:
                        response = updateVezba(request);
                        break;
                    case Operation.OPERATION_DELETE_VEZBA:
                        response = deleteVezba(request);
                        break;
                    case Operation.OPERATION_SEARCH_PLAN_VEZBI:
                        response = searchPlanVezba(request);
                        break;
                    case Operation.OPERATION_GET_PLAN_VEZBI:
                        response = getPlanVezbanja(request);
                        break;
                    case Operation.OPERATION_DELETE_PLAN_VEZBI:
                        response = deletePlanVezbanja(request);
                        break;
                    case Operation.OPERATION_UPDATE_PLAN_VEZBI:
                        response = updatePlanVezbanja(request);
                        break;
                    case Operation.OPERATION_SAVE_PLAN_VEZBI:
                        response = savePlanVezbanja(request);
                        break;
                }
                sendResponse(response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private ResponseObject logIn(RequestObject request) {
        Korisnik korisnik = (Korisnik) request.getData();
        ResponseObject response = new ResponseObject();
        try {
            Korisnik user = Controller.getInstance().logIn(korisnik);
            loggedUser = user;
            response.setData(user);
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(e);
        }
        return response;
    }

    private ResponseObject getKorisnik(RequestObject request) {
        Korisnik korisnik = (Korisnik) request.getData();
        ResponseObject response = new ResponseObject();

        try {
            List<OpstiDomenskiObjekat> korisnikITezine = Controller.getInstance().getKorisnik(korisnik);
            response.setData(korisnikITezine);
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(e);
        }
        return response;
    }

    private ResponseObject saveKorisnik(RequestObject request) {

        ResponseObject response = new ResponseObject();
        Map<String, Object> data = (Map<String, Object>) request.getData();
        Korisnik korisnik = (Korisnik) data.get("korisnik");
        TezinaNaDan tezinaNaDan = (TezinaNaDan) data.get("tezinaNaDan");

        try {
            Controller.getInstance().saveKorisnik(korisnik, tezinaNaDan);
            //   response.setData(korisnik);
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(e);
        }
        return response;

    }

    private ResponseObject deleteKorisnik(RequestObject request) {
        Korisnik korisnik = (Korisnik) request.getData();

        ResponseObject response = new ResponseObject();
        try {
            Controller.getInstance().deleteKorisnik(korisnik);
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(e);
        }
        return response;
    }

    private ResponseObject searchKorisnik(RequestObject request) {
        Map<String, Object> data = (Map<String, Object>) request.getData();
        Korisnik korisnik = (Korisnik) data.get("korisnik");
        String kriterijumPretrage = (String) data.get("kriterijumPretrage");

        ResponseObject response = new ResponseObject();
        try {
            List<OpstiDomenskiObjekat> listaKorisnika = Controller.getInstance().searchKorisnik(korisnik, kriterijumPretrage);
            if (listaKorisnika.isEmpty()) {
                throw new Exception("Sitem ne moze da nadje korisnike po zadatoj vrednosti");
            }
            response.setData(listaKorisnika);
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(e);
        }
        return response;
    }

    private ResponseObject updateKorisnik(RequestObject request) {
        Map<String, Object> data = (Map<String, Object>) request.getData();
        Korisnik korisnik = (Korisnik) data.get("korisnik");
        List<OpstiDomenskiObjekat> listaTezina = (List<OpstiDomenskiObjekat>) data.get("listaTezina");

        ResponseObject response = new ResponseObject();
        try {
            Controller.getInstance().updateKorisnik(korisnik, listaTezina);
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(e);
        }
        return response;
    }

    private ResponseObject searchVezba(RequestObject request) {
        Map<String, Object> data = (Map<String, Object>) request.getData();
        Vezba vezba = (Vezba) data.get("vezba");
        String kriterijumPretrage = (String) data.get("kriterijumPretrage");

        ResponseObject response = new ResponseObject();
        try {
            List<OpstiDomenskiObjekat> listaKorisnika = Controller.getInstance().searchVezba(vezba, kriterijumPretrage);
            if (listaKorisnika.isEmpty()) {
                throw new Exception("Sistem ne moze da nadje vezbe po zadatoj vrednosti");
            }
            response.setData(listaKorisnika);
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(e);
        }
        return response;
    }

    private ResponseObject saveVezba(RequestObject request) {
        Vezba vezba = (Vezba) request.getData();

        ResponseObject response = new ResponseObject();
        try {
            Controller.getInstance().saveVezba(vezba);
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(e);
        }
        return response;
    }

    private ResponseObject getVezba(RequestObject request) {
        Vezba vezba = (Vezba) request.getData();
        ResponseObject response = new ResponseObject();
        try {
            Vezba vezba2 = Controller.getInstance().getVezba(vezba);
            response.setData(vezba2);
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(e);
        }
        return response;
    }

    private ResponseObject updateVezba(RequestObject request) {
        Vezba v = (Vezba) request.getData();
        ResponseObject response = new ResponseObject();
        try {
            Controller.getInstance().updateVezba(v);
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(e);
        }
        return response;
    }

    private ResponseObject deleteVezba(RequestObject request) {
        Vezba v = (Vezba) request.getData();
        ResponseObject response = new ResponseObject();
        try {
            Controller.getInstance().deleteVezba(v);
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(e);
        }
        return response;
    }
    
        private ResponseObject searchPlanVezba(RequestObject request) {
        Map<String, Object> data = (Map<String, Object>) request.getData();
        PlanVezbanja planVezbanja = (PlanVezbanja) data.get("planVezbanja");
        String kriterijumPretrage = (String) data.get("kriterijumPretrage");

        ResponseObject response = new ResponseObject();
        try {
            List<OpstiDomenskiObjekat> listaPlanova = Controller.getInstance().searchPlanVezbanja(planVezbanja, kriterijumPretrage);
            if (listaPlanova.isEmpty()) {
                throw new Exception("Sitem ne moze da nadje plan vezbanja po zadatoj vrednosti");
            }
            response.setData(listaPlanova);
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(e);
        }
        return response;
    }
        
    private ResponseObject getPlanVezbanja(RequestObject request) {
        PlanVezbanja planVezbanja = (PlanVezbanja) request.getData();
        ResponseObject response = new ResponseObject();

        try {
            List<OpstiDomenskiObjekat> korisnikITezine = Controller.getInstance().getPlanVezbanja(planVezbanja);
            response.setData(korisnikITezine);
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(e);
        }
        return response;
    }

    private ResponseObject deletePlanVezbanja(RequestObject request) {
        PlanVezbanja pv = (PlanVezbanja) request.getData();
        ResponseObject response = new ResponseObject();
        try {
            Controller.getInstance().deletePlanVezbanja(pv);
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(e);
        }
        return response;
    }

    private ResponseObject updatePlanVezbanja(RequestObject request) {
        Map<String, Object> data = (Map<String, Object>) request.getData();
        PlanVezbanja planVezbanja = (PlanVezbanja) data.get("planVezbanja");
        List<OpstiDomenskiObjekat> listaStavki = (List<OpstiDomenskiObjekat>) data.get("listaStavki");

        ResponseObject response = new ResponseObject();
        try {
            Controller.getInstance().updatePlanVezbanja(planVezbanja, listaStavki);
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(e);
        }
        return response;
    }

    private ResponseObject savePlanVezbanja(RequestObject request) {
        PlanVezbanja planVezbanja = (PlanVezbanja) request.getData();

        ResponseObject response = new ResponseObject();
        try {
            Controller.getInstance().savePlanVezbanja(planVezbanja);
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(e);
        }
        return response;
    }

    
    private RequestObject receiveRequest() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        return (RequestObject) in.readObject();
    }

    private void sendResponse(ResponseObject response) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(response);
        out.flush();
    }
    
    public void stopClientHandler() throws IOException {
        socket.close();
    }

    public Korisnik getLoggedUser() {
        return loggedUser;
    }

}
