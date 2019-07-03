package com.swapnilsankla.customerservice.listener

import com.swapnilsankla.customerservice.publisher.CustomerDataAvailableEventPublisher
import com.swapnilsankla.customerservice.repository.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import java.util.logging.Logger

@Component
class NeedLoanEventListener(@Autowired val repository: CustomerRepository,
                            @Autowired val customerDataAvailableEventPublisher: CustomerDataAvailableEventPublisher) {

    @KafkaListener(topics = ["needLoanEvent"])
    fun listen(needLoanEvent: NeedLoanEvent) {
        Logger.getLogger(NeedLoanEventListener::class.simpleName)
                .info("event received $needLoanEvent")

        repository.
                findByCustomerId(needLoanEvent.customerId)
                .doOnSuccess(customerDataAvailableEventPublisher::publish)
                .subscribe()
    }
}