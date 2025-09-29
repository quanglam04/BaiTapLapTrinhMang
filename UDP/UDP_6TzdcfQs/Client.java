package BaiTap.UDP.UDP_6TzdcfQs;

import BaiTap.Config.Config;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
  private static  final int SERVER_PORT = 2209;
  private  static final String SERVER_HOST = Config.SERVER_HOST;
  public static void main(String[] args) throws Exception {
    DatagramSocket datagramSocket = new DatagramSocket();
    InetAddress inetAddress = InetAddress.getByName(SERVER_HOST);

    String message = ";B22DCCN482;6TzdcfQs";
    DatagramPacket datagramPacket = new DatagramPacket(message.getBytes(),message.length(),inetAddress,SERVER_PORT);
    datagramSocket.send(datagramPacket);
    System.out.println("Send message to Server");

    byte[] buf = new byte[4096];
    DatagramPacket datagramPacket1 = new DatagramPacket(buf,buf.length);
    datagramSocket.receive(datagramPacket1);

    byte[] data = datagramPacket1.getData();
    String requestId = new String(data,0,8);
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data,8,data.length-8);
    ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
    Employee employee = (Employee) objectInputStream.readObject();

    System.out.println("Data from Server: "+employee.toString());
    employee.chuanHoaName();
    employee.chuanHoaSalary();
    employee.chuanHoaHireDate();

    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
    objectOutputStream.writeObject(employee);
    objectOutputStream.flush();

    byte[] objectByte = byteArrayOutputStream.toByteArray();
    byte[] finalData = new byte[8+objectByte.length];

    System.arraycopy(requestId.getBytes(),0,finalData,0,8);
    System.arraycopy(objectByte,0,finalData,8,objectByte.length);
    DatagramPacket datagramPacket2 = new DatagramPacket(finalData,finalData.length,inetAddress,SERVER_PORT);
    datagramSocket.send(datagramPacket2);
    System.out.println("Send to Server");
  }
}
