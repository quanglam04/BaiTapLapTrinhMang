package BaiTap.UDP.UDP_kx00gf6g;

import BaiTap.Config.Config;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client{

  public static void main(String[] args) throws IOException, ClassNotFoundException {
    int server_port = 2209;
    DatagramSocket datagramSocket = new DatagramSocket();
    InetAddress inetAddress = InetAddress.getByName(Config.SERVER_HOST);
    String message = ";B22DCCN482;kx00gf6g";
    DatagramPacket datagramPacket = new DatagramPacket(message.getBytes(),message.length(),inetAddress,server_port);
    datagramSocket.send(datagramPacket);
    System.out.println("Send data to server");

    byte[] buff = new byte[4096];
    DatagramPacket datagramPacket1 = new DatagramPacket(buff,buff.length);
    datagramSocket.receive(datagramPacket1);

    byte[] data = datagramPacket1.getData();
    String requestId = new String(data,0,8);

    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data,8,data.length-8);
    ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
    Employee employee = (Employee) objectInputStream.readObject();
    System.out.println(">>>"+employee.toString());
  }

}
