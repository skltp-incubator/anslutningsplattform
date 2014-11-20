class UrlMappings {

    static mappings = {

        //Controllers
        "/$controller/$action?/$id?(.$format)?" {
            constraints {
                // apply constraints here
            }
        }
        //API
        group "/api", {
            "/tjanstekomponenter"(version: '1.0', namespace: 'v1', controller: 'tjansteKomponentApi')
            "/producentbestallningar"(version: '1.0', namespace: 'v1', controller: 'producentBestallningApi')
            "/hsa"(version: '1.0', namespace: 'v1', controller: 'hsaApi')

            "/serviceComponents"(version: '1.0', controller: 'tjansteKomponentApi') {
                action = [GET:"query"]
                format = "json"
            }
        }

        "/"(view: "/index")
        "500"(view: '/error')
    }

}