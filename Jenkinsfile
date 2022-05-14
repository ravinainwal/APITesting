pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                bat './gradlew build'

            }

            post {


                      publishHTML target: [
                          allowMissing: false,
                          alwaysLinkToLastBuild: false,
                          keepAll: true,
                          reportDir: 'build/report',
                          reportFiles: 'index.html',
                          reportName: 'RCov Report'
                        ]

                  }
        }



    }
}
