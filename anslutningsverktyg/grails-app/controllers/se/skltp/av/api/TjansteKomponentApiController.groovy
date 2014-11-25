package se.skltp.av.api

import grails.rest.RestfulController;
import se.skltp.av.TjansteKomponent
import se.skltp.av.services.dto.TjansteKomponentDTO;

class TjansteKomponentApiController extends RestfulController {

    def tjansteKomponentService

    TjansteKomponentApiController() {
        super(TjansteKomponent)
    }

    def query() {
        log.debug params
        //TODO: Hämta bara namn och id
        def serviceComponentDTOs = tjansteKomponentService.query(params.query)
        respond serviceComponentDTOs.collect {
            it.properties.minus(it.properties.findAll { it.value == null }) //hack to get rid of null values in the api
        }
    }

    def get(long id) {
        log.debug params
        def serviceComponentDTO = tjansteKomponentService.findById(id)
        respond serviceComponentDTO.properties.minus(serviceComponentDTO.properties.findAll { it.value == null }) //hack to get rid of null values in the api
    }
}
