package com.dianping.rotate.shop;

import org.springframework.test.context.ContextConfiguration;
import spock.lang.Ignore;
import spock.lang.Specification;

/**
 * Created by zaza on 14/12/31.
 */
@ContextConfiguration(locations = [
    "classpath*:/config/spring/common/appcontext-*.xml",
    "classpath*:/config/spring/local/appcontext-*.xml"])
@Ignore
public abstract class AbstractSpockTest extends Specification {

}
