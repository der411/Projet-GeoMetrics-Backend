# Utilisez une image de base officielle Java
FROM openjdk:17-jdk-slim

# Copiez le fichier JAR de votre application dans l'image Docker
COPY ./target/Projet-Backend-0.0.1-snapshot.jar /app.jar

# Exposez le port sur lequel votre application tournera
EXPOSE 8080

# Commande pour démarrer votre application
ENTRYPOINT ["java", "-jar", "/app.jar"]
