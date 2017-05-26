package com.csjbot.robot.api.pay.config;

import com.csjbot.robot.biz.pay.service.WxPayDBService;
import com.csjbot.robot.biz.pay.util.WxPayParamName;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.Netty4ClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.KeyManagerFactory;
import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.security.KeyStore;

// 这是一个配置文件，和spring-xxx.xml配置文件一样都可以创建bean，只不过形式是写Java代码
@Configuration
public class WxPayConfiguration {

    @Autowired
    @Qualifier("wxPayDBService")
    private WxPayDBService dbService;
    @Autowired
    private WxPayPorperties porperties;

    /**
     * 创建一个HTTP RestTemplate bean交由Spring管理；
     * 可以在别处注入使用，比如
     * <pre>
     * &#064Autowired
     * private RestTemplate restTemplate;
     * //注意因为暂时没有进行更多设置、变量名一定要和method名一致（即restTemplate）、否则会注入失败
     * </pre>
     */
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters()
            .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }

    /**
     * 创建一个HTTPS RestTemplate bean，用于微信退款接口；
     * 使用方法同restTemplate()
     *
     * @see WxPayConfiguration#restTemplate
     */
    @Bean
    public RestTemplate restTemplateWithCert() throws Exception {
        ClientHttpRequestFactory factory = createHttpRequestFactory();
        RestTemplate restTemplate = new RestTemplate(factory);
        restTemplate.getMessageConverters()
            .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }

    private ClientHttpRequestFactory createHttpRequestFactory() throws Exception {
        Netty4ClientHttpRequestFactory factory = new Netty4ClientHttpRequestFactory();
        SslContextBuilder sslContextBuilder = SslContextBuilder.forClient();
        if (porperties.isRefundEnabled()) {  // todo use environment settings
            final String fileName = porperties.getValueStrict(WxPayPorperties.K_CERT_FILE);
            final String passwd = dbService.getAccount().get(WxPayParamName.K_MCH_ID);
            if (passwd == null) throw new NullPointerException("mch_id");
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            try (FileInputStream fileIn = new FileInputStream(fileName)) {
                keyStore.load(fileIn, passwd.toCharArray());
            }
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(keyStore, passwd.toCharArray());
            SslContext sslContext = sslContextBuilder.keyManager(keyManagerFactory).build();
            factory.setSslContext(sslContext);
        } else {
            factory.setSslContext(sslContextBuilder.build());
        }
        return factory;
    }


}
