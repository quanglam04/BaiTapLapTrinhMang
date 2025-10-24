package BaiTap.RMI.RMI_MwySSamM;

import BaiTap.Config.Config;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.LinkedHashSet;

public class Client {

  public static void main(String[] args) throws Exception {
    Registry registry = LocateRegistry.getRegistry(Config.SERVER_HOST,1099);
    CharacterService characterService = (CharacterService) registry.lookup("RMICharacterService");

    String data = characterService.requestCharacter("B22DCCN482","MwySSamM");
    System.out.println(data);
    LinkedHashSet<Character> set = new LinkedHashSet<>();
    HashMap<Character,Integer> map = new HashMap<>();
    for(char c : data.toCharArray())
    {
      set.add(c);
      map.put(c,map.getOrDefault(c,0)+1);
    }

    StringBuilder kq = new StringBuilder();
    for(char c : set)
      kq.append(String.format("\"%c\": %d, ",c,map.get(c)));
    String kq1 = "{" + kq.delete(kq.length()-2,kq.length()).toString() +"}";
    characterService.submitCharacter("B22DCCN482","MwySSamM",kq1);
    System.out.println("ok");
  }

}
