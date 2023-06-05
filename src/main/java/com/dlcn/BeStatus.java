package com.dlcn;

import com.dlcn.entity.ServerInfo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class BeStatus {
    public static ServerInfo lookup(String address) {
        // create data packet
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
        try {
            // send data packet
            DatagramSocket client = new DatagramSocket();
            DatagramPacket packet = new DatagramPacket(data, data.length,
                    InetAddress.getByName("45.125.44.43"), 19132);
            client.send(packet);
            // receive data packet
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            client.receive(receivePacket);
            System.out.println(new String(receivePacket.getData()));
            // close udp client
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ServerInfo();
    }
}
