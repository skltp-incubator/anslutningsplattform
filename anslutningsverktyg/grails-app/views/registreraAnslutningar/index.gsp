
<%@ page import="se.skltp.av.ProducentBestallning" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'producentBestallning.label', default: 'ProducentBestallning')}" />
		<title>Registrera anslutningar</title>
		
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
				<li>Miljö:</li>
				<li><g:fieldValue bean="${producentBestallningInstance}" field="miljo"/></li>
			</ul>
			<ul>
				<li>Tjänsteproducent:</li>
				<li><g:fieldValue bean="${producentBestallningInstance.tjansteproducent}" field="hsaId"/></li>
			</ul>
			<ul>
				<li>Default RIV TA profil:</li>
				<li><g:fieldValue bean="${producentBestallningInstance}" field="defaultRivTaProfile"/></li>
			</ul>
			<ul>
				<li>Tjänstekontrakt:</li>
				<g:each in="${producentBestallningInstance.producentAnslutning}" var="p">
					<!-- Fix for demo to only show name of interaction urn:riv:itintegration:engagementindex:FindContent:1:rivtabp21 gets FindContent 
                    Note that this only works for the exact positions and will not work when we get the actual servicecontract name from TAK-->
					<li>${p?.tjansteKontrakt?.encodeAsHTML().split(':')[-3]}</li>
				</g:each>
			</ul>
			<ul>
				<li>Default logiska adresser:</li>
				<g:each in="${producentBestallningInstance.defaultLogiskAdress}" var="p">
					<li>${p?.hsaId?.encodeAsHTML()}</li>
				</g:each>
			</ul>
		</div>
		
		<div id="page-body" role="main">
		
			<g:form action="update" controller="registreraAnslutningar" method="POST">
		
				<div id="list-producentBestallning" class="content scaffold-list" role="main">
					<h1>Registrera anslutningar</h1>
					<g:if test="${flash.message}">
						<div class="message" role="status">${flash.message}</div>
					</g:if>
					<table>
						<thead>
							<tr>	
								<th>Tjänstekontrakt</th>
								<th>RIV TA profil</th>
								<th>Logiska adresser</th>
								<th>Url</th>
							</tr>
						</thead>
						<tbody>
						<g:each in="${producentBestallningInstance?.producentAnslutning}" status="i" var="anslutning">
							<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
								<td>${fieldValue(bean: anslutning, field: "tjansteKontrakt").split(':')[-3]}</td>
								<td><g:select name="rivTaProfile" required="" from="${['RIVTABP20', 'RIVTABP21']}"  valueMessagePrefix="rivTaProfile" value="${producentBestallningInstance?.defaultRivTaProfile}"/></td>
								<td><g:actionSubmit name="show" class="edit" value="Ändra logiska adresser" action="show" controller="registreraLogiskAdress" disabled="true"/></td>
								<td><input name="${fieldValue(bean: anslutning, field: "tjansteKontrakt")}" type="text" value="${fieldValue(bean: anslutning, field: "url")}"/></td>
							</tr>
						</g:each>
						</tbody>
					</table>
					
					<g:hiddenField name="producentBestallningId" value="${fieldValue(bean: producentBestallningInstance, field: "id")}"/>
					
					<fieldset class="buttons">
						<g:actionSubmit name="update" class="edit" value="Spara" action="update" controller="registreraAnslutningar" disabled="false"/>
						<g:actionSubmit name="create" class="save" value="Spara och gå vidare till bekräfta beställning" action="save" controller="registreraAnslutningar"/>
					</fieldset>
				</div>
				
			</g:form>
		</div>
	</body>
</html>
