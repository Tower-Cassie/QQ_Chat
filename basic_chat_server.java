package Github.chat;

import java.io.IOException;
import java.net.*;
/**
 * ���ܣ��ͻ��˺ͷ����֮�佨������
 * �����ƣ��ͻ��˺ͷ�����������ͨѶ
 * �ص㣺�������Ҫ��һ���˿ںţ����ͻ�����Ҫ��Ӧ�ķ�������Լ���Ӧ�Ķ˿ں�
 * @author xiaohong
 *
 */
public class basic_chat_server {
	public static void main(String[] args) throws IOException{
		//��������˽���
		ServerSocket server = new ServerSocket(9999);
		//����һ���ͻ���
		Socket client = server.accept();
		
	}

}
