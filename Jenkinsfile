pipeline {
    agent { 
    	node { 
    		label 'jenkins_slave'
    	}
    }
    environment {
        AZ_ACCESS_KEY_ID     = credentials('3f56ad64-c46f-4253-a70d-424d0402ab97')
    }
    stages {
        stage('Git Clone'){
            steps {
                git credentialsId: 'jenkins-user-for-git-repository', url: 'https://github.com/hansleolml/demos_springdocker.git'
            }
        }
        stage('Prueba login') {
            steps {
            	sh("hostname")
                sh("az --version")
                sh("ls -la")
            }
        }
        stage('Docker Build') {
            steps {
                script{
                    //def customImage = docker.build("my-image:${env.BUILD_ID}")
                    def customImage = docker.build("hansleolml/demo_spring:${env.BUILD_ID}")
    	    	    //sh("docker build -t hansleolml/demo_spring:latest .")
                }
            }
        }
        stage('Push Docker') {
            steps {
    	    	script {
    	    		docker.withRegistry('https://registry.hub.docker.com','jenkins-user-for-docker-repository') {
                        //def customImage = docker.build("hansleolml/demo_spring:${env.BUILD_ID}")
                        customImage.push()
                        customImage.push('latest')
                    }
                }
            }
        }
    }
    /*
    post { 
        always { 
            cleanWs()
        }
    }
    */
}
