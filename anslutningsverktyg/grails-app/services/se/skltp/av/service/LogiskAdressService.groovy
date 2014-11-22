package se.skltp.av.service

import grails.transaction.Transactional
import se.skltp.av.services.dto.LogiskAdressDTO

@Transactional(readOnly = true)
class LogiskAdressService {

    def hsaService

    List<LogiskAdressDTO> freeTextSearch(String queryString, int limit) {
//        List<HsaDTO> hsaDTOList = hsaService.freeTextSearch(queryString, limit)
//        hsaDTOList.collect {
//            new LogiskAdressDTO(
//                    hsaId: it.hsaId,
//                    namn: it.hsaId //TODO: get a real name from somewhere?
//            )
//        }
        getLogiskAdressMockDTOs(queryString)
    }

    List<LogiskAdressDTO> getByEnvironmentAndServiceDomain(long environmentId, long serviceDomainId) {
        //TODO:query DB for existing logical addresses configured for environment and serviceDomain
        getLogiskAdressMockDTOs(null)
    }

    private getLogiskAdressMockDTOs(String query) {
        [
                new LogiskAdressDTO(
                        hsaId: 'SE0000000001-1234',
                        namn: 'Nässjö VC DLM'
                ),
                new LogiskAdressDTO(
                        hsaId: 'SE0000000005-1234',
                        namn: 'Eksjö Primärvårdsområde'
                )
        ].findAll {
            query == null ||
                    it.namn.toLowerCase().contains(query.toLowerCase()) ||
                    it.hsaId.toLowerCase().contains(query.toLowerCase())
        }

    }
}
