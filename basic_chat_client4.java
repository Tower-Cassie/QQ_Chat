package Github.chat;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class basic_chat_client4 {
	public static void main(String[] args) throws UnknownHostException, IOException{
		  Socket client = new Socket("localhost",9999);
			  new Thread(new client_send_msg(client)).start();//一条路径
			  new Thread(new client_receive_msg(client)).start();		//另一条路径  
	}

}
//向服务器端发送消息
class client_send_msg implements Runnable{
	DataOutputStream dos;
	public client_send_msg(){}
	public client_send_msg(Socket client) throws IOException{
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
//接收服务器端发送的消息
class client_receive_msg implements Runnable{
	DataInputStream dis;
	public client_receive_msg(){}
	public client_receive_msg(Socket client) throws IOException{
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
