package com.dlcn;

import com.dlcn.entity.ServerInfo;

public class Main {
    public static void main(String[] args) {
        // example code
        ServerInfo s = BeStatus.lookup("127.0.0.1:19132");
        System.out.println(s);
    }
}