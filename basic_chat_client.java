package Github.chat;

import java.io.IOException;
import java.net.*;
/**
 * 功能:实现客户端和服务端的连接
 * 待完善：需要实现客户端与服务端之间的通信
 * @author xiaohong
 *
 */
public class basic_chat_client {
	public static void main(String[] args) throws UnknownHostException, IOException{
		Socket client = new Socket("localhost",9999);
	}

}
