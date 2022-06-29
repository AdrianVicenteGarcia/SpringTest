pipeline {
    agent any

    stages {
        stage('Test') {
            steps {
                sh './gradlew clean test check pitest'
            }
            post {
                always {
                    junit 'build/test-results/test/*.xml'
                    jacoco execPattern: 'build/jacoco/*.exec'
                    recordIssues(
                        tools: [
                            pmdParser(pattern: 'build/reports/pmd/*.xml')
                        ]
                    )
                    recordIssues(tools: [pit(pattern: 'build/reports/pitest/*.xml')])
                }
            }
        }
        stage('Build') {
            steps {
                // Run Gradle Wrapper
                sh "./gradlew assemble"
                with Gradle{
                         withCredentials([usernamePassword(credentialsId: 'adriangarcia3', passwordVariable: 'gitPassword', usernameVariable: 'gitUsername')]) {
                         sh "./gradlew publish"
                }
            }
            }

            post {
                // If Gradle was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    archiveArtifacts 'build/libs/*.jar'
                }
            }
        }
        stage('Deploy') {
            steps {
                   echo 'Deploying...'
            }
        }
    }
}



