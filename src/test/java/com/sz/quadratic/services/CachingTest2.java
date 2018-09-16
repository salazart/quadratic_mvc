package com.sz.quadratic.services;

import com.sz.quadratic.dao.impl.GenericHibernateDAO;
import com.sz.quadratic.dao.interfaces.IDAO;
import com.sz.quadratic.exceptions.QuadraticException;
import com.sz.quadratic.models.Quadratic;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring-caching-context.xml"})
public class CachingTest2 {

    private List<Quadratic> quadratics = Arrays.asList(
            new Quadratic(1,2,1),
            new Quadratic(1,3,1),
            new Quadratic(1,4,1)
            );

    private QuadraticService quadraticService;
    private IDAO<Quadratic, Long> dao;

    @Before
    public void setUp() throws QuadraticException {
        this.quadraticService = new QuadraticService();
        this.dao = mock(GenericHibernateDAO.class);
        this.quadraticService.setDao(dao);
        when(dao.getAll()).thenReturn(quadratics);
    }

    @Test
    public void test() throws QuadraticException {
        quadraticService.getAllQuadratics();
        quadraticService.getAllQuadratics();
        verify(dao, times(2)).getAll();
        assertThat(true, is(true));
    }
}