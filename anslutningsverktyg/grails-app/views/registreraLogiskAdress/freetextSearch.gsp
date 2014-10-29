<%@ page import="se.skltp.av.ProducentBestallning" %>
<g:javascript library="jquery"/>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'producentBestallning.label', default: 'ProducentBestallning')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<fieldset class="form">
		    <g:form action="freetextSearch" method="GET">
		        <div class="fieldcontain">
		            <label for="query">Sök logiska adresser:</label>
		            <g:textField name="query" value="${params.query}"/>
		            <g:submitToRemote url="[controller: 'registreraLogiskAdress' ,action: 'freetextSearch']" value="Sök" update="searchresults" />	
		        </div>
		    </g:form>
		    
		  
		</fieldset>	
		
		<g:render template="searchResults"/>
	</body>
</html>