package com.gederin;

import com.gederin.handlers.PriceHandler;
import com.gederin.repositories.PriceRepository;

import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.extern.slf4j.Slf4j;
import reactor.ipc.netty.http.server.HttpServer;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RouterFunctions.toHttpHandler;

@Slf4j
public class Server {

    private static final String ROOT = "/api";
    private static final String ALL_PRICES = "/prices";
    private static final String PRICE_BY_ID = "/price/{id}";
    private static final String SAVE_PRICE = "/price";

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.startReactorServer(args[0], Integer.parseInt(args[1]));

        log.info("Press ENTER to exit.");
        System.in.read();
    }

    private RouterFunction<ServerResponse> routingFunction() {
        PriceRepository repository = new PriceRepository();
        PriceHandler handler = new PriceHandler(repository);

        return nest(path(ROOT),
                nest(accept(APPLICATION_JSON), route(GET(ALL_PRICES), handler::fetchAllPrices))
                        .andRoute(GET(PRICE_BY_ID), handler::fetchPriceById)
                        .andRoute(POST(SAVE_PRICE).and(contentType(APPLICATION_JSON)), handler::savePrice));
    }

    private void startReactorServer(String host, int port) {
        RouterFunction<ServerResponse> route = routingFunction();
        HttpHandler httpHandler = toHttpHandler(route);

        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(httpHandler);
        HttpServer server = HttpServer.create(host, port);
        server.newHandler(adapter).block();
    }
}
