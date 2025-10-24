package BaiTap.RMI.RMI_AFw7JWIb;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ByteService extends Remote {
  public byte[] requestData(String studentCode, String qCode) throws RemoteException;
  public void submitData(String studentCode, String qCode, byte[] data) throws RemoteException;
}