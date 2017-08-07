package Github.chat;

import java.io.*;
import java.net.*;
/**
 * 功能：实现客户端和服务器之间的通信，弊端;虽然可以实现很多客户端与服务器之间的通信，
 * 但是服务器接收客户端的消息有先后顺序，而实现聊天功能不应该有先后顺序
 * @author xiaohong
 *
 */
public class basic_chat_server3 {
	public static void  main(String[] args) throws IOException{
		//创建服务器进程
		ServerSocket s = new ServerSocket(9999);
		//与多个客户端进程进行交互，如若不用循环，则只能创建一个客户端进程
		while(true){
			Socket client = s.accept();
			//输入端
			DataInputStream dis =  new DataInputStream(client.getInputStream());
			String str = dis.readUTF();
			System.out.println(str);
			//输出端
			DataOutputStream dos = new DataOutputStream(client.getOutputStream());
			dos.writeUTF(str);
		}
	}
}
