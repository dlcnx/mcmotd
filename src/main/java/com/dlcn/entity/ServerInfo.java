package com.dlcn.entity;

import com.alibaba.fastjson.JSON;

public class ServerInfo {
    public boolean online = false;
    public String motd;
    public String protocol;
    public String version;
    public int players_online;
    public int players_max;
    public String level_name;
    public String gamemode;
    public int port;
    public int port6;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
