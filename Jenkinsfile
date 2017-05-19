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
        stage('pack'){
            steps{
                echo 'build and push api'
                sh 'DOCKER_HOST=tcp://localhost:2375'
                sh './mvnw docker:build -pl robot-api -DpushImage'
            }
        }
    }
}