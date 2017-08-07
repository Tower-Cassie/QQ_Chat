package Github.chat;

import java.io.*;
import java.net.*;
import java.util.*;
/**
 * 功能：实现客户端和服务器之间的通信,弊端：只能一个客户端发消息，发消息也只能一行一行的发，服务器只能一条一条的接收
 * 待完善：允许多个客户端给服务器传递消息
 * @author xiaohong
 *
 */

public class basic_chat_client2 {
	public static void main(String[] args) throws UnknownHostException, IOException{
		//创建客户端
		  Socket client = new Socket("localhost",9999);
		  //实现通话
		  //输出流
		  
		  DataOutputStream dos = new DataOutputStream(client.getOutputStream());
		  //从控制台进行输入客户端所需要传递的消息
		  Scanner input = new Scanner(System.in);
		  String msg = input.next();
		  dos.writeUTF(msg);
		  dos.flush();
		  //输入流
		  /*
		   * BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			bw.write(msg);//不能bw.close(),因为会关掉服务器端
			bw.newLine();//此处非常重要，在读取数据时是按照行来读取的，所以此处必须添加一个行的标记符，也可以在字符串后加\n
			bw.flush();//刷新
			
			
			BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
			String str = br.readLine();
			System.out.println(str);
			//接受数据和发送数据的类型必须一致
			
			*/
			//为了防止漏洞bw.newLine(),使用DataOutputStream

		  
		  DataInputStream dis = new DataInputStream(client.getInputStream());
		  String str = dis.readUTF();
		  System.out.println(str);
		  
	}

}
