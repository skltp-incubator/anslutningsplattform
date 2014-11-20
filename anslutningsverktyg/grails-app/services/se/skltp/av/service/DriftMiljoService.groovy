package se.skltp.av.service

import grails.transaction.Transactional
import se.skltp.av.services.dto.DriftMiljoDTO

@Transactional(readOnly = true)
class DriftMiljoService {

    def list() {
        [new DriftMiljoDTO(
                id: 1,
                name: 'Test'
        ),
        new DriftMiljoDTO(
                id: 2,
                name: 'QA'
        ),
        new DriftMiljoDTO(
                id: 3,
                name: 'Production'
        )]
    }
}
