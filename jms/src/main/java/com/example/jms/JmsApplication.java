package com.example.jms;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsClient;
import org.springframework.stereotype.Component;

import static com.example.jms.JmsApplication.Q;

@SpringBootApplication
public class JmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(JmsApplication.class, args);
    }

    static final String Q = "messages";

}


@Component
class Consumer {

    @JmsListener(destination = Q)
    void onMessage(String message) {
        System.out.println("got a message [" + message + ']');
    }
}

@Component
class Producer implements ApplicationRunner {

    private final JmsClient jms;

    Producer(JmsClient jms) {
        this.jms = jms;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        jms.destination(Q)
//                .withDeliveryDelay(10_000)
                .send("see ya later");
//        var next = jms.destination(Q).receive();
//        next.ifPresent(message -> System.out.println(message.getPayload()));
    }
}