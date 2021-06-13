import java.util.ArrayList;

public class Client {
    private String nci;
    private String nomComplet;
    private String tel;
    private String adresse;
    private String mail;

    public ArrayList<Local> listLocal = new ArrayList<Local>();

    private ArrayList<Reservation> listReservations = new ArrayList<Reservation>();
    public void addReservation(Reservation reservation) {
        Local local = new Local();
        int pos = listLocal.indexOf(local);
        if (pos != -1) {
            listReservations.add(reservation);
        }
    }
    public ArrayList<Reservation> getListReservations() {
        return listReservations;
    }
    public void setListReservations(Local local) {
    }
    public Client(){

    }
    public Client(String nci, String nomComplet, String tel, String adresse, String mail) {
        this.nci = nci;
        this.nomComplet = nomComplet;
        this.tel = tel;
        this.adresse = adresse;
        this.mail = mail;
    }

    public String getAdresse() {
        return adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public String getNci() {
        return nci;
    }
    public void setNci(String nci) {
        this.nci = nci;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getNomComplet() {
        return nomComplet;
    }
    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }
    public String getEmail() {
        return mail;
    }
    public void setEmail(String email) {
        this.mail = email;
    }
    public void addLocal(Local local){
        listLocal.add(local);
    }
	public String afficher() {

		return "nci : "+getNci()
        +" nom : "+getNomComplet()
        +"telephone : "+getTel()
        +"adresse : "+getAdresse()
        +" email : "+getEmail() ;
	}


    
}
