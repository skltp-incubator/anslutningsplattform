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
	
	<div class="fieldcontain">
		<label for="tjansteDoman">
			<g:message code="producentAnslutning.tjansteDoman.label" default="Tjanstedoman" />
			<span class="required-indicator">*</span>
		</label>
	
		<g:select name="tjansteDoman" required="" 
			from="${['clinicalprocess:healthcond:basic',
					'clinicalprocess:healthcond:description',
					'crm:scheduling',
					'insuranceprocess:healthreporting', 
					'itintegration:engagementindex',
					'itintegration:registry']}"  
			valueMessagePrefix="tjansteDoman" value="${producentAnslutningInstance?.tjansteDoman}"/>
	</div>
	
	<div class="fieldcontain ${hasErrors(bean: producentBestallningInstance, field: 'defaultRivTaProfile', 'error')} required">
		<label for="defaultRivTaProfile">
			<g:message code="producentBestallning.defaultRivTaProfile.label" default="Default Riv Ta Profile" />
			<span class="required-indicator">*</span>
		</label>
		<g:select name="defaultRivTaProfile" required="" from="${['RIVTABP21', 'RIVTABP20']}"  valueMessagePrefix="defaultRivTaProfile" value="${producentBestallningInstance?.defaultRivTaProfile}"/>
	</div>
	
	<div class="fieldcontain">
		<fieldset class="buttons">
			<g:submitToRemote url="[controller: 'registreraTjansteKontrakt' ,action: 'searchTjansteKontrakt']" value="Sök tjänstekontrakt" update="searchresults" />
		</fieldset>
	</div>


<g:render template="tjansteKontraktLista"/>


