package com.dianping.rotate.shop

import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.transaction.TransactionConfiguration
import org.springframework.transaction.annotation.Transactional
import spock.lang.Ignore
import spock.lang.Specification

/**
 * Created by zaza on 14/12/31.
 */
@ContextConfiguration(locations = [
    "classpath*:/config/spring/common/appcontext-*.xml",
    "classpath*:/config/spring/local/appcontext-*.xml"])
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager",defaultRollback = true)
@Ignore
public abstract class AbstractSpockTest extends Specification {

}
