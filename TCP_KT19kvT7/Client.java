package BaiTap.TCP_KT19kvT7;

import BaiTap.Config.Config;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

/**
 * [Mã câu hỏi (qCode): KT19kvT7].  Một chương trình server hỗ trợ kết nối qua giao thức TCP tại cổng 2206 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu xây dựng chương trình client thực hiện kết nối tới server sử dụng luồng byte dữ liệu (InputStream/OutputStream) để trao đổi thông tin theo thứ tự: <br>
 * a. Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". <br>
 * Ví dụ: "B16DCCN999;E56FAB67"<br>
 * b. Nhận dữ liệu từ server là một chuỗi các số nguyên được phân tách bởi ký tự ",".<br>
 * Ví dụ: " 3,7,2,5,8,1"<br>
 * c. Tìm vị trí mà độ lệch của tổng bên trái và tổng bên phải là nhỏ nhất -> Gửi lên server vị trí đó, tổng trái, tổng phải và độ lệch. Ví dụ: với dãy " 3,7,2,5,8,1", vị trí 3 có độ lệch nhỏ nhất = 3 → Kết quả gửi server: "3,12,9,3"<br>
 * d. Đóng kết nối và kết thúc chương trình.<br>
 */

public class Client {
  private static int SERVER_PORT = 2206;
  private static String SERVER_HOST = Config.SERVER_HOST;
  public static void main(String[] args) throws IOException {
    Socket socket = new Socket(SERVER_HOST,SERVER_PORT);
    InputStream is = socket.getInputStream();
    OutputStream os = socket.getOutputStream();

    String qCode = "KT19kvT7";
    String message = Config.STUDENT_CODE+";"+qCode;

    os.write(message.getBytes());
    os.flush();

    byte[] buffer = new byte[1024];
    int bytesRead = is.read(buffer);
    if(bytesRead!=-1) {
      String data = new String(buffer,0,bytesRead);
      System.out.println("Data nhận từ server: "+data);
      String result = process(data);
      os.write(result.getBytes());
      os.flush();
      System.out.println("Đã gửi kết quả lên Server: "+result);
    }

  }

  public static String process(String data) {
    int[] arr = Arrays.stream(data.split(",")).mapToInt(Integer::parseInt).toArray();
    int sum = Arrays.stream(arr).sum();
    int sumLeft = 0;
    int sumRight = 0;
    int index = 1;
    int min = Integer.MAX_VALUE;


    int bestSumLeft = 0;
    int bestSumRight = 0;


    for(int i = 1; i < arr.length - 1; i++) {
      sumLeft += arr[i-1];
      sumRight = sum - sumLeft - arr[i];

      int diff = Math.abs(sumLeft - sumRight);
      if(diff < min) {
        index = i;
        min = diff;
        bestSumLeft = sumLeft;
        bestSumRight = sumRight;
      }
    }

    StringBuilder result = new StringBuilder("");
    result.append(index).append(",")
        .append(bestSumLeft).append(",")
        .append(bestSumRight).append(",")
        .append(min);
    return result.toString();
  }

}
