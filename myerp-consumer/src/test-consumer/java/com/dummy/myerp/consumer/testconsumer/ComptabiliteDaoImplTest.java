package com.dummy.myerp.consumer.testconsumer;

import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.consumer.dao.contrat.DaoProxy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/com/dummy/myerp/consumer/testconsumer/bootstrapContext.xml" })
public class ComptabiliteDaoImplTest {


    @Autowired
    @Qualifier(value="DaoProxy")
    private DaoProxy daoProxy;

    private ComptabiliteDao comptabiliteDao;

    @Before
    public void setUp() throws Exception {
        comptabiliteDao = daoProxy.getComptabiliteDao();
    }

    @Test
    public void test1(){


        System.out.println(comptabiliteDao.getListEcritureComptable());
    }



}
