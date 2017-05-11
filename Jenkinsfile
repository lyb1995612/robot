pipeline{
    agent any
    stages{
        stage('info'){
            steps{
                sh 'printenv'
            }
        }
        stage('build'){
            steps{
                echo 'start build'
                sh 'chmod a+x mvnw'
                sh './mvnw -version'
                sh './mvnw clean package'
            }
        }
    }
}