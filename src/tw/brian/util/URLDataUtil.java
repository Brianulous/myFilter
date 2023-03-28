package tw.brian.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class URLDataUtil {
	/**
	 * 以url的形式傳入
	 * @param urlsString
	 * @return
	 */
	public List<String> getURLContent(String urlsString) {

		try {
			String url = urlsString;
			URL urlObj = new URL(url);

			LinkedList<Object> contents = new LinkedList<>();
			String content = "";

			InputStream inputStream = urlObj.openStream();
			InputStreamReader isr = new InputStreamReader(inputStream);
			BufferedReader bfr = new BufferedReader(isr);

			while (bfr.ready()) {
				content = bfr.readLine();
				contents.add(content);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

}
