package BaiTap.TCP.TCP_KWJINTpj;

import BaiTap.Config.Config;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Client {

  private static int SERVER_PORT = 2208;
  private static String SERVER_HOST = Config.SERVER_HOST;

  public static void main(String[] args) throws IOException {
    String data = new String("hello world this is a test example");

    String[] arr = data.split("\\s+");
    System.out.println("Before sort: "+Arrays.toString(arr));

    Arrays.sort(arr,(a,b) -> {
      if(a.length() == b.length())
        return 0;
      return a.length() - b.length();
    });

    System.out.println(Arrays.toString(arr));
  }
}
