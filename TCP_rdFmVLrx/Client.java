package BaiTap.TCP_rdFmVLrx;

import BaiTap.Config.Config;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 [Mã câu hỏi (qCode): rdFmVLrx].  Một chương trình server hỗ trợ kết nối qua giao thức TCP tại cổng 2206 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu xây dựng chương trình client thực hiện kết nối tới server sử dụng luồng byte dữ liệu (InputStream/OutputStream) để trao đổi thông tin theo thứ tự: <br>
 a. Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode".<br>
 Ví dụ: "B16DCCN999;C89DAB45"<br>
 b. Nhận dữ liệu từ server là một chuỗi các số nguyên được phân tách bởi ký tự ",".<br>
 Ví dụ: "8,4,2,10,5,6,1,3"<br>
 c. Tính tổng của tất cả các số nguyên tố trong chuỗi và gửi kết quả lên server.<br>
 Ví dụ: Với dãy "8,4,2,10,5,6,1,3", các số nguyên tố là 2, 5, 3, tổng là 10. Gửi lên server chuỗi "10".<br>
 d. Đóng kết nối và kết thúc chương trình.<br>
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

      String qCode = "rdFmVLrx";
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
