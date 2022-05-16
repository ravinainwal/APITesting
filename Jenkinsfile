pipeline {
    agent any

    stages {
        stage('Test') {
            steps {
                bat './gradlew test'

                 publishHTML target: [
                            allowMissing: false,
                            alwaysLinkToLastBuild: false,
                            keepAll: true,
                            reportDir: 'C:\Users\ravin\IdeaProjects\APITesting\build\reports\tests\test',
                            reportFiles: 'index.html',
                            reportName: 'HTML Report'
                          ]

            }


        }



    }
}
