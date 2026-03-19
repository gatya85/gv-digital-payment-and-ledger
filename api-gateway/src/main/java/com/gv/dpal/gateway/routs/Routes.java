package com.gv.dpal.gateway.routs;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;


@Configuration
public class Routes {

    @Value("${dpal-services.account-service}")
    private String accountServiceUrl;

    @Value("${dpal-services.fraud-service}")
    private String fraudServiceUrl;

    @Value("${dpal-services.ledger-service}")
    private String ledgerServiceUrl;

    @Value("${dpal-services.notification-service}")
    private String notificationServiceUrl;

    @Value("${dpal-services.payment-service}")
    private String paymentServiceUrl;

    @Value("${dpal-services.wallet-service}")
    private String walletServiceUrl;

    @Bean
    public RouterFunction<ServerResponse> accountServiceRoute(){
        return GatewayRouterFunctions
                .route("account_service")
                .route(RequestPredicates.path("/api/accounts/**"), HandlerFunctions.http())
                .before(BeforeFilterFunctions.uri(accountServiceUrl))
                /*.filter(CircuitBreakerFilterFunctions.circuitBreaker("productServiceCircuitBreaker",
                        URI.create("forward:/fallbackroute")))*/
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> accountServiceSwaggerRoute(){
        return GatewayRouterFunctions
                .route("account_service_swagger")
                .route(RequestPredicates.path("/aggregate/account-service/v3/api-docs"), HandlerFunctions.http())
                .before(BeforeFilterFunctions.uri(accountServiceUrl))
                .before(BeforeFilterFunctions.setPath("/api-docs"))
                /*.filter(CircuitBreakerFilterFunctions.circuitBreaker("accountServiceSwaggerCircuitBreaker",
                        URI.create("forward:/fallbackroute")))*/
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> fraudServiceRoute(){
        return GatewayRouterFunctions
                .route("fraud_service")
                .route(RequestPredicates.path("/api/frauds/**"), HandlerFunctions.http())
                .before(BeforeFilterFunctions.uri(fraudServiceUrl))
                /*.filter(CircuitBreakerFilterFunctions.circuitBreaker("productServiceCircuitBreaker",
                        URI.create("forward:/fallbackroute")))*/
                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> fraudServiceSwaggerRoute(){
        return GatewayRouterFunctions
                .route("fraud_service_swagger")
                .route(RequestPredicates.path("/aggregate/fraud-service/v3/api-docs"), HandlerFunctions.http())
                .before(BeforeFilterFunctions.uri(fraudServiceUrl))
                .before(BeforeFilterFunctions.setPath("/api-docs"))
                /*.filter(CircuitBreakerFilterFunctions.circuitBreaker("fraudServiceSwaggerCircuitBreaker",
                        URI.create("forward:/fallbackroute")))*/
                .build();
    }


    @Bean
    public RouterFunction<ServerResponse> ledgerServiceRoute(){
        return GatewayRouterFunctions
                .route("ledger_service")
                .route(RequestPredicates.path("/api/ledgers/**"), HandlerFunctions.http())
                .before(BeforeFilterFunctions.uri(ledgerServiceUrl))
                /*.filter(CircuitBreakerFilterFunctions.circuitBreaker("productServiceCircuitBreaker",
                        URI.create("forward:/fallbackroute")))*/
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> ledgerServiceSwaggerRoute(){
        return GatewayRouterFunctions
                .route("ledger_service_swagger")
                .route(RequestPredicates.path("/aggregate/ledger-service/v3/api-docs"), HandlerFunctions.http())
                .before(BeforeFilterFunctions.uri(ledgerServiceUrl))
                .before(BeforeFilterFunctions.setPath("/api-docs"))
                /*.filter(CircuitBreakerFilterFunctions.circuitBreaker("ledgerServiceSwaggerCircuitBreaker",
                        URI.create("forward:/fallbackroute")))*/
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> notificationRoute(){
        return GatewayRouterFunctions
                .route("notification_service")
                .route(RequestPredicates.path("/api/notifications/**"), HandlerFunctions.http())
                .before(BeforeFilterFunctions.uri(notificationServiceUrl))
                /*.filter(CircuitBreakerFilterFunctions.circuitBreaker("productServiceCircuitBreaker",
                        URI.create("forward:/fallbackroute")))*/
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> notificationServiceSwaggerRoute(){
        return GatewayRouterFunctions
                .route("notification_service_swagger")
                .route(RequestPredicates.path("/aggregate/notification-service/v3/api-docs"), HandlerFunctions.http())
                .before(BeforeFilterFunctions.uri(notificationServiceUrl))
                .before(BeforeFilterFunctions.setPath("/api-docs"))
                /*.filter(CircuitBreakerFilterFunctions.circuitBreaker("notificationServiceSwaggerCircuitBreaker",
                        URI.create("forward:/fallbackroute")))*/
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> paymentRoute(){
        return GatewayRouterFunctions
                .route("payment_service")
                .route(RequestPredicates.path("/api/payments/**"), HandlerFunctions.http())
                .before(BeforeFilterFunctions.uri(paymentServiceUrl))
                /*.filter(CircuitBreakerFilterFunctions.circuitBreaker("productServiceCircuitBreaker",
                        URI.create("forward:/fallbackroute")))*/
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> paymentServiceSwaggerRoute(){
        return GatewayRouterFunctions
                .route("payment_service_swagger")
                .route(RequestPredicates.path("/aggregate/payment-service/v3/api-docs"), HandlerFunctions.http())
                .before(BeforeFilterFunctions.uri(paymentServiceUrl))
                .before(BeforeFilterFunctions.setPath("/api-docs"))
                /*.filter(CircuitBreakerFilterFunctions.circuitBreaker("paymentServiceSwaggerCircuitBreaker",
                        URI.create("forward:/fallbackroute")))*/
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> walletRoute(){
        return GatewayRouterFunctions
                .route("wallet_service")
                .route(RequestPredicates.path("/api/wallets/**"), HandlerFunctions.http())
                .before(BeforeFilterFunctions.uri(walletServiceUrl))
                /*.filter(CircuitBreakerFilterFunctions.circuitBreaker("productServiceCircuitBreaker",
                        URI.create("forward:/fallbackroute")))*/
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> walletServiceSwaggerRoute(){
        return GatewayRouterFunctions
                .route("wallet_service_swagger")
                .route(RequestPredicates.path("/aggregate/wallet-service/v3/api-docs"), HandlerFunctions.http())
                .before(BeforeFilterFunctions.uri(walletServiceUrl))
                .before(BeforeFilterFunctions.setPath("/api-docs"))
                /*.filter(CircuitBreakerFilterFunctions.circuitBreaker("walletServiceSwaggerCircuitBreaker",
                        URI.create("forward:/fallbackroute")))*/
                .build();
    }
}
