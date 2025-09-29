package BaiTap.UDP.UDP_rHdchPlh;

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

    String message = ";B22DCCN482;rHdchPlh";
    DatagramPacket dpSend = new DatagramPacket(message.getBytes(),message.length(),inetAddress,SERVER_PORT);
    datagramSocket.send(dpSend);

    byte[] buf = new byte[4096];
    DatagramPacket dpReceive = new DatagramPacket(buf,buf.length);
    datagramSocket.receive(dpReceive);

    byte[] data = dpReceive.getData();
    String requestId = new String(data,0,8);
    System.out.println("Request Id: ["+requestId+"]");
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data,8,data.length-8);
    ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
    Book book =(Book) objectInputStream.readObject();
    System.out.println("Data from Server: "+book.toString());

    book.chuanHoaAuthor();
    book.chuanHoaISBN();
    book.chuanHoaTitle();
    book.chuanHoaPublishDate();
    System.out.println("SAU KHI CHUAN HOA: "+book.toString());
    // gui len server

    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
    objectOutputStream.writeObject(book);
    objectOutputStream.flush();

    byte[] objectData = byteArrayOutputStream.toByteArray();

    byte[] finalData = new byte[8+objectData.length];
    System.arraycopy(requestId.getBytes(),0,finalData,0,8);
    System.arraycopy(objectData,0,finalData,8,objectData.length);
    DatagramPacket datagramPacket = new DatagramPacket(finalData,finalData.length,inetAddress,SERVER_PORT);
    datagramSocket.send(datagramPacket);
    System.out.println("Send result to server");

  }
}
