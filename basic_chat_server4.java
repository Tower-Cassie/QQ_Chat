package Github.chat;

import java.io.*;
import java.net.*;
import java.util.*;
/**
 * 功能;基本实现聊天室的功能，就差私Q功能
 * 犯错之处：1.”某人进入聊天室“应该在进程初始化的时候就进行输出，在send_other中应该是实现真正的聊天内容
 *          2.将start方法写在main函数中
 *          3.在用for循环时，没弄清指引所对应的方法
 *          4.在实现run方法时，如果不采用循环，则只能进行一次操作，要想实现人与人之间的聊天，必须用循环
 *          5.服务器端实现的是转发的功能，是在一个路径中实现的功能，不同于客户端，客户端的接收和发送都是不同的路径
 * @author xiaohong
 *
 */
public class basic_chat_server4 {
	//创建一个容纳服务器容器;将每一个客户端加载进去，用以判断是私Q还是群聊
	private static List<server_receive> container = new ArrayList<server_receive>();
	
	
	public static void main(String[] args) throws IOException{
		//不能将此方法中的方法体直接放在main函数中，因为server_receive是一个内部类，
				//如需知道为什么，将方法体放在main函数中，看提示即知
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
	
	//server_receive是一个内部类
	class server_receive implements Runnable{
		DataInputStream dis;//数据输入端
		DataOutputStream dos;//数据输出端
		private String name;//客户端名
		//构造器
		public server_receive(){}
		public server_receive(Socket client) throws IOException{
		    dis =  new DataInputStream(client.getInputStream());
		    dos = new DataOutputStream(client.getOutputStream());
		    this.name = Receive_msg();
		    this.Send_msg(name + "欢迎进入聊天室");//特别注意：在进程加载时就应该输出提示，而不应该在send_other中输出
		    send_other(name + "进入了聊天室");
		}
		//接收客户端发送的消息
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
		//将客服端发送的消息进行转发给客户端
		public void Send_msg(String str) throws IOException{
			try {
				dos.writeUTF(str);
				dos.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Close_util.closeall(dis);
			}
		}
		//转发给别的客户端
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
				while(true){//run方法中如果不采用循环，则只能进行一次键盘的输入，无法实现人与人之间的聊天
				try {
					String res = Receive_msg();//接收消息
					send_other(res);//转发
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

