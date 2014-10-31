
<%@ page import="se.skltp.av.ProducentBestallning" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'producentBestallning.label', default: 'ProducentBestallning')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
		
		<style type="text/css" media="screen">
			#status {
				background-color: #eee;
				border: .2em solid #fff;
				margin: 2em 2em 1em;
				padding: 1em;
				width: 12em;
				float: right;
				-moz-box-shadow: 0px 0px 1.25em #ccc;
				-webkit-box-shadow: 0px 0px 1.25em #ccc;
				box-shadow: 0px 0px 1.25em #ccc;
				-moz-border-radius: 0.6em;
				-webkit-border-radius: 0.6em;
				border-radius: 0.6em;
			}

			.ie6 #status {
				display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
			}

			#status ul {
				font-size: 0.9em;
				list-style-type: none;
				margin-bottom: 0.6em;
				padding: 0;
			}

			#status li {
				line-height: 1.3;
			}

			#status h1 {
				text-transform: uppercase;
				font-size: 1.1em;
				margin: 0 0 0.3em;
			}

			#page-body {
				margin: 2em 20em 1.25em 2em;
			}

			h2 {
				margin-top: 1em;
				margin-bottom: 0.3em;
				font-size: 1em;
			}

			p {
				line-height: 1.5;
				margin: 0.25em 0;
			}

			#controller-list ul {
				list-style-position: inside;
			}

			#controller-list li {
				line-height: 1.3;
				list-style-position: inside;
				margin: 0.25em 0;
			}

			@media screen and (max-width: 480px) {
				#status {
					display: none;
				}

				#page-body {
					margin: 0 1em 1em;
				}

				#page-body h1 {
					margin-top: 0;
				}
			}
		</style>
		
	</head>
	<body>
	
		<div id="status" role="complementary">
			<ul>
				<li>Beställningshistorik:</li>
			</ul>
			<g:each in="${producentBestallningInstance.bestallningsHistorik}" status="i" var="historik">
				<ul>
					<li>Status: ${fieldValue(bean: historik, field: "status")}</li>
					<li>Ändrad av: ${fieldValue(bean: historik, field: "senastUppdateradAv")}</li>
					<li>${fieldValue(bean: historik, field: "datum")}</li>
				</ul>
			</g:each>
	
		</div>
		
		<div id="page-body" role="main">
	
			<a href="#show-producentBestallning" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
			<div class="nav" role="navigation">
				<ul>
					<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
					<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				</ul>
			</div>
			<div id="show-producentBestallning" class="content scaffold-show" role="main">
				
				<h1>Sammanställning</h1>
				<g:if test="${flash.message}">
					<div class="message" role="status">${flash.message}</div>
				</g:if>
				
				
				<div id="create-producentBestallning" class="content scaffold-create" role="main">
					
					<fieldset class="fieldcontain">
						<span id="miljo-label" class="property-label"><g:message code="producentBestallning.miljo.label" default="Miljo" /></span>
						<span class="property-value" aria-labelledby="miljo-label"><g:fieldValue bean="${producentBestallningInstance}" field="miljo"/></span>
						
						<span id="tjansteproducent-label" class="property-label"><g:message code="producentBestallning.tjansteproducent.label" default="Tjansteproducent" /></span>
						<span class="property-value" aria-labelledby="tjansteproducent-label">${producentBestallningInstance?.tjansteproducent?.hsaId?.encodeAsHTML()}</span>
						
						<span id="defaultRivTaProfile-label" class="property-label"><g:message code="producentBestallning.defaultRivTaProfile.label" default="Default Riv Ta Profile" /></span>
						<span class="property-value" aria-labelledby="defaultRivTaProfile-label"><g:fieldValue bean="${producentBestallningInstance}" field="defaultRivTaProfile"/></span>
					
						<span id="defaultLogiskAdress-label" class="property-label"><g:message code="producentBestallning.defaultLogiskAdress.label" default="Default Logisk Adress" /></span>
						<g:each in="${producentBestallningInstance.defaultLogiskAdress}" var="d">
							<span class="property-value" aria-labelledby="defaultLogiskAdress-label">${d?.encodeAsHTML()}</span>
						</g:each>
					</fieldset>
			
				</div>
	
				<div id="list-producentBestallning" class="content scaffold-list" role="main">
					<h1>Anslutningar</h1>
					<table>
						<thead>
							<tr>
								<th>Tjänstekontrakt</th>
								<th>RIV TA profil</th>
								<th>Logiska adresser</th>
								<th>URL</th>
							</tr>
						</thead>
						<tbody>
						<g:each in="${producentBestallningInstance.producentAnslutning}" status="i" var="anslutning">
							<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
							
								<td>${fieldValue(bean: anslutning, field: "tjansteKontrakt").split(':')[-3]}</td>
								<td>${fieldValue(bean: anslutning, field: "rivTaProfile")}</td>
								<td>${fieldValue(bean: anslutning, field: "logiskAdresser")}</td>
								<td>${fieldValue(bean: anslutning, field: "url")}</td>
							</tr>
						</g:each>
						</tbody>
					</table>
				</div>
				
				<g:form url="[resource:producentBestallningInstance, action:'confirm']" method="POST">
					<fieldset class="buttons">
						<g:actionSubmit class="delete" action="none" value="Skriv ut" disabled="true"/>
						<g:actionSubmit class="edit" action="none" value="Ändra i befintlig beställning" disabled="true"/>
						<g:actionSubmit class="save" action="confirm" value="Bekräfta" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					</fieldset>
				</g:form>
			</div>
		</div>
	</body>
</html>
