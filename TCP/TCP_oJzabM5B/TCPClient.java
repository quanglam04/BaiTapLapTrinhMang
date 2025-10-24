package BaiTap.TCP.TCP_oJzabM5B;

import BaiTap.Config.Config;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class TCPClient {
  public static void main(String[] args) throws Exception {
    Socket socket = new Socket(Config.SERVER_HOST,2207);
    DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
    DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

    String message = "B22DCCN482;oJzabM5B";
    dataOutputStream.writeUTF(message);
    dataOutputStream.flush();

    int a = dataInputStream.readInt();
    int b = dataInputStream.readInt();

    dataOutputStream.writeInt(a+b);
    dataOutputStream.flush();
    dataOutputStream.writeInt(a*b);
    dataOutputStream.flush();
  }
}
