package ch.hsr.traildevils.util;

import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpHandler {

	private HttpClient client = new DefaultHttpClient();
	
	private InputStreamReader isr;

	public void connectTo(String url) {
		try {
			HttpGet httpGet = new HttpGet(url);
			httpGet.setHeader("Accept", "application/json");
			
			System.out.println("send request to url:" + url);
			HttpResponse response = client.execute(httpGet);
			isr = new InputStreamReader(response.getEntity().getContent(), "utf-8");
			System.out.println("http response received");
		} catch (Exception e) {
			System.out.println("connecting to server failed");
			e.printStackTrace();
		}
	}

	public InputStreamReader getReader() {
		return (isr != null) ? isr : null;
	}

	public void resetStream() {
		if (isr != null) {
			try {
				isr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
