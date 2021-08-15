### Issues fixed

1. Added static to the LOGGER constant in the MobileNumberUtils class in the econet-utils module. Is is referenced from a static context
2. Changed @PreInsert to @PrePersist in the SubscriberRequest class in the electronic-payments-domain module. The @PreInsert callback annotation does not exist in JPA.
3. Removed this(super) in the PartnerCodeValidatorImpl class it does not make sense.
4. Replaced subscriberRequestDao.persist with subscriberRequestDao.save a valid JPA method
5. Replaced subscriberRequestDao.update with subscriberRequestDao.save a valid JPA method
6. Replaced subscriberRequestDao.persist with subscriberRequestDao.save in the credit method in the CreditsServiceImpl a valid JPA method
7. Replaced subscriberRequestDao.update with subscriberRequestDao.save in the credit method in the CreditsServiceImpl class a valid JPA method
8. Added a constructor in the EpayResource class to ensure dependency injection on the reportingProcessor and epayRequestProcessor instance variables.
9. Added "this" to code = code; in the constructor of ResponseCode class in the econet-utils module. To eliminate confusion between class and method  variables.
10. Renamed Request to request to match the name of the entity in the SubscriberRequest class in the electronic-payments-domain module.
11. Added the missing @WebParam (name = "partnerCode") on the enquireBalance method in the IntelligentNetworkService class in the intelligent-network-api module
12. Added the missing @PathVariable("partnerCode") in enquireAirtime balance method in the EpayResource class in the electronic-payments-api module.
13. Changed the @BindingType(value = SOAPBinding.SOAP12HTTP_BINDING) to @BindingType(value = SOAPBinding.SOAP11HTTP_BINDING) in the IntelligentNetworkServiceImpl class . According to the IntelligentNetworkService.wsdl the soap namespace defined is "http://schemas.xmlsoap.org/wsdl/soap/" which entails that the it is using SOAP v1.1