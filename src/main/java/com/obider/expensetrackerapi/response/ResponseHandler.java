package com.obider.expensetrackerapi.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object obj){
        Map<String, Object> map = new LinkedHashMap<String,Object>();
        map.put("timestamp", LocalDateTime.now());
        map.put("status", status.value());
        map.put("message", message);
        map.put("data", obj);

        return new ResponseEntity<Object>(map,status);
    }
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("timestamp", LocalDateTime.now());
        map.put("status", status.value());
        map.put("message", message);
        map.put("data", null);

        return new ResponseEntity<Object>(map,status);
    }
}
