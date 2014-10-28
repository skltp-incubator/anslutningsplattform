<%@ page import="se.skltp.av.ProducentBestallning" %>

<div class="fieldcontain ${hasErrors(bean: producentBestallningInstance, field: 'tjansteproducent', 'error')} required">
	<label for="tjansteproducent">
		<g:message code="producentBestallning.tjansteproducent.label" default="Tjansteproducent" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="tjansteproducent" name="tjansteproducent.id" from="${se.skltp.av.Tjansteproducent.list()}" optionKey="id" required="" value="${producentBestallningInstance?.tjansteproducent?.id}" class="many-to-one" optionValue="hsaId"/>

</div>

<div class="fieldcontain ${hasErrors(bean: producentBestallningInstance, field: 'miljo', 'error')} required">
	<label for="miljo">
		<g:message code="producentBestallning.miljo.label" default="Miljo" />
		<span class="required-indicator">*</span>
	</label>

	<g:select name="miljo" required="" from="${['SIT', 'QA', 'Produktion']}"  valueMessagePrefix="miljo" value="${producentBestallningInstance?.miljo}"/>
	
</div>

<div class="fieldcontain ${hasErrors(bean: producentBestallningInstance, field: 'defaultLogiskAdress', 'error')} ">
	<label for="defaultLogiskAdress">
		<g:message code="producentBestallning.defaultLogiskAdress.label" default="Default Logisk Adress" />
		
	</label>
	<g:select name="defaultLogiskAdress" from="${se.skltp.av.LogiskAdress.list()}" multiple="multiple" optionKey="id" size="5" value="${producentBestallningInstance?.defaultLogiskAdress*.id}" class="many-to-many"/>

</div>

<div class="fieldcontain ${hasErrors(bean: producentBestallningInstance, field: 'defaultRivTaProfile', 'error')} required">
	<label for="defaultRivTaProfile">
		<g:message code="producentBestallning.defaultRivTaProfile.label" default="Default Riv Ta Profile" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="defaultRivTaProfile" required="" from="${['RIVTABP20', 'RIVTABP21']}"  valueMessagePrefix="defaultRivTaProfile" value="${producentBestallningInstance?.defaultRivTaProfile}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: producentBestallningInstance, field: 'producentAnslutning', 'error')} ">
	<label for="producentAnslutning">
		<g:message code="producentBestallning.producentAnslutning.label" default="Producent Anslutning" />
	</label>
	
	<ul class="one-to-many">
		<g:each in="${producentBestallningInstance?.producentAnslutning?}" var="p">
	    	<li><g:link controller="producentAnslutning" action="show" id="${p.id}">${p?.tjansteKontrakt?.encodeAsHTML()}</g:link></li>
		</g:each>
		<li class="add">
			&nbsp;
		</li>
		<li class="add">
			<g:link controller="producentAnslutning" action="create" params="['producentBestallning.id': producentBestallningInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'producentAnslutning.label', default: 'ProducentAnslutning')])}</g:link>
		</li>
	</ul>
</div>

<div class="fieldcontain ${hasErrors(bean: producentBestallningInstance, field: 'bestallningsHistorik', 'error')} ">
	<label for="bestallningsHistorik">
		<g:message code="producentBestallning.bestallningsHistorik.label" default="Bestallnings Historik" />
		
	</label>
	
	<g:link controller="bestallningsHistorik" action="index" params="['producentBestallning.id': producentBestallningInstance?.id]">${message(code: 'default.list.label', args: [message(code: 'bestallningsHistorik.label', default: 'BestallningsHistorik')])}</g:link>

</div>


