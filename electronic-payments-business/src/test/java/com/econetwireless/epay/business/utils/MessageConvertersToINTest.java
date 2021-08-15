package com.econetwireless.epay.business.utils;

import com.econetwireless.in.webservice.BalanceResponse;
import com.econetwireless.in.webservice.CreditRequest;
import com.econetwireless.in.webservice.CreditResponse;
import com.econetwireless.utils.pojo.INBalanceResponse;
import com.econetwireless.utils.pojo.INCreditRequest;
import com.econetwireless.utils.pojo.INCreditResponse;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Author: Samson Nyabanga
 */
public class MessageConvertersToINTest {

    private CreditRequest creditRequest;
    private BalanceResponse balanceResponse;
    private CreditResponse creditResponse;

    @Before
    public void setUp() {

        String partnerCode = "PARTNER CODE";
        String msisdn = "0775245867";
        double amount = 10.0;
        String referenceNumber = "REF-12345";
        String responseCode = "400";
        String narrative = "successful";
        double balance = 15.0;

        INCreditRequest inRequest = new INCreditRequest();
        inRequest.setPartnerCode(partnerCode);
        inRequest.setMsisdn(msisdn);
        inRequest.setAmount(amount);
        inRequest.setReferenceNumber(referenceNumber);

        creditRequest = new CreditRequest();
        creditRequest.setPartnerCode(partnerCode);
        creditRequest.setMsisdn(msisdn);
        creditRequest.setAmount(amount);
        creditRequest.setReferenceNumber(referenceNumber);

        INCreditResponse inCreditResponse = new INCreditResponse();
        inCreditResponse.setBalance(balance);
        inCreditResponse.setMsisdn(msisdn);
        inCreditResponse.setNarrative(narrative);
        inCreditResponse.setResponseCode(responseCode);

        creditResponse = new CreditResponse();
        creditResponse.setBalance(balance);
        creditResponse.setMsisdn(msisdn);
        creditResponse.setNarrative(narrative);
        creditResponse.setResponseCode(responseCode);

        INBalanceResponse inBalanceResponse = new INBalanceResponse();
        inBalanceResponse.setAmount(amount);
        inBalanceResponse.setMsisdn(msisdn);
        inBalanceResponse.setNarrative(narrative);
        inBalanceResponse.setResponseCode(responseCode);

        balanceResponse = new BalanceResponse();
        balanceResponse.setAmount(amount);
        balanceResponse.setMsisdn(msisdn);
        balanceResponse.setNarrative(narrative);
        balanceResponse.setResponseCode(responseCode);
    }

    @Test
    public void conversionOfCreditRequestToINCreditRequestShouldPassIfValidRequestIsPassed() {
        final INCreditRequest response = MessageConverters.convert(creditRequest);
        assertNotNull(response);
        assertEquals(creditRequest.getAmount(), response.getAmount(), 0.01);
        assertEquals(creditRequest.getMsisdn(), response.getMsisdn());
        assertEquals(creditRequest.getPartnerCode(), response.getPartnerCode());
        assertEquals(creditRequest.getReferenceNumber(), response.getReferenceNumber());
    }

    @Test
    public void conversionOfCreditRequestToINCreditRequestShouldFailIfRequestIsNull() {
        CreditRequest request = null;
        final INCreditRequest response = MessageConverters.convert(request);
        assertNull(response);
    }

    @Test
    public void conversionOfCreditResponseToINCreditResponseShouldFailIfRequestIsNull () {
          CreditResponse creditResponse = null;
          final INCreditResponse inCreditResponse = MessageConverters.convert(creditResponse);
          assertNull(inCreditResponse);
    }

    @Test
    public void conversionOfCreditResponseToINCreditResponseShouldPassIfValidRequestIsPassed() {
        final INCreditResponse response = MessageConverters.convert(creditResponse);
        assertNotNull(response);
        assertEquals(creditResponse.getBalance(), response.getBalance(), 0.01);
        assertEquals(creditResponse.getMsisdn(), response.getMsisdn());
        assertEquals(creditResponse.getNarrative(), response.getNarrative());
        assertEquals(creditResponse.getResponseCode(), response.getResponseCode());
    }

    @Test
    public void conversionOfBalanceResponseToINBalanceResponseShouldFailIfRequestIsNull() {
        BalanceResponse request = null;
        final INBalanceResponse response = MessageConverters.convert(request);
        assertNull(response);
    }

    @Test
    public void conversionOfBalanceResponseToINBalanceResponseShouldPassIfValidRequestIsPassed() {
        final INBalanceResponse response = MessageConverters.convert(balanceResponse);
        assertNotNull(response);
        assertEquals(balanceResponse.getAmount(), response.getAmount(), 0.01);
        assertEquals(balanceResponse.getMsisdn(), response.getMsisdn());
        assertEquals(balanceResponse.getNarrative(), response.getNarrative());
        assertEquals(balanceResponse.getResponseCode(), response.getResponseCode());
    }
}
