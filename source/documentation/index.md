---
title: Safety and Security Import End-to-End Service Guide
weight: 1
description: Software developers, designers, product owners or business analysts.
---

# Safety and security import declarations service guide

Version 1.0 issued 31 March 2020
***

## Overview
 
This guide explains how you can integrate your software with our APIs to submit an import notification known as an Entry Summary Declaration (ENS).

It shows how the APIs fit into various end-to-end user journeys. It is also intended to help software developers, designers, product owners or business analysts understand how your software needs to interact with HMRC systems.

The Safety and Security system handles digital communications between:

* customs administrators
* carriers or their appointed representatives 
You must provide the UK customs authorities with advance information by submitting an ENS before you bring goods into the UK.

The Safety and Security system is designed to incorporate the:

* lodging, handling and processing of the ENS in advance of the arrival of goods
* issuing of a Movement Reference Number (MRN)

The MRN is a Customs computer system-generated number that is automatically allocated after a successful validation. The MRN must be issued to the carrier and, where different, the declarant.

The ENS must be lodged before goods arrive into the UK and before loading in the case of maritime deep-sea containerised shipping.
The carrier or their authorised representative submitting the ENS must have a valid GB Economic Operator Registration and Identification (EORI) number.  For more information, see [EORI Guidance](https://www.gov.uk/eori)</br>
 
## End-to-end user journeys

These journeys show examples of use. Journeys for businesses and agents are broken down into:

* authentication
* submitting a new ENS (IE315), amending a ENS (IE313) and receiving, downloading and acknowledging messages (accepted or rejected)
* receiving, downloading Advanced (IE351) acknowledgement messages


## Terms of use

Your application must comply with our [terms of use](https://developer.service.hmrc.gov.uk/api-documentation/docs/terms-of-use). You must accept the terms of use before we issue your application's production credentials.

## Related API documentation
<!--- Section owner: MTD Programme --->

* [Create Test User API](https://developer.service.hmrc.gov.uk/api-documentation/docs/api/service/api-platform-test-user/1.0)


<!-- add the change log here -->
## Changelog

### Version 1.0

Updates to the API will be listed here in future.
 
