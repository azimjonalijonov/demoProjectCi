
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
        script {
            def cargoConfig = [
                containerId: 'tomcat9',
                credentialsId: 'tomcatadmin',
                containerUrl: 'http://localhost:8181/'
            ]
            
            def deployConfig = [
                war: 'target/*.jar',
                contextPath: '/'
            ]
            
            def cargo = com.cloudbees.plugins.credentials.CredentialsMatchers.firstOrNull(
                com.cloudbees.plugins.credentials.CredentialsProvider.lookupCredentials(
                    com.cloudbees.plugins.credentials.common.StandardUsernameCredentials.class,
                    Jenkins.getInstance(),
                    null,
                    null
                ),
                com.cloudbees.plugins.credentials.CredentialsMatchers.withId('tomcatadmin')
            )
            
            def deployer = new org.jenkinsci.plugins.deploy.weblogic.deployers.DeployPublisher(cargoConfig, deployConfig, cargo)
            deployer.deploy()
        }
    }
}

    //     stage('Deploy') {
    //         steps {
    //     deploy adapters: [tomcat9(credentialsId: 'tomcatadmin', path: '', url: 'http://localhost:8181/')], contextPath: null, war: 'target/*.jar'

    //             // deploy adapters: [tomcat9(credentialsId: 'tomcatadmin', path: '', url: 'http://localhost:8181/')], contextPath: null, war: 'target/**/*.war'
    //     }

    // }
    }
}
