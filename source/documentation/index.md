---
title: Safety and Security Import End-to-End Service Guide
weight: 1
description: Software developers, designers, product owners or business analysts. Integrate your software with VAT API for Making Tax Digital.
---

# Safety and security import service guide

Version 1.0 issued 27 March 2020
***

This guide explains how you can integrate your software with our APIs to submit an import notification known as an Entry Summary Declaration (ENS). It shows how the APIs fit into various end-to-end user journeys. It is intended to help software developers, designers, product owners or business analysts understand how your software needs to interact with HMRC systems.

## Overview
<!-- Section owner: MTD Programme -->
Safety and Security Import Notifications end-to-end service guide
 
This guide explains how you can integrate your software with our APIs to submit an import notification known as an Entry Summary Declaration (ENS). 

It shows how the APIs fit into various end-to-end user journeys. It is intended to help software developers, designers, product owners or business analysts understand how your software needs to interact with HMRC systems.
 
The Import Control System (ICS) handles digital communications between: 

* customs administrators
* carriers or their appointed representatives
You must provide the UK customs authorities with advance information by using a ENS before you bring goods into the UK.

The ICS is designed to incorporate the:

* lodging, handling and processing of ENS in advance of the arrival of goods
* safety and security risk analysis and the exchange of results between member states
* issuing of a Movement Reference Number (MRN)

The MRN is a Customs computer system-generated number that is automatically allocated after a successful validation. The MRN must be issued to the carrier and, where different, the declarant.

The ENS must be lodged before goods arrive into the UK - before loading in the case of maritime deep sea containerised shipping.

The carrier or their authorised representative submitting the ENS must have a valid GB Economic Operator Registration and Identification (EORI) number. 

For more information, see EORI Guidance  ** insert link to policy information **
For more information on ICS, see ** insert link to policy information **

 
## End-to-end user journeys

These journeys show examples of use. Journeys for businesses and agents are broken down into:

* authentication
* submitting new ENS IE315 or amendment IE313 and receiving
downloading and acknowledging messages (accepted or rejected)
* receiving, downloading and Advanced (IE351) acknowledging messages 


## Related API documentation
<!--- Section owner: MTD Programme --->

* [Create Test User API](https://developer.service.hmrc.gov.uk/api-documentation/docs/api/service/api-platform-test-user/1.0)
* [Test Fraud Prevention Headers API](https://developer.service.hmrc.gov.uk/api-documentation/docs/api/service/txm-fph-validator-api/1.0)

<!-- add the change log here -->
## Changelog

### Version 1.0

Updates to the API or endpoints will be listed here in future.
 
