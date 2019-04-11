package org.github.caishijun.motan.server.service;

import org.github.caishijun.motan.api.ClusterUseZooKeeperService;

public class ClusterUseZooKeeperServiceImpl implements ClusterUseZooKeeperService {

    public String hello(String name) {
        System.out.println(name + " invoked rpc service ClusterUseZooKeeper");
        return "hello " + name;
    }

}
