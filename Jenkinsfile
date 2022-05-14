pipeline {
    agent any

    stages {
        stage('Test') {
            steps {
                bat './gradlew test'

            }

            post {
                   always{
                   junit '**/build/test-results/test/TEST-findCoverageAPI.xml'
                   }
                  }
        }



    }
}
