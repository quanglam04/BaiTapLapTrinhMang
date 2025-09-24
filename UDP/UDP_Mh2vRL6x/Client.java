package BaiTap.UDP.UDP_Mh2vRL6x;

import BaiTap.Config.Config;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class Client {
  private static final String SERVER_HOST = Config.SERVER_HOST;
  private static final int SERVER_PORT = 2207;

  public static void main(String[] args) throws Exception {
    // khoi tao
    DatagramSocket dSocket = new DatagramSocket();
    InetAddress inetAddress = InetAddress.getByName(SERVER_HOST);
    // gui message len server

    String initMessage = ";"+Config.STUDENT_CODE+";Mh2vRL6x";
    DatagramPacket dpSend = new DatagramPacket(initMessage.getBytes(),initMessage.length(),inetAddress,SERVER_PORT);
    dSocket.send(dpSend);
    System.out.println("Send message to Server");
    // lay data ve

    byte[] data = new byte[1024];
    DatagramPacket dpReceive = new DatagramPacket(data,data.length);
    dSocket.receive(dpReceive);
    String dataString = new String(dpReceive.getData()).trim();
    System.out.println("Data from Server: "+dataString);

    // xu ly
    String[] arr = dataString.split(";");
    String requestId = arr[0];
    int[] arrInt = Arrays.stream(arr[1].split(",")).mapToInt(Integer::parseInt).toArray();
    System.out.println(Arrays.toString(arrInt));
    Arrays.sort(arrInt);
    int secondMin = arrInt[1],secondMax = arrInt[arrInt.length-2];
    StringBuilder sb = new StringBuilder(requestId+";");
    sb.append(secondMax+","+secondMin);

    // gui ket qua len server
    System.out.println(">>> "+sb.toString());
    DatagramPacket dpSend2 = new DatagramPacket(sb.toString().getBytes(),sb.toString().length(),inetAddress,SERVER_PORT);
    dSocket.send(dpSend2);
    System.out.println("Data send to Server success");





  }

}
