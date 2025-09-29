package BaiTap.UDP.UDP_kEqvaHmm;

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
    DatagramSocket datagramSocket = new DatagramSocket();
    InetAddress inetAddress = InetAddress.getByName(SERVER_HOST);

    String message = ";"+Config.STUDENT_CODE+";kEqvaHmm";
    DatagramPacket dpSend = new DatagramPacket(message.getBytes(),message.length(),inetAddress,SERVER_PORT);
    datagramSocket.send(dpSend);
    System.out.println("Send message to server");

    byte[] buf = new byte[8192];
    DatagramPacket datagramPacket = new DatagramPacket(buf,buf.length);
    datagramSocket.receive(datagramPacket);

    byte[] data = datagramPacket.getData();
    // lay 8 byte dau
    String requestId = new String(data,0,8);
    // con lai la object
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data,8,data.length-8);
    ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
    Student student = (Student) objectInputStream.readObject();
    System.out.println("Data from Server: requestId: "+requestId+" "+student.toString());

    // xu ly
    student.changeToStandardName();
    student.makeEmail();

    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
    objectOutputStream.writeObject(student);
    objectOutputStream.flush();

    byte[] dataObject = byteArrayOutputStream.toByteArray();
    byte[] finalData = new byte[8+dataObject.length];

    System.arraycopy(requestId.getBytes(),0,finalData,0,8);
    System.arraycopy(dataObject,0,finalData,8,dataObject.length);
    DatagramPacket datagramPacket1 = new DatagramPacket(finalData,finalData.length,inetAddress,SERVER_PORT);
    datagramSocket.send(datagramPacket1);
    System.out.println("Done");
    System.out.println(student.toString());





  }
}
