package com.fx.xzt.sys.util.log.converter;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class InstanceConverter extends ClassicConverter {

    private static String instance;
    
    static {
        instance = System.getProperty("instance");
        System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;;");
        System.out.println("========================instance:" + instance);
    }
    
    @Override
    public String convert(ILoggingEvent event) {
        return instance;
    }

}
