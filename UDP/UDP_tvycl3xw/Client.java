package BaiTap.UDP.UDP_tvycl3xw;

import BaiTap.Config.Config;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
  private static final String SERVER_HOST = Config.SERVER_HOST;
  private static final int SERVER_PORT = 2208;

  public static void main(String[] args) throws Exception {
    DatagramSocket dSocket = new DatagramSocket();
    InetAddress inetAddress = InetAddress.getByName(SERVER_HOST);

    String initMessage = ";"+Config.STUDENT_CODE+";"+"tvycl3xw";
    DatagramPacket dpSend = new DatagramPacket(initMessage.getBytes(),initMessage.length(),inetAddress,SERVER_PORT);
    dSocket.send(dpSend);
    System.out.println("Send to Server");

    byte[] buffer = new byte[1024];
    DatagramPacket dpReceive = new DatagramPacket(buffer,buffer.length);
    dSocket.receive(dpReceive);
    String dataString = new String(dpReceive.getData()).trim();
    System.out.println("Data from Server: "+dataString);

    String[] arr = dataString.split(";");
    String requestId = arr[0];
    String[] words = arr[1].split("\\s+");
    StringBuilder result = new StringBuilder(requestId+";");
    for(String word: words){
      result.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1).toLowerCase()).append(" ");

    }

    DatagramPacket dpSend2 = new DatagramPacket(result.toString().trim().getBytes(),result.toString().trim().length(),inetAddress,SERVER_PORT);
    dSocket.send(dpSend2);
    System.out.println("Send result to Server: "+result.toString().trim());

  }

}
