pipeline {
    agent any
    options {
            ansiColor('xterm')
            timestamps()
            disableConcurrentBuilds()
            buildDiscarder(logRotator(numToKeepStr: '5', artifactNumToKeepStr: '5'))
        }
    stages {
        stage('Build') {
            steps {
                //git branch: 'main', url: 'https://github.com/rodauher/Hello-Springboot.git'
                //sh "./gradlew test assemble"
                withGradle {
                sh "./gradlew test assemble check"
                jacoco execPattern: 'build/jacoco/*.exec'
                recordIssues(tools: [pmdParser(pattern: 'build/reports/pmd/*.xml')])
                recordIssues(tools: [pit(pattern: 'build/reports/pitest/*.xml')])
                }

            }
            post {
                success {
                    junit 'build/test-results/test/*.xml'
                    archiveArtifacts 'build/libs/*jar'
                }
            }
        }
        stage('Publish'){
            steps{
            withGradle {
                withCredentials([usernamePassword(credentialsId: 'Credentials', passwordVariable: 'TOKEN', usernameVariable: 'USERNAME'), usernamePassword(credentialsId: 'nexus', passwordVariable: 'TOKENEX', usernameVariable: 'USERNAMENEX')]) {
                //withCredentials([usernamePassword(credentialsId: 'nexus', passwordVariable: 'TOKENEX', usernameVariable: 'USERNAMENEX')]){//, usernamePassword(credentialsId: 'nexus', passwordVariable: 'TOKENEX', usernameVariable: 'USERNAMENEX')]) {
                sh "./gradlew publish"}
                }
                sshagent(['git-private']) {
                sh 'git tag BUILD-1.0.${BUILD_NUMBER}'
                sh 'git push --tags'
                }
            }
        }
    }
}


