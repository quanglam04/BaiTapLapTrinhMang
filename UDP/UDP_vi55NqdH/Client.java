package BaiTap.UDP.UDP_vi55NqdH;

import BaiTap.Config.Config;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class Client {
  private static final int SERVER_PORT = 2207;
  private static final String SERVER_HOST = Config.SERVER_HOST;
  public static void main(String[] args) throws Exception {
    // khai bao
    DatagramSocket dSocket = new DatagramSocket();
    InetAddress IPAddress = InetAddress.getByName(SERVER_HOST);

    // gui len server
    String initMessage = ";"+Config.STUDENT_CODE+";"+"vi55NqdH";
    DatagramPacket dpSend = new DatagramPacket(initMessage.getBytes(),initMessage.length(),IPAddress,SERVER_PORT);
    dSocket.send(dpSend);

    // nhan du lieu ve
    byte[] data = new byte[1024];
    DatagramPacket dpReceive = new DatagramPacket(data,data.length);
    dSocket.receive(dpReceive);
    String dataString = new String(dpReceive.getData());
    System.out.println("Data receive from Server: "+dataString.trim());

    // xu ly
    String[] arr = dataString.trim().split(";");
    String requestId = arr[0];
    int[] arrInt = Arrays.stream(arr[1].split(",")).mapToInt(Integer::parseInt).toArray();
    Arrays.sort(arrInt);
    System.out.println("Sau khi sort: "+Arrays.toString(arrInt));

    int min = arrInt[0];
    int max = arrInt[arrInt.length-1];
    StringBuilder result = new StringBuilder("");
    result.append(requestId).append(";").append(max+","+min);

    // gui len server
    DatagramPacket dpSend2 = new DatagramPacket(result.toString().getBytes(),result.toString().length(),IPAddress,SERVER_PORT);
    dSocket.send(dpSend2);



  }
}