
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
//         stage('Debug Workspace') {
//     steps {
//         script {
//             // Print the current workspace location
//             echo "Current Workspace: ${env.WORKSPACE}"

//             // List the contents of the workspace
//             bat 'dir /s'
//         }
//     }
// }

         stage('Archive Artifacts') {
            steps {
                 archiveArtifacts artifacts: 'target/**/*.jar', followSymlinks: false
             }
         }
        stage('Deploy') {
            steps {
                deploy adapters: [tomcat9(credentialsId: 'tomcatadmin', path: '', url: 'http://localhost:8181/')], contextPath: null, war: 'target/**/*.war'
        }

    }
    }
}
