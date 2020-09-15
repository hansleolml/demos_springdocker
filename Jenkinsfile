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
        stage('Prueba login') {
            steps {
            	sh("hostname")
                sh("az --version")
            }
        }
    }
}
