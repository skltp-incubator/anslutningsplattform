
<%@ page import="se.skltp.av.ProducentAnslutning" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'producentAnslutning.label', default: 'ProducentAnslutning')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-producentAnslutning" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-producentAnslutning" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
						<g:sortableColumn property="tjansteKontrakt" title="${message(code: 'producentAnslutning.tjansteKontrakt.label', default: 'Tjanste Kontrakt')}" />
					
						<g:sortableColumn property="url" title="${message(code: 'producentAnslutning.url.label', default: 'Url')}" />
								
						<g:sortableColumn property="rivTaProfile" title="${message(code: 'producentAnslutning.rivTaProfile.label', default: 'Riv Ta Profile')}" />
					
						<g:sortableColumn property="validFromTime" title="${message(code: 'producentAnslutning.validFromTime.label', default: 'Valid From Time')}" />
					
						<g:sortableColumn property="validToTime" title="${message(code: 'producentAnslutning.validToTime.label', default: 'Valid To Time')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${producentAnslutningInstanceList}" status="i" var="producentAnslutningInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						
						<td><g:link action="show" id="${producentAnslutningInstance.id}">${fieldValue(bean: producentAnslutningInstance, field: "tjansteKontrakt")}</g:link></td>
						
						<td>${fieldValue(bean: producentAnslutningInstance, field: "url")}</td>	
					
						<td>${fieldValue(bean: producentAnslutningInstance, field: "rivTaProfile")}</td>
					
						<td><g:formatDate date="${producentAnslutningInstance.validFromTime}" /></td>
					
						<td><g:formatDate date="${producentAnslutningInstance.validToTime}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${producentAnslutningInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
