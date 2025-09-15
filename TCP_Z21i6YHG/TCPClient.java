package BaiTap.TCP_Z21i6YHG;

import BaiTap.Config.Config;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

public class TCPClient {
  private static final int SERVER_PORT = 2208;
  private static final String SERVER_HOST = Config.SERVER_HOST;
  public static void main(String[] args) {
    try{
      Socket socket = new Socket(SERVER_HOST,SERVER_PORT);
      BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

      String qCode = "Z21i6YHG";
      String initMessage = Config.STUDENT_CODE+";"+qCode;

      writer.write(initMessage);
      writer.newLine();
      writer.flush();

      String data = reader.readLine();
      System.out.println("Data from Server: "+data);
      String result = process(data);
      System.out.println("Data send to Server: "+result);
      writer.write(result);
      writer.newLine();
      writer.flush();

      socket.close();
      reader.close();
      writer.close();
    }
    catch (UnknownHostException exception){
      System.out.println(exception.getMessage());
    }
    catch (IOException exception){
      System.out.println(exception.getMessage());
    }

  }

  public static String process(String data){
    // Đảo ngược sau đó mã hóa từng từ
    String[] arr = data.split("\\s+");
    StringBuilder sb = new StringBuilder("");
    for(int i=0;i<arr.length;++i){
      sb.append(encode(arr[i]));
      if(i<arr.length-1)
        sb.append(" ");
    }

    return sb.toString();
  }

  public static String encode(String word){
      // hello -> olleh -> ol2eh
    char[] arr = new StringBuilder(word).reverse().toString().toCharArray();
    boolean checkEqual = false;
    StringBuilder encodedString = new StringBuilder();
    for(int i=0;i<arr.length;++i){
      checkEqual = true;
      int count = 1;
      encodedString.append(arr[i]);
      for(int j=i+1;j<arr.length;++j)
        if(arr[j] != arr[i])
          break;
        else count++;
      if(count>1){
        i+=count - 1;
        encodedString.append(String.valueOf(count));
      }

    }

    return encodedString.toString();


  }

}
