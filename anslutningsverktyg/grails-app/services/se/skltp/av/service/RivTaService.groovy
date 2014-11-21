package se.skltp.av.service

import grails.transaction.Transactional
import se.skltp.av.services.dto.TjansteDomanDTO


@Transactional(readOnly = true)
class RivTaService {

    def getAllTjansteKontrakt() {
    }

    List<TjansteDomanDTO> queryTjansteDoman(String hsaId, long environmentId) {
        [
                new TjansteDomanDTO(
                    id: 1,
                    tjansteDomanId: 'clinicalprocess:activity:actions',
                    namn: 'Hantera aktiviteter, aktiviteter',
                    version: '1.0_RC1',
                    rivTaVersion: '2.1'
                ),
                new TjansteDomanDTO(
                        id: 2,
                        tjansteDomanId: 'clinicalprocess:activity:request',
                        namn: 'vård- och omsorg kärnprocess, hantera aktiviteter, remisshantering',
                        version: '1.0_RC3',
                        rivTaVersion: '2.1'
                ),
                new TjansteDomanDTO(
                        id: 3,
                        tjansteDomanId: 'clinicalprocess:activityprescription:actoutcome',
                        namn: 'Hantera ordinations- och förskrivningsrelaterat utfall av aktivitet',
                        version: '2.0_RC1',
                        rivTaVersion: '2.1'
                )

        ]
    }
}
