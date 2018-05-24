package com.xiakee.service.yz;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.junit.Ignore;

@Ignore
public class EcstoreMainTest {
	private static Logger log = Logger.getLogger(EcstoreMainTest.class);
	
	private static final String TOKEN = "ec7f3926c47f21428fc224a13e21d9fd097d2c645695d490a814c2a705436c1f";
	private static final String URL = "http://test.xiakee.com/index.php/api?";

	public static void main(String[] args) {
		try {
			HttpClient client = new DefaultHttpClient();
			
			String params = "direct=true&method=ectools.get_payments.get_all&sign=" + TOKEN;
			
			HttpGet request = new HttpGet(URL + params);
 
			HttpResponse response = client.execute(request);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				result.append(line);
			}

			System.out.println(result.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
