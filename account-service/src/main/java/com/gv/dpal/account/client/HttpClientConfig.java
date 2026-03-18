package com.gv.dpal.account.client;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.service.registry.ImportHttpServices;

@Configuration
@ImportHttpServices(basePackages = "com.gv.dpal.account.client", types = {WalletClient.class})
public class HttpClientConfig {
}
