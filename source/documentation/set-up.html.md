---
title: Set up | Safety and Security Import Declarations end-to-end Service Guide
weight: 2
---

# Set up

## Authentication for Software vendors

Software Vendors will be required to register standard applications with HMRC.  End users will be required to have an ICS enrolment assigned to their Government Gateway Credential.

<img src="figures/ics-authentication.svg" alt="Authentication for Software vendors process diagram" style="width:720px;" />
</br>
<a href="figures/ics-authentication.svg" target="blank">Open the Authentication for Software vendors process diagram in a new tab</a>.

The end user authenticates directly with us using their Government Gateway account, and grants authority to your software for specific scopes.
We then issue an OAuth 2.0 ```access_token``` that is specific to the end user. Your application passes the access token in subsequent API requests to user-restricted endpoints.
A user ```access_token``` expires after 4 hours and will need to be refreshed.

Full details and examples can be found on the HMRC Developer Hub:

[https://developer.service.hmrc.gov.uk/api-documentation/docs/authorisation/user-restricted-endpoints](https://developer.service.hmrc.gov.uk/api-documentation/docs/authorisation/user-restricted-endpoints)

## Community System Provider's

Community System Provider's (CSP’s) may register privileged applications with HMRC, which is in line with the practice previously used for CDS (Customs Declaration Service).

Privileged Applications don’t require each end user to have Government Gateway credentials.

Following Registration, the credentials you are supplied with can be used to generate a Time-based One-Time Password (TOTP) code, which can then be exchanged for an access_token.

Submit and acknowledge flow
Link to documentation about do not load/rim rules etc
Content
Advanced notification
Content
Endpoints 


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


## API endpoints

There are five endpoints, two are for submitting:

* submit a new ENS declaration
* submit an ENS amendment to an existing declaration
* get a list of outcomes

* fetch a single outcome
* acknowledge an outcome



## Submitting a new ENS declaration

You can use this endpoint to create and submit a new entry declaration.

There is no SOAP header and the body envelope remains the same.  It starts with a 315 element at the top level.

The message sender element contains your EORI

```
<MesSenMES3>
```
The response returned to confirm that we have received your submission will contain a correlation Id which you can use to obtain your outcomes and acknowledge the outcomes that you get.

## Submit a ENS Amendment

You can use this endpoint to submit an amendment to an existing entry summary declaration.

It contains an element <docNumHEA5> which holds the movement reference number given to you as an outcome.  The movement reference number must match the PUT path parameter.

A successful call will return a HTTP 200 response.  If the call is not successful a HTTP 400 response with the error.xml is returned. 


## Get unacknowledged outcomes

This endpoint allows you to retrieve a batch of unacknowledged outcomes.  

In this example there are two outcomes:  

```
entryDeclarationResponses>
 <response>
   <correlationId>0JRF7UncK0t004</correlationId>
   <link>/customs/imports/entry-summary-declarations/outcomes/0JRF7UncK0t004</link>
   <MRN>10GB08I01234567891</MRN>
 </response>
 <response>
   <correlationId>0JRF7UncAqr004</correlationId>
   <link>/customs/imports/entry-summary-declarations/outcomes/0987654321</link>
 </response>
</entryDeclarationResponses>
```

Both have a ```<correlationId>``` with responses received with the original declaration.

The link within the ```<link>``` element can be used to fetch the detail or acknowledge the outcome.

```
<link>/customs/imports/entry-summary-declarations/outcomes/0JRF7UncK0t004</link>
```
Outcomes that are successful or accepted will have a movement reference number inside the response.

```
<MRN>10GB08I01234567891</MRN>
```
Those that don’t have a movement reference number will be rejections:

```
 <response>
   <correlationId>0JRF7UncAqr004</correlationId>
   <link>/customs/imports/entry-summary-declarations/outcomes/0987654321</link>
 </response>
```

## Fetch outcome

This endpoint is used to retrieve the details for an individual entry summary declaration.

The path parameter contains the correlation ID that was returned to the declaration itself.

The returned XML is in the same format:

```
<outcomeResponse>
   <response>
       <CC328A>
           <MesSenMES3>GB000012340003/1234567890</MesSenMES3>
           <MesRecMES6>GB000012340003/1234567890</MesRecMES6>
           <MesIdeMES19>MSUI11235227</MesIdeMES19>
           <MesTypMES20>CC328A</MesTypMES20>
           <CorIdeMES25>0JRF7UncK0t004</CorIdeMES25>
           <HEAHEA>
               ...
               <DocNumHEA5>10GB08I01234567891</DocNumHEA5>
               ...
           </HEAHEA>
           ...
       </CC328A>
   </response>
   <acknowledgement method='DELETE' href='/customs/imports/entry-summary-declarations/outcomes/0JRF7UncK0t004'/>
</outcomeResponse>
```

A successful accepted outcome contains a movement reference number with the <DocNumHEA5> element.
 
```
<DocNumHEA5>10GB08I01234567891</DocNumHEA5>
```

## Acknowledge outcome

This endpoint allows you to acknowledge an existing outcome.  

The path parameter is the same that used for a fetch however the HTTP method is a DELETE rather than a GET

After using the endpoint the outcome will no longer be fetchable and no longer appear in the list of outcomes.
