import java.util.ArrayList;


public class Appartement extends Local{
    private int nbrePieces;
   private ArrayList<Local> listLocal = new ArrayList<Local>();
   public ArrayList<Local> getListLocal(){
       return listLocal;
   }
   public void setListLocal(){}
   
   public void addLocal(Local local) {
       listLocal.add(local);
   }
    public Appartement() {

    }
    public Appartement(String localisation, int prix, int tauxLoc, int nbrePieces) {
        super(localisation, prix, tauxLoc);
        this.nbrePieces = nbrePieces;
        this.type="Appartement";
    }
    public Appartement(String ref, String localisation, int prix, int tauxLoc,int nbrePieces) {
        super(ref, localisation, prix, tauxLoc);
        this.nbrePieces = nbrePieces;
        this.type = "Appartement";
    }
    public int getNbrePieces() {
        return nbrePieces;
    }
    public void setNbrePieces(int nbrePieces) {
        this.nbrePieces = nbrePieces;
    }
    @Override
    public  void cout (int prix, int tauxLoc) {
         cout = getCout();
    }
    @Override
    public String afficher(){
        return super.afficher()
        +" \n Nombre de pieces : "+getNbrePieces()
        +" \n Cout : "+getCout();
    }
}
