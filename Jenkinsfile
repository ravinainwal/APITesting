pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                bat './gradlew build'

            }

            post {
                    success {

                      publishHTML target: [
                          allowMissing: false,
                          alwaysLinkToLastBuild: false,
                          keepAll: true,
                          reportDir: 'coverage',
                          reportFiles: 'index.html',
                          reportName: 'RCov Report'
                        ]
                    }
                  }
        }



    }
}
