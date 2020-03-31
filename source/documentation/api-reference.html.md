---
title: Safety and Security Import End-to-End Service Guide
weight: 3
description: Software developers, designers, product owners or business analysts.
---

# API Reference

## Endpoint overview 

There are five endpoints, two are for submitting:

* [submit a new ENS declaration](https://developer.service.hmrc.gov.uk/api-documentation/docs/api/service/import-control-entry-declaration-store/1.0)
* [submit an ENS amendment to an existing declaration](https://developer.service.hmrc.gov.uk/api-documentation/docs/api/service/import-control-entry-declaration-store/1.0)
* [get a list of outcomes](https://developer.service.hmrc.gov.uk/api-documentation/docs/api/service/import-control-entry-declaration-outcome/1.0)
* [get a single outcome](https://developer.service.hmrc.gov.uk/api-documentation/docs/api/service/import-control-entry-declaration-outcome/1.0)
* [acknowledge an outcome](https://developer.service.hmrc.gov.uk/api-documentation/docs/api/service/import-control-entry-declaration-outcome/1.0)

The following endpoints are currently in development:

* get list for advanced notification
* get advanced notification using submission ID
* acknowledge a response



## Submitting a new ENS declaration

You can use this endpoint to create and submit a new Entry Summary Declaration (ENS).
There is no SOAP header and the body envelope XML remains the same. It starts with a ```<ie:CC315A>``` element at the top level.

```
<ie:CC315A xmlns:ie="http://ics.dgtaxud.ec/CC315A">
   <MesSenMES3>GB1234231/1234567890</MesSenMES3>
   <MesRecMES6>ABCD1234</MesRecMES6>
   <DatOfPreMES9>091231</DatOfPreMES9>
   <TimOfPreMES10>2359</TimOfPreMES10>
   <MesIdeMES19>ABCD1234</MesIdeMES19>
   <MesTypMES20>CC315A</MesTypMES20>
   <HEAHEA>
    …
   </HEAHEA>
  …
</ie:CC315A>
```

The message sender element ```<MesSenMES3>``` contains your EORI. It is the same EORI that the authentication bearer token is based on.
The response returned confirms that we have received your submission and will contain a correlation ID:

```
<ns:SuccessResponse TestInLive="false" xmlns:xd="http://www.w3.org/2000/09/xmldsig#" xmlns:ns="http://www.hmrc.gov.uk/successresponse/2">
   <ns:ResponseData>
       <CorrelationId>0JRF7UncK0t004</CorrelationId>
   </ns:ResponseData>
</ns:SuccessResponse>
You can use the correlation ID to obtain your outcomes and acknowledge any outcomes that you get.
```

## Submitting a ENS amendment

You can use this endpoint to submit an amendment to an existing ENS.

```
<ie:CC313A xmlns:ie="http://ics.dgtaxud.ec/CC313A">
 <MesSenMES3>GBCD1234/1234567890</MesSenMES3>
 <MesRecMES6>GBC123</MesRecMES6>
 <DatOfPreMES9>030211</DatOfPreMES9>
 <TimOfPreMES10>0123</TimOfPreMES10>
 <PriMES15>A</PriMES15>
 <MesIdeMES19>ABC123</MesIdeMES19>
 <MesTypMES20>CC313A</MesTypMES20>
 <CorIdeMES25>ABC123</CorIdeMES25>
 <HEAHEA>
   <DocNumHEA5>12AB3C4D5E6F7G8H90</DocNumHEA5>
    …
 </HEAHEA>
 …
</ie:CC313A>
```

The element ```<DocNumHEA5>``` holds the movement reference number given to you as an outcome. The movement reference number must match the ```{mrn}`` in the PUT path parameter.

```
PUT /customs/imports/declarations/{mrn}
```

A successful call will return a HTTP 200 response. 

```
<ns:SuccessResponse TestInLive="false" xmlns:xd="http://www.w3.org/2000/09/xmldsig#" xmlns:ns="http://www.hmrc.gov.uk/successresponse/2">
   <ns:ResponseData>
       <CorrelationId>0JRF7UncAqr004</CorrelationId>
   </ns:ResponseData>
</ns:SuccessResponse>
```

If the call is not successful a HTTP 400 response is returned with an XML error message.

## Getting outcomes

### Getting an unacknowledged outcome

This endpoint allows you to retrieve a batch of unacknowledged outcomes.

In this example there are two outcomes each contained within the ```<response>``` element:

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

Both have a ```<correlationId>``` element containing the correlation ID received with the original declaration:

```
<correlationId>0JRF7UncK0t004</correlationId>
…
<correlationId>0JRF7UncAqr004</correlationId>
```

The link within the ```<link>``` element can be used to fetch the detail or acknowledge the outcome:

```
<link>/customs/imports/entry-summary-declarations/outcomes/0JRF7UncK0t004</link>
```

Outcomes that are successful or accepted will have a movement reference number inside the response:

```
<MRN>10GB08I01234567891</MRN>
```

Those that do not have a movement reference number will be rejections, for example:

```
 <response>
   <correlationId>0JRF7UncAqr004</correlationId>
   <link>/customs/imports/entry-summary-declarations/outcomes/0987654321</link>
 </response>
```

### Getting an outcome

This endpoint is used to retrieve the details for an individual Entry Summary Declaration (ENS).

The path parameter contains the correlation ID that was returned in response to the  declaration itself:

```
GET /customs/imports/outcomes/{correlationId}
```

The returned XML is in the same format and the current system:

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

A successful accepted outcome contains a movement reference number within the ```<DocHumHEA5>``` element.
 
```
<DocNumHEA5>10GB08I01234567891</DocNumHEA5>
```

### Acknowledging an outcome

This endpoint allows you to acknowledge an existing outcome.

The path parameter is the same as that used for a GET however the HTTP method is now a DELETE:

```
DELETE /customs/imports/outcomes/{correlationId}
```

After calling this endpoint the outcome will no longer be retrievable and will no longer appear in the list of outcomes.


## Risking responses

If we use the ```simulateRiskingReponse``` header for a Entry Summary Declaration (IE315) and it has ```accept``` value in it, a IE328 is returned when the fetch API is called.

Similarly, for an entry summary amendment (IE313) a IE304 success response is returned when the fetch API is called.

If we change the value to ```reject``` we will receive an IE316 and IE305 rejection response when the fetch API is called.


