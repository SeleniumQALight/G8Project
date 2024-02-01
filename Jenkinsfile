pipeline {
    agent any

    stages {
        stage('Trigger DevJobImulation_G8') {
            steps {
                script {
                    def job = build(job: 'DevJobImulation_G8', propagate: false)
                    if (job.result == 'SUCCESS') {
                        stage('Trigger G8_run_test') {
                            step {
                                build(job: 'G8_run_test', parameters: [
                                    string(name: 'Module', value: 'overAll'),
                                    string(name: 'testName', value: 'LoginTestWithPageObject#validLogin'),
                                    string(name: 'defaultLogin', value: '-DdefaultLogin=qaauto'),
                                    string(name: 'additionalParameters', value: '-DdefaultPassword=123456qwerty')
                                ])
                            }
                        }
                    } else {
                        error("DevJobImulation_G8 job failed")
                    }
                }
            }
        }
    }
}