package se.skltp.av.service

import grails.transaction.Transactional
import se.skltp.av.ProducentBestallning
import se.skltp.av.services.dto.ProducentBestallningDTO
import se.skltp.av.util.BestallningsStatus

@Transactional(readOnly = true)
class ProducentBestallningService {

    def listProducentBestallning() {
        def producentBestallningar = ProducentBestallning.list()
        producentBestallningar.collect {
            new ProducentBestallningDTO(
                    id: it.id,
                    status: it.status,
                    miljo: it.miljo
            )
        }
    }

    @Transactional
    def updateProducentBestallning(producentBestallningDTO) {
        def producentBestallning = ProducentBestallning.get(id)
    }
}
