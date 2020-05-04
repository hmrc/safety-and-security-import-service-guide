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

There are 3 endpoints for notifications.

* [get list a list of advanced notifications](https://developer.service.hmrc.gov.uk/api-documentation/docs/api/service/import-control-entry-declaration-intervention/1.0#_get-a-list-of-notifications_get_accordion)
* [get advanced notification using submission ID](https://developer.service.hmrc.gov.uk/api-documentation/docs/api/service/import-control-entry-declaration-intervention/1.0#_retrieve-a-notification_get_accordion)
* [acknowledge a notification](https://developer.service.hmrc.gov.uk/api-documentation/docs/api/service/import-control-entry-declaration-intervention/1.0#_acknowledge-a-notification_delete_accordion)



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

The element ```<DocNumHEA5>``` holds the Movement Reference Number given to you as an outcome. The Movement Reference Number must match the ```{mrn}``` in the PUT path parameter:

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

This endpoint allows you to retrieve a list of unacknowledged outcomes. 

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

Outcomes that are successful or accepted will have a Movement Reference Number inside the response:

```
<MRN>10GB08I01234567891</MRN>
```

Those that do not have a Movement Reference Number will be rejections, for example:

```
 <response>
   <correlationId>0JRF7UncAqr004</correlationId>
   <link>/customs/imports/entry-summary-declarations/outcomes/0987654321</link>
 </response>
```

### Getting an outcome

This endpoint is used to retrieve the details for an individual Entry Summary Declaration (ENS).  You will need to ensure that you retrieve a list of unacknowledged advanced notifications as well as outcomes.

The path parameter contains the correlation ID that was returned in response to the  declaration itself:

```
GET /customs/imports/outcomes/{correlationId}
```

The returned XML is in the same format as the current system:

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

A successful accepted outcome contains a Movement Reference Number within the ```<DocHumHEA5>``` element.
 
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

## Advanced notifications (Do not load) - IE351

### Get a list of advanced notifications - IE351

This endpoint allows a developer to retrieve a list of advanced notifications that are yet to be acknowledged.  You will need to ensure that you retrieve a list of unacknowledged advanced notifications as well as outcomes.

The path parameter is:

```
GET /customs/imports/notifications
```

A successful response will return a correlation ID contained within the ```<response>``` element. In this example two correlation IDs have been returned.

```
<advancedNotifications>
  <response>
    <correlationId>1234567890</correlationId>
    <link>/customs/imports/notifications/1234567890</link>
  </response>
  <response>
    <correlationId>0987654321</correlationId>
    <link>/customs/imports/notifications/0987654321</link>
  </response>
</advancedNotifications>
```

A HTTP 204 response indicates there are no messages available to download.

### Retrieve a notification - IE351

This endpoint retrieves a notification for given correlation ID.

```
GET /customs/imports/notifications/{correlationId}
```

The XML returned is the same as the current system. An example of a successful response:

```
<cc3:CC351A xmlns:cc3="http://ics.dgtaxud.ec/CC351A">
  <MesSenMES3>GBCD1234/1234567890</MesSenMES3>
  <MesRecMES6>GBC123</MesRecMES6>
  <DatOfPreMES9>030211</DatOfPreMES9>
  <TimOfPreMES10>0123</TimOfPreMES10>
  <MesIdeMES19>ABC123</MesIdeMES19>
  <MesTypMES20>CC313A</MesTypMES20>
  <CorIdeMES25>ABC123</CorIdeMES25>
  <HEAHEA>
    <RefNumHEA4>ABCD1234</RefNumHEA4>
    <DocNumHEA5>12AB3C4D5E6F7G8H90</DocNumHEA5>
    <TraModAtBorHEA76>4</TraModAtBorHEA76>
    <NatHEA001>GB</NatHEA001>
    <IdeOfMeaOfTraCroHEA85>ABC123</IdeOfMeaOfTraCroHEA85>
    <TotNumOfIteHEA305>42</TotNumOfIteHEA305>
    <ComRefNumHEA>ABC123</ComRefNumHEA>
    <ConRefNumHEA>ABC123</ConRefNumHEA>
    <NotDatTimHEA104>200302111234</NotDatTimHEA104>
    <DecRegDatTimHEA115>200302111234</DecRegDatTimHEA115>
    <DecSubDatTimHEA118>200302111234</DecSubDatTimHEA118>
  </HEAHEA>
  <GOOITEGDS>
    <IteNumGDS7>1</IteNumGDS7>
    <ComRefNumGIM1>ABC123</ComRefNumGIM1>
    <PRODOCDC2>
      <DocTypDC21>AB12</DocTypDC21>
      <DocRefDC23>ABC123</DocRefDC23>
      <DocRefDCLNG>en</DocRefDCLNG>
    </PRODOCDC2>
    <CONNR2>
      <ConNumNR21>ABC123</ConNumNR21>
    </CONNR2>
    <IDEMEATRAGI970>
      <NatIDEMEATRAGI973>EN</NatIDEMEATRAGI973>
      <IdeMeaTraGIMEATRA971>ABC123</IdeMeaTraGIMEATRA971>
      <IdeMeaTraGIMEATRA972LNG>en</IdeMeaTraGIMEATRA972LNG>
    </IDEMEATRAGI970>
  </GOOITEGDS>
  <CUSOFFLON>
    <RefNumCOL1>ES000055</RefNumCOL1>
  </CUSOFFLON>
  <TRAREP>
    <NamTRE1>ABC123</NamTRE1>
    <StrAndNumTRE1>ABC123</StrAndNumTRE1>
    <PosCodTRE1>ABC123</PosCodTRE1>
    <CitTRE1>ABC123</CitTRE1>
    <CouCodTRE1>EN</CouCodTRE1>
    <TRAREPLNG>en</TRAREPLNG>
    <TINTRE1>YZ^</TINTRE1>
  </TRAREP>
  <PERLODSUMDEC>
    <NamPLD1>ABC123</NamPLD1>
    <StrAndNumPLD1>ABC123</StrAndNumPLD1>
    <PosCodPLD1>ABC123</PosCodPLD1>
    <CitPLD1>ABC123</CitPLD1>
    <CouCodPLD1>EN</CouCodPLD1>
    <PERLODSUMDECLNG>en</PERLODSUMDECLNG>
    <TINPLD1>YZ^</TINPLD1>
  </PERLODSUMDEC>
  <CUSOFFFENT730>
    <RefNumCUSOFFFENT731>AB3C4D5E</RefNumCUSOFFFENT731>
    <ExpDatOfArrFIRENT733>200302111234</ExpDatOfArrFIRENT733>
  </CUSOFFFENT730>
  <TRACARENT601>
    <NamTRACARENT604>ABC123</NamTRACARENT604>
    <StrNumTRACARENT607>ABC123</StrNumTRACARENT607>
    <PstCodTRACARENT606>ABC123</PstCodTRACARENT606>
    <CtyTRACARENT603>ABC123</CtyTRACARENT603>
    <CouCodTRACARENT605>EN</CouCodTRACARENT605>
    <TRACARENT601LNG>en</TRACARENT601LNG>
    <TINTRACARENT602>YZ^</TINTRACARENT602>
  </TRACARENT601>
  <CUSINT632>
    <IteNumConCUSINT668>1</IteNumConCUSINT668>
    <CusIntCodCUSINT665>ABCD</CusIntCodCUSINT665>
    <CusIntTexCUSINT666>ABC12</CusIntTexCUSINT666>
    <CusIntTexCUSINT667LNG>en</CusIntTexCUSINT667LNG>
  </CUSINT632>
</cc3:CC351A>
```

A HTTP 404 response indicates there are no details currently available for the given correlation ID.

### Acknowledge a notification - IE351 

This endpoint allows a developer to acknowledge the receipt of an advanced notification response for a given correlation ID.

```
DELETE /customs/imports/notifications/{correlationId}
```

A HTTP 200 response indicates the acknowledgement request has been accepted.  A HTTP 404 response indicates there are no details available for the given correlation ID.


## Risking responses

If we use the ```simulateRiskingReponse``` header for a Entry Summary Declaration (IE315) and it has ```accept``` value in it, a IE328 is returned when the fetch API is called.

Similarly, for an entry summary amendment (IE313) a IE304 success response is returned when the fetch API is called.

If we change the value to ```reject``` we will receive an IE316 and IE305 rejection response when the fetch API is called.


