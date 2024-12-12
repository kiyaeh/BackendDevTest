package com.testone.service;

import com.testone.model.BankInformation;
import com.testone.repository.BankInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

@Service
public class BankInformationService {

    @Autowired
    private BankInformationRepository bankInformationRepository;
    public Page<BankInformation> getPaginatedBankInfo(UUID userId, Pageable pageable) {
        return bankInformationRepository.findByUserId(userId, pageable);
    }
    public BankInformation createBankInformation(BankInformation bankInformation) {
        return bankInformationRepository.save(bankInformation);
    }

    public List<BankInformation> getBankInformationByUserId(UUID userId) {
        return bankInformationRepository.findByUserId(userId);
    }

    public BankInformation updateBankInformation(UUID userId, BankInformation updatedBankInfo) {
        List<BankInformation> bankInfos = bankInformationRepository.findByUserId(userId);
        if (bankInfos.isEmpty()) {
            throw new RuntimeException("No bank information found for this user");
        }
        BankInformation existingBankInfo = bankInfos.get(0); // Assuming one bank info per user
        existingBankInfo.setBankAccountNumber(updatedBankInfo.getBankAccountNumber());
        existingBankInfo.setBankName(updatedBankInfo.getBankName());
        existingBankInfo.setAccountType(updatedBankInfo.getAccountType());
        return bankInformationRepository.save(existingBankInfo);
    }

    public void deleteBankInformation(UUID userId) {
        List<BankInformation> bankInfos = bankInformationRepository.findByUserId(userId);
        if (!bankInfos.isEmpty()) {
            bankInformationRepository.deleteAll(bankInfos);
        }
    }
}
