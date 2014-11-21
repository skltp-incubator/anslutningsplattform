package se.skltp.av.service

import grails.transaction.Transactional
import se.skltp.av.services.dto.TjansteDomanDTO
import se.skltp.av.services.dto.TjansteKontraktDTO


@Transactional(readOnly = true)
class RivTaService {

    List<TjansteKontraktDTO> queryTjansteKontrakt(String hsaId, long environmentId, long serviceDomainId) {
        switch(serviceDomainId) {
            case 1:
                getClinicalProcessActivityActionsMockContracts()
                break
            case 2:
                getClinicalProcessActivityRequestMockContracts()
                break
            case 3:
                getClinicalProcessActivityPrescriptionActOutcomeMockContracts()
                break
            default:
                []
        }
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

    private List<TjansteKontraktDTO> getClinicalProcessActivityActionsMockContracts() {
        [
                new TjansteKontraktDTO(
                        namn: 'DeleteActivity',
                        namnrymd: 'urn:riv:clinicalprocess:activity:actions:DeleteActivityResponder:1',
                        majorVersion: 1,
                        minorVersion: 0
                ),
                new TjansteKontraktDTO(
                        namn: 'GetActivity',
                        namnrymd: 'urn:riv:clinicalprocess:activity:actions:GetActivityResponder:1',
                        majorVersion: 1,
                        minorVersion: 0
                ),
                new TjansteKontraktDTO(
                        namn: 'ProcessActivity',
                        namnrymd: 'urn:riv:clinicalprocess:activity:actions:ProcessActivityResponder:1',
                        majorVersion: 1,
                        minorVersion: 0
                )
        ]
    }

    private List<TjansteKontraktDTO> getClinicalProcessActivityRequestMockContracts() {
        [
                new TjansteKontraktDTO(
                        namn: 'GetRequestInstruction',
                        namnrymd: 'urn:riv:clinicalprocess:activity:request:GetRequestInstructionResponder:1',
                        majorVersion: 1,
                        minorVersion: 0
                ),
                new TjansteKontraktDTO(
                        namn: 'GetRequestStatus',
                        namnrymd: 'urn:riv:clinicalprocess:activity:request:GetRequestStatusResponder:1',
                        majorVersion: 1,
                        minorVersion: 0
                ),
                new TjansteKontraktDTO(
                        namn: 'ProcessRequestConfirmation',
                        namnrymd: 'urn:riv:clinicalprocess:activity:request:ProcessRequestConfirmationResponder:1',
                        majorVersion: 1,
                        minorVersion: 0
                ),
                new TjansteKontraktDTO(
                        namn: 'ProcessRequest',
                        namnrymd: 'urn:riv:clinicalprocess:activity:request:ProcessRequestResponder:1',
                        majorVersion: 1,
                        minorVersion: 0
                ),
                new TjansteKontraktDTO(
                        namn: 'ProcessRequestOutcome',
                        namnrymd: 'urn:riv:clinicalprocess:activity:request:ProcessRequestOutcomeResponder:1',
                        majorVersion: 1,
                        minorVersion: 0
                )
        ]
    }

    private List<TjansteKontraktDTO> getClinicalProcessActivityPrescriptionActOutcomeMockContracts() {
        [
                new TjansteKontraktDTO(
                        namn: 'GetVaccinationHistory',
                        namnrymd: 'urn:riv:clinicalprocess:activityprescription:actoutcome:GetVaccinationHistoryResponder:1',
                        majorVersion: 1,
                        minorVersion: 0
                )
        ]
    }
}
