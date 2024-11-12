## Dependency
### lombok.jar

This project uses [Lombok](https://projectlombok.org/) `v1.18.24`.

1. Download jar file from [Project Lombok](https://projectlombok.org/).
1. Put jar file on the path where `STS.ini` exists.
    * Windows: `sts-3.*.*.RELEASE\`
    * Mac: `/Applications/STS.app/Contents/Eclipse/`
1. Start lombok.jar library installer by running command:  
`java -jar lombok.jar`
1. Choose STS(eclipse) which you use to open this project.
1. Click Install and done!
1. To check if lombok is installed correctly, open `STS.ini` file and see if it has follwing statement:  
`-javaagent:[path to STS]/lombok.jar`

### Installing from Maven

1. Right-click the project from package explorer in STS.
2. Select `Maven > Update Project...` and run update
3. Try `Force Update of Snapshots/Releases` if it didn't work.