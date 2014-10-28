
<%@ page import="se.skltp.av.ProducentBestallning" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'producentBestallning.label', default: 'ProducentBestallning')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-producentBestallning" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-producentBestallning" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list producentBestallning">
			
				<g:if test="${producentBestallningInstance?.miljo}">
				<li class="fieldcontain">
					<span id="miljo-label" class="property-label"><g:message code="producentBestallning.miljo.label" default="Miljo" /></span>
					
						<span class="property-value" aria-labelledby="miljo-label"><g:fieldValue bean="${producentBestallningInstance}" field="miljo"/></span>
					
				</li>
				</g:if>
				
				<g:if test="${producentBestallningInstance?.tjansteproducent}">
				<li class="fieldcontain">
					<span id="tjansteproducent-label" class="property-label"><g:message code="producentBestallning.tjansteproducent.label" default="Tjansteproducent" /></span>
					<span class="property-value" aria-labelledby="tjansteproducent-label">${producentBestallningInstance?.tjansteproducent?.hsaId?.encodeAsHTML()}</span>
				</li>
				</g:if>
			
				<g:if test="${producentBestallningInstance?.defaultLogiskAdress}">
				<li class="fieldcontain">
					<span id="defaultLogiskAdress-label" class="property-label"><g:message code="producentBestallning.defaultLogiskAdress.label" default="Default Logisk Adress" /></span>
					
						<g:each in="${producentBestallningInstance.defaultLogiskAdress}" var="d">
						<span class="property-value" aria-labelledby="defaultLogiskAdress-label"><g:link controller="logiskAdress" action="show" id="${d.id}">${d?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${producentBestallningInstance?.defaultRivTaProfile}">
				<li class="fieldcontain">
					<span id="defaultRivTaProfile-label" class="property-label"><g:message code="producentBestallning.defaultRivTaProfile.label" default="Default Riv Ta Profile" /></span>
					
						<span class="property-value" aria-labelledby="defaultRivTaProfile-label"><g:fieldValue bean="${producentBestallningInstance}" field="defaultRivTaProfile"/></span>
					
				</li>
				</g:if>
			
				<li class="fieldcontain">
					<span id="producentAnslutning-label" class="property-label"><g:message code="producentBestallning.producentAnslutning.label" default="Producent Anslutning" /></span>
					
						<g:each in="${producentBestallningInstance.producentAnslutning}" var="p">
						<span class="property-value" aria-labelledby="producentAnslutning-label"><g:link controller="producentAnslutning" action="show" id="${p.id}">${p?.tjansteKontrakt?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				
				<li class="fieldcontain">
					<span id="bestallningsHistorik-label" class="property-label">
						<g:message code="producentBestallning.bestallningsHistorik.label" default="Bestallnings Historik" />
					</span>
					<span class="property-value" aria-labelledby="bestallningsHistorik-label">
						<g:link controller="bestallningsHistorik" action="index" params="['producentBestallning.id': producentBestallningInstance?.id]">${message(code: 'default.list.label', args: [message(code: 'bestallningsHistorik.label', default: 'BestallningsHistorik')])}</g:link>
					</span>
				</li>
			
			</ol>
			<g:form url="[resource:producentBestallningInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${producentBestallningInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
