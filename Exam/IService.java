import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface IService {
    public void saveFile(JSONArray ArrayList, String file) throws IOException;

    public JSONArray readFile(String file);

    public JSONObject createLoc(Local local);

    public JSONObject createCli(Client client);

    public void addInFile(String file);

    public Client searchClient(String nci);

    public Local searchRefLocal(String ref);

    public void addReservation(String ref);

    public void addLocal(Local local);

    public JSONArray listerLocauxDispo();

    public void annulerReservation(int id);

    public JSONArray reservationByClient(String nci);

    public JSONArray listerLocal(String type);

    public JSONArray searchLocalByRef(String ref);
}