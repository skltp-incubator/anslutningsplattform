<div id="searchresults">
    <g:if test="${searchresults}">
       <br/>
       <h3>Sök resultat</h3>
       
        <g:form controller="registreraLogiskAdress" action="save" method="POST">
       
        	<div class="list">
                <table>
                    <thead>
                        <tr>
                        	<th>Vald</th>
                            <g:sortableColumn property="title" title="${message(code: 'default.hsaid.label', default: 'HSA ID')}" />
                            <g:sortableColumn property="title" title="${message(code: 'default.dn.label', default: 'DN')}" />
                           
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${searchresults}" status="i" var="hsa">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        	<td><g:checkBox name="${fieldValue(bean: hsa, field: "hsaId")}" value="${false}" /></td>
                            <td>${fieldValue(bean: hsa, field: "hsaId")}</td>
                            <td>${fieldValue(bean: hsa, field: "dn")}</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
        
            <g:submitButton value="${message(code: 'default.paginate.next', default: 'Next')}" name="next"/>
            
         </g:form>   
            
    </g:if>
</div>