<div id="searchresults">
    <g:if test="${searchresults}">
       <br/>
       
        	<div class="list">
                <table>
                    <thead>
                        <tr>
                        	<th>Vald</th>
                            <g:sortableColumn property="title" title="${message(code: 'producentAnslutning.tjansteKontrakt.label', default: 'Tjänstekontrakt')}" />
                            <g:sortableColumn property="title" title="${message(code: 'producentAnslutning.tjansteKontrakt.version.label', default: 'Version')}" />
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${searchresults}" status="i" var="tjanstekontrakt">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        	<td><g:checkBox name="${fieldValue(bean: tjanstekontrakt, field: "namnrymd")}" value="${false}" disabled="${fieldValue(bean: tjanstekontrakt, field: "existerarRedanForProducent")}"/></td>
                            
                            <!-- Fix for demo to only show name of interaction urn:riv:itintegration:engagementindex:FindContent:1:rivtabp21 gets FindContent 
                            	Note that this only works for the exact positions and will not work when we get the actual servicecontract name from TAK-->
                            <td>${fieldValue(bean: tjanstekontrakt, field: "namnrymd").split(':')[-3]}</td>
                            <td>${fieldValue(bean: tjanstekontrakt, field: "version")}</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            
    </g:if>
</div>