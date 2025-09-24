package BaiTap.TCP.TCP_YfnoRfSR;

import BaiTap.Config.Config;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 [Mã câu hỏi (qCode): YfnoRfSR].  Một chương trình máy chủ cho phép kết nối qua TCP tại cổng 2207 (hỗ trợ thời gian liên lạc tối đa cho mỗi yêu cầu là 5s), yêu cầu xây dựng chương trình (tạm gọi là client) thực hiện kết nối tới server tại cổng 2207, sử dụng luồng byte dữ liệu (DataInputStream/DataOutputStream) để trao đổi thông tin theo thứ tự: <br>
 a.	Gửi chuỗi là mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;1D25ED92" <br>
 b.	Nhận lần lượt hai số nguyên a và b từ server <br>
 c.	Thực hiện tính toán tổng, tích và gửi lần lượt từng giá trị theo đúng thứ tự trên lên server <br>
 d.	Đóng kết nối và kết thúc <br>
 */


public class Client {
  private static final int SERVER_PORT = 2207;
  private static final String SERVER_HOST = Config.SERVER_HOST;

  public static void main(String[] args) throws IOException {
    Socket socket = null;
    DataInputStream dis = null;
    DataOutputStream dos = null;
    try{
      socket = new Socket(SERVER_HOST,SERVER_PORT);
      dis = new DataInputStream(socket.getInputStream());
      dos = new DataOutputStream(socket.getOutputStream());

      String qCode = "YfnoRfSR";
      String initialMessage = Config.STUDENT_CODE+";"+qCode;

      dos.writeUTF(initialMessage);
      dos.flush();

      int a = dis.readInt();
      int b = dis.readInt();
      int tong = a+b;
      int tich = a*b;


      dos.write(tong);
      dos.flush();

      dos.write(tich);
      dos.flush();

      System.out.println("Kết quả: "+tong+" "+tich);

    } catch (UnknownHostException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    finally {
      if(dos!= null){
        dos.close();
      }
      if(dis!=null){
        dis.close();
      }
      if(socket!=null){
        socket.close();
      }

    }
  }


}
