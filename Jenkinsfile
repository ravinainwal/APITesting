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
                }

    }
}
