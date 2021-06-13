
import java.util.*;
public class Validator {
    static Scanner sc = new Scanner(System.in);
    public static Client c = new Client();
    
    
   public static boolean isNci(String nci){
     //int nci = c.getNci();
    String reg="[12](?:[\\s.-])*01(?:[\\s.-])*(?:[\\s.-])*\\d{4}(?:[\\s.-])-((0\\d)|1[012])(([012]\\d)|(3[01]))(?:[\\s.-])*\\d{5}(?:[\\s.-])*\\d";
    /*if (Pattern.compile(reg).matcher(nci) == null) {
      return false;
    }*/
      return nci.matches(reg);
    }

  public static boolean isTel(String tel){
    
    //String rege = "#^7[78]/s//d{3}/s//d{2}/s//d{2}$#";
    String regex="(?:(?:\\+|00)221)*(?:[\\s.-])*7[78](?:[\\s.-])*\\d{3}(?:[\\s.-]*\\d{2}){2}";
    
    /*if(Pattern.compile(regex).matcher(telString)==null){
      return false;
    }*/
   return tel.matches(regex);
  }

  public static boolean isMail(String mail) {
      String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
      return mail.matches(regex);
      
  }



}
