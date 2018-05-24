package com.xiakee.view.material;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.crypto.hash.Sha1Hash;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.xiakee.domain.urm.SecurityUser;

@Controller
public class UploadImageParamController extends BaseController {

	@ResponseBody
	@RequestMapping("upload-param")
	public String getUploadImageParam(@RequestParam String type) {
		SecurityUser user = getUser();
		if (user == null) {
			return null;
		}
		String param = String.valueOf(user.getId());
		String timestamp = String.valueOf(System.currentTimeMillis());
		String signature = generateSignature(new String[] { param, timestamp });
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		params.put("type", type);
		params.put("param", param);
		params.put("timestamp", timestamp);
		params.put("signature", signature);
		return JSON.toJSONString(params);
	}

	private static String generateSignature(String[] args) {
		String token = "jiuxianqian798@xiakee.com";
		String[] temp = new String[args.length + 1];
		for (int i = 0; i < args.length; i++) {
			temp[i] = args[i];
		}
		temp[(temp.length - 1)] = token;
		Arrays.sort(temp);
		StringBuilder builder = new StringBuilder();
		for (String s : temp) {
			builder.append(s);
		}
		return new Sha1Hash(builder.toString()).toHex();
	}

}
