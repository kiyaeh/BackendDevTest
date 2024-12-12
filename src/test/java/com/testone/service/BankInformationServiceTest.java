package com.testone.service;

import com.testone.model.BankInformation;
import com.testone.repository.BankInformationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class BankInformationServiceTest {

    @Mock
    private BankInformationRepository bankInformationRepository;

    @InjectMocks
    private BankInformationService bankInformationService;

    private BankInformation testBankInfo;
    private UUID userId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        userId = UUID.randomUUID();
        testBankInfo = new BankInformation();
        testBankInfo.setId(UUID.randomUUID());
        testBankInfo.setUserId(userId);
        testBankInfo.setBankAccountNumber("1234567890");
        testBankInfo.setBankName("Test Bank");
        testBankInfo.setAccountType("Checking");
    }

    @Test
    void createBankInformation_Success() {
        when(bankInformationRepository.save(any(BankInformation.class))).thenReturn(testBankInfo);

        BankInformation created = bankInformationService.createBankInformation(testBankInfo);

        assertNotNull(created);
        assertEquals(testBankInfo.getBankName(), created.getBankName());
        assertEquals(testBankInfo.getBankAccountNumber(), created.getBankAccountNumber());
        verify(bankInformationRepository, times(1)).save(any(BankInformation.class));
    }

    @Test
    void getBankInformationByUserId_Success() {
        List<BankInformation> bankInfoList = new ArrayList<>();
        bankInfoList.add(testBankInfo);

        when(bankInformationRepository.findByUserId(userId)).thenReturn(bankInfoList);

        List<BankInformation> found = bankInformationService.getBankInformationByUserId(userId);

        assertFalse(found.isEmpty());
        assertEquals(1, found.size());
        assertEquals(testBankInfo.getBankName(), found.get(0).getBankName());
        verify(bankInformationRepository, times(1)).findByUserId(userId);
    }

    @Test
    void updateBankInformation_Success() {
        List<BankInformation> bankInfoList = new ArrayList<>();
        bankInfoList.add(testBankInfo);

        BankInformation updatedInfo = new BankInformation();
        updatedInfo.setBankAccountNumber("9876543210");
        updatedInfo.setBankName("Updated Bank");
        updatedInfo.setAccountType("Savings");

        when(bankInformationRepository.findByUserId(userId)).thenReturn(bankInfoList);
        when(bankInformationRepository.save(any(BankInformation.class))).thenReturn(updatedInfo);

        BankInformation result = bankInformationService.updateBankInformation(userId, updatedInfo);

        assertNotNull(result);
        assertEquals(updatedInfo.getBankName(), result.getBankName());
        assertEquals(updatedInfo.getBankAccountNumber(), result.getBankAccountNumber());
        verify(bankInformationRepository, times(1)).findByUserId(userId);
        verify(bankInformationRepository, times(1)).save(any(BankInformation.class));
    }

    @Test
    void updateBankInformation_NotFound() {
        when(bankInformationRepository.findByUserId(userId)).thenReturn(new ArrayList<>());

        assertThrows(RuntimeException.class, () ->
                bankInformationService.updateBankInformation(userId, testBankInfo)
        );
        verify(bankInformationRepository, times(1)).findByUserId(userId);
        verify(bankInformationRepository, never()).save(any(BankInformation.class));
    }

    @Test
    void deleteBankInformation_Success() {
        List<BankInformation> bankInfoList = new ArrayList<>();
        bankInfoList.add(testBankInfo);

        when(bankInformationRepository.findByUserId(userId)).thenReturn(bankInfoList);
        doNothing().when(bankInformationRepository).deleteAll(anyList());

        bankInformationService.deleteBankInformation(userId);

        verify(bankInformationRepository, times(1)).findByUserId(userId);
        verify(bankInformationRepository, times(1)).deleteAll(bankInfoList);
    }

    @Test
    void deleteBankInformation_NoRecordsFound() {
        when(bankInformationRepository.findByUserId(userId)).thenReturn(new ArrayList<>());

        bankInformationService.deleteBankInformation(userId);

        verify(bankInformationRepository, times(1)).findByUserId(userId);
        verify(bankInformationRepository, never()).deleteAll(anyList());
    }
}