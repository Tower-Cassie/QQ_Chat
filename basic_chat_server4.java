package Github.chat;

import java.io.*;
import java.net.*;
import java.util.*;
/**
 * ����;����ʵ�������ҵĹ��ܣ��Ͳ�˽Q����
 * ����֮����1.��ĳ�˽��������ҡ�Ӧ���ڽ��̳�ʼ����ʱ��ͽ����������send_other��Ӧ����ʵ����������������
 *          2.��start����д��main������
 *          3.����forѭ��ʱ��ûŪ��ָ������Ӧ�ķ���
 *          4.��ʵ��run����ʱ�����������ѭ������ֻ�ܽ���һ�β�����Ҫ��ʵ��������֮������죬������ѭ��
 *          5.��������ʵ�ֵ���ת���Ĺ��ܣ�����һ��·����ʵ�ֵĹ��ܣ���ͬ�ڿͻ��ˣ��ͻ��˵Ľ��պͷ��Ͷ��ǲ�ͬ��·��
 * @author xiaohong
 *
 */
public class basic_chat_server4 {
	//����һ�����ɷ���������;��ÿһ���ͻ��˼��ؽ�ȥ�������ж���˽Q����Ⱥ��
	private static List<server_receive> container = new ArrayList<server_receive>();
	
	
	public static void main(String[] args) throws IOException{
		//���ܽ��˷����еķ�����ֱ�ӷ���main�����У���Ϊserver_receive��һ���ڲ��࣬
				//����֪��Ϊʲô�������������main�����У�����ʾ��֪
		new basic_chat_server4().start();
		
	}
	public void start() throws IOException{
		ServerSocket s = new ServerSocket(9999);
		while(true){
			Socket client = s.accept();
			
			server_receive server = new server_receive(client);
			container.add(server);
			new Thread(server).start();
		}
	}
	
	//server_receive��һ���ڲ���
	class server_receive implements Runnable{
		DataInputStream dis;//���������
		DataOutputStream dos;//���������
		private String name;//�ͻ�����
		//������
		public server_receive(){}
		public server_receive(Socket client) throws IOException{
		    dis =  new DataInputStream(client.getInputStream());
		    dos = new DataOutputStream(client.getOutputStream());
		    this.name = Receive_msg();
		    this.Send_msg(name + "��ӭ����������");//�ر�ע�⣺�ڽ��̼���ʱ��Ӧ�������ʾ������Ӧ����send_other�����
		    send_other(name + "������������");
		}
		//���տͻ��˷��͵���Ϣ
		public String Receive_msg() throws IOException{
			String str = null;
			try {
				str = dis.readUTF();
				System.out.println(str);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Close_util.closeall(dis);
			}
			return str;
		}
		//���ͷ��˷��͵���Ϣ����ת�����ͻ���
		public void Send_msg(String str) throws IOException{
			try {
				dos.writeUTF(str);
				dos.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Close_util.closeall(dis);
			}
		}
		//ת������Ŀͻ���
		public void send_other(String msg){
				try {
						for(server_receive index:container)
							if(index != this)
								index.Send_msg(msg);
							else
								continue;
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
		}
		@Override
		public void run() {
				while(true){//run���������������ѭ������ֻ�ܽ���һ�μ��̵����룬�޷�ʵ��������֮�������
				try {
					String res = Receive_msg();//������Ϣ
					send_other(res);//ת��
				} catch (IOException e) {
					// TODO Auto-generated catch block
					try {
						Close_util.closeall(dis);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		}
		
	}
}

