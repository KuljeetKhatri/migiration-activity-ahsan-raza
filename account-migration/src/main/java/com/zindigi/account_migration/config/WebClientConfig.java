package com.zindigi.account_migration.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import javax.net.ssl.SSLException;
import java.security.cert.CertificateException;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(WebClient.Builder builder) throws SSLException, CertificateException {
        return builder
                .clientConnector(getConnector())
                .build();
    }

    private ClientHttpConnector getConnector() throws SSLException, CertificateException {
        SslContext sslContext = SslContextBuilder.forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();

        TcpClient tcpClient = TcpClient.create()
                .secure(sslContextSpec -> sslContextSpec.sslContext(sslContext))
                .wiretap(true)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 120000)
                .doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(100))
                        .addHandlerLast(new WriteTimeoutHandler(120)));

        HttpClient httpClient = HttpClient.from(tcpClient);

        return new ReactorClientHttpConnector(httpClient);
    }

    @Bean
    public WebClientCustomizer webClientCustomizer() {
        return webClientBuilder -> webClientBuilder
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(1024 * 1024 * 10)); // Set max buffer size to 10 MB
    }
}
