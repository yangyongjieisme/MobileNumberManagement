package com.myrepublic.numbermanage.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.myrepublic.numbermanage.input.BindNumber;
import com.myrepublic.numbermanage.input.BindService;
/**
 * 
 * @date 2018年11月1日
 * 
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class NumberManageServiceTest {


	@Autowired
	NumberManageService service;
	@Test
	public void bindNumberTest() throws Exception {
		
		BindNumber input=new BindNumber();
		input.setAccount("account_2");
		input.setNumber("98888888");
		boolean result=service.bindNumber(input);
		assertTrue(result);
	}
	@Test
	public void bindServiceTest() throws Exception {
		
		BindService input=new BindService();
		input.setAccount("account_2");
		input.setNumber("98888888");
		input.setService("Roaming");
		boolean result=service.bindService(input);
		assertTrue(result);
	}

}