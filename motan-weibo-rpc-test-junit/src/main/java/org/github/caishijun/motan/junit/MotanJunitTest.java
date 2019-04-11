package org.github.caishijun.motan.junit;

import org.github.caishijun.motan.http.HttpClientUtils;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class MotanJunitTest {

    private static String SERVER_HOST = "localhost";
    private static int SERVER_PORT = 9090;
    private static String CLIENT_HOST = "localhost";
    private static int CLIENT_PORT = 9091;

    private static int FOR_TIMES = 10;
    private static int SLEEP_TIME = 5000;

    public static String getUrl(String uri, String host, int port) {
        return "http://" + host + ":" + port + uri;
    }

    @Test
    public void simpleSyncInvoke() throws Exception {
        for (int i = 0; i < FOR_TIMES; i++) {
            HttpClientUtils.sendGetRequest(getUrl("/motanServer/simpleSyncInvoke", SERVER_HOST, SERVER_PORT));
            TimeUnit.SECONDS.sleep(5);
            HttpClientUtils.sendGetRequest(getUrl("/motanClient/simpleSyncInvoke", CLIENT_HOST, CLIENT_PORT));
            TimeUnit.SECONDS.sleep(5);

            Thread.sleep(SLEEP_TIME);
        }
    }

    @Test
    public void simpleAsyncInvoke() throws Exception {
        for (int i = 0; i < FOR_TIMES; i++) {
            HttpClientUtils.sendGetRequest(getUrl("/motanServer/simpleAsyncInvoke", SERVER_HOST, SERVER_PORT));
            TimeUnit.SECONDS.sleep(5);
            HttpClientUtils.sendGetRequest(getUrl("/motanClient/simpleAsyncInvoke", CLIENT_HOST, CLIENT_PORT));
            TimeUnit.SECONDS.sleep(5);

            Thread.sleep(SLEEP_TIME);
        }
    }

    @Test
    public void clusterUseConsul() throws Exception {
        for (int i = 0; i < FOR_TIMES; i++) {
            HttpClientUtils.sendGetRequest(getUrl("/motanServer/clusterUseConsul", SERVER_HOST, SERVER_PORT));
            TimeUnit.SECONDS.sleep(5);
            HttpClientUtils.sendGetRequest(getUrl("/motanClient/clusterUseConsul", CLIENT_HOST, CLIENT_PORT));
            TimeUnit.SECONDS.sleep(5);

            Thread.sleep(SLEEP_TIME);
        }
    }

    @Test
    public void clusterUseZooKeeper() throws Exception {
        for (int i = 0; i < FOR_TIMES; i++) {
            HttpClientUtils.sendGetRequest(getUrl("/motanServer/clusterUseZooKeeper", SERVER_HOST, SERVER_PORT));
            TimeUnit.SECONDS.sleep(5);
            HttpClientUtils.sendGetRequest(getUrl("/motanClient/clusterUseZooKeeper", CLIENT_HOST, CLIENT_PORT));
            TimeUnit.SECONDS.sleep(5);

            Thread.sleep(SLEEP_TIME);
        }
    }
}
