package se.skltp.av.service

import grails.transaction.Transactional
import se.skltp.av.TjansteKomponent
import se.skltp.av.services.dto.TjansteKomponentDTO

@Transactional
class TjansteKomponentService {

    @Transactional(readOnly = true)
    List<TjansteKomponentDTO> query(String queryString) {
        def domainServiceComponents = TjansteKomponent.findAllByHsaIdIlikeOrNamnIlike("%$queryString%", "%$queryString%")
        domainServiceComponents.collect {
            new TjansteKomponentDTO(
                    id: it.id,
                    hsaId: it.hsaId,
                    namn: it.namn,
            )
        }
    }

    TjansteKomponentDTO findById(long id) {
        def domainServiceComponent = TjansteKomponent.findById(id)
        new TjansteKomponentDTO(
                id: domainServiceComponent.id,
                hsaId: domainServiceComponent.hsaId,
                namn: domainServiceComponent.namn,
                tekniskKontaktEpost: domainServiceComponent.tekniskKontaktEpost,
                tekniskKontaktNamn: domainServiceComponent.tekniskKontaktNamn,
                tekniskKontaktTelefon: domainServiceComponent.tekniskKontaktTelefon,
                funktionsBrevladaEpost: domainServiceComponent.funktionsBrevladaEpost,
                funktionsBrevladaTelefon: domainServiceComponent.funktionsBrevladaTelefon,
                ipadress: domainServiceComponent.ipadress
        )
    }
}
