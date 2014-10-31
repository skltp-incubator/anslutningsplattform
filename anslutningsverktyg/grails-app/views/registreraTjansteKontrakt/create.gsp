<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'producentBestallning.label', default: 'Välj Tjänstekontrakt')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
		
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
					<li>${p?.tjansteKontrakt?.encodeAsHTML()}</li>
				</g:each>
			</ul>
		</div>
		
		<div id="page-body" role="main">
	
			<a href="#create-producentBestallning" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
			<div class="nav" role="navigation">
				<ul>
					<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				</ul>
			</div>
			<div id="create-producentBestallning" class="content scaffold-create" role="main">
				<h1>Välj tjänstekontrakt</h1>
				<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
				</g:if>
				<g:hasErrors bean="${producentBestallningInstance}">
				<ul class="errors" role="alert">
					<g:eachError bean="${producentBestallningInstance}" var="error">
					<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</g:hasErrors>
				<g:form action="update">
					<fieldset class="form">
						<g:render template="form"/>
					</fieldset>
					<fieldset class="buttons">
						<g:actionSubmit name="update" class="edit" value="Spara" action="update" controller="registreraTjansteKontrakt" disabled="true"/>
						<g:actionSubmit name="create" class="save" value="Spara och gå vidare till nästa steg" action="save" controller="registreraTjansteKontrakt"/>
					</fieldset>
				</g:form>
			</div>
			
		</div>
	</body>
</html>
