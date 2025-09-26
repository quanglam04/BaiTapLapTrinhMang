package BaiTap.UDP.UDP_9VhseINq;

import BaiTap.Config.Config;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
  private static final String SERVER_HOST = Config.SERVER_HOST;
  private static final int SERVER_PORT = 2209;
  public static void main(String[] args) throws Exception {
    DatagramSocket dSocket = new DatagramSocket();
    InetAddress inetAddress = InetAddress.getByName(SERVER_HOST);

    // gui message
    String initMessage = ";"+Config.STUDENT_CODE+";9VhseINq";
    DatagramPacket dpSend = new DatagramPacket(initMessage.getBytes(),initMessage.length(),inetAddress,SERVER_PORT);
    dSocket.send(dpSend);
    System.out.println("Send message to Server!");

    // nhan data from server
    byte[] buf = new byte[4096];
    DatagramPacket dpReceive = new DatagramPacket(buf,buf.length);
    dSocket.receive(dpReceive);

    byte[] data = dpReceive.getData();
    // lay 8 byte dau -> requestId
    String requestId = new String(data,0,8).trim();

    // con lai la object
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data,8,dpReceive.getLength()-8);
    ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
    Product product = (Product) objectInputStream.readObject();

    System.out.println("Object from Server: "+product.toString());
    product.fixName();
    product.fixQuantity();
    System.out.println("Object after process: "+product.toString());

    // serilize lai object
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
    objectOutputStream.writeObject(product);
    objectOutputStream.flush();
    byte[] objectByte = byteArrayOutputStream.toByteArray();

    System.out.println(">>"+objectByte.length);
    // gop requestId + objectByte
    byte[] finalData = new byte[8+objectByte.length];
    System.arraycopy(requestId.getBytes(),0,finalData,0,8);
    System.arraycopy(objectByte,0,finalData,8,objectByte.length);

    DatagramPacket result = new DatagramPacket(finalData,finalData.length,inetAddress,SERVER_PORT);
    dSocket.send(result);
    System.out.println("Send to Server");


  }
}
