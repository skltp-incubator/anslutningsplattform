# Anslutningsverktyg

## Tak tjänsterna under test

Kräver att det finns en TAK som svarar. För lokal test mot mockade stubbar. importera av-soapui-project.xml till soapui och starta mocktjänsten SokVagvalsInfoV2 (kör default på port 8090)

Sök på producentanslutningar med tjänsteproducents HSA ID. Nedan exempel ger alla tjänsteproducenter vars HSA ID startar på H

	curl -i -X GET -H "Accept: application/json" http://localhost:8080/anslutningsverktyg/tak/producentAnslutningar/H

Hämta alla tjänstekontrakt som finns registrerade i TAK

	curl -i -X GET -H "Accept: application/json" http://localhost:8080/anslutningsverktyg/tak/tjanstekontrakt
	

## HSA tjänsterna under test

Fritextsök på "SE"

	curl -i -X GET -H "Accept: application/json" http://localhost:8080/anslutningsverktyg/hsa/hsaInformation/SE