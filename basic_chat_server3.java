package Github.chat;

import java.io.*;
import java.net.*;
/**
 * ���ܣ�ʵ�ֿͻ��˺ͷ�����֮���ͨ�ţ��׶�;��Ȼ����ʵ�ֺܶ�ͻ����������֮���ͨ�ţ�
 * ���Ƿ��������տͻ��˵���Ϣ���Ⱥ�˳�򣬶�ʵ�����칦�ܲ�Ӧ�����Ⱥ�˳��
 * @author xiaohong
 *
 */
public class basic_chat_server3 {
	public static void  main(String[] args) throws IOException{
		//��������������
		ServerSocket s = new ServerSocket(9999);
		//�����ͻ��˽��̽��н�������������ѭ������ֻ�ܴ���һ���ͻ��˽���
		while(true){
			Socket client = s.accept();
			//�����
			DataInputStream dis =  new DataInputStream(client.getInputStream());
			String str = dis.readUTF();
			System.out.println(str);
			//�����
			DataOutputStream dos = new DataOutputStream(client.getOutputStream());
			dos.writeUTF(str);
		}
	}
}
