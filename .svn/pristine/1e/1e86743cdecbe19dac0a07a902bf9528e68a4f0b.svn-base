package com.xiakee.service.yz;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Ignore;

@Ignore
public class BaiweiApiTest {

	public static void main(String[] args) {
		try {
			HttpClient client = new DefaultHttpClient();
			String expressnoUrl = "http://api.bsibuy.com.cn/BoxOpen/open/?expressno=510828570482086";
			String boxnoUrl = "http://api.bsibuy.com.cn/boxopen/api?boxno=C1505130007";
			
			HttpGet request = new HttpGet(boxnoUrl);
 
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
