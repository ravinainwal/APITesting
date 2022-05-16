pipeline {
    agent any

    stages {
        stage('Test') {
            steps {
                bat './gradlew test'


            }

            post {
                    always {
                      // publish html
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
