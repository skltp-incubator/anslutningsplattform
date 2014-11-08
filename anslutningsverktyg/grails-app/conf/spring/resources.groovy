// Place your Spring DSL code here

import grails.rest.render.json.JsonRenderer
import grails.rest.render.json.JsonCollectionRenderer
import se.skltp.av.api.v1.*
import se.skltp.av.*

beans = {

	/* Default excludes for api v1 */
	final v1_DEFAULT_EXCLUDES = ['class']

	producentBestallningV1Renderer(JsonRenderer, ProducentBestallning){
		excludes = v1_DEFAULT_EXCLUDES
	}

	producentBestallningV1CollectionRenderer(JsonCollectionRenderer, ProducentBestallning){
		excludes = v1_DEFAULT_EXCLUDES
	}
	
	tjanstekomponentV1Renderer(JsonRenderer, TjansteKomponent){
		excludes = v1_DEFAULT_EXCLUDES
	}
	
	tjanstekomponentV1CollectionRenderer(JsonCollectionRenderer, TjansteKomponent){
		excludes = v1_DEFAULT_EXCLUDES
	}
}
