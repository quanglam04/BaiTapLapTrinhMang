package BaiTap.TCP_Me6jetPg;

import BaiTap.Config.Config;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

/**
 * @author Trịnh Quang Lâm
 * @since 10/09/2025<br>
 * [qCode]: Me6jetPg<br>
 */

public class Client{
  private static final int SERVER_PORT = 2206;
  private static final String SERVER_HOST = Config.SERVER_HOST;
  public static void main(String[] args) throws IOException {
      Socket socket = null;
    InputStream ip = null;
    OutputStream os = null;

    socket = new Socket(SERVER_HOST,SERVER_PORT);
    ip = socket.getInputStream();
    os = socket.getOutputStream();

    String qCode = "Me6jetPg";
    String message = Config.STUDENT_CODE+";"+qCode;

    os.write(message.getBytes());
    os.flush();
    System.out.println("Đã gửi message lên server");

    byte[] buffer = new byte[1024];
    int bytesRead = ip.read(buffer);
    if(bytesRead!=-1){
      String data = new String(buffer,0,bytesRead);
      System.out.println(">>>> Data từ server: "+data);
      String result = process(data);
      os.write(result.getBytes());
      os.flush();
      System.out.println("Đã xử lý xong và gửi kết quả lên server: "+result);
    }
  }

  public static boolean isPrime(int x){
    if(x<2) return false;
    for(int i=2;i*i<=x;++i)
      if(x%i==0)
        return false;
    return true;
  }

  public static String process(String data){
    String[] arr = data.split(",");
    return String.valueOf(Arrays.stream(arr)
        .mapToInt(Integer::parseInt)
        .filter((x) -> isPrime(x))
        .sum());

  }
}

