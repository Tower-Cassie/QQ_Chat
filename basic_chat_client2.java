package Github.chat;

import java.io.*;
import java.net.*;
import java.util.*;
/**
 * ���ܣ�ʵ�ֿͻ��˺ͷ�����֮���ͨ��,�׶ˣ�ֻ��һ���ͻ��˷���Ϣ������ϢҲֻ��һ��һ�еķ���������ֻ��һ��һ���Ľ���
 * �����ƣ��������ͻ��˸�������������Ϣ
 * @author xiaohong
 *
 */

public class basic_chat_client2 {
	public static void main(String[] args) throws UnknownHostException, IOException{
		//�����ͻ���
		  Socket client = new Socket("localhost",9999);
		  //ʵ��ͨ��
		  //�����
		  
		  DataOutputStream dos = new DataOutputStream(client.getOutputStream());
		  //�ӿ���̨��������ͻ�������Ҫ���ݵ���Ϣ
		  Scanner input = new Scanner(System.in);
		  String msg = input.next();
		  dos.writeUTF(msg);
		  dos.flush();
		  //������
		  /*
		   * BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			bw.write(msg);//����bw.close(),��Ϊ��ص���������
			bw.newLine();//�˴��ǳ���Ҫ���ڶ�ȡ����ʱ�ǰ���������ȡ�ģ����Դ˴��������һ���еı�Ƿ���Ҳ�������ַ������\n
			bw.flush();//ˢ��
			
			
			BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
			String str = br.readLine();
			System.out.println(str);
			//�������ݺͷ������ݵ����ͱ���һ��
			
			*/
			//Ϊ�˷�ֹ©��bw.newLine(),ʹ��DataOutputStream

		  
		  DataInputStream dis = new DataInputStream(client.getInputStream());
		  String str = dis.readUTF();
		  System.out.println(str);
		  
	}

}
