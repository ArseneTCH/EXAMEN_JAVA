import java.util.ArrayList;

public class Local {
    protected String ref;
    protected String localisation;
    protected int prix;
    protected int tauxLoc; 
    protected String type;
    protected int cout;
    private final int FORMAT = 4;
    private int nombreLocal;

    private Client client = new Client();
    private ArrayList<Local> listLocal = new ArrayList<Local>();
    //private ArrayList<Local> listAppartement = new ArrayList<Local>();
    private ArrayList<Local> listReservations = new ArrayList<Local>();
    
    public void addReservation(Reservation reservation){
        Local local = new Local();
        int pos = listLocal.indexOf(local);
        if (pos != -1) {
            listReservations.add(local);
        }
    }
    public ArrayList<Local> getListReservations(){
        return listReservations;
    }
    public void setListReservations(Local local){}
    /* private Local appartement = new Appartement();
    public Local getAppartement(){
        return appartement;
    }
    public void setAppartement(Local appartement){
        this.appartement=appartement;
        ((Appartement) appartement).addLocal(this);
    } */
    
   /*  public ArrayList<Local> getListerAppartement() {
        return listAppartement;
    }
    public ArrayList<Local> listerAppartement(List<Local> listLocal) {
        for (Local ch : listLocal) {
            if (ch instanceof Appartement) {
                listAppartement.add(ch);
            }
        }
        return listAppartement;
    } */
   // private Local chambre =new Chambre();
    
    //algorithme de formatage de reference
    private String generatRef() {
        String nombreZero = "";
        String nombreLocalString = String.valueOf(++nombreLocal);
        for (int i = 1; i <= (FORMAT - nombreLocalString.length()); i++) {
            nombreZero += "0";
        }
        return "Ref"+nombreZero + nombreLocalString;
    }
    //Constructeurs
    public Local(){
        ref= generatRef();
    }
    public Local( String localisation, int prix, int tauxLoc) {
        ref = generatRef();
        this.localisation = localisation;
        this.prix = prix;
        this.tauxLoc = tauxLoc;
    }
    public Local(String ref, String localisation, int prix, int tauxLoc) {
        ref = generatRef();
        this.localisation = localisation;
        this.prix = prix;
        this.tauxLoc = tauxLoc;
    }


    //Getteurs et setteurs
    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getRef() {
        return ref;
    }
    
    public void setRef(String  ref) {
        this.ref = ref;
    }
    public int getTauxLoc() {
        return tauxLoc;
    }
    public void setTauxLoc(int tauxLoc) {
        this.tauxLoc = tauxLoc;
    }
    public int getCout() {
        return cout;
    }
    public void setCout(int cout) {
        this.cout = cout;
    }
    public void cout(int prix,int tauxLoc){

        cout=prix*((100+tauxLoc)/100);
    }
    
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
        client.addLocal(this);
    }
    
    public String getType() {
          return type;
    }

    //methode metier afficher
    public String afficher() {
        
        return "Ref :"+getRef()
        +"\n Localisation :"+getLocalisation()
        +"\n Prix :"+getPrix();
    }
    
}