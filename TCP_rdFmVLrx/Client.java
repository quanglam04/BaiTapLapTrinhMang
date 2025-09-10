package BaiTap.TCP_rdFmVLrx;

import BaiTap.Config.Config;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author Trịnh Quang Lâm
 * @since 09/09/2025<br>
 * [qCode]: rdFmVLrx<br>
 */


public class Client {
  private static final int SERVER_PORT = 2207;
  private static final String SERVER_HOST = Config.SERVER_HOST;

  public static void main(String[] args) throws IOException {
    Socket socket = null;
    DataInputStream dis = null;
    DataOutputStream dos = null;
    try{
      socket = new Socket(SERVER_HOST,SERVER_PORT);
      dis = new DataInputStream(socket.getInputStream());
      dos = new DataOutputStream(socket.getOutputStream());

      String qCode = "rdFmVLrx";
      String initialMessage = Config.STUDENT_CODE+";"+qCode;

      dos.writeUTF(initialMessage);
      dos.flush();

      int a = dis.readInt();
      int b = dis.readInt();
      int tong = a+b;
      int tich = a*b;


      dos.write(tong);
      dos.flush();

      dos.write(tich);
      dos.flush();

      System.out.println("Kết quả: "+tong+" "+tich);

    } catch (UnknownHostException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    finally {
      if(dos!= null){
        dos.close();
      }
      if(dis!=null){
        dis.close();
      }
      if(socket!=null){
        socket.close();
      }

    }
  }


}
