pipeline {
    agent any

    stages {

    stage('Build') {
    steps {
                    bat './gradlew build'

                }

    }


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
