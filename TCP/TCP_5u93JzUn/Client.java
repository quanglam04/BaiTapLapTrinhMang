package BaiTap.TCP.TCP_5u93JzUn;

import BaiTap.Config.Config;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
  public static final String SERVER_HOST = Config.SERVER_HOST;
  public static final int SERVER_PORT = 2209;
  public static void main(String[] args) throws Exception {
    Socket socket = new Socket(SERVER_HOST,SERVER_PORT);
    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
    ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

    String message = "B22DCCN482;5u93JzUn";
    objectOutputStream.writeObject(message);
    objectOutputStream.flush();

    // doc data
    Laptop laptop = (Laptop) objectInputStream.readObject();
    System.out.println("Data from Server: "+laptop.toString());
    laptop.process();
    objectOutputStream.writeObject(laptop);
    System.out.println("Send Result to Server:" +laptop.toString());

  }
}
