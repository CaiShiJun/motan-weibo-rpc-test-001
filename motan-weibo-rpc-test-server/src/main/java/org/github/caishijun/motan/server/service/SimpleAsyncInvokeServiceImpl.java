package org.github.caishijun.motan.server.service;

import org.github.caishijun.motan.api.SimpleAsyncInvokeService;

public class SimpleAsyncInvokeServiceImpl implements SimpleAsyncInvokeService {

    public String hello(String name) {
        System.out.println(name + " invoked rpc service SimpleAsyncInvokeService");
        return "hello " + name;
    }
}
