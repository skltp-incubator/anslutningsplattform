
<%@ page import="se.skltp.av.ProducentBestallning" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'producentBestallning.label', default: 'ProducentBestallning')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-producentBestallning" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="list-producentBestallning" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<th><g:message code="producentBestallning.tjansteproducent.label" default="Tjansteproducent" /></th>
					
						<g:sortableColumn property="miljo" title="${message(code: 'producentBestallning.miljo.label', default: 'Miljo')}" />
					
						<g:sortableColumn property="defaultRivTaProfile" title="${message(code: 'producentBestallning.defaultRivTaProfile.label', default: 'Default Riv Ta Profile')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${producentBestallningInstanceList}" status="i" var="producentBestallningInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${producentBestallningInstance.id}">${fieldValue(bean: producentBestallningInstance, field: "tjansteproducent.hsaId")}</g:link></td>
						
						<td>${fieldValue(bean: producentBestallningInstance, field: "miljo")}</td>
					
						<td>${fieldValue(bean: producentBestallningInstance, field: "defaultRivTaProfile")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${producentBestallningInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
