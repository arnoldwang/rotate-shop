package com.dianping.rotate.shop;

import junit.framework.Assert;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spock.lang.Ignore;
import spock.lang.Specification;

/**
 * Created by zaza on 14/12/31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath*:/config/spring/common/appcontext-*.xml",
        "classpath*:/config/spring/local/appcontext-*.xml"})
@Ignore
public  class AbstractSpockTest extends Specification {

}
