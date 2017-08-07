package Github.chat;

import java.io.IOException;
import java.net.*;
/**
 * 功能：客户端和服务端之间建立连接
 * 待完善：客户端和服务器端是想通讯
 * 重点：服务端需要打开一个端口号，而客户端需要对应的服务端名以及对应的端口号
 * @author xiaohong
 *
 */
public class basic_chat_server {
	public static void main(String[] args) throws IOException{
		//创建服务端进程
		ServerSocket server = new ServerSocket(9999);
		//创建一个客户端
		Socket client = server.accept();
		
	}

}
