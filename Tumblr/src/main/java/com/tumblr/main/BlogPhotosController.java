package com.tumblr.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.google.common.collect.ArrayListMultimap;
//import com.google.common.collect.Multimap;

@RestController
public class BlogPhotosController {

	private String api_key = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
	
	@RequestMapping("/photos/{blogName}")
	public Map<String, Object> sayHello(@PathVariable("blogName") String blogName) {
				
		final String url = "https://api.tumblr.com/v2/blog/"+blogName+"/posts/photo?limit=50&api_key=";
		Map<String, String> photos_Map = new LinkedHashMap<>();
		Map<String, Object> response_Map = new HashMap<>();
		List<Map<String, String>> phots_List = new ArrayList<>();
		
//		Multimap<String,Object> photos_Map = ArrayListMultimap.create();
			
		try {		
			InputStream is = new URL(url+api_key).openStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	        String jsonText = readAll(rd);
	        JSONObject jsonObject = new JSONObject(jsonText);
	        
	        JSONObject response = jsonObject.getJSONObject("response");
	        
	        JSONArray posts = response.getJSONArray("posts");
	        
	        
	        for(int i=0; i<posts.length(); i++) {
	        	JSONObject postObject = posts.getJSONObject(i);	 
	        	Long id = postObject.getLong("id");
	        	
	        	JSONArray photos = postObject.getJSONArray("photos");
	        	JSONObject postPhotoObject = photos.getJSONObject(0);
	        	
	        	JSONArray alt_sizes = postPhotoObject.getJSONArray("alt_sizes");
	        	JSONObject alt_sizes_Object = alt_sizes.getJSONObject(3);
	        	String alt_size_photo = alt_sizes_Object.getString("url");
	        	
	        	JSONObject original_size = postPhotoObject.getJSONObject("original_size");
	        	String photoUrl = original_size.getString("url");
//	        	System.out.println(photoUrl);	
	        	
	        	photos_Map = new LinkedHashMap<>();
	        	photos_Map.put("id", String.valueOf(id));
	        	photos_Map.put("image_path", alt_size_photo);
	        	photos_Map.put("original_path", photoUrl);
	        	phots_List.add(photos_Map);
	        }
		} catch(IOException | JSONException e) {
			e.printStackTrace();
		}
		response_Map.put("blog_name", "sexybrazil");
		response_Map.put("photos", phots_List);
		return response_Map;
	}
	
	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }
}
