package cn.gdut.myblog.util;

import cn.gdut.myblog.common.utils.AddressUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest

public class Address {

    @Test
    public void  test(){
        String address = AddressUtil.getAddress("183.63.119.30");
        System.out.println(address);
    }
}
