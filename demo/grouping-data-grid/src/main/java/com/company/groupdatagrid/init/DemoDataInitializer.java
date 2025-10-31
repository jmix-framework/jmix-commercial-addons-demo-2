package com.company.groupdatagrid.init;

import com.company.groupdatagrid.entity.Client;
import com.company.groupdatagrid.entity.ClientType;
import com.company.groupdatagrid.entity.Order;
import com.company.groupdatagrid.entity.OrderStatus;
import io.jmix.core.Metadata;
import io.jmix.core.UnconstrainedDataManager;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Loads demo data on first application startup. If clients table is not empty, does nothing.
 */
@Component
public class DemoDataInitializer {

    private static final Logger log = LoggerFactory.getLogger(DemoDataInitializer.class);

    @Autowired
    private UnconstrainedDataManager dataManager;
    @Autowired
    private Metadata metadata;

    @EventListener(ApplicationReadyEvent.class)
    public void init(ApplicationReadyEvent event) {
        Long cnt = dataManager.loadValue("select count(c) from Client c", Long.class).one();
        if (cnt > 0) {
            log.info("Demo data already present ({} clients). Skipping initialization.", cnt);
            return;
        }
        log.info("Initializing demo data...");

        List<Client> clients = generateClients(60);
        List<Order> orders = generateOrders(clients);

        log.info("Demo data initialization finished: clients={} orders={}",
                clients.size(),
                dataManager.loadValue("select count(o) from Order_ o", Long.class).one()
        );
    }

    private List<Client> generateClients(int count) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        var faker = new Faker();

        List<String> cities = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            cities.add(faker.address().city());
        }

        List<Client> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Client client = metadata.create(Client.class);
            client.setName(faker.company().name());
            client.setFullName(faker.company().name() + " " + faker.company().suffix());
            client.setCity(cities.get(random.nextInt(cities.size())));
            client.setType(ClientType.values()[random.nextInt(ClientType.values().length)]);
            client.setVatNumber(randomVatLike(random));
            client.setRegNumber("REG-" + (1000 + random.nextInt(9000)) + (i % 10));
            client.setWebsite("https://" + faker.internet().domainName());

            result.add(dataManager.save(client));
        }
        log.info("Generated {} clients", result.size());
        return result;
    }

    private List<Order> generateOrders(List<Client> clients) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        List<Order> result = new ArrayList<>();
        for (Client client : clients) {
            int n = random.nextInt(0, 9); // 0..8
            for (int i = 0; i < n; i++) {
                Order order = metadata.create(Order.class);
                order.setClient(client);
                LocalDate date = randomDateWithinYears(2, random);
                order.setDate(date);
                order.setQuote("Q-" + date.getYear() + "-" + (1000 + random.nextInt(9000)));
                if (random.nextBoolean()) order.setComment(sampleComment(random));
                BigDecimal total = BigDecimal.valueOf(100 + random.nextInt(9_000)).setScale(2);
                order.setTotal(total);
                if (random.nextInt(4) == 0) {
                    order.setDiscountPercent(BigDecimal.valueOf(random.nextInt(3, 20)));
                    order.setDiscountValue(order.getTotal().multiply(order.getDiscountPercent()).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_DOWN));
                }
                order.setStatus(OrderStatus.values()[random.nextInt(OrderStatus.values().length)]);
                result.add(dataManager.save(order));
            }
        }
        log.info("Generated {} orders", result.size());
        return result;
    }

    private String randomVatLike(ThreadLocalRandom r) {
        String[] cc = {"US","GB","DE","FR","CA","AU","IE","NL","SE","NO","ES","IT","PL","JP","SG"};
        String country = cc[r.nextInt(cc.length)];
        int part1 = 10 + r.nextInt(89);
        int part2 = 1000000 + r.nextInt(9000000);
        return country + part1 + "-" + part2;
    }


    private String sampleComment(ThreadLocalRandom r) {
        String[] comments = {
                "Urgent delivery requested.",
                "Include extended warranty.",
                "Customer asked for bulk discount.",
                "Repeat order based on last year's contract.",
                "Requires onsite installation.",
                "Custom branding needed.",
                "Ship in two batches.",
                "Coordinate with procurement before invoicing."
        };
        return comments[r.nextInt(comments.length)];
    }

    private LocalDate randomDateWithinYears(int years, ThreadLocalRandom r) {
        LocalDate now = LocalDate.now();
        LocalDate start = now.minusYears(years);
        long days = now.toEpochDay() - start.toEpochDay();
        return start.plusDays(r.nextLong(days + 1));
        
    }
}
