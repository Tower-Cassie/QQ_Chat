package Github.chat;

import java.io.*;
import java.net.*;
import java.util.*;
/**
 * ���ܣ�ʵ�ּ򵥵�QQͨѶ��Ⱥ�ĺ�˽Q��
 * ����֮����ʵ����˽Q�Ĺ��ܣ���Ҫ�����ַ��������жϣ�
 * ����֮����1.��ĳ�˽��������ҡ�Ӧ���ڽ��̳�ʼ����ʱ��ͽ����������send_other��Ӧ����ʵ����������������
 *          2.�ڱȽ��ַ����Ƿ���ͬʱ��Ӧ����equal�������������á�==��
 *          3.��start����д��main������
 *          4.����forѭ��ʱ��ûŪ��ָ������Ӧ�ķ���
 *          5.��ʵ��run����ʱ�����������ѭ������ֻ�ܽ���һ�β�����Ҫ��ʵ��������֮������죬������ѭ��
 *          6.��������ʵ�ֵ���ת���Ĺ��ܣ�����һ��·����ʵ�ֵĹ��ܣ���ͬ�ڿͻ��ˣ��ͻ��˵Ľ��պͷ��Ͷ��ǲ�ͬ��·��
 * @author xiaohong
 *
 */

public class QQ_chat_server {
	//����һ�����ɷ���������;��ÿһ���ͻ��˼��ؽ�ȥ�������ж���˽Q����Ⱥ��
private static List<server_receive> container = new ArrayList<server_receive>();
	
	
	public static void main(String[] args) throws IOException{
		//���ܽ��˷����еķ�����ֱ�ӷ���main�����У���Ϊserver_receive��һ���ڲ��࣬
		//����֪��Ϊʲô�������������main�����У�����ʾ��֪
		new QQ_chat_server().start();//һ��·��
		
	}
	public void start() throws IOException{
		ServerSocket s = new ServerSocket(9999);
		while(true){
			Socket client = s.accept();
			
			server_receive server = new server_receive(client);
			container.add(server);//���ص�������
			new Thread(server).start();
		}
	}
	
	//server_receive��һ���ڲ���
	class server_receive implements Runnable{
		DataInputStream dis;//���������
		DataOutputStream dos;//���������
		private String name;//������
		public server_receive(){}
		public server_receive(Socket client) throws IOException{
		    dis =  new DataInputStream(client.getInputStream());
		    dos = new DataOutputStream(client.getOutputStream());
		    this.name = Receive_msg();
		    this.Send_msg("��ӭ�����������");//�ر�ע�⣺�ڽ��̼���ʱ��Ӧ�������ʾ������Ӧ����send_other�����
		    send_other(name + "������������");
		}
		//������Ϣ
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
		//������Ϣ
		public void Send_msg(String str) throws IOException{
			try {
				dos.writeUTF(str);
				dos.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Close_util.closeall(dis);
			}
		}
		//���͸���Ľ���
		public void send_other(String msg) throws IOException{
			if(msg.startsWith("@")  && msg.indexOf(":") > 1){//ʵ��˽�ģ������ʽ�ǣ�@zhang:���������
				String name = msg.substring(1, msg.indexOf(":"));//����ҿ�
				String content = msg.substring(msg.indexOf(":") + 1);
				for(server_receive index:container)
					if(index.name.equals(name))//�ҵ�˽Q����
						index.Send_msg(this.name + "���Ķ���˵��" + content);//ע��;index����Ӧ�Ľ���
					else continue;
				
			}
			else{//ʵ��Ⱥ��
				for(server_receive index:container)
					if(index == this)//��������ת����Ϣ
						continue;
					else 
						index.Send_msg(this.name + "��" + msg);
			}
		}
		//��дrun����
		@Override
		public void run() {//run���������������ѭ������ֻ�ܽ���һ�μ��̵����룬�޷�ʵ��������֮�������
				while(true){
				try {
					String res = Receive_msg();//���տͻ��˷��͵���Ϣ
					send_other(res);//���ͻ��˷��͵���Ϣת������������
					//System.out.println("!@#@$##@#$#$");
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
