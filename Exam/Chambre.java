public class Chambre extends Local {
    private int dimension;

    public Chambre(){    
        this.type = "Chambre";
    }

    public Chambre( String localisation, int prix, int tauxLoc, int dimension) {
        super(localisation, prix, tauxLoc);
        this.dimension = dimension;
        this.type = "Chambre";
    }

    public Chambre(String ref, String localisation, int prix, int tauxLoc, int dimension) {
        super(ref, localisation, prix, tauxLoc);
        this.dimension = dimension;
        this.type = "Chambre";
    }
    public int getDimension() {
        return dimension;
    }
    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    @Override
    public  void cout(int prix, int tauxLoc) {
         cout = getCout();
    }
    
    @Override
    public String afficher() {
        
        return super.afficher()
        +"\n Dimention"+getDimension()
        +"\n Cout : "+getCout();//////////////////////////
    }


    
}
