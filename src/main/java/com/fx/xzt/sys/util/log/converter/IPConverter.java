package com.fx.xzt.sys.util.log.converter;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPConverter extends ClassicConverter {

    private static String address;
    
    static {
        try {
            address = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        
    }
    
    public String convert(ILoggingEvent arg0) {
        return address;
    }

}

