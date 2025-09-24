package BaiTap.TCP.TCP_ZX6EJc3G;

import BaiTap.Config.Config;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

/**
 * [Mã câu hỏi (qCode): ZX6EJc3G].  Một chương trình server hỗ trợ
 * kết nối qua giao thức TCP tại cổng 2206 (hỗ trợ thời gian giao tiếp
 * tối đa cho mỗi yêu cầu là 5s). Yêu cầu xây dựng chương trình client
 * thực hiện kết nối tới server sử dụng luồng byte dữ liệu (InputStream/OutputStream) để trao đổi thông tin theo thứ tự: <br>
 * a. Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode".<br>
 * Ví dụ: "B16DCCN999;D45EFA12"<br>
 * b. Nhận dữ liệu từ server là một chuỗi các số nguyên được phân tách bởi ký tự ",".<br>
 * Ví dụ: "10,5,15,20,25,30,35"<br>
 * c. Xác định hai số trong dãy có tổng gần nhất với gấp đôi giá trị trung bình của toàn bộ dãy. Gửi thông điệp lên server theo định dạng "num1,num2" (với num1 < num2)<br>
 * Ví dụ: Với dãy "10,5,15,20,25,30,35", gấp đôi giá trị trung bình là 40, hai số có tổng gần nhất là 15 và 25. Gửi lên server chuỗi "15,25".<br>
 * d. Đóng kết nối và kết thúc chương trình.<br>
 */
public class Client {
  private static int SERVER_HOST = 2206;
  private static String SERVER_POST = Config.SERVER_HOST;
  public static void main(String[] args) throws IOException {
    Socket socket = new Socket(SERVER_POST, SERVER_HOST);
    InputStream is = socket.getInputStream();
    OutputStream os = socket.getOutputStream();

    String qCode = "ZX6EJc3G";
    String message = Config.STUDENT_CODE+";"+qCode;

    os.write(message.getBytes());
    os.flush();

    byte[] buffer = new byte[1024];
    int byteRead = is.read(buffer);
    if(byteRead!=-1){
      String data = new String(buffer,0,byteRead);
      System.out.println("Đã nhận dữ liệu từ Server: "+data);
      String result = process(data);
      os.write(result.getBytes());
      os.flush();
      System.out.println("Đã gửi kết quả lên Server "+result);
    }

    socket.close();
  }

  public static String process(String data){
    int[] arr = Arrays.stream(data.split(",")).mapToInt(Integer::parseInt).toArray();
    int average = Arrays.stream(arr).sum()/arr.length *2;
    int min = Integer.MAX_VALUE;
    int firstNumber=0,secondNumber=0;
    for(int i=0;i<arr.length;++i)
      for(int j=0;j<arr.length;++j)
      {
        int sum = arr[i]+arr[j];
        if(min>=Math.abs(sum-average)) {
          firstNumber = Math.min(arr[i],arr[j]);
          secondNumber = Math.max(arr[i],arr[j]);
          min = Math.abs(sum-average);
        }
      }


    return firstNumber+","+secondNumber;
  }

}
