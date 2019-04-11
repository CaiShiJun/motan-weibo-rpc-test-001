package org.github.caishijun.motan.api;

import com.weibo.api.motan.transport.async.MotanAsync;

@MotanAsync
public interface SimpleAsyncInvokeService {
    public String hello(String name);

}
