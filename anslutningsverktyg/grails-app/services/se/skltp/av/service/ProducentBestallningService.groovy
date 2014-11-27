package se.skltp.av.service

import grails.transaction.Transactional

import org.apache.shiro.crypto.hash.Sha256Hash

import se.skltp.av.BestallningsHistorik
import se.skltp.av.ProducentBestallning
import se.skltp.av.TjansteKomponent
import se.skltp.av.User
import se.skltp.av.services.dto.AnsvarigDTO
import se.skltp.av.services.dto.DriftMiljoDTO
import se.skltp.av.services.dto.ProducentBestallningDTO
import se.skltp.av.services.dto.TjansteDomanDTO
import se.skltp.av.services.dto.TjansteKomponentDTO
import se.skltp.av.util.BestallningsStatus

@Transactional(readOnly = true)
class ProducentBestallningService {
	
    def listProducentBestallning() {
        def producentBestallningar = ProducentBestallning.list()
        producentBestallningar.collect {
			
			def serviceDomain = new TjansteDomanDTO()
			
			def serviceComponent = new TjansteKomponentDTO()
			
			def tKomp = it.tjansteKomponent
			def serviceConsumer = new TjansteKomponentDTO(
				id: tKomp.id,
				hsaId: tKomp.hsaId,
				namn: tKomp.namn,
				//huvudAnsvarigNamn
				//huvudAnsvarigEpost
				//huvudAnsvarigTelefon
				tekniskKontaktEpost: tKomp.tekniskKontaktEpost,
				tekniskKontaktNamn: tKomp.tekniskKontaktNamn,
				tekniskKontaktTelefon: tKomp.tekniskKontaktTelefon,
				funktionsBrevladaEpost: tKomp.funktionsBrevladaEpost,
				funktionsBrevladaTelefon: tKomp.funktionsBrevladaTelefon,
				ipadress: tKomp.ipadress
				//pingForConfiguration
			)
			
			def miljo = new DriftMiljoDTO()
			
			def client = new AnsvarigDTO()
	
			def producentBestallningDTO = new ProducentBestallningDTO(
				id: it.id,
				status: it.status,
				serviceDomain: serviceDomain, 
				serviceComponent:
				serviceComponent, 
				serviceConsumer: serviceConsumer, 
				miljo: miljo, 
				client: client)
        }
    }

    @Transactional
    def updateProducentBestallning(producentBestallningDTO) {
		
		User user = upsertUser(producentBestallningDTO)
		TjansteKomponent tjansteKomponent = upsertTjansteKomponent(producentBestallningDTO, user)
		ProducentBestallning producentBestallning = upsertProducentBestallning(producentBestallningDTO, tjansteKomponent)
		//producentBestallning.save()
		
		//createProducentBestallningHistorik(producentBestallning, user.epost)
		
		//log.debug "Producentbestallning done, lets save in database: $producentBestallning"
    }
	
	private ProducentBestallning upsertProducentBestallning(producentBestallningDTO, tjansteKomponent){
		
		ProducentBestallning producentBestallning = ProducentBestallning.get(producentBestallningDTO.id)
		
		if(!producentBestallning){
			producentBestallning = new ProducentBestallning()
			producentBestallning.setStatus(BestallningsStatus.NY.toString())	
		}else{
			producentBestallning.setStatus(BestallningsStatus.UPPDATERAD.toString())
		}
		
		//tjansteKomponent.addToProducentBestallningar(producentBestallning)
		
		producentBestallning.setTjansteKomponent(tjansteKomponent)
		producentBestallning.setMiljo(producentBestallningDTO.miljo.namn)
		producentBestallning.save(flush:true)
		return producentBestallning
	}
	
	private TjansteKomponent upsertTjansteKomponent(producentBestallningDTO, user){
		
		TjansteKomponentDTO serviceComponent = producentBestallningDTO.serviceComponent
		TjansteKomponent tjansteKomponent = TjansteKomponent.get(serviceComponent.id)
		
		if(!tjansteKomponent){
			tjansteKomponent = new TjansteKomponent()
			log.debug "Tjanstekomponent not found in database, create a new one: $serviceComponent"
		}
		
		//user.addToTjansteKomponenter(tjansteKomponent)
		
		tjansteKomponent.setUser(user)
		tjansteKomponent.setHsaId(serviceComponent.hsaId)
		tjansteKomponent.setNamn(serviceComponent.namn)
		tjansteKomponent.setTekniskKontaktEpost(serviceComponent.tekniskKontaktEpost)
		tjansteKomponent.setTekniskKontaktNamn(serviceComponent.tekniskKontaktNamn)
		tjansteKomponent.setTekniskKontaktTelefon(serviceComponent.tekniskKontaktTelefon)
		tjansteKomponent.setFunktionsBrevladaEpost(serviceComponent.funktionsBrevladaEpost)
		tjansteKomponent.setFunktionsBrevladaTelefon(serviceComponent.funktionsBrevladaTelefon)
		tjansteKomponent.setIpadress(serviceComponent.ipadress)
		tjansteKomponent.save(flush:true)
		return tjansteKomponent
	}
	
	private User upsertUser(producentBestallningDTO){
		
		AnsvarigDTO ansvarig = producentBestallningDTO.client
		
		User user = User.get(ansvarig.id)
		
		if(!user){
			user = new User(passwordHash: new Sha256Hash("changeme").toHex())
			log.debug "Tjanstekomponent responsible user not found in database, create a new one: $ansvarig"
		}
		
		user.setNamn(ansvarig.name)
		user.setUsername(ansvarig.email)
		user.setEpost(ansvarig.email)
		user.setTelefonNummer(ansvarig.phone)
		user.save(flush:true)
		return user
	}
	
	private void createProducentBestallningHistorik(producentBestallning, epost){
		new BestallningsHistorik(
			status: producentBestallning.status,
			producentBestallning: producentBestallning,
			senastUppdateradAv: epost
			).save()
		
		log.debug "Producentbestallning history data created in database: $BestallningsHistorik"
	}
}
