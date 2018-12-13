package com.core.library;

import java.util.HashMap;
import java.util.Map;

public class BaseController {
	public HashMap<String,Object> mapParam = null;
	private Map<String, Object> map = null;
	public BaseController() {
		map = new HashMap<String, Object>();
	} 
	
	public Map<String, Object> JsonData(Object data){
    	map.put("state", 0);
    	map.put("data", data);
    	return map;
    }
	
	public Map<String, Object> Success(AmericaEnum me){
    	map.put("state", 0);
    	map.put("data", me.GetMessage());
    	return map;
    }
    
    public Map<String, Object> Failure(AmericaEnum me){
    	map.put("state", -1);
    	map.put("data", me.GetMessage());
    	return map;
    }
}
