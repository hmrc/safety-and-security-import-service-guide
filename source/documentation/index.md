---
title: Safety and Security Import End-to-End Service Guide
weight: 1
description: Software developers, designers, product owners or business analysts.
---

This guide explains how you can use our Safety and Security (S&S GB) APIs with your software to submit an Entry Summary declaration (also known as an ENS) to import goods into Great Britain.

Our APIs will allow your software to submit ENS Declarations and receive notifications and outcomes information.

<div class="govuk-warning-text">
  <span class="govuk-warning-text__icon" aria-hidden="true">!</span>
  <strong class="govuk-warning-text__text">
    <span class="govuk-warning-text__assistive">Warning</span>
    Traders who submit ENS declarations using third party software will need to enrol with S&S GB.<br><br>
    From 31 October 2024, a reduced dataset was introduced for ENS declarations, as set out in the <a href="https://www.gov.uk/government/publications/the-border-target-operating-model-august-2023">Border Target Operating Model (BTOM)</a> in August 2023. 
  </strong>
</div>

The reduced dataset consists of 20 mandatory fields, up to 8 conditional fields (which need to be completed in certain circumstances), and 9 optional data fields. Further information on the reduced dataset can be found [here](https://assets.publishing.service.gov.uk/media/64f6e2629ee0f2000db7be8e/Final_Border_Target_Operating_Model.pdf) within Annex A.

* Businesses that are already set up to submit ENS declarations may choose to complete only the mandatory and any relevant conditional fields, but they do not need to make any changes to their existing systems or procedures if they do not wish to.
* Those who are preparing to complete ENS declarations for the first time will be able to choose to complete only the mandatory and any relevant conditional fields.

This guide explains how you can integrate your software with the S&S GB APIs:

* Safety and Security Import Declarations API
* Safety and Security Import Notifications API
* Safety and Security Import Control Entry Summary Declaration Outcomes API

## Safety and Security Import Declarations API
<a href="https://developer.service.hmrc.gov.uk/api-documentation/docs/api/service/import-control-entry-declaration-store/1.0">This API</a> allows you to:

* create a new ENS submission (IE 315)
* amend an existing ENS submission (IE313)

## Safety and Security Import Notifications API
<a href="https://developer.service.hmrc.gov.uk/api-documentation/docs/api/service/import-control-entry-declaration-intervention/1.0">This API</a> allows you to:

* get a list of notifications
* retrieve a notification
* acknowledge a notification

A notification is an IE351 message – Advanced interventions.

## Safety and Security Import Outcomes API
<a href="https://developer.service.hmrc.gov.uk/api-documentation/docs/api/service/import-control-entry-declaration-outcome/1.0">This API</a> will allow you to:

* get a list of outcomes
* retrieve an outcome
* acknowledge an outcome

Outcomes for IE315 are either IE328 or IE316.

Outcomes for IE313 are either IE304 or IE305.

* IE328 - Entry Summary Declaration Acceptance
* IE316 - Entry Summary Declaration Rejection
* IE304 - Entry Summary Declaration Amendment Acceptance
* IE305 - Entry Summary Declaration Amendment Rejection

The response will also include a movement reference number.

These APIs will receive new ENS submissions or amendments and will return a response or an error message for the end-user.

## Overview
Traders moving goods into Great Britain need to make an entry summary (ENS) declaration. To submit the declaration traders must be registered with the S&S GB service.

S&S GB handles digital communications between customs administrators and carriers (or their appointed representatives), and is designed to incorporate the:

* lodging, handling and processing of the ENS in advance of the arrival of goods
* issuing of a Safety and Security Movement Reference Number (MRN)

The MRN is a Safety and Security GB generated reference number that is automatically allocated after a successful validation. The MRN must be issued to the carrier and, where different, the declarant.

The ENS must be lodged before goods arrive into the UK, and before loading in the case of maritime deep-sea containerised shipping.

[Timing Requirements](https://www.gov.uk/guidance/making-an-entry-summary-declaration#when-to-submit)

The carrier or their authorised representative submitting the ENS must have a valid GB Economic Operator Registration and Identification (EORI) number.

For more information, see EORI Guidance pages on GOV.UK.

S&S GB cannot be used for entry summary declarations where the office of first entry is a Northern Ireland port.

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
* Rewording of Overview section
* Revision of Field Details

### Version 1.6
* Changes ahead of 31 October 2024, where a reduced dataset will be introduced for ENS declarations, as set out in the Border Target Operating Model (BTOM) in August 2023.

### Version 1.7

* Corrected IDEMEATRAGI970 Field ID in XML reference
* Added SEAID529 field, which was previously missing from XML reference

### Version 1.8

* Added type, length and requirement information to XML field descriptions.
