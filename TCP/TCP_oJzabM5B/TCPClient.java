package BaiTap.TCP.TCP_oJzabM5B;

import BaiTap.Config.Config;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class TCPClient {
  public static final int SERVER_PORT = 2207;
  public static final String SERVER_HOST = Config.SERVER_HOST;
  public static void main(String[] args) throws Exception {
    Socket socket = new Socket(SERVER_HOST,SERVER_PORT);
    DataInputStream dis = new DataInputStream(socket.getInputStream());
    DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

    String message = Config.STUDENT_CODE+";oJzabM5B";
    dos.writeUTF(message);
    dos.flush();
    System.out.println("Send data to server");

    int a = dis.readInt();
    int b = dis.readInt();
    int c = a + b;
    int d = a*b;
    dos.writeInt(c);
    dos.flush();
    dos.writeInt(d);
    dos.flush();

    System.out.println("Send result to Server");
  }
}
