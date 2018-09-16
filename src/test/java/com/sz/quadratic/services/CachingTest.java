package com.sz.quadratic.services;

import com.sz.quadratic.interfaces.IQuadraticService;
import com.sz.quadratic.models.Quadratic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring-context.xml"})
public class CachingTest {

    @Autowired
    private IQuadraticService quadraticService;

    @Test
    public void testCaching(){
        List<Quadratic> firstQuadratics = quadraticService.getAllQuadratics();
        quadraticService.clearCache();
        List<Quadratic> secondQuadratics = quadraticService.getAllQuadratics();
        assertThat(firstQuadratics.hashCode(), is(not(equalTo(secondQuadratics.hashCode()))));
    }

    @Test
    public void testWithOutCaching(){
        List<Quadratic> firstQuadratics = quadraticService.getAllQuadratics();
        List<Quadratic> secondQuadratics = quadraticService.getAllQuadratics();
        assertThat(firstQuadratics.hashCode(), is(equalTo(secondQuadratics.hashCode())));
    }
}
