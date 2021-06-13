import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Reservation {
    private Date date;
    private Time dure;
    private boolean etat=false;
    private int id;
    
    private Local local = new Local();
    public Local getLocal(){
        return local;
    }
    public void setLocal(Local local){
        this.local=local;
        local.addReservation(this);
    } 
    private Client client = new Client();
    
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
        client.addReservation(this);
    }

    ArrayList<Reservation> listReservations =new ArrayList<Reservation>();
    public Reservation(){
        ++id;   
    }
    public Reservation(Date date, Time dure, boolean etat, int id) {
        this.setDate(date);
        this.setDure(dure);
        this.setEtat(etat);
        ++id;
    }
    public void addReservation(Reservation reservation){
        listReservations.add(reservation);
    }

    public boolean getEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public Time getDure() {
        return dure;
    }

    public void setDure(Time dure) {
        this.dure = dure;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        ++id;
    }

    public String afficher(){
        return "id :"+getId()+"\t"  ;
    }
 


}
