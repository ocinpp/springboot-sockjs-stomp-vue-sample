package com.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Reference: https://docs.spring.io/spring/docs/current/spring-framework-reference/html/websocket.html
 *
 */
@Controller
public class InfoController {

	@Autowired
	private SimpMessagingTemplate messageTemplate;
    
    @Scheduled(fixedDelay=10000)    
    public void priceManualConvert() throws Exception {
    	double val1 = (new BigDecimal(Math.random() * 1000 + "")).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
    	double val2 = (new BigDecimal(Math.random() * 1000 + "")).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
    	
    	StockQuote stock1 = new StockQuote("Fantastic Food", val1);
    	StockQuote stock2 = new StockQuote("Marvellous Car", val2);
    	
    	List<StockQuote> list = new ArrayList<StockQuote>();
    	list.add(stock1);
    	list.add(stock2);
    	
    	// convert from object to JSON
    	ObjectMapper mapper = new ObjectMapper();    	
    	String jsonInString = mapper.writeValueAsString(list);    	
    	
    	this.messageTemplate.convertAndSend("/stock/price", jsonInString);
    }

    @Scheduled(fixedDelay=5000)    
    public void priceAutoConvert() throws Exception {
    	double val1 = (new BigDecimal(Math.random() * 1000 + "")).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
    	double val2 = (new BigDecimal(Math.random() * 1000 + "")).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
    	
    	StockQuote stock1 = new StockQuote("Airplane 1", val1);
    	StockQuote stock2 = new StockQuote("Electricity", val2);
    	
    	List<StockQuote> list = new ArrayList<StockQuote>();
    	list.add(stock1);
    	list.add(stock2);    	
    	
    	// convert from object to JSON
    	ObjectMapper mapper = new ObjectMapper();    	
    	String jsonInString = mapper.writeValueAsString(list);    	
    	
    	this.messageTemplate.convertAndSend("/stock/price-fast", jsonInString);
    }
}
