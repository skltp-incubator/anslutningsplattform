package se.skltp.av.service

import grails.transaction.Transactional
import se.skltp.av.TjansteKomponent
import se.skltp.av.services.dto.TjansteKomponentDTO

@Transactional
class TjansteKomponentService {

    @Transactional(readOnly = true)
    List<TjansteKomponentDTO> query(String queryString) {
        def domainServiceComponents = TjansteKomponent.findAllByHsaIdIlike("%$queryString%")
        return domainServiceComponents.collect {
            new TjansteKomponentDTO(
                    id: it.id,
                    hsaId: it.hsaId,
                    namn: it.hsaId, //FIXME: another name?
            )
        }
    }

    TjansteKomponentDTO findById(long id) {
        def domainServiceComponent = TjansteKomponent.findById(id)
        return new TjansteKomponentDTO(
                id: domainServiceComponent.id,
                hsaId: domainServiceComponent.hsaId,
                namn: domainServiceComponent.hsaId, //FIXME: another name?
                tekniskKontaktEpost: domainServiceComponent.tekniskKontaktEpost,
                tekniskKontaktNamn: domainServiceComponent.tekniskKontaktNamn,
                tekniskKontaktTelefon: domainServiceComponent.tekniskKontaktTelefon,
                funktionsBrevladaEpost: domainServiceComponent.funktionsBrevladaEpost,
                funktionsBrevladaTelefon: domainServiceComponent.funktionsBrevladaTelefon,
                ipadress: domainServiceComponent.ipadress
        )
    }
}
