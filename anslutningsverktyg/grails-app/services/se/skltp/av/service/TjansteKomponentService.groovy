package se.skltp.av.service

import grails.transaction.Transactional
import se.skltp.av.TjansteKomponent

@Transactional
class TjansteKomponentService {

    @Transactional(readOnly = true)
    List<TjansteKomponent> query(String queryString) {
        return TjansteKomponent.findAllByHsaIdIlike("%$queryString%")
    }
}
