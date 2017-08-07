package Github.chat;

/**
 * 功能;实现客户端和服务器端的通信，弊端：虽然可以实现很多客户端与服务器之间的通信，
 * 但是服务器接收客户端的消息有先后顺序，而实现聊天功能不应该有先后顺序
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class basic_chat_client3 {
	public static void main(String[] args) throws UnknownHostException, IOException{
		  Socket client = new Socket("localhost",9999);
			  new Thread(new client_send(client)).start();//一条路径
			  new Thread(new client_receive(client)).start();//另一条路径  
	}
}
//发送消息
class client_send implements Runnable{
	DataOutputStream dos;
	//构造器
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
//发送消息
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