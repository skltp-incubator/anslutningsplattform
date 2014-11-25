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

    List<LogiskAdressDTO> getByEnvironmentAndServiceDomain(long environmentId, String serviceDomainId) {
        //TODO:query DB for existing logical addresses configured for environment and serviceDomain
        getLogiskAdressMockDTOs(null)
    }

    private getLogiskAdressMockDTOs(String query) {
        [
                new LogiskAdressDTO(
                        hsaId: 'SE2321000065-7330028000014',
                        namn: 'Kronobergs landsting'
                ),
                new LogiskAdressDTO(
                        hsaId: 'SE2321000065-7365566019822',
                        namn: 'Kronoberg Achima Care'
                ),
                new LogiskAdressDTO(
                        hsaId: 'SE2321000065-7365565696222',
                        namn: 'Kronoberg HälsoRingen Vård'
                ),
                new LogiskAdressDTO(
                        hsaId: 'SE2321000065-7330028091937',
                        namn: 'Kronoberg Smålandshälsan i Ljungby'
                ),
                new LogiskAdressDTO(
                        hsaId: 'SE2321000057-493K',
                        namn: 'Jönköpings landsting'
                ),
                new LogiskAdressDTO(
                        hsaId: 'SE2321000057-5SRJ',
                        namn: 'Jönköping Bräcke Diakoni VC Lokstallarna'
                ),
                new LogiskAdressDTO(
                        hsaId: 'SE2321000057-6SV4',
                        namn: 'Jönköping A6 Ögonklinik'
                ),
                new LogiskAdressDTO(
                        hsaId: 'SE2321000073-1000',
                        namn: 'Kalmars landsting'
                ),
                new LogiskAdressDTO(
                        hsaId: 'SE2321000073-4C87',
                        namn: 'Kalmar Husläkarcentrum Kalmar'
                ),
                new LogiskAdressDTO(
                        hsaId: 'SE2321000073-4C86',
                        namn: 'Kalmar Virserums läkarhus'
                ),
        ].findAll {
            query == null ||
                    it.namn.toLowerCase().contains(query.toLowerCase()) ||
                    it.hsaId.toLowerCase().contains(query.toLowerCase())
        }

    }
}
