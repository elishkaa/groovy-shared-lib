def call(Map config = [:]) {
    def emailData = [
                        email: "${config.TO}",
                        subject: "${config.SUBJECT}",
                        message: "${config.MESSAGE}"
                    ]
    def emailDataJson = groovy.json.JsonOutput.toJson(emailData)

    def apiResponse = sh(
        script: """
            curl -X POST http://host.docker.internal:8000/send-email/ \
            -H 'Content-Type: application/json' \
            -d '${emailDataJson}'
        """,
        returnStdout: true
        ).trim()
}