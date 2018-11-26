package com.myrepublic.numbermanage;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.myrepublic.numbermanage.NumberManageApplication;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NumberManageApplicationTests {
   
    @Test
    public void applicationStarts() {
    	NumberManageApplication.main(new String[] {});
    }
}
