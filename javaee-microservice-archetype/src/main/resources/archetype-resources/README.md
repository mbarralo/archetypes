Sample micro service with persistence data to kickstart any microservice demo

### Run in Wildlfy 10 and hot deploy war during DEV

```bash
mvn clean package
```

### Test with wilfly-swarm

```bash
mvn clean package -Puberjar && java -jar target/microservice-swarm.jar
```
