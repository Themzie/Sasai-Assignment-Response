package com.econetwireless.epay.business.services.impl;

import com.econetwireless.epay.business.integrations.api.ChargingPlatform;
import com.econetwireless.epay.business.services.api.EnquiriesService;
import com.econetwireless.epay.dao.subscriberrequest.api.SubscriberRequestDao;
import com.econetwireless.epay.domain.SubscriberRequest;
import com.econetwireless.utils.enums.ResponseCode;
import com.econetwireless.utils.messages.AirtimeBalanceResponse;
import com.econetwireless.utils.pojo.INBalanceResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * @Author: Samson Nyabanga
 */
@RunWith(MockitoJUnitRunner.class)
public class EnquiriesServiceImplTest {

    @Mock
    private SubscriberRequestDao subscriberRequestDao;

    @Mock
    private ChargingPlatform chargingPlatform;

    private String msisdn;
    private String partnerCode;
    private EnquiriesService enquiriesService;
    private INBalanceResponse inBalanceResponse;
    private SubscriberRequest subscriberRequest;

    @Before
    public void setUp() {
        enquiriesService = new EnquiriesServiceImpl(chargingPlatform, subscriberRequestDao);

        msisdn = "0775245867";
        partnerCode = "TEST-PARTNER-CODE";
        String requestType = "Airtime Balance Enquiry";

        subscriberRequest = new SubscriberRequest();
        subscriberRequest.setMsisdn(msisdn);
        subscriberRequest.setPartnerCode(partnerCode);
        subscriberRequest.setRequestType(requestType);
        subscriberRequest.setBalanceBefore(1);
        subscriberRequest.setAmount(2);
        subscriberRequest.setBalanceAfter(3);
        subscriberRequest.setReference("REF-12345");
        subscriberRequest.setId(1L);

        inBalanceResponse = new INBalanceResponse();
        inBalanceResponse.setAmount(2);
        inBalanceResponse.setMsisdn(msisdn);
        inBalanceResponse.setNarrative("Top-up was successful");
        inBalanceResponse.setResponseCode("200");
    }

    @Test
    public void whenValidParametersArePassedBalanceEnquiryShouldPass() {
        final String message = "Top-up was successful";
        when(chargingPlatform.enquireBalance(partnerCode, msisdn)).thenReturn(inBalanceResponse);
        when(subscriberRequestDao.save(any(SubscriberRequest.class))).thenReturn(subscriberRequest);
        final AirtimeBalanceResponse airtimeBalanceResponse = enquiriesService.enquire(partnerCode, msisdn);
        assertNotNull(airtimeBalanceResponse);
        assertEquals(message, airtimeBalanceResponse.getNarrative());
        assertEquals(ResponseCode.SUCCESS.getCode(), airtimeBalanceResponse.getResponseCode());
        verify(subscriberRequestDao, times(2)).save(any(SubscriberRequest.class));
        verify(chargingPlatform, times(1)).enquireBalance(partnerCode, msisdn);
    }
}
