package BaiTap.UDP.UDP_FPUG83h8;

import BaiTap.Config.Config;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class Client {
  public static final int SERVER_PORT = 2208;
  public static final String SERVER_HOST = Config.SERVER_HOST;
  public static void main(String[] args) throws Exception {
    DatagramSocket datagramSocket = new DatagramSocket();
    InetAddress inetAddress = InetAddress.getByName(SERVER_HOST);

    String message = ";B22DCCN482;FPUG83h8";
    DatagramPacket datagramPacket = new DatagramPacket(message.getBytes(),message.length(),inetAddress,SERVER_PORT);
    datagramSocket.send(datagramPacket);

    byte[] buf = new byte[1024];
    DatagramPacket datagramPacket1 = new DatagramPacket(buf,buf.length);
    datagramSocket.receive(datagramPacket1);
    String dataString = new String(datagramPacket1.getData()).trim();
    System.out.println("Data from server: "+dataString);
    String[] arr = dataString.split(";");
    String requestId = arr[0];
    String[] numbers = arr[1].split(",");
    int firstNumber = toDecimal(numbers[0]);
    int secondNumber = toDecimal(numbers[1]);
    System.out.println(firstNumber+"   "+secondNumber);
    int sum = firstNumber+secondNumber;

    DatagramPacket datagramPacket2 = new DatagramPacket(
        (requestId + ";" + sum).getBytes(), (requestId + ";" + sum).length(),inetAddress,SERVER_PORT);
    datagramSocket.send(datagramPacket2);
    System.out.println("Send data to Server");
  }

  public static int toDecimal(String binary){
    int[] reverseArr = Arrays.stream(new StringBuilder(binary).reverse().toString().split("|")).mapToInt(Integer::parseInt).toArray();
    System.out.println(Arrays.toString(reverseArr));
    int kq = 0;
    for(int i=0;i<reverseArr.length;++i)
    {
      kq+= ((int)Math.pow(2,i))*reverseArr[i];
      System.out.println(kq);
    }
    return kq;
  }

}
