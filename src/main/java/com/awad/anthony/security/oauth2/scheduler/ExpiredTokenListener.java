package com.awad.anthony.security.oauth2.scheduler;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.awad.anthony.security.oauth2.service.AccessTokenService;

@Component
@EnableScheduling
public class ExpiredTokenListener{
	@Autowired
	AccessTokenService accessTokenService;
	
	@Scheduled(initialDelay=0,fixedRate = 60000*2) // 1000 = 1ms 60000 = 1mn 900000 = 15mn	3600000 = 60mn
	public void scheduledFunction() {
		try {
			//this method takes date now on server 
				Date insertDate = new Date();
				accessTokenService.removeExpiredTokens(insertDate);
			}catch(Exception e) {
				//ignore exception
			}
	}

//	@PostConstruct
//	public void removeExpiredTokens() {
//		//PostConstruct annotation runs one time on application run
//		ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
//		exec.scheduleAtFixedRate(this , 0, 5, TimeUnit.MINUTES);
//		
//	}
	
	}
