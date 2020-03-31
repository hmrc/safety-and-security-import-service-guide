---
title: Set up | Safety and Security Import Declarations end-to-end Service Guide
weight: 2
---

# Set up

This video gives some background to the ICS uplift and API endpoints.
<br>
<br>
<div class="video">
<iframe width="560" height="315" src="https://www.youtube.com/embed/2BMGrX8Axo8" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
</div>

[ICS uplift video](https://www.youtube.com/watch?v=2BMGrX8Axo8&feature=youtu.be)


Software vendors will be required to register standard applications with HMRC.  End users will be required to have an ICS enrolment assigned to their Government Gateway Credential.

<img src="figures/ics-authentication.svg" alt="Authentication for Software vendors process diagram" style="width:720px;" />
</br>
<a href="figures/ics-authentication.svg" target="blank">Open the Authentication for Software vendors process diagram in a new tab</a>.

The end user authenticates directly with us using their Government Gateway account, and grants authority to your software for specific scopes.
We then issue an OAuth 2.0 ```access_token``` that is specific to the end user. Your application passes the access token in subsequent API requests to user-restricted endpoints.
A user ```access_token``` expires after 4 hours and will need to be refreshed.

Full details and examples can be found on the HMRC Developer Hub:

[https://developer.service.hmrc.gov.uk/api-documentation/docs/authorisation/user-restricted-endpoints](https://developer.service.hmrc.gov.uk/api-documentation/docs/authorisation/user-restricted-endpoints)

## Community System Provider's

Community System Provider's (CSP) may register privileged applications with HMRC, which is in line with the practice previously used for CDS (Customs Declaration Service).

Privileged Applications don’t require each end user to have Government Gateway credentials.

Following Registration, the credentials you are supplied with can be used to generate a Time-based One-Time Password (TOTP) code, which can then be exchanged for an access_token.

## Submit and acknowledge a E313 and E315

<img src="figures/submit-ack-IE315-IE313.svg" alt="Submit and acknowledge a E313 and E315 process diagram" style="width:720px;" />
</br>
<a href="figures/submit-ack-IE315-IE313.svg" target="blank">Open the Submit and acknowledge a E313 and E315 process diagram in a new tab</a>.

## Advanced notifications

<img src="figures/advanced-notification.svg" alt="Authentication for Advanced notifications process diagram" style="width:720px;" />
</br>
<a href="figures/advanced-notification.svg" target="blank">Open the Advanced notifications process diagram in a new tab</a>.

## Advanced notifications Do Not Load (DNL) messages

For deep-sea containerised shipments only, a DNL message (IE351) may be issued. This will be sent to the person submitting the declaration.

## Test environment

Our test environment will allow you to test your software.  You will need to authenticate through our API platform.

The test environment will allow you to submit:

* ENS Declarations
* ENS Amendments

It will also allow you to:

* collect responses
* acknowledge responses
* list unacknowledged responses

The test environment will also validate submissions to make sure the schema is valid.

The stub will also simulate any risking responses, returning the MRNs and Error code scenarios.

Our test environment allows you test in three ways:

You can use a header to test accept responses and with a different value test rejected responses.  If no header is used, the submission is sent through and no response is sent back.

All responses returned are in XML.

Our test environment will not allow performance or load testing.  You can use a latency header for delayed responses in milliseconds.
