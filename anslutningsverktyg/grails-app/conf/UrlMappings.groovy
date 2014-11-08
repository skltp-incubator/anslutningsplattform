class UrlMappings {

	static mappings = {
		
		//API
		"/api/tjanstekomponenter"(version:'1.0', namespace:'v1', controller: 'tjansteKomponentApi')
		"/api/producentbestallningar"(version:'1.0', namespace:'v1', controller: 'producentBestallningApi')
		"/api/hsa"(version:'1.0', namespace:'v1', controller: 'hsaApi')
		
		//Controllers
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
		

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
