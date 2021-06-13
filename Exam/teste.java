
//import java.util.*;
import java.util.regex.*;
public class teste {

  public static void main(String[] args) {
        // Scanner sc = new Scanner(System.in);
       /* System.out.println("Entrer le car : ");
        String p = sc.next();
        ArrayList<String> cars = new ArrayList<String>();
        cars.add(p);
        for (int i = 0; i < cars.size(); i++) {
          System.out.println("voici le car"+cars.get(i));
        }*/

      //String regex="[12](?:[\\s.-]*)01(?:[\\s.-]*)(?:[\\s.-]*)\\d{4}(?:[\\s.-]*)((0\\d)|1[012])(([012]\\d)|(3[01]))(?:[\\s.-]*)\\d{5}(?:[\\s.-]*)\\d";
      //String regex = "[12](?:[\\s.-])*01(?:[\\s.-])*(?:[\\s.-])*\\d{4}(?:[\\s.-])*((0\\d)|1[012])(([012]\\d)|(3[01]))(?:[\\s.-])*\\d{5}(?:[\\s.-])*\\d";

       String regex = "(?:(?:\\+|00)221)*(?:[\\s.-])*7[78](?:[\\s.-])*\\d{3}(?:[\\s.-]*\\d{2}){2}";
      String tel = "+22178145 21 4";
      System.out.println(Pattern.matches(regex, tel));
      
     /* if (Pattern.compile(regex).matcher(tel)==null) {
        System.out.println("faux");
      }else{
        System.out.println("vrai");
      }

  System.out.println("un nombeesrhhg");
  System.out.println(" un nbre >10");
  int x=sc.nextInt();
  while (x < 10){
    System.out.println("le nbr est <10");
    x=sc.nextInt();
  } ;*/
}
      
}

