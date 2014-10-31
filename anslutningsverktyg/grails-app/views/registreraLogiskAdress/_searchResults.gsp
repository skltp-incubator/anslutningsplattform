<%@ page import="se.skltp.av.ProducentBestallning" %>

<div id="searchresults">
    <g:if test="${searchresults}">
      
      	<div class="list">
              <table>
                  <thead>
                      <tr>
                      	<th>Vald</th>
                          <g:sortableColumn property="title" title="Logisk adress" />
                          <g:sortableColumn property="title" title="${message(code: 'default.hsaid.label', default: 'HSA ID')}" />
                      </tr>
                  </thead>
                  <tbody>
	                  <g:each in="${searchresults}" status="i" var="hsa">
	                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
	                      	  <td><g:checkBox name="${fieldValue(bean: hsa, field: "hsaId")}" value="${false}" /></td>
	                      	
	                      	  <!-- For know show the first element in the HSA DN string, just for demo. 
	                      	  ou=Nässjö VC DLK,ou=Nässjö Primärvårdsområde,ou=Höglandets sjukvårdsområde,o=Landstinget i Jönköping,l=VpW,c=SE
	                      	  gets 
	                      	  ou=Nässjö VC DLK-->
	                      	  
	                          <td>${fieldValue(bean: hsa, field: "dn").decodeHTML().split(',')[0]}</td>
	                          <td>${fieldValue(bean: hsa, field: "hsaId")}</td>
	                      </tr>
	                  </g:each>
                  </tbody>
              </table>
          </div>
            
    </g:if>
</div>