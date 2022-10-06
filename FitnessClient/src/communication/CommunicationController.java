/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import domain.Korisnik;
import domain.OpstiDomenskiObjekat;
import domain.PlanVezbanja;
import domain.StavkaPlanaVezbanja;
import domain.TezinaNaDan;
import domain.Vezba;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import transfer.RequestObject;
import transfer.ResponseObject;
import util.Operation;

/**
 *
 * @author Marija
 */
public class CommunicationController {

    private Socket socket;
    private Korisnik loggedUser = null;
    private Object SavedData = null;

    public Object getSavedData() {
        return SavedData;
    }

    public void setSavedData(Object SavedData) {
        this.SavedData = SavedData;
    }
    private static CommunicationController instance;

    private CommunicationController() throws IOException {
        socket = new Socket("localhost", 9000);

    }

    /**
     *
     * @return @throws IOException
     */
    public static CommunicationController getInstance() throws IOException {
        if (instance == null) {
            instance = new CommunicationController();
        }
        return instance;
    }

    public Korisnik getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(Korisnik loggedUser) {
        this.loggedUser = loggedUser;
    }

    private void sendRequest(RequestObject request) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();
    }

    private ResponseObject receiveResponse() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        return response;
    }

    public Korisnik logIn(String username, String password) throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(Operation.OPERATION_LOGIN);
        Korisnik korisnik = new Korisnik();
        korisnik.setUsername(username);
        korisnik.setPassword(password);
        request.setData(korisnik);
        sendRequest(request);
        ResponseObject response = receiveResponse();
        if (response.getException() != null) {
            throw response.getException();
        }

        return loggedUser = (Korisnik) response.getData();
    }

    // od objekata koji se vracaju prvi objekat je tipa korisnik ostali su tipa tezinaNaDan
    public List<OpstiDomenskiObjekat> getKorisnikSaTezinama(Korisnik korisnik) throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(Operation.OPERATION_GET_KORISNIK);
        request.setData(korisnik);
        sendRequest(request);
        ResponseObject response = receiveResponse();
        if (response.getException() != null) {
            throw response.getException();
        }
        return (List<OpstiDomenskiObjekat>) response.getData();
    }

    public void saveKorisnik(Korisnik korisnik, TezinaNaDan tezinaNaDan) throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(Operation.OPERATION_SAVE_KORISNIK);
        Map<String, Object> data = new HashMap<>();
        data.put("korisnik", korisnik);
        data.put("tezinaNaDan", tezinaNaDan);
        request.setData(data);
        sendRequest(request);
        ResponseObject response = receiveResponse();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public void deleteKorisnik(Korisnik korisnik) throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(Operation.OPERATION_DELETE_KORISNIK);
        request.setData(korisnik);
        sendRequest(request);
        ResponseObject response = receiveResponse();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public void updateKorisnik(Korisnik korisnik, List<TezinaNaDan> listaTezina) throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(Operation.OPERATION_UPDATE_KORISNIK);
        Map<String, Object> data = new HashMap<>();
        data.put("korisnik", korisnik);
        data.put("listaTezina", listaTezina);
        request.setData(data);
        sendRequest(request);
        ResponseObject response = receiveResponse();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public List<Korisnik> searchKorisnik(Korisnik korisnik, String kriterijumPretrage) throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(Operation.OPERATION_SEARCH_KORISNIK);
        Map<String, Object> data = new HashMap<>();
        data.put("korisnik", korisnik);
        data.put("kriterijumPretrage", kriterijumPretrage);
        request.setData(data);
        sendRequest(request);
        ResponseObject response = receiveResponse();
        if (response.getException() != null) {
            throw response.getException();
        }
        return (List<Korisnik>) response.getData();
    }

    public List<Vezba> searchVezba(Vezba vezba, String kriterijumPretrage) throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(Operation.OPERATION_SEARCH_VEZBA);
        Map<String, Object> data = new HashMap<>();
        data.put("vezba", vezba);
        data.put("kriterijumPretrage", kriterijumPretrage);
        request.setData(data);
        sendRequest(request);
        ResponseObject response = receiveResponse();
        if (response.getException() != null) {
            throw response.getException();
        }
        return (List<Vezba>) response.getData();
    }

    public void saveVezba(Vezba vezba) throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(Operation.OPERATION_SAVE_VEZBA);
        request.setData(vezba);
        sendRequest(request);
        ResponseObject response = receiveResponse();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public Vezba getVezba(Vezba vezba) throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(Operation.OPERATION_GET_VEZBA);
        request.setData(vezba);
        sendRequest(request);
        ResponseObject response = receiveResponse();
        if (response.getException() != null) {
            throw response.getException();
        }
        return (Vezba) response.getData();
    }

    public void updateVezba(Vezba vezba) throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(Operation.OPERATION_UPDATE_VEZBA);
        request.setData(vezba);
        sendRequest(request);
        ResponseObject response = receiveResponse();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public void deleteVezba(Vezba vezba) throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(Operation.OPERATION_DELETE_VEZBA);
        request.setData(vezba);
        sendRequest(request);
        ResponseObject response = receiveResponse();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public List<PlanVezbanja> searchPlanVezbanja(PlanVezbanja planVezbanja, String kriterijumPretrage) throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(Operation.OPERATION_SEARCH_PLAN_VEZBI);
        Map<String, Object> data = new HashMap<>();
        data.put("planVezbanja", planVezbanja);
        data.put("kriterijumPretrage", kriterijumPretrage);
        request.setData(data);
        sendRequest(request);
        ResponseObject response = receiveResponse();
        if (response.getException() != null) {
            throw response.getException();
        }
        return (List<PlanVezbanja>) response.getData();
    }

    public List<OpstiDomenskiObjekat> getPlanSaVezbama(PlanVezbanja planVezbanja) throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(Operation.OPERATION_GET_PLAN_VEZBI);
        request.setData(planVezbanja);
        sendRequest(request);
        ResponseObject response = receiveResponse();
        if (response.getException() != null) {
            throw response.getException();
        }
        return (List<OpstiDomenskiObjekat>) response.getData();
    }

    public void deletePlanVezbanja(PlanVezbanja planVezbanja) throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(Operation.OPERATION_DELETE_PLAN_VEZBI);
        request.setData(planVezbanja);
        sendRequest(request);
        ResponseObject response = receiveResponse();
        if (response.getException() != null) {
            throw response.getException();
        }
    }
    
        public void updatePlanVezbanja(PlanVezbanja planVezbanja, List<StavkaPlanaVezbanja> listaStavki) throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(Operation.OPERATION_UPDATE_PLAN_VEZBI);
        Map<String, Object> data = new HashMap<>();
        data.put("planVezbanja", planVezbanja);
        data.put("listaStavki", listaStavki);
        request.setData(data);
        sendRequest(request);
        ResponseObject response = receiveResponse();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public void savePlanVezbanja(PlanVezbanja planVezbanja) throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(Operation.OPERATION_SAVE_PLAN_VEZBI);
        request.setData(planVezbanja);
        sendRequest(request);
        ResponseObject response = receiveResponse();
        if (response.getException() != null) {
            throw response.getException();
        }
    }
}
