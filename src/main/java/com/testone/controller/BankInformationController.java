package com.testone.controller;

import com.testone.model.BankInformation;
import com.testone.service.BankInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users/{userId}/bank-information")
public class BankInformationController {

    @Autowired
    private BankInformationService bankInformationService;

    @PostMapping
    public ResponseEntity<BankInformation> createBankInformation(@PathVariable UUID userId, @RequestBody BankInformation bankInformation) {
        bankInformation.setUserId(userId);
        BankInformation createdBankInfo = bankInformationService.createBankInformation(bankInformation);
        return ResponseEntity.ok(createdBankInfo);
    }

    @GetMapping
    public ResponseEntity<List<BankInformation>> getBankInformation(@PathVariable UUID userId) {
        List<BankInformation> bankInfos = bankInformationService.getBankInformationByUserId(userId);
        return ResponseEntity.ok(bankInfos);
    }

    @PutMapping
    public ResponseEntity<BankInformation> updateBankInformation(@PathVariable UUID userId, @RequestBody BankInformation updatedBankInfo) {
        BankInformation bankInfo = bankInformationService.updateBankInformation(userId, updatedBankInfo);
        return ResponseEntity.ok(bankInfo);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteBankInformation(@PathVariable UUID userId) {
        bankInformationService.deleteBankInformation(userId);
        return ResponseEntity.noContent().build();
    }
}
