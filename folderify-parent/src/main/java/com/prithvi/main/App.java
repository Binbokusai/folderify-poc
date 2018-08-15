package com.prithvi.main;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * EZ Code for generating mapping JPaths
 *
 */
public class App {
	public static void main(String[] args) throws IOException {
		String json = "{\"results\":[{\"columns\":[\"p\"],\"data\":[{\"row\":[[{\"aNumber\":\"3214235014\"},{},{\"bNumber\":\"3212820572\"}]],\"meta\":[[{\"id\":1678,\"type\":\"node\",\"deleted\":false},{\"id\":914,\"type\":\"relationship\",\"deleted\":false},{\"id\":994,\"type\":\"node\",\"deleted\":false}]]}]}],\"errors\":[]}";
		JsonNode JSON = new ObjectMapper().readTree(json);
		LinkedHashMap<?, ?> map = new ObjectMapper().convertValue(JSON, LinkedHashMap.class);
		Map<String,String>finalOutput=json2pathMap(map);
		System.out.println(finalOutput);

	}

	private static Map<String,String> json2pathMap(LinkedHashMap<?, ?> map) {
		LinkedHashMap<String,String> dataMap=new LinkedHashMap<>();
		jPathGen(dataMap, map, "$");
		return dataMap;
	}

	@SuppressWarnings("unchecked")
	private static void jPathGen(LinkedHashMap<String, String> linkedHashMap, LinkedHashMap<?, ?> map, String string) {
		for (Object key : map.keySet()) {
			String Key = (String) key;
			if (map.get(key) instanceof LinkedHashMap) {
				jPathGen(linkedHashMap, (LinkedHashMap<?, ?>) map.get(key), string + "." + Key);
			} else if (map.get(key) instanceof List) {
				List<Object> list = (List<Object>) map.get(key);
				int i = 0;
				for (Object obj : list) {
					if (obj instanceof String) {
						String data = (String) obj;
						LinkedHashMap<String, String> Map = new LinkedHashMap<String, String>();
						Map.put(Key + "[" + i + "]", data);
						jPathGen(linkedHashMap, Map, string);
					} else if (obj instanceof List) {
						List<Object> LIST = (List<Object>) obj;
						int j = 0;
						for (Object object : LIST) {
							LinkedHashMap<String, Object> arrMap = new LinkedHashMap<>();
							arrMap.put(Key + "[" + i + "][" + j + "]", object);
							jPathGen(linkedHashMap, arrMap, string);
							j++;
						}
					} else {
						LinkedHashMap<?, ?> itemMap = (LinkedHashMap<?, ?>) obj;
						jPathGen(linkedHashMap, itemMap, string + "." + Key + "[" + i + "]");
					}
					i++;

				}
			}
			else {
				linkedHashMap.put(string + "." + Key, map.get(key).toString());
			}

		}
	}

}
