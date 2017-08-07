package Github.chat;

/**
 * ����;ʵ�ֿͻ��˺ͷ������˵�ͨ�ţ��׶ˣ���Ȼ����ʵ�ֺܶ�ͻ����������֮���ͨ�ţ�
 * ���Ƿ��������տͻ��˵���Ϣ���Ⱥ�˳�򣬶�ʵ�����칦�ܲ�Ӧ�����Ⱥ�˳��
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class basic_chat_client3 {
	public static void main(String[] args) throws UnknownHostException, IOException{
		  Socket client = new Socket("localhost",9999);
			  new Thread(new client_send(client)).start();//һ��·��
			  new Thread(new client_receive(client)).start();//��һ��·��  
	}
}
//������Ϣ
class client_send implements Runnable{
	DataOutputStream dos;
	//������
	public client_send(){}
	public client_send(Socket client) throws IOException{
		 dos = new DataOutputStream(client.getOutputStream());
	}

	@Override
	public void run() {
		while(true){
			System.out.println("Please input the messeage:");
			Scanner input = new Scanner(System.in);
			  String msg = input.next();
			  try {
				 dos.writeUTF(msg);
				 dos.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
//������Ϣ
class client_receive implements Runnable{
	DataInputStream dis;
	public client_receive(){}
	public client_receive(Socket client) throws IOException{
		  dis = new DataInputStream(client.getInputStream());
	}

	@Override
	public void run() {
		while(true){
			  try {
				  String str = dis.readUTF();
				  System.out.println(str);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}