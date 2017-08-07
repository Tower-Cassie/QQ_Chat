package Github.chat;

import java.io.*;
/**
 * 功能:实现关闭流的功能
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
