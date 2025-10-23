package BaiTap.UDP.UDP_q2Bc0BOq;

import BaiTap.Config.Config;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class UDPClient {
    public static final int SERVER_PORT = 2207;
    public static final String SERVER_HOST = Config.SERVER_HOST;

    public static void main(String[] args) throws Exception {
      DatagramSocket datagramSocket = new DatagramSocket() ;
      InetAddress inetAddress = InetAddress.getByName(SERVER_HOST);

      String message = ";"+Config.STUDENT_CODE+";q2Bc0BOq";
      DatagramPacket datagramPacket = new DatagramPacket(message.getBytes(),message.length(),inetAddress,SERVER_PORT);

      datagramSocket.send(datagramPacket);
      System.out.println("Send data to Server");

      byte[] buff = new byte[2048];
      System.out.println("Start to receive data from Server");
      DatagramPacket datagramPacket1 = new DatagramPacket(buff,buff.length);
      datagramSocket.receive(datagramPacket1);
      String dataString = new String(datagramPacket1.getData()).trim();
      System.out.println("DataString: "+dataString);

      String[] arr = dataString.split(";");
      String requestId = arr[0];
      int[] numbers = Arrays.stream(arr[1].split(",")).mapToInt(Integer::parseInt).toArray();
      Arrays.sort(numbers);
      int min = numbers[0];
      int max = numbers[numbers.length-1];

      String kq = requestId+";"+max+","+min;
      DatagramPacket result = new DatagramPacket(kq.getBytes(),kq.length(),inetAddress,SERVER_PORT);
      datagramSocket.send(result);
      System.out.println("Send result to Server");

    }
}
