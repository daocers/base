package com.alipay.sdk.wappay;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipayLogger;

public class AlipayClientFactory {
    private static class SingletonHolder {
        private static final AlipayClient alipayClient;

        static {
            alipayClient = new DefaultAlipayClient(AlipayConfigs.GATEWAY_URL, AlipayConfigs.APP_ID,
                AlipayConfigs.PRIVATE_KEY, "json", AlipayConfigs.CHARSET,
                AlipayConfigs.ALIPAY_PUBLIC_KEY);
            if ("TRUE".equalsIgnoreCase(AlipayConfigs.DEBUG_LOG))
                AlipayLogger.setJDKDebugEnabled(true);
        }
    }

    public static final AlipayClient getClient() {
        return SingletonHolder.alipayClient;
    }
}
