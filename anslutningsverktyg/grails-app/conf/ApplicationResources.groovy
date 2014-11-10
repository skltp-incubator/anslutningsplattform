modules = {
    jquery {
        resource url: 'js/lib/jquery/dist/jquery.js'
    }

    bootstrap {
        dependsOn: 'jquery'
        resource url: 'js/lib/bootstrap/dist/css/bootstrap.css', disposition: 'head'
    }

    angular {
        resource url: 'js/lib/angular/angular.js', disposition: 'head'
        resource url: 'js/lib/angular-cookies/angular-cookies.js', disposition: 'head'
        resource url: 'js/lib/angular-resource/angular-resource.js', disposition: 'head'
        resource url: 'js/lib/angular-route/angular-route.js', disposition: 'head'
        resource url: 'js/lib/angular-sanitize/angular-sanitize.js', disposition: 'head'
        resource url: 'js/lib/angular-animate/angular-animate.js', disposition: 'head'
    }
}
