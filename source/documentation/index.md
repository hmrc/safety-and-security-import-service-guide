---
title: Safety and Security Import End-to-End Service Guide
weight: 1
description: Software developers, designers, product owners or business analysts.
---
<div class="govuk-warning-text">
  <span class="govuk-warning-text__icon" aria-hidden="true">!</span>
  <strong class="govuk-warning-text__text">
    <span class="govuk-warning-text__assistive">Warning</span>
    From 1 January 2021 Traders who submit Entry Summary declarations (also known as an ENS) using third party software will need to enrol with the Safety and Security Great Britain (S&S GB) service.
  </strong>
</div>

This guide explains how you can integrate your software with the S&S GB APIs:

* Safety and Security Import Declarations API
* Safety and Security Import Notifications API
* Safety and Security Import Control Entry Summary Declaration Outcomes API

### Safety and Security Import Declarations API
<a href="https://developer.service.hmrc.gov.uk/api-documentation/docs/api/service/import-control-entry-declaration-store/1.0">This API</a> allows you to:

* create a new ENS submission
* amend an existing ENS submission

### Safety and Security Import Notifications API
<a href="https://developer.service.hmrc.gov.uk/api-documentation/docs/api/service/import-control-entry-declaration-intervention/1.0">This API</a> allows you to:

* get a list of notifications
* retrieve a notification
* acknowledge a notification

### Safety and Security Import Outcomes API
<a href="https://developer.service.hmrc.gov.uk/api-documentation/docs/api/service/import-control-entry-declaration-outcome/1.0">This API</a> will allow you to:

* get a list of outcomes
* retrieve an outcome
* acknowledge an outcome

The response will also include a movement reference number.

These APIs will receive new ENS submissions or amendments and will return a response or and error message for the end-user.

## Overview
 
Traders importing goods into the UK must provide the UK customs authorities with advance information by submitting an ENS before bringing goods into the UK.

S&S GB handles digital communications between customs administrators and carriers (or their appointed representatives), and is designed to incorporate the:

* lodging, handling and processing of the ENS in advance of the arrival of goods
* issuing of a Movement Reference Number (MRN)

The MRN is a Customs computer system-generated number that is automatically allocated after a successful validation. The MRN must be issued to the carrier and, where different, the declarant.

The ENS must be lodged before goods arrive into the UK, and before loading in the case of maritime deep-sea containerised shipping.

The carrier or their authorised representative submitting the ENS must have a valid GB Economic Operator Registration and Identification (EORI) number.

For more information, see EORI Guidance pages on GOV.UK.

S&S GB cannot be used for Entry Summary declarations where the office of first entry is a Northern Ireland port.
 
## End-to-end user journeys

These journeys show examples of use. Journeys for businesses and agents are broken down into:

* authentication
* submitting a new ENS (IE315), amending a ENS (IE313) and receiving, downloading and acknowledging messages (accepted or rejected)
* receiving, downloading and acknowledging Advanced Intervention (IE351) notification messages

## Terms of use

Your application must comply with our [terms of use](https://developer.service.hmrc.gov.uk/api-documentation/docs/terms-of-use). You must accept the terms of use before we issue your application's production credentials.

## Related API documentation
<!--- Section owner: MTD Programme --->

* [Create Test User API](https://developer.service.hmrc.gov.uk/api-documentation/docs/api/service/api-platform-test-user/1.0)

* Additional information can be found at
 [Import Control System: support for software developers](https://www.gov.uk/government/collections/import-control-system-support-for-software-developers))

<!-- add the change log here -->
## Changelog

### Version 1.2

* advanced notifications endpoints description and field descriptions added to service guide
* updated XML reference

### Version 1.3

* ENS Validation added 

### Version 1.4
* Change of name from Safety and Security to S&S GB
* Requirement for traders to enrol with S&S GB

### Version 1.5
* Update IE313 and IE315 submission examples to reflect optional MesRecMES6 element
* Fix MesRecMES6 format in example IE351 notification response
