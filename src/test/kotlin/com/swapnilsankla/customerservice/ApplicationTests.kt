package com.swapnilsankla.customerservice

import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
@EmbeddedKafka(partitions = 1, controlledShutdown = false,
        brokerProperties = ["listeners=PLAINTEXT://localhost:3333", "port=3333"])
@TestPropertySource(properties = [
    "spring.kafka.bootstrap-servers=\${spring.embedded.kafka.brokers}"
])
class ApplicationTests {

    @Test
    fun contextLoads() {
    }

}
