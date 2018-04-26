package com.zdp.zsso.client.component;

import com.zdp.zsso.client.entity.ZssoConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * @author <a href="mailto:zhoudapeng8888@126.com">zhoudapeng</a>
 * Date 2018/4/25
 * Time 下午5:17
 */
public class PropertiesZssoConfigResolver implements ZssoConfigResolver {
    private static Logger logger = LoggerFactory.getLogger(PropertiesZssoConfigResolver.class);
    private static final String CONFIG_FILE_NAME = "zsso.properties";
    private static ZssoConfig config;
    static {
        try {
            Properties properties = new Properties();
            properties.load(PropertiesZssoConfigResolver.class.getResourceAsStream(CONFIG_FILE_NAME));
            config.setSsoCheckTokenUrl("check.token.url");
            config.setLoginSucUrl(properties.getProperty("login.suc.url"));
            config.setLogoutUrl(properties.getProperty("logout.url"));
        }catch (Exception e) {
            logger.error("load zsso.properties fail,please check!",e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public ZssoConfig resolve() {
        return config;
    }
}
