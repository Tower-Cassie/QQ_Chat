package Github.chat;

import java.io.*;
import java.net.*;
/**
 * ���ܣ�ʵ�ֿͻ��˺ͷ�����֮���ͨ��,�׶ˣ�ֻ��һ���ͻ��˷���Ϣ������ϢҲֻ��һ��һ�еķ���������ֻ��һ��һ���Ľ���
 * �����ƣ��������ͻ��˸�������������Ϣ
 * @author xiaohong
 *
 */
public class basic_chat_server2 {
	public static void  main(String[] args) throws IOException{
		//������������
		ServerSocket s = new ServerSocket(9999);
		//ֻ����һ���ͻ�������
		Socket client = s.accept();
		//������
		DataInputStream dis =  new DataInputStream(client.getInputStream());
		String str = dis.readUTF();
		System.out.println(str);
		//�����
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());
		dos.writeUTF(str);
	}

}
