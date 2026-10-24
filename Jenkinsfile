pipeline {
    agent any

    tools {
        maven 'MAVEN_HOME'
        jdk 'JAVA_HOME'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Test') {
            when {
                not { expression { fileExists('test-output/testng-failed.xml') } }
            }
            steps {
                echo "Running full suite"
                bat 'mvn clean test -DsuiteXmlFile=testng.xml -Dheadless=true'
            }
        }

        stage('Rerun Failed Tests') {
            when {
                expression { fileExists('test-output/testng-failed.xml') }
            }
            steps {
                echo "Re-running failed tests only"
                bat 'mvn test -DsuiteXmlFile=test-output/testng-failed.xml -Dheadless=true'
            }
        }

        stage('Allure Report') {
            steps {
                allure includeProperties: false,
                       results: [[path: 'allure-results/']]
            }
        }

        stage('Extent Report') {
            steps {
                publishHTML([
                    allowMissing: true,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'reports',
                    reportFiles: 'ExtentReport.html',
                    reportName: 'Extent_Report'
                ])
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '''
                reports/**,
                target/surefire-reports/**,
                test-output/**,
                logs/**
            ''', allowEmptyArchive: true

            cleanWs()
        }

        success {
            echo "Pipeline Succeeded!"
        }

        failure {
            echo "Pipeline Failed! Check the reports."
        }
    }
}
