package com.econetwireless.epay.business.services.impl;

import com.econetwireless.epay.business.services.api.ReportingService;
import com.econetwireless.epay.dao.subscriberrequest.api.SubscriberRequestDao;
import com.econetwireless.epay.domain.SubscriberRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * @Author: Samson Nyabanga
 */
@RunWith(MockitoJUnitRunner.class)
public class ReportingServiceImplTest {

    @Mock
    SubscriberRequestDao subscriberRequestDao;

    private ReportingService reportingService;
    private List<SubscriberRequest> subscriberRequests
            ;
    private String partnerCode;

    @Before
    public void setUp() {
        partnerCode = "PARTNER CODE";
        reportingService = new ReportingServiceImpl(subscriberRequestDao);
        String msisdn = "0775245867";
        String requestType = "Reporting";

        subscriberRequests = new ArrayList<>();

        SubscriberRequest subscriberRequestOne = new SubscriberRequest();
        subscriberRequestOne.setMsisdn(msisdn);
        subscriberRequestOne.setPartnerCode(partnerCode);
        subscriberRequestOne.setRequestType(requestType);
        subscriberRequestOne.setBalanceBefore(1);
        subscriberRequestOne.setAmount(2);
        subscriberRequestOne.setBalanceAfter(3);
        subscriberRequestOne.setReference("REF-12345");
        subscriberRequestOne.setId(1L);
        subscriberRequests.add(subscriberRequestOne);
    }

    @Test
    public void whenValidPartnerCodeIsPassedShouldReturnSubscriberRequests() {
        when(subscriberRequestDao.findByPartnerCode(partnerCode)).thenReturn(subscriberRequests);
        List<SubscriberRequest> reportResponse = reportingService.findSubscriberRequestsByPartnerCode(partnerCode);
        assertNotNull(reportResponse);
    }
}
