package se.skltp.av.service


import spock.lang.*
import se.skltp.av.ProducentBestallning
import se.skltp.av.ProducentBestallningService
import se.skltp.av.util.BestallningsStatus

/**
 *
 */
class ProducentBestallningServiceSpec extends Specification {

    def producentBestallningService

    def setup() {
    }

    def cleanup() {
    }

    void "create new producentbestallning"() {

        setup:

        when:
            def newProducentBestallning = new ProducentBestallningDTO(status: BestallningsStatus.NY, miljo: 'TEST')
            producentBestallningService.updateProducentBestallning(newProducentBestallning)

        then:
            ProducentBestallning.list() == 1

    }
}
