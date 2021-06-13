import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.*;
import org.json.simple.parser.*;

public class Service implements IService { 
    private ArrayList<Client> listClients = new ArrayList<Client>();
 
    private ArrayList<Local> listLocal = new ArrayList<Local>();
    private ArrayList<Reservation> listReservations=new ArrayList<Reservation>();
    private JSONArray json=new JSONArray();
    public void saveFile(JSONArray ArrayList,String file) throws IOException{
       try {
            FileWriter f =new FileWriter(file);
            f.write(ArrayList.toString());
            f.flush();
            f.close();
        }catch(IOException e){
            System.out.println("Cant save the file");
        }
    }
    

    public JSONArray readFile(String file){
        JSONParser jsonParser = new JSONParser();
        JSONArray json;
        try {
            Object ob = jsonParser.parse(new FileReader(file));
            json= (JSONArray) ob;
            return json;
        } catch (ParseException|IOException e) {
            json=new JSONArray();
            return json;
        } 
        
    }

    @SuppressWarnings("unchecked")
    public JSONObject createLoc(Local local){
        JSONObject l = new JSONObject();
        l.put("référence", local.getRef());
        l.put("localisation", local.getLocalisation());
        l.put("prix", local.getPrix());
        l.put("taux", local.getTauxLoc());
        l.put("type", local.getType());
        if (local.getType() == "Chambre") {
            l.put("dimension", ((Chambre) local).getDimension());
        } else if (local.getType() == "Appartement") {
            l.put("nbrePiece", ((Appartement) local).getNbrePieces());
            ArrayList<Local> loc = ((Appartement) local).getListLocal();
            if (!loc.isEmpty()) {
                JSONArray chamb = new JSONArray();
                for (Local r : loc) {
                    JSONObject c = new JSONObject();
                    c.put("dimension", ((Chambre) r).getDimension());
                    chamb.add(c);
                }
                l.put("Pieces", chamb);
            }
        }
        
        return l;
    }

    @SuppressWarnings("unchecked")
    public JSONObject createCli(Client client){
        JSONObject cli = new JSONObject();
        cli.put("nci", client.getNci());
        cli.put("nomComplet", client.getNomComplet());
        cli.put("tel", client.getTel());
        cli.put("adresse", client.getAdresse());
        cli.put("email", client.getEmail());
        ArrayList<Reservation> reservation = client.getListReservations();
        if (!reservation.isEmpty()) {
            JSONArray reser = new JSONArray();
            for (Reservation res : reservation) {
                JSONObject r = new JSONObject();
                r.put("date", res.getDate().toString());
                r.put("durée", res.getDure());
                r.put("état", res.getEtat());
                r.put("id", res.getId());
                reser.add(r);
            }
            cli.put("réservations", reser);
        }
        addInFile("Client.json");
        return cli;
    }

    //ajouter  dans fichier
    @SuppressWarnings("unchecked")
    public void addInFile(String file){
        JSONArray data = readFile(file);
        if(file.compareTo("Local.json")==0){
            for (Local local : listLocal) {
                JSONObject l = createLoc(local);
                data.add(l);
            }
        }else if (file.compareTo("Client.json")==0){
            for(Client client:listClients){
                JSONObject cli= createCli(client);
                data.add(cli);
            }
        }else if(file.compareTo("Reservation.json")==0){
            for(Reservation reservation:listReservations){
                JSONObject res = new JSONObject();
                Local local= reservation.getLocal();
                addReservation(local.getRef());
                data.add(res);
            }
            
        }
        
        try {
            this.saveFile(data, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Client searchClient(String nci) {
        this.readFile("Clients.json");
        for (Object o : json) {
            if (nci==(((Client) o).getNci())) {
                return (Client)o;
            }
        }
        return null;
    }
    public Local searchRefLocal(String ref) {  
       Local local = new Local();
        int p = listLocal.indexOf(local);
        if (p != -1) {
            return listLocal.get(p);
        }
        return null;
    } 

    //a refaire
    @SuppressWarnings("unchecked")
    public void addReservation(String ref){
        Reservation Reservation=new Reservation();
        JSONObject res = new JSONObject();
                res.put("date", Reservation.getDate().toString());
                res.put("duree", Reservation.getDure());
                res.put("etat", Reservation.getEtat());
                res.put("id", Reservation.getId());
                Client client = Reservation.getClient();
                JSONObject cli = createCli(client);
                Local local= Reservation.getLocal();
                JSONObject loc=createLoc(local);
                res.put("client",cli);
                res.put("local",loc);
    }
    public void addLocal(Local local) {
        listLocal.add(local);
        addInFile("Local.json");
    }
    @SuppressWarnings("unchecked")
    public JSONArray listerLocauxDispo() {

        JSONArray lList = this.readFile("Local.json");
        JSONArray lListR = this.readFile("Reservation.json");
        JSONArray data = new JSONArray();
        JSONArray dataR = new JSONArray();
        for(int i=1 ;i<data.size();i++){
            JSONObject local = (JSONObject) lList.get(i);
            for(int j=1 ;j<dataR.size();j++){
            JSONObject localR = (JSONObject) lListR.get(j);
                if (local.equals(localR)) {
                    System.out.println("Les locaux disponibles " + "\n\n");
                    System.out.println("\t\t" + local);
                    data.add(local);
                }
            }
        }
        return data;
    }
    @SuppressWarnings("unchecked")
    public void annulerReservation(int id) {
        JSONArray dataReser = new JSONArray();
        JSONArray lList = this.readFile("Reservation.json");
        for (int i = 1; i < dataReser.size(); i++) {
            JSONObject res = (JSONObject) lList.get(i);
            int idRes = (int) res.get("id");
            if (id!=idRes) {
                dataReser.add(res);
                addInFile("Reservation.json");
            
            }
        }
    }
    @SuppressWarnings("unchecked")
    public JSONArray reservationByClient (String nci){
        JSONArray dataReser = new JSONArray();
        JSONArray lList = this.readFile("Reservation.json");

        for (int i = 1; i < dataReser.size(); i++) {
            JSONObject res = (JSONObject) lList.get(i);
            JSONObject ob = (JSONObject) res.get("client");
            JSONObject obL = (JSONObject) res.get("local");
            String nc = (String) ob.get("nci").toString();
            if (nc == nci) {
                Object id = res.get("id").toString();
                Object duree = res.get("duree");
                Object etat = res.get("etat");
                Object date = res.get("date");
                JSONObject Obt = new JSONObject();
                Obt.put("id", id);
                Obt.put("duree", duree);
                Obt.put("etat", etat);
                Obt.put("date", date);
                JSONObject Ob = new JSONObject();
                Ob.put("reservation", Obt);
                Ob.put("local", obL);
                dataReser.add(Ob);
                
            }
        }
        return dataReser;
    }
    @SuppressWarnings("unchecked")   
    public JSONArray listerLocal(String type) {
        JSONArray data = new JSONArray();
        JSONArray lList = this.readFile("Local.json");
        for(int i=1 ;i<data.size();i++){
            JSONObject local = (JSONObject) lList.get(i);
            String typeLocal = (String) local.get("type");
            if (type.compareToIgnoreCase(typeLocal) == 0) {
                data.add(local);
            }
        }
        return data;
    }
    @SuppressWarnings("unchecked")
    public JSONArray searchLocalByRef(String ref) {
		JSONArray data = new JSONArray();
		JSONArray lList = this.readFile("Local.json");
        for(int i=1 ;i<data.size();i++){
            JSONObject local = (JSONObject) lList.get(i);
            String type = (String) local.get("référence");
            if (type.compareToIgnoreCase(ref) == 0) {
                data.add(local);
            }
	    }
    return data;
    }
}