package com.dlcn;

import com.dlcn.entity.ServerInfo;

public class Main {
    public static void main(String[] args) {
        ServerInfo s = BeStatus.lookup("");
        System.out.println(s.motd);
    }
}