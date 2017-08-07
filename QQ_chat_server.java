package Github.chat;

import java.io.*;
import java.net.*;
import java.util.*;
/**
 * 功能：实现简单的QQ通讯（群聊和私Q）
 * 完善之处：实现了私Q的功能（主要利用字符串进行判断）
 * 犯错之处：1.”某人进入聊天室“应该在进程初始化的时候就进行输出，在send_other中应该是实现真正的聊天内容
 *          2.在比较字符串是否相同时，应该用equal方法，而不是用”==“
 *          3.将start方法写在main函数中
 *          4.在用for循环时，没弄清指引所对应的方法
 *          5.在实现run方法时，如果不采用循环，则只能进行一次操作，要想实现人与人之间的聊天，必须用循环
 *          6.服务器端实现的是转发的功能，是在一个路径中实现的功能，不同于客户端，客户端的接收和发送都是不同的路径
 * @author xiaohong
 *
 */

public class QQ_chat_server {
	//创建一个容纳服务器容器;将每一个客户端加载进去，用以判断是私Q还是群聊
private static List<server_receive> container = new ArrayList<server_receive>();
	
	
	public static void main(String[] args) throws IOException{
		//不能将此方法中的方法体直接放在main函数中，因为server_receive是一个内部类，
		//如需知道为什么，将方法体放在main函数中，看提示即知
		new QQ_chat_server().start();//一条路径
		
	}
	public void start() throws IOException{
		ServerSocket s = new ServerSocket(9999);
		while(true){
			Socket client = s.accept();
			
			server_receive server = new server_receive(client);
			container.add(server);//加载到容器中
			new Thread(server).start();
		}
	}
	
	//server_receive是一个内部类
	class server_receive implements Runnable{
		DataInputStream dis;//数据输入端
		DataOutputStream dos;//数据输出端
		private String name;//进程名
		public server_receive(){}
		public server_receive(Socket client) throws IOException{
		    dis =  new DataInputStream(client.getInputStream());
		    dos = new DataOutputStream(client.getOutputStream());
		    this.name = Receive_msg();
		    this.Send_msg("欢迎你进入聊天室");//特别注意：在进程加载时就应该输出提示，而不应该在send_other中输出
		    send_other(name + "进入了聊天室");
		}
		//接收消息
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
		//发送消息
		public void Send_msg(String str) throws IOException{
			try {
				dos.writeUTF(str);
				dos.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Close_util.closeall(dis);
			}
		}
		//发送给别的进程
		public void send_other(String msg) throws IOException{
			if(msg.startsWith("@")  && msg.indexOf(":") > 1){//实现私聊，输入格式是：@zhang:你最近好吗？
				String name = msg.substring(1, msg.indexOf(":"));//左闭右开
				String content = msg.substring(msg.indexOf(":") + 1);
				for(server_receive index:container)
					if(index.name.equals(name))//找到私Q的人
						index.Send_msg(this.name + "悄悄对你说：" + content);//注意;index所对应的进程
					else continue;
				
			}
			else{//实现群聊
				for(server_receive index:container)
					if(index == this)//本进程则不转发消息
						continue;
					else 
						index.Send_msg(this.name + "：" + msg);
			}
		}
		//重写run方法
		@Override
		public void run() {//run方法中如果不采用循环，则只能进行一次键盘的输入，无法实现人与人之间的聊天
				while(true){
				try {
					String res = Receive_msg();//接收客户端发送的消息
					send_other(res);//将客户端发送的消息转发给其他进程
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
