package BaiTap.UDP.UDP_FPUG83h8;

import BaiTap.Config.Config;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.security.spec.RSAOtherPrimeInfo;
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

    byte[] buf = new byte[2048];
    DatagramPacket datagramPacket1 = new DatagramPacket(buf,buf.length);
    datagramSocket.receive(datagramPacket1);

    String dataString = new String(datagramPacket1.getData()).trim();
    System.out.println("Data string: "+dataString);
    String[] arr = dataString.split(";");
    String requestId = arr[0];
    String[] mangNhiPhan = arr[1].split(",");
    int firstNumber = process(mangNhiPhan[0]);
    int secondNumber = process(mangNhiPhan[1]);
    int sum = firstNumber+secondNumber;

    String kq = requestId+";"+String.valueOf(sum);
    DatagramPacket datagramPacket2 = new DatagramPacket(kq.getBytes(),kq.length(),inetAddress,SERVER_PORT);
    datagramSocket.send(datagramPacket2);
    System.out.println("send result to server "+sum);
  }

  public static int process(String binary){
    char[] reverseBinary = new StringBuilder(binary).reverse().toString().toCharArray();
    int kq = 0;
    for(int i=0;i<reverseBinary.length;++i)
      kq+=Math.pow(2,i)*Integer.parseInt(String.valueOf(reverseBinary[i]));
    return kq;
  }

}
