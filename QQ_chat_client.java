package Github.chat;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class QQ_chat_client {
	public static void main(String[] args) throws UnknownHostException, IOException{
		  Socket client = new Socket("localhost",9999);
			  new Thread(new qq_send_msg(client)).start();//一条路径
			  new Thread(new qq_receive_msg(client)).start();		//一天路径  
	}

}
//想服务器发送消息
class qq_send_msg implements Runnable{
	DataOutputStream dos;
	public qq_send_msg(){}
	public qq_send_msg(Socket client) throws IOException{
		 dos = new DataOutputStream(client.getOutputStream());
	}

	@Override
	public void run() {
			while(true){
				// TODO Auto-generated method stub
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
//接收客户端发送的消息
class qq_receive_msg implements Runnable{
	DataInputStream dis;
	public qq_receive_msg(){}
	public qq_receive_msg(Socket client) throws IOException{
		  dis = new DataInputStream(client.getInputStream());
	}

	@Override
	public void run() {
		while(true){
			// TODO Auto-generated method stub
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

