package com.github.broncho.npoauth2.client.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Route;

/**
 * Author: ZhangXiao
 * Created: 2017/1/10
 */
public abstract class ClientBaseHandler implements Route {
    
    protected Logger logger = LoggerFactory.getLogger(getClass());
}
