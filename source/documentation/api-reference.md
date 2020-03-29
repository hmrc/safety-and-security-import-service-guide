---
title: Safety and Security Import End-to-End Service Guide
weight: 3
description: Software developers, designers, product owners or business analysts.
---

# API Reference

There are five endpoints, two are for submitting:

* submit a new ENS declaration
* submit an ENS amendment to an existing declaration
* get a list of outcomes
* fetch a single outcome
* acknowledge an outcome

* HOLDER - get list for advanced notification
* HOLDER - get advanced notification using submission ID
* HOLDER - acknowledge the response


## Submitting a new ENS declaration

You can use this endpoint to create and submit a new entry declaration.

There is no SOAP header and the body envelope remains the same.  It starts with a 315 element at the top level.

The message sender element  ```<MesSenMES3>``` contains your EORI

The response returned to confirm that we have received your submission will contain a correlation Id which you can use to obtain your outcomes and acknowledge the outcomes that you get.

## Submitting a ENS amendment

You can use this endpoint to submit an amendment to an existing entry summary declaration.

It contains an element <docNumHEA5> which holds the movement reference number given to you as an outcome.  The movement reference number must match the PUT path parameter.

A successful call will return a HTTP 200 response.  If the call is not successful a HTTP 400 response with the error.xml is returned. 


## Getting unacknowledged outcomes

This endpoint allows you to retrieve a batch of unacknowledged outcomes.  

In this example there are two outcomes each contained within the ```<response>``` body: 

```
<entryDeclarationResponses>
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

The link within the ```<link>``` element can be used to fetch the detail or acknowledge the outcome:

```
<link>/customs/imports/entry-summary-declarations/outcomes/0JRF7UncK0t004</link>
```
Outcomes that are successful or accepted will have a movement reference number inside the response:

```
<MRN>10GB08I01234567891</MRN>
```
Those that do not have a movement reference number will be rejections:

```
 <response>
   <correlationId>0JRF7UncAqr004</correlationId>
   <link>/customs/imports/entry-summary-declarations/outcomes/0987654321</link>
 </response>
```

## Fetching an outcome

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

## Acknowledging outcome

This endpoint allows you to acknowledge an existing outcome.  

The path parameter is the same that used for a fetch however the HTTP method is a DELETE rather than a GET

After using the endpoint the outcome will no longer be fetchable and no longer appear in the list of outcomes.
