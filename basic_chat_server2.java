package Github.chat;

import java.io.*;
import java.net.*;
/**
 * 功能：实现客户端和服务器之间的通信,弊端：只能一个客户端发消息，发消息也只能一行一行的发，服务器只能一条一条的接收
 * 待完善：允许多个客户端给服务器传递消息
 * @author xiaohong
 *
 */
public class basic_chat_server2 {
	public static void  main(String[] args) throws IOException{
		//创建服务器端
		ServerSocket s = new ServerSocket(9999);
		//只允许一个客户端连入
		Socket client = s.accept();
		//输入流
		DataInputStream dis =  new DataInputStream(client.getInputStream());
		String str = dis.readUTF();
		System.out.println(str);
		//输出流
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());
		dos.writeUTF(str);
	}

}
