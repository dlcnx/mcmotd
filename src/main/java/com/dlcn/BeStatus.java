package com.dlcn;

import com.dlcn.entity.PacketData;
import com.dlcn.entity.ServerInfo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class BeStatus {
    private static final byte[] packet_data = PacketData.be_data();
    public static ServerInfo lookup(String address) {
        ServerInfo server_info = new ServerInfo();
        // spilt host and port
        String[] res = address.split(":");
        String host = res[0];
        // default port is 19132
        int port = 19132;
        if (res.length > 1) {
            port = Integer.parseInt(res[1]);
        }
        try {
            // send data packet
            DatagramSocket client = new DatagramSocket();
            client.setSoTimeout(3000);
            DatagramPacket packet = new DatagramPacket(packet_data, packet_data.length,
                    InetAddress.getByName(host), port);
            client.send(packet);
            // receive data packet
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            client.receive(receivePacket);
            // parse received data
            String[] info_arr = (new String(receivePacket.getData())).split(";");
            server_info.online = true;
            server_info.motd = info_arr[1];
            server_info.protocol = info_arr[2];
            server_info.version = info_arr[3];
            server_info.players_online = Integer.parseInt(info_arr[4]);
            server_info.players_max = Integer.parseInt(info_arr[5]);
            // server_info.??? = info_arr[6];
            server_info.level_name = info_arr[7];
            server_info.gamemode = info_arr[8];
            // server_info.??? = info_arr[9];
            // The following items may not exist on unofficial servers
            // server_info.port = Integer.parseInt(info_arr[10]);
            // server_info.port6 = Integer.parseInt(info_arr[11]);
            // server_info.??? = info_arr[12];
            // close udp client
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return server_info;
    }
}
