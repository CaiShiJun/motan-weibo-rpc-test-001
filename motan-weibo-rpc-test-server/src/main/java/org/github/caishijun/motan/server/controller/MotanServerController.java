package org.github.caishijun.motan.server.controller;

import com.weibo.api.motan.common.MotanConstants;
import com.weibo.api.motan.util.MotanSwitcherUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/motanServer")
public class MotanServerController {

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

        ApplicationContext applicationContext = getMotanApplicationContext("classpath:motan-server-simple-sync.xml");
        System.out.println("simpleSyncInvoke server start...");

        return "simpleSyncInvoke success";
    }

    @RequestMapping("/simpleAsyncInvoke")
    public String simpleAsyncInvoke() {

        ApplicationContext applicationContext = getMotanApplicationContext("classpath:motan-server-simple-async.xml");
        System.out.println("simpleSyncInvoke server start...");

        return "simpleAsyncInvoke success";
    }

    @RequestMapping("/clusterUseConsul")
    public String clusterUseConsul() {

        ApplicationContext applicationContext = getMotanApplicationContext("classpath:motan-server-cluster-consul.xml");
        MotanSwitcherUtil.setSwitcherValue(MotanConstants.REGISTRY_HEARTBEAT_SWITCHER, true);
        System.out.println("clusterUseConsul server start...");

        return "clusterUseConsul success";
    }

    @RequestMapping("/clusterUseZooKeeper")
    public String clusterUseZooKeeper() {

        ApplicationContext applicationContext = getMotanApplicationContext("classpath:motan-server-cluster-zookeeper.xml");
        MotanSwitcherUtil.setSwitcherValue(MotanConstants.REGISTRY_HEARTBEAT_SWITCHER, true);
        System.out.println("clusterUseZooKeeper server start...");

        return "clusterUseZooKeeper success";
    }

}
