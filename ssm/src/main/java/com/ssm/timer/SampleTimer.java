package com.ssm.timer;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xiaoleilu.hutool.date.DateUtil;
@Component
public class SampleTimer {
	@Scheduled(cron = "0 0 23 * * ?")
	public void timer() {
		System.out.println(DateUtil.formatDateTime(new Date()));
	}
}
