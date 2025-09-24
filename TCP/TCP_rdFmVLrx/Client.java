package BaiTap.TCP.TCP_rdFmVLrx;

import BaiTap.Config.Config;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;

public class Client{
  private static final int SERVER_PORT = 2206;
  private static final String SERVER_HOST = Config.SERVER_HOST;
  public static void main(String[] args) {
    try{
      Socket socket = new Socket(SERVER_HOST,SERVER_PORT);
      InputStream ip = socket.getInputStream();
      OutputStream os = socket.getOutputStream();

      String qCode = "rdFmVLrx";
      String initMessage = Config.STUDENT_CODE+";"+qCode;

      os.write(initMessage.getBytes());
      os.flush();

      byte[] buffer = new byte[1024];
      int convertData = ip.read(buffer);
      if(convertData != -1){
        String receivedData = new String(buffer,0,convertData);
        System.out.println("Data from Server: "+receivedData);
        String dataAfterProcess = process(receivedData);

        os.write(dataAfterProcess.getBytes());
        System.out.println("Data send to server: "+dataAfterProcess);
      }

      socket.close();
    }catch (UnknownHostException exception){
      System.out.println(exception.getMessage());
    }
    catch (IOException exception){
      System.out.println(exception.getMessage());
    }
  }

  public static String process(String data) {
    return String.valueOf(Arrays.stream(data.split(",")).mapToInt(Integer::parseInt).
        filter((x) -> isPrime(x)).sum());
//    String[] arr = data.split(",");
//    System.out.println(Arrays.toString(arr));
//    int sum = 0;
//
//    for(String n: arr){
//      if(isPrime(Integer.parseInt(n)))
//        sum+=Integer.parseInt(n);
//    }
//    return String.valueOf(sum);
  }

  public static boolean isPrime(int n){
    if (n<2)
      return false;
    for(int i=2;i<=Math.sqrt(n);++i)
      if (n%i==0)
        return false;
    return true;
  }
}