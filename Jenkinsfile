pipeline {
    agent any

    tools {
        jdk 'JDK'
        maven 'maven'
    }

    stages {
        

        stage('Build & Test') {
            steps {
                sh 'mvn clean test package'
            }
        }

        stage('Run App') {
            steps {
                sh 'java -jar target/Firefox-1.0-SNAPSHOT.jar'
            }
        }
    }

    post {
        success {
            echo 'Build Successful!'
        }
        failure {
            echo 'Build Failed!'
        }
        always {
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
        }
    }
}
