package org.github.caishijun.motan.client.controller;

import com.weibo.api.motan.rpc.Future;
import com.weibo.api.motan.rpc.FutureListener;
import com.weibo.api.motan.rpc.ResponseFuture;
import org.github.caishijun.motan.api.ClusterUseConsulService;
import org.github.caishijun.motan.api.ClusterUseZooKeeperService;
import org.github.caishijun.motan.api.SimpleAsyncInvokeServiceAsync;
import org.github.caishijun.motan.api.SimpleSyncInvokeService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/motanClient")
public class MotanClientController {

    private ClassPathXmlApplicationContext motanApplicationContext = null;

    public synchronized ClassPathXmlApplicationContext getMotanApplicationContext(String configLocation) {
        if (motanApplicationContext != null) {
            motanApplicationContext.close();
        }
        motanApplicationContext = new ClassPathXmlApplicationContext(new String[] {configLocation});
        return motanApplicationContext;
    }

    @RequestMapping("/simpleSyncInvoke")
    public String simpleSyncInvoke() {
        ClassPathXmlApplicationContext ctx = getMotanApplicationContext("classpath:motan-client-simple-sync.xml");

        SimpleSyncInvokeService simpleSyncInvokeService = (SimpleSyncInvokeService) ctx.getBean("simpleSyncInvokeService");
        for (int i = 0; i < 1; i++) {
            String result = simpleSyncInvokeService.hello("motan" + i);
            System.out.println(result);
        }
        System.out.println("simpleSyncInvoke motan demo is finish.");
        return "simpleSyncInvoke success";
    }

    @RequestMapping("/simpleAsyncInvoke")
    public String simpleAsyncInvoke() {
        ClassPathXmlApplicationContext ctx = getMotanApplicationContext("classpath:motan-client-simple-async.xml");

        SimpleAsyncInvokeServiceAsync service = (SimpleAsyncInvokeServiceAsync) ctx.getBean("simpleAsyncInvokeService");
        for (int i = 0; i < 1; i++) {
            // sync call
            System.out.println(service.helloAsync("motan" + i));

            // async call
            ResponseFuture future = service.helloAsync("motan async " + i);
            System.out.println(future.getValue());

            // multi call
            ResponseFuture future1 = service.helloAsync("motan async multi-1-" + i);
            ResponseFuture future2 = service.helloAsync("motan async multi-2-" + i);
            System.out.println(future1.getValue() + ", " + future2.getValue());

            // async with listener
            ResponseFuture future3 = service.helloAsync("motan async multi-1-" + i);
            ResponseFuture future4 = service.helloAsync("motan async multi-2-" + i);
            FutureListener listener = new FutureListener() {
                @Override
                public void operationComplete(Future future) throws Exception {
                    System.out.println("async call "
                        + (future.isSuccess() ? "sucess! value:" + future.getValue() : "fail! exception:"
                        + future.getException().getMessage()));
                }
            };
            future3.addListener(listener);
            future4.addListener(listener);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("motan demo is finish.");

        return "simpleAsyncInvoke success";
    }

    @RequestMapping("/clusterUseConsul")
    public String clusterUseConsul() {
        ClassPathXmlApplicationContext ctx = getMotanApplicationContext("classpath:motan-client-cluster-consul.xml");

        ClusterUseConsulService clusterUseConsulService = (ClusterUseConsulService) ctx.getBean("clusterUseConsulService");
        for (int i = 0; i < 1; i++) {
            String result = clusterUseConsulService.hello("motan" + i);
            System.out.println(result);
        }
        System.out.println("clusterUseConsul motan demo is finish.");
        return "clusterUseConsul success";
    }

    @RequestMapping("/clusterUseZooKeeper")
    public String clusterUseZooKeeper() {
        ClassPathXmlApplicationContext ctx = getMotanApplicationContext("classpath:motan-client-cluster-zookeeper.xml");

        ClusterUseZooKeeperService clusterUseZooKeeperService = (ClusterUseZooKeeperService) ctx.getBean("clusterUseZooKeeperService");
        for (int i = 0; i < 1; i++) {
            String result = clusterUseZooKeeperService.hello("motan" + i);
            System.out.println(result);
        }
        System.out.println("clusterUseZooKeeper motan demo is finish.");
        return "clusterUseZooKeeper success";
    }

}
