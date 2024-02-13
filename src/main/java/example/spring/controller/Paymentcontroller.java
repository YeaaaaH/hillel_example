package example.spring.controller;

import example.spring.model.Account;
import example.spring.model.Payment;
import example.spring.model.dto.PaymentDTO;
import example.spring.repository.PaymentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/payment")
public class Paymentcontroller {
    private PaymentRepository paymentRepository;

    public Paymentcontroller(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

//    @PostMapping
//    public ResponseEntity<Long> createPayment(@RequestBody PaymentDTO payment) {
//        Long id = paymentRepository.createPayment(payment);
//        return new ResponseEntity<>(id, HttpStatus.CREATED);
//    }

    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        return new ResponseEntity<>(paymentRepository.getAllPayments(), HttpStatus.CREATED);
    }
}
