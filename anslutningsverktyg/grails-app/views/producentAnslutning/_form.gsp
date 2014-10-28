<%@ page import="se.skltp.av.ProducentAnslutning" %>



<div class="fieldcontain ${hasErrors(bean: producentAnslutningInstance, field: 'url', 'error')} required">
	<label for="url">
		<g:message code="producentAnslutning.url.label" default="Url" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="url" required="" value="${producentAnslutningInstance?.url}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: producentAnslutningInstance, field: 'tjansteKontrakt', 'error')} required">
	<label for="tjansteKontrakt">
		<g:message code="producentAnslutning.tjansteKontrakt.label" default="Tjanste Kontrakt" />
		<span class="required-indicator">*</span>
	</label>

	<g:select name="tjansteKontrakt" required="" 
		from="${['urn:riv:insuranceprocess:healthreporting:ReceiveMedicalCertificateAnswerResponder:1', 
				'urn:riv:insuranceprocess:healthreporting:RegisterMedicalCertificateResponder:3',
				'urn:riv:insuranceprocess:healthreporting:RevokeMedicalCertificateResponder:1']}"  
		valueMessagePrefix="tjansteKontrakt" value="${producentAnslutningInstance?.tjansteKontrakt}"/>

</div>




<div class="fieldcontain ${hasErrors(bean: producentAnslutningInstance, field: 'logiskAdresser', 'error')} ">
	<label for="logiskAdresser">
		<g:message code="producentAnslutning.logiskAdresser.label" default="Logisk Adresser" />
		
	</label>
	<g:select name="logiskAdresser" from="${se.skltp.av.LogiskAdress.list()}" multiple="multiple" optionKey="id" size="5" value="${producentAnslutningInstance?.logiskAdresser*.id}" class="many-to-many"/>
	
</div>

<div class="fieldcontain ${hasErrors(bean: producentAnslutningInstance, field: 'rivTaProfile', 'error')} required">
	<label for="rivTaProfile">
		<g:message code="producentAnslutning.rivTaProfile.label" default="Riv Ta Profile" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="rivTaProfile" required="" from="${['RIVTABP20', 'RIVTABP21']}"  valueMessagePrefix="rivTaProfile" value="${producentAnslutningInstance?.rivTaProfile}"/>

</div>


<div class="fieldcontain ${hasErrors(bean: producentAnslutningInstance, field: 'producentBestallning', 'error')} required">
	<label for="producentBestallning">
		<g:message code="producentAnslutning.producentBestallning.label" default="Producent Bestallning" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="producentBestallning" name="producentBestallning.id" from="${se.skltp.av.ProducentBestallning.list()}" 
		optionKey="id" required="" value="${producentAnslutningInstance?.producentBestallning?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: producentAnslutningInstance, field: 'validFromTime', 'error')} required">
	<label for="validFromTime">
		<g:message code="producentAnslutning.validFromTime.label" default="Valid From Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="validFromTime" precision="day"  value="${producentAnslutningInstance?.validFromTime}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: producentAnslutningInstance, field: 'validToTime', 'error')} required">
	<label for="validToTime">
		<g:message code="producentAnslutning.validToTime.label" default="Valid To Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="validToTime" precision="day"  value="${producentAnslutningInstance?.validToTime}"  />

</div>

