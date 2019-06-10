package org.rif.lumino.explorer.utils;

import org.rif.lumino.explorer.models.enums.ChannelState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomUtil {

    private static final int NUMBER_OF_CHARS_LENGTH_RSK_ADDRRESS = 42;

    public static int getRandomNumberId() {
        int num=(int)(Math.random()*100);
        return num;
    }

    public static String getRandomRSKAddress() {
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        sb.append("0x");
        while(sb.length() < NUMBER_OF_CHARS_LENGTH_RSK_ADDRRESS){
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0, NUMBER_OF_CHARS_LENGTH_RSK_ADDRRESS);
    }

    public static String getRandomChannelState() {
        List<String> channelStates = new ArrayList<>();
        channelStates.add(ChannelState.Opened.toString());
        channelStates.add(ChannelState.Closed.toString());
        channelStates.add(ChannelState.WaitingForClose.toString());
        channelStates.add(ChannelState.Settled.toString());

        Random rand = new Random();
        return channelStates.get(rand.nextInt(channelStates.size()));
    }

    public static void main(String[] args) {
        System.out.println(getRandomChannelState());
    }

}
