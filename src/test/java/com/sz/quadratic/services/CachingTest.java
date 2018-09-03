package com.sz.quadratic.services;

import com.sz.quadratic.interfaces.IQuadraticService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring-caching-context.xml","classpath*:spring-context.xml"})
public class CachingTest {

    @Autowired
    private IQuadraticService quadraticService;

    @Test
    public void test(){
        quadraticService.getAllQuadratics();
        assertThat(true, is(true));
    }
}
