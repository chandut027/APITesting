package com.generic.test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

public class JunitREST {

	@Test
	public void testApp() {
		try {
			URL url = new URL(
					"http://localhost:4502/libs/cq/security/userinfo.json");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			String encoded = Base64.getEncoder().encodeToString(
					("admin" + ":" + "admin").getBytes(StandardCharsets.UTF_8)); // Java
																					// 8
			conn.setRequestProperty("Authorization", "Basic " + encoded);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			String resp = IOUtils.toString(conn.getInputStream(), "utf-8");
			System.out.println(resp);
			JSONObject obj = new JSONObject(resp);
			String object = obj.getString("userID");
			System.out.println(object);
			Assert.assertEquals("admin", object);
			JSONArray array = obj.getJSONArray("allowedApps");
			int lengthAdm = obj.getJSONArray("allowedApps").length();
			System.out.println(lengthAdm);
			Assert.assertEquals(7, lengthAdm);
			JSONObject ctn;
			for (int i = 0; i <= lengthAdm - 1; i++) {
				ctn = (JSONObject) array.get(i);
				String path = (String) ctn.get("appName");
				System.out.println(path);

			}

			conn.disconnect();
		} catch (Exception e) {
			System.out.println(" Error " + e.getMessage());
		}
	}

	@Test
	public void testApp1() {
		try {
			URL url = new URL(
					"http://localhost:4502/libs/cq/security/userinfo.json");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			String encoded = Base64.getEncoder().encodeToString(
					("chandu" + ":" + "chandu")
							.getBytes(StandardCharsets.UTF_8)); // Java
																// 8
			conn.setRequestProperty("Authorization", "Basic " + encoded);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			String resp = IOUtils.toString(conn.getInputStream(), "utf-8");
			System.out.println(resp);
			JSONObject obj = new JSONObject(resp);
			String object = obj.getString("userID");
			System.out.println(object);
			Assert.assertEquals("chandu", object);
			JSONArray array = obj.getJSONArray("allowedApps");
			int lengthAuth = obj.getJSONArray("allowedApps").length();
			System.out.println(lengthAuth);
			Assert.assertEquals(4, lengthAuth);
			JSONObject ctn;
			for (int i = 0; i <= lengthAuth - 1; i++) {
				ctn = (JSONObject) array.get(i);
				String path = (String) ctn.get("appName");
				System.out.println(path);

			}

			conn.disconnect();
		} catch (Exception e) {
			System.out.println(" Error " + e.getMessage());
		}
	}

}
