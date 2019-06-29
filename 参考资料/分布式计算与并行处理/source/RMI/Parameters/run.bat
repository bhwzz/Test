rmiregistry -J -Djava.rmi.server.codebase=url
java -Djava.security.policy=java.policy RMIServer
java RMIClient
