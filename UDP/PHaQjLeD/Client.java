package BaiTap.UDP.PHaQjLeD;

import BaiTap.Config.Config;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class Client {
  private static final String SERVER_HOST = Config.SERVER_HOST;
  private static int SERVER_PORT = 2208;

  public static void main(String[] args) throws Exception {
    // khai bao
    DatagramSocket dSocket = new DatagramSocket();
    InetAddress IPAddress = InetAddress.getByName(SERVER_HOST);

    // gui message len server
    String initMessage = ";"+Config.STUDENT_CODE+";"+"PHaQjLeD";
    DatagramPacket dpSend = new DatagramPacket(initMessage.getBytes(),initMessage.length(),IPAddress,SERVER_PORT);
    dSocket.send(dpSend);

    // lay data ve
    byte[] data = new byte[1024];
    DatagramPacket dpReceive = new DatagramPacket(data,data.length);
    dSocket.receive(dpReceive);
    String dataString = new String(dpReceive.getData()).trim();
    System.out.println("Data from Server: "+dataString);

    // xu ly
    String[] arr = dataString.split(";");
    String requestId = arr[0];
    String[] words = arr[1].split("\\s+");
    Arrays.sort(words, (a,b) -> b.compareToIgnoreCase(a));
    System.out.println("After process: "+Arrays.toString(words));
    String sb = requestId+";"+String.join(",",words);

    // gui ket qua len server
    DatagramPacket dSend2 = new DatagramPacket(sb.toString().getBytes(),sb.toString().length(),IPAddress,SERVER_PORT);
    dSocket.send(dSend2);
    System.out.println(sb.toString());
  }
}
