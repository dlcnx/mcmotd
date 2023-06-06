package com.dlcn.entity;

public class PacketData {
    // create data packet
    public static byte[] be_data() {
        String text = "01" + "0000000000000000" + "00ffff00fefefefefdfdfdfd12345678" + "0000000000000000";
        int data_length = text.length() / 2;
        byte[] data = new byte[data_length];
        for (int i = 0; i < data_length; i++) {
            char large = text.charAt(i * 2);
            char small = text.charAt(i * 2 + 1);
            String hex_str = String.valueOf(large) + small;
            byte one_byte = (byte) Integer.parseInt(hex_str, 16);
            data[i] = one_byte;
        }
        return data;
    }
}
