package se.skltp.av.api

import grails.rest.RestfulController
import se.skltp.av.services.dto.DriftMiljoDTO

class DriftMiljoApiController extends RestfulController {

    DriftMiljoApiController() {
        super(DriftMiljoDTO)
    }
    def driftMiljoService

    def list() {
        respond driftMiljoService.list()
    }
}
