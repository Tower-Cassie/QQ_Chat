package Github.chat;

import java.io.*;
/**
 * ����:ʵ�ֹر����Ĺ���
 * @author xiaohong
 *
 */
public class Close_util {
	public static void closeall(Closeable... io) throws IOException{
		for(Closeable index:io)
			if(index != null)
				index.close();
	}

}
