package weixin.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import weixin.entity.AccessToken;
import weixin.entity.Button;
import weixin.entity.ClickButton;
import weixin.entity.Menu;
import weixin.entity.ViewButton;



public class WeixinUtil {
	
	//测试号配置
	public static final String APPID = "wx01dc6749556c4428";
	public static final String APPSECRET = "4bfe294939d6421d8f049ef9f4dc483b";
	public static final String DN = "http://7bc1b27b.ngrok.io";
	
	//微信号的配置
//	public static final String APPID = "wxde3504dfb219fc20";
//	public static final String APPSECRET = "1824588d88f3251162b7ba687776b855";
//	public static final String DN = "http://www.iotesta.com.cn";

	private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	private static final String UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
	private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	private static final String GET_USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

	private static final String SCOPE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";

	private static final String GET_USER_ID = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	private static final String GET_MATERIAL = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN";

	private static final String POST_CusService_SEND = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";

	/**
	 * get请求
	 * 
	 * @param url
	 * @return
	 */
	public static JSONObject doGetStr(String url) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		JSONObject jsonObject = null;
		try {
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String result = EntityUtils.toString(entity, "UTF-8");
				jsonObject = JSONObject.fromObject(result);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return jsonObject;
	}

	/**
	 * post请求
	 */
	public static JSONObject doPostStr(String url, String outStr) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		JSONObject jsonObject = null;
		try {
			httpPost.setEntity(new StringEntity(outStr, "UTF-8"));
			HttpResponse response = httpClient.execute(httpPost);
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			jsonObject = JSONObject.fromObject(result);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonObject;
	}

	/**
	 * 获取access_token
	 */
	public static AccessToken getAccessToken() {
		AccessToken token = new AccessToken();
		String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
		JSONObject jsonObject = doGetStr(url);
		if (jsonObject != null) {
			token.setToken(jsonObject.getString("access_token"));
			token.setExpiresIn(jsonObject.getInt("expires_in"));
		}

		return token;
	}

	/**
	 * 调用微信JS接口的临时票据
	 * 
	 * @param access_token
	 *            接口访问凭证
	 * @return
	 */
	public static String getJsApiTicket(String access_token) {
		String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
		String requestUrl = url.replace("ACCESS_TOKEN", access_token);
		// 发起GET请求获取凭证
		JSONObject jsonObject = doGetStr(requestUrl);
		String jsTicket = null;
		if (jsonObject != null) {
			try {
				jsTicket = jsonObject.getString("ticket");
			} catch (JSONException e) {
				// 获取token失败
				System.out.println("获取token失败 \n"
						+ jsonObject.getInt("errcode")
						+ jsonObject.getString("errmsg"));
			}
		}
		return jsTicket;
	}

	/*
	 * 文件上传
	 */
	public static String upLoad(String filePath, String accessToken, String type)
			throws IOException {
		File file = new File(filePath);
		if (!file.exists() || !file.isFile()) {
			throw new IOException("文件不存在");
		}

		String url = UPLOAD_URL.replace("ACCESS_TOKEN", accessToken).replace(
				"TYPE", type);

		URL urlObj = new URL(url);
		// 连接
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

		con.setRequestMethod("POST");
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false);

		// 设置请求头信息
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");

		// 设置边界
		String BOUNDARY = "---------" + System.currentTimeMillis();
		con.setRequestProperty("Content-type", "multipart/form-data;boundary="
				+ BOUNDARY);

		StringBuilder sb = new StringBuilder();
		sb.append("--");
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition:form-data;name=\"file\";filename=\""
				+ file.getName() + "\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");

		byte head[] = sb.toString().getBytes("utf-8");

		// 获得输出流
		OutputStream out = new DataOutputStream(con.getOutputStream());
		// 输出表头
		out.write(head);

		// 文件正文部分
		// 把文件以流的形式推入到url中
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = in.read(bufferOut)) != -1) {
			out.write(bufferOut, 0, bytes);
		}
		in.close();

		// 结尾
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");

		out.write(foot);
		out.flush();
		out.close();

		// 用BufferedReader输入流来读取URL的响应
		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		String result = null;
		try {
			reader = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			if (result == null) {
				result = buffer.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
		}

		JSONObject jsonObject = JSONObject.fromObject(result);
		System.out.println(jsonObject);
		String mediaId = jsonObject.getString("media_id");

		return mediaId;
	}

	/**
	 * 组装菜单
	 * @return
	 */
	public static Menu initMenu() {
		Menu menu = new Menu();
		
		ViewButton button1 = new ViewButton();
		button1.setName("进入主页");
		button1.setType("view");
		button1.setUrl(DN + "/Teaching_assistant");
		
		Button button2 = new Button();
		button2.setName("校区");
		
		ViewButton button21 = new ViewButton();
		button21.setName("常州校区官网");
		button21.setType("view");
		button21.setUrl("http://www.hhuc.edu.cn/");
		
		ViewButton button22 = new ViewButton();
		button22.setName("教务");
		button22.setType("view");
		button22.setUrl("http://jwurp.hhuc.edu.cn/");
		
		button2.setSub_button(new Button[]{button21, button22});
		
		ClickButton button3 = new ClickButton();
		button3.setName("帮助");
		button3.setType("click");
		button3.setKey("help");
		
		menu.setButton(new Button[]{button1, button2, button3});
		
		return menu;	
	}

	public static int createMenu(String token, String menu) {
		int result = 0;
		String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject = doPostStr(url, menu);
		if (jsonObject != null) {
			result = jsonObject.getInt("errcode");
		}

		return result;
	}

	/**
	 * 网页授权获取openID
	 * @param code
	 * @return
	 */
	public static String getUserID(String code) {
		String userID = "";
		/*
		 * 如果网页授权的作用域为snsapi_base， 则本步骤中获取到网页授权access_token的同时，也获取到了openid，
		 * 而snsapi_base式的网页授权流程即到此为止。
		 */
		String url = GET_USER_ID.replace("APPID", APPID)
				.replace("SECRET", APPSECRET).replace("CODE", code);
		JSONObject jsonObject = doGetStr(url);
		if (jsonObject != null) {
			userID = jsonObject.getString("openid");
		}

		return userID;
	}

	/**
	 * 客服接口
	 * @param access_token
	 * @param outStr
	 * @return
	 */
	public static int Cus_Service(String access_token, String outStr) {
		int result = 0;

		String url = POST_CusService_SEND.replace("ACCESS_TOKEN", access_token);
		JSONObject jsonObject = doPostStr(url, outStr);
		if (jsonObject != null) {
			result = jsonObject.getInt("errcode");
		}
		return result;
	}
}
