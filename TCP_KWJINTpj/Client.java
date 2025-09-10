package BaiTap.TCP_KWJINTpj;

import BaiTap.Config.Config;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Client {
  private static int SERVER_PORT = 2208;
  private static String SERVER_HOST = Config.SERVER_HOST;

  public static void main(String[] args) throws IOException {
    Socket socket = new Socket(SERVER_HOST,SERVER_PORT);

    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

    String qCode = "KWJINTpj";
    String message = Config.STUDENT_CODE+";"+qCode;

    writer.write(message);
    writer.newLine();
    writer.flush();

    String data = reader.readLine();
    System.out.println("Nhận data từ server: "+data);
    String result = process(data);
    writer.write(result);
    writer.newLine();
    writer.flush();
    System.out.println("Đã gửi kết quả lên server: "+result);

    socket.close();
  }
    public static String process(String data){
      ArrayList<String> list = new ArrayList<>(Arrays.asList(data.split("\\s+")));
      Collections.sort(list,(str1,str2) -> {
        if(str1.length() == str2.length())
            return 0;
        return str1.length()-str2.length();
      });
      StringBuilder result = new StringBuilder("");
      for(String s: list)
      {
        result.append(s);
        if(!s.equals(list.get(list.size()-1)))
          result.append(", ");
      }
      return result.toString();
    }
}
