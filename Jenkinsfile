
pipeline {
    agent any
    tools {
maven 'maven'
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Test') {
            steps { bat 'mvn test' }
        }
        stage('Build') {
            steps { bat 'mvn clean install' }
        }
        stage('Code Coverage') {
            steps {
                bat 'mvn clean test'
                bat 'mvn jacoco:report'
            }
        }
       stage('Scan') {
            steps {
                withSonarQubeEnv(installationName: 'fortest'){
                    bat 'mvn clean verify sonar:sonar'
                }
            }
        }
         stage('Archive Artifacts') {
            steps {
                 archiveArtifacts artifacts: '**/*.war', followSymlinks: false
             }
         }
        stage('Deploy') {
            steps {
             deploy adapters: [tomcat9(credentialsId: 'tomcatadmin', path: '', url: 'http://localhost:8181')], contextPath: 'Fortest', war: '**/*war'
            }
        }
    }
}
