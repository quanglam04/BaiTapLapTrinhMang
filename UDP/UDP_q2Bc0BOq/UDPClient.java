package BaiTap.UDP.UDP_q2Bc0BOq;

import BaiTap.Config.Config;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class UDPClient {

  public static void main(String[] args) throws Exception {
    DatagramSocket datagramSocket = new DatagramSocket();
    InetAddress inetAddress = InetAddress.getByName(Config.SERVER_HOST);

    String message = ";B22DCCN482;q2Bc0BOq";
    DatagramPacket datagramPacket = new DatagramPacket(message.getBytes(),message.length(),inetAddress,2207);
    datagramSocket.send(datagramPacket);

    byte[] buf = new byte[1024];
    DatagramPacket datagramPacket1 = new DatagramPacket(buf,buf.length);
    datagramSocket.receive(datagramPacket1);
    String dataString = new String(buf,buf.length).trim();

    String[] arr = dataString.split(";");
    String requestId = arr[0];
    int[] numbers = Arrays.stream(arr[1].split(",")).mapToInt(Integer::parseInt).toArray();
    Arrays.sort(numbers);
    int max = numbers[numbers.length-1];
    int min = numbers[0];

    String kq = requestId+";"+max+","+min;
    DatagramPacket datagramPacket2 = new DatagramPacket(kq.getBytes(),kq.length(),inetAddress,2207);
    datagramSocket.send(datagramPacket2);
    System.out.println("OK");

  }
}
