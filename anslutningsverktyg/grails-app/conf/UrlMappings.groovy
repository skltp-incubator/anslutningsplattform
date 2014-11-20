class UrlMappings {

    static mappings = {
//
//        //Controllers
//        "/$controller/$action?/$id?(.$format)?" {
//            constraints {
//                // apply constraints here
//            }
//        }
        //API
        group "/api", {
            "/tjanstekomponenter"(version: '1.0', namespace: 'v1', controller: 'tjansteKomponentApi')
            "/producentbestallningar"(version: '1.0', namespace: 'v1', controller: 'producentBestallningApi')
            "/hsa"(version: '1.0', namespace: 'v1', controller: 'hsaApi')

            "/serviceComponents"(version: '1.0', controller: 'tjansteKomponentApi') {
                action = [GET:"query"]
                format = "json"
                "/serviceComponents/$id"(version: '1.0', controller: 'tjansteKomponentApi') {
                    action = [GET:"get"]
                    format = "json"
                }
            }

            "/environments"(version: '1.0', controller: 'driftMiljoApi') {
                action = [GET:"list"]
                format = "json"
            }
        }

        "/"(view: "/index")
        "500"(view: '/error')
    }

}