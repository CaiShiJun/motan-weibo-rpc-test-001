package org.github.caishijun.motan.server.service;

import org.github.caishijun.motan.api.ClusterUseConsulService;

public class ClusterUseConsulServiceImpl implements ClusterUseConsulService {

    public String hello(String name) {
        System.out.println(name + " invoked rpc service ClusterUseConsulService");
        return "hello " + name;
    }

}
