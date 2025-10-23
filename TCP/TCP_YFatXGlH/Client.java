package BaiTap.TCP.TCP_YFatXGlH;

import BaiTap.Config.Config;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Client {
  public static final int SERVER_PORT = 2208;
  public static final String SERVER_HOST = Config.SERVER_HOST;
  public static void main(String[] args) throws Exception {
    Socket socket = new Socket(SERVER_HOST,SERVER_PORT);
    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    String message = "B22DCCN482;YFatXGlH";
    writer.write(message);
    writer.newLine();
    writer.flush();
    System.out.println("Send data to Server");
    List<Character> list = new ArrayList<>();
    String dataString = reader.readLine();
    for(char c: dataString.toCharArray())
      list.add(c);
    LinkedHashMap<Character,Integer> map = new LinkedHashMap();
    LinkedHashSet<Character> set = new LinkedHashSet<>(list);

    for(char c : dataString.toCharArray())
        if(Character.isLetterOrDigit(c))
        {
          map.put(c,map.getOrDefault(c,0)+1);
        }

    StringBuilder result = new StringBuilder("");
    for(char c: set)
      if(Character.isLetterOrDigit(c) &&  map.get(c)>1) result.append(c+":"+map.get(c)+",");
    System.out.println("Data to Server: "+result.toString());
    writer.write(result.toString());
    writer.newLine();
    writer.flush();
  }

}
