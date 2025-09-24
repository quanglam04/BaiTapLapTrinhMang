package BaiTap.UDP.JdkPLBhB;

import BaiTap.Config.Config;
import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
  private static final String SERVER_HOST = Config.SERVER_HOST;
  private static final int SERVER_PORT = 2207;

  public static void main(String[] args) throws Exception {
    // khai bao
    DatagramSocket dSocket = new DatagramSocket();
    InetAddress IPAddress = InetAddress.getByName(SERVER_HOST);

    // gui message len server
    String initMessage = ";"+Config.STUDENT_CODE+";"+"JdkPLBhB";
    DatagramPacket dpSend = new DatagramPacket(initMessage.getBytes(),initMessage.length(),IPAddress,SERVER_PORT);
    dSocket.send(dpSend);

    // nhan du lieu tu server tra ve
    byte[] data = new byte[1024];
    DatagramPacket dpReceive = new DatagramPacket(data,data.length);
    dSocket.receive(dpReceive);

    String dataString = new String(dpReceive.getData()).trim();
    System.out.println("Data from Server: "+dataString);

    // xu ly
    String[] arr = dataString.split(";");
    String requestId = arr[0];
    BigInteger num1 = new BigInteger(arr[1]);
    BigInteger num2 = new BigInteger(arr[2]);

    // gui du lieu len server
    StringBuilder sb = new StringBuilder(requestId);
    sb.append(";").append(num1.add(num2)+","+num1.subtract(num2));
    DatagramPacket dpSend2 = new DatagramPacket(sb.toString().getBytes(),sb.toString().length(),IPAddress,SERVER_PORT);
    dSocket.send(dpSend2);
    System.out.println("Send to Server: "+sb.toString());




  }
}
