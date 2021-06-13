import java.util.Scanner;

import org.json.simple.JSONArray;

public class Main { 
    public static Scanner sc = new Scanner(System.in);
    private static Client client;
    public static void main(String[] args)  {
        Service service= new Service();
        String choix;
        Reservation reservation=new Reservation();
        do {
            choix = menu();
            switch (choix) {
            case "1":
               
                System.out.println("Entrer la Localisation : ");
                String localisation = sc.nextLine();

                System.out.println("Entrer le prix : ");
                String prix = sc.nextLine();

                System.out.println("Entrer le taux : ");
                String tauxLoc = sc.nextLine();
                
                String typeLocal;
                int typeLocalInt;
                do {
                    System.out.println("Quel type de local voulez vous ajouter" + "\n 1-Chambre" + "\n 2-Appartement");
                    typeLocal = sc.nextLine();
                    typeLocalInt = Integer.parseInt(typeLocal);
                } while (typeLocalInt != 1 && typeLocalInt != 2);
                Local local;
                if (typeLocalInt == 1) {
                    System.out.println("entrer les Dimentions");
                    int dimention = sc.nextInt();
                    local = new Chambre( localisation,Integer.parseInt(prix),Integer.parseInt(tauxLoc),dimention);
                   
                } else {
                    String nbrePieces;
                    do {
                        System.out.println("enter le nombre de piece. Minimum de piece 3.");
                        nbrePieces = sc.nextLine();
                    } while (Integer.parseInt(nbrePieces)<3);
                    local = new Appartement(localisation, Integer.parseInt(prix), Integer.parseInt(tauxLoc),Integer.parseInt(nbrePieces));
                    for (int i = 1; i <= Integer.parseInt(nbrePieces); i++) {
                        System.out.println("entrer les Dimentions de la piece "+i);
                        String dimen =sc.nextLine(); 
                        local=new Chambre(localisation, Integer.parseInt(prix),Integer.parseInt(tauxLoc), Integer.parseInt(dimen));
                        service.createLoc(local);
                    }  
                }
               local.setClient(client);// affectation du client au local
                service.createLoc(local);
                break;
            case "2":
                do {
                    System.out.println("Quel type de local voulez vous lister" + "\n 1-Chambre" + "\n 2-Appartement");
                    typeLocal = sc.nextLine();
                    typeLocalInt = Integer.parseInt(typeLocal);
                } while (typeLocalInt != 1 && typeLocalInt != 2);
                if (typeLocalInt == 1) {
                    System.out.println("Les Chambres");
                    service.listerLocal("Chambre");
                }else {
                    System.out.println("Les Appartements");
                    service.listerLocal("Appartement");
                    
                }
                break;
            case "3":

                System.out.println("lister locaux reservé par un client");
                System.out.print("entrer le nci du client : ");
                String nci = sc.nextLine();
                 JSONArray x= service.reservationByClient (nci);
                if (x.isEmpty()) {
                    System.out.println("nci introuvable");
                } else {
                    System.out.println("Donnee du client");
                    System.out.println(x);
                }
                break;
            case "4":
                System.out.print("entrer la reference du local : ");
                String ref = sc.nextLine();
                JSONArray y = service.searchLocalByRef(ref);
                if (y.isEmpty()) {
                    System.out.println("reference introuvable");
                } else {
                    System.out.println("Donnee du local");
                    System.out.println(y);
                }
                break;
            case "5":
                System.out.println("Faire un  reservation : ");
                System.out.print("Entrer le nci");
                nci = sc.nextLine();
                do {
                    System.out.print("Format de nci incorect : ");
                    nci = sc.nextLine();
                } while (!Validator.isNci(nci));
                Client client = service.searchClient(nci);
                if (client == null) {
                    System.out.print("entrer le nom complet : ");
                    String nomComplet = sc.nextLine();

                    System.out.print("entrer le numero de telephone  : ");
                    String tel = sc.nextLine();
                    do{
                        System.out.print("Format de numero incorect : ");
                        tel = sc.nextLine();
                    } while (!Validator.isTel(tel));
                    System.out.print("entrer le mail : ");
                    String mail = sc.nextLine();
                    do{
                        System.out.println("Format de mail incorect : ");
                        mail = sc.nextLine();
                    } while (!Validator.isMail(mail));

                    System.out.print("entrer votre adresse : ");
                    String adresse = sc.nextLine();

                    client = new Client(nci, nomComplet, tel, adresse, mail);
                    service.createCli(client);
                }
                System.out.println("Les Chambres");
                System.out.println(service.listerLocal("Chambre"));

                System.out.println("Les Appartements");
                System.out.println(service.listerLocal("Appartement"));

                System.out.print("entrer la reference du local : ");
                ref = sc.nextLine();
                local=service.searchRefLocal(ref);
               do{
                    System.out.print("Reference inexistant,veillez reesayer : ");
                    ref = sc.nextLine();
                } while (local == null);
                reservation.setClient(client);
                service.addReservation(ref);
                
                break;
            case "6":
                System.out.print("votre Nci :");
                nci=sc.nextLine();
                client = service.searchClient(nci);
                if (client == null) {
                    System.out.println("nci introuvable");
                }else{
                    System.out.println( service.reservationByClient (nci));
                }
                System.out.println("Entrez l'id de la reservation a annuler");
                int id=sc.nextInt();
                service.annulerReservation(id);
            break;
            case "7":
                System.out.println("locaux disponibles");
                JSONArray z =service.listerLocauxDispo();
                System.out.println(z);
                break;
            case "8":
                System.out.println("Merci Pour Votre Confiance.Bye!!!");
            break;
            default:
                System.out.println("Mauvaix choix");   
            }
        } while (choix !="8");
    }
   
    public static String menu () {
        String choix;
        System.out.println("\t \t \t**********menu**********\n" 
        + " 1-Ajouter un Local\n" 
        + " 2-Lister les locaux par type\n"
        + " 3-Lister les locaux reservés par un client \n"
        + " 4-Voir les details d'un local\n"
        + " 5-Faire une reservation\n"
        + " 6-Annuler une reservation\n" 
        + " 7-Lister les locaux disponibles\n"
        + " 8-Quitter");
        System.out.print("votre choix :");
        choix = sc.nextLine();
        return choix;
    }
}
