
<%@ page import="se.skltp.av.ProducentAnslutning" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'producentAnslutning.label', default: 'ProducentAnslutning')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-producentAnslutning" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-producentAnslutning" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list producentAnslutning">
			
				<g:if test="${producentAnslutningInstance?.url}">
				<li class="fieldcontain">
					<span id="url-label" class="property-label"><g:message code="producentAnslutning.url.label" default="Url" /></span>
					
						<span class="property-value" aria-labelledby="url-label"><g:fieldValue bean="${producentAnslutningInstance}" field="url"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${producentAnslutningInstance?.tjansteKontrakt}">
				<li class="fieldcontain">
					<span id="tjansteKontrakt-label" class="property-label"><g:message code="producentAnslutning.tjansteKontrakt.label" default="Tjanste Kontrakt" /></span>
					
						<span class="property-value" aria-labelledby="tjansteKontrakt-label"><g:fieldValue bean="${producentAnslutningInstance}" field="tjansteKontrakt"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${producentAnslutningInstance?.logiskAdresser}">
				<li class="fieldcontain">
					<span id="logiskAdresser-label" class="property-label"><g:message code="producentAnslutning.logiskAdresser.label" default="Logisk Adresser" /></span>
					
						<g:each in="${producentAnslutningInstance.logiskAdresser}" var="l">
						<span class="property-value" aria-labelledby="logiskAdresser-label"><g:link controller="logiskAdress" action="show" id="${l.id}">${l?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${producentAnslutningInstance?.producentBestallning}">
				<li class="fieldcontain">
					<span id="producentBestallning-label" class="property-label"><g:message code="producentAnslutning.producentBestallning.label" default="Producent Bestallning" /></span>
					
						<span class="property-value" aria-labelledby="producentBestallning-label"><g:link controller="producentBestallning" action="show" id="${producentAnslutningInstance?.producentBestallning?.id}">${producentAnslutningInstance?.producentBestallning?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${producentAnslutningInstance?.rivTaProfile}">
				<li class="fieldcontain">
					<span id="rivTaProfile-label" class="property-label"><g:message code="producentAnslutning.rivTaProfile.label" default="Riv Ta Profile" /></span>
					
						<span class="property-value" aria-labelledby="rivTaProfile-label"><g:fieldValue bean="${producentAnslutningInstance}" field="rivTaProfile"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${producentAnslutningInstance?.validFromTime}">
				<li class="fieldcontain">
					<span id="validFromTime-label" class="property-label"><g:message code="producentAnslutning.validFromTime.label" default="Valid From Time" /></span>
					
						<span class="property-value" aria-labelledby="validFromTime-label"><g:formatDate date="${producentAnslutningInstance?.validFromTime}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${producentAnslutningInstance?.validToTime}">
				<li class="fieldcontain">
					<span id="validToTime-label" class="property-label"><g:message code="producentAnslutning.validToTime.label" default="Valid To Time" /></span>
					
						<span class="property-value" aria-labelledby="validToTime-label"><g:formatDate date="${producentAnslutningInstance?.validToTime}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:producentAnslutningInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${producentAnslutningInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
