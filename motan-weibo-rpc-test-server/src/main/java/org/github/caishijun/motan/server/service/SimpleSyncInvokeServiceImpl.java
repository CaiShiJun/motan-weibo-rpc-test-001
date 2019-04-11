package org.github.caishijun.motan.server.service;

import org.github.caishijun.motan.api.SimpleSyncInvokeService;

public class SimpleSyncInvokeServiceImpl implements SimpleSyncInvokeService {

    public String hello(String name) {
        System.out.println(name + " invoked rpc service SimpleSyncInvokeService");
        return "hello " + name;
    }
}