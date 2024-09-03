def call(Map config = [:]) {
    def emailData = [
                        email: "${config.TO}",
                        subject: "${config.SUBJECT}",
                        message: "${config.MESSAGE}"
                    ]
                    def emailDataJson = groovy.json.JsonOutput.toJson(emailData)

                    sh """
                        curl -X POST http://host.docker.internal:8000/send-email/ \
                        -H 'Content-Type: application/json' \
                        -d '${emailDataJson}'
                    """
    
    def isSuccess = true
    if (apiResponse != "200") {
        isSuccess = false
    }

    return isSuccess
}