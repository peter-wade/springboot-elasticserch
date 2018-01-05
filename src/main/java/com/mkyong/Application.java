package com.mkyong;

import static org.mockito.Matchers.intThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mkyong.book.model.Log;
import com.mkyong.book.service.BookService;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

@SpringBootApplication
@RestController
public class Application{

	protected static Logger logger = LoggerFactory.getLogger(Application.class);
	
	@Autowired
    private ElasticsearchOperations es;

    @Autowired
    private BookService bookService;

    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }
    
    @RequestMapping(value="/getmessage")
    public String getmessgae() {
    	Iterable<Log> iterable=bookService.findAll();
    	
    	int i=0;
    	for (Iterator<Log> iter = iterable.iterator(); iter.hasNext();) {
    	     Log str = (Log)iter.next();
    	     
    	     
    	     
    	     
    	}
    	
    	return "hello";
    }
    
	@RequestMapping(value="/message")
	@ResponseBody
	public List<Object> getMessage(@RequestBody Map<String,Object> map) {
		
		ArrayList<String> periodlist=(ArrayList<String>) map.get("period");
		ArrayList<String> serieslist=(ArrayList<String>) map.get("series");
		String timeinterval=(String) map.get("window");
		
		boolean timeflog=false;
		boolean turnout=false;
		boolean changeinto=false;
		for(String str:serieslist) {
			if("pay".equals(str)) {
				timeflog=true;
			}else if("turnout".equals(str)){
				turnout=true;
			}else if("changeinto".equals(str)) {
				changeinto=true;
			}
		}
		
		ArrayList<Object> resultlist=new ArrayList<Object>();
		if(changeinto) {
			ArrayList<Long> timelist=getMoneyData(periodlist,timeinterval);
			HashMap<String, Object> timemap=new HashMap<String,Object>();
			timemap.put("data", timelist);
			timemap.put("name", "转入总额");
             resultlist.add(timemap);
		}else {
        	logger.info("Logtest1Application===================timeflog=false");
        }
		
		if(timeflog) {
			ArrayList<Long> timelist=getMoneyData(periodlist,timeinterval);
			HashMap<String, Object> timemap=new HashMap<String,Object>();
			timemap.put("data", timelist);
			timemap.put("name", "转出总额");
             resultlist.add(timemap);
		}else {
        	logger.info("Logtest1Application===================timeflog=false");
        }
	
		if(turnout) {
			ArrayList<Long> timelist=getMoneyData(periodlist,timeinterval);
			HashMap<String, Object> timemap=new HashMap<String,Object>();
			timemap.put("data", timelist);
			timemap.put("name", "支付总额");
             resultlist.add(timemap);
		}else {
        	logger.info("Logtest1Application===================timeflog=false");
        }
	
            
	    return resultlist;
		}
	
	
	
	/* 
     * 获取支付耗时队列
     */    
    public ArrayList<Long> getMoneyData(List periodlist,String inteval){
    	int timeinteval=Integer.valueOf(inteval);
    	if(periodlist.size()>0) {
    		 ArrayList<Long> turnlist=new ArrayList<Long>();
			for(int i=0;i<periodlist.size();i++) {
			String start=(String)periodlist.get(i);
			Long startstamp=Long.valueOf(dateToStamp(start))*1000;
            long endstamp=startstamp+timeinteval*1000000;
        	
            
            Iterable<Log> iterable=bookService.findAll();
        	
            Long total = 0L;
        	for (Iterator<Log> iter = iterable.iterator(); iter.hasNext();) {
        	     Log stt = (Log)iter.next();
        	     JSONObject jb = JSONObject.fromObject(stt.getMessage());
        	     Map<String, Object> map = (Map<String, Object>)jb;
        	     String ste=dateToStamp2((String)map.get("timestamp"));
        	     if(Long.valueOf(ste)*1000>startstamp && Long.valueOf(ste)*1000<endstamp) {
        	    	 total+=Long.valueOf((String)map.get("total"));
        	     }
        	}
        	turnlist.add(total);
			}
			return turnlist;
    	}
		return new ArrayList<Long>();
    }
	
	
	/* 
     * 将时间转换为时间戳
     */    
    public static String dateToStamp(String s){
        String res="";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
		try {
			date = simpleDateFormat.parse(s);
			 long ts = date.getTime();
	          res = String.valueOf(ts);
	          return res;
		} catch (ParseException e) {
			logger.error("Logtest1Application========ParseException="+e);
			e.printStackTrace();
		}
        return res;
    }
    
    /* 
     * 将时间转换为时间戳2
     */    
    public static String dateToStamp2(String s){
        String res="";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date date;
		try {
			date = simpleDateFormat.parse(s);
			 long ts = date.getTime();
	          res = String.valueOf(ts);
	          return res;
		} catch (ParseException e) {
			logger.error("Logtest1Application========ParseException="+e);
			e.printStackTrace();
		}
        return res;
    }
    
    

}