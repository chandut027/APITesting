package com.generic.test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

public class PhilipsRESTtest {

	@Test
	public void testApp() {
		try {
			String resp = callRESTService("https://www.philips.co.uk/prx/product/B2C/en_GB/CONSUMER/products/SRU8015_34.summary");
			System.out.println(resp);
			JSONObject data = new JSONObject(resp).getJSONObject("data");
			String ctn = data.getString("ctn");
			System.out.println(ctn);
			Assert.assertEquals("SRU8015/34", ctn);
			JSONArray array = data.getJSONArray("allowedApps");
			int lengthAdm = data.getJSONArray("allowedApps").length();
			System.out.println(lengthAdm);
			Assert.assertEquals(7, lengthAdm);
			JSONObject app;
			for (int i = 0; i <= lengthAdm - 1; i++) {
				app = (JSONObject) array.get(i);
				String path = (String) app.get("appName");
				System.out.println(path);

			}

			
		} catch (Exception e) {
			System.out.println(" Error " + e.getMessage());
		}
	}
	
	@Test
	public void testApp1() {
		try {
			String resp = callRESTService("https://www.philips.co.uk/prx/product/B2C/en_GB/CONSUMER/products/SRU8015_34.summary");
			System.out.println(resp);
			JSONObject data = new JSONObject(resp).getJSONObject("data");
			String ctn = data.getString("ctn");
			System.out.println(ctn);
			Assert.assertEquals("SRU8015/34", ctn);

		} catch (Exception e) {
			System.out.println(" Error " + e.getMessage());
		}
	}

	public String callRESTService(String path) throws MalformedURLException, IOException,
			ProtocolException {
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}

		String resp = IOUtils.toString(conn.getInputStream(), "utf-8");
		conn.disconnect();
		return resp;
	}

	

}
