package anslutningsverktyg

class AuthFilters {
    def filters = {
        api(uri: '/api/**') {
            before = {
                def authHeader = request.getHeader('x-ap-auth')

                if(authHeader) {
                    return true
                } else {
                    response.setStatus(401)
                    return false
                }
            }
        }
    }
}
