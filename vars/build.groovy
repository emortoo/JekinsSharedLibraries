def call(String mavenGoal){
  
  if ("${mavenGoal}" == "Clean")
     {
       sh "mvn clean"
     }
  else if ("${mavenGoal}" == "Compile")
     {
       sh "mvn clean compile"
     }
  else if ("${mavenGoal}" == "Test")
     {
       sh "mvn clean test"
     }
   else if ("${mavenGoal}" == "Package")
      {
        sh "mvn clean package"
     }
}

stage('upload artifact to nexus') {
            steps {
                nexusArtifactUploader artifacts: [
                    [
                        artifactId: 'wwp', 
                        classifier: '', 
                        file: 'target/wwp-1.0.0.war', 
                        type: 'war'
                    ]
                ], 
                    credentialsId: 'nexus3', 
                    groupId: 'koddas.web.war', 
                    nexusUrl: '10.0.0.91:8081', 
                    nexusVersion: 'nexus3', 
                    protocol: 'http', 
                    repository: 'samplerepo', 
                    version: '1.0.0'
            }
        }
    }
