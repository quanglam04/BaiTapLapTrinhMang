package BaiTap.TCP.TCP_YFatXGlH;

import BaiTap.Config.Config;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class Client{
  public static final int SERVER_PORT = 2208;
  public static final String SERVER_HOST = Config.SERVER_HOST;
  public static void main(String[] args) throws Exception {
    Socket socket = new Socket(SERVER_HOST,SERVER_PORT);
    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    String message = Config.STUDENT_CODE+";YFatXGlH";
    writer.write(message);
    writer.newLine();
    writer.flush();

    String data = reader.readLine();
    System.out.println("Data from Server  " + data);
    LinkedHashSet<Character> set = new LinkedHashSet<>();
    LinkedHashMap<Character,Integer>map = new LinkedHashMap<>();
    for(char c : data.toCharArray())
        if(Character.isLetterOrDigit(c))
        {
          set.add(c);
          map.put(c,map.getOrDefault(c,0)+1);
        }

    StringBuilder kq = new StringBuilder();
    for(char c: set)
      if(map.get(c) > 1)
        kq.append(c+":"+map.get(c)+",");
    System.out.println("Result: "+kq.toString());
    writer.write(kq.toString());
    writer.newLine();
    writer.flush();

  }
}