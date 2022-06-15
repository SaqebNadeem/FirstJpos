package com.maintest;

import org.jpos.iso.*;
import org.jpos.iso.channel.ASCIIChannel;
import org.jpos.iso.packager.ISO87APackager;
import org.jpos.util.LogSource;
import org.jpos.util.Logger;
import org.jpos.util.SimpleLogListener;
import java.io.IOException;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws IOException, ISOException {
        Logger logger = new Logger();
        logger.addListener(new SimpleLogListener(System.out));
        ISOChannel channel = new ASCIIChannel("localhost",8000,new ISO87APackager());
        ((LogSource)channel).setLogger(logger,"channel");
        channel.connect();

        ISOMsg isoMsg = new ISOMsg();
        isoMsg.set(0,"0800");
        isoMsg.set(3,"39000");
        isoMsg.set(4,"000000000000");
        isoMsg.set(7, ISODate.getDate(new Date()));
        isoMsg.set(11,"143708");
        isoMsg.set(12,"143708");
        isoMsg.set(13,"1005");
        isoMsg.set(32,"123");
        isoMsg.set(41,"11110001");
        isoMsg.set(42,"111111111100001");
        isoMsg.set(48,"DF01053132383032DF020A30323933333630313438DF090431313031DF0A06313233343536");
        isoMsg.set(49,"360");

        channel.send(isoMsg);
        channel.receive();
    }
}