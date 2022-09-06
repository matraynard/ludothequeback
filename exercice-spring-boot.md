
Technologies à maitriser:
    java 8:  les Stream, les lambda les date du package java.time
    java 11/17 (java 11, les modules, java 17, les Text blocks )
    Spring boot derniere version
    les services REST et Jackson
    Hibernate, JPA et Spring DATA
    docker
    junit 5 Jupiter
    un framework JS pour accéder aux services REST (un sujet à part entière)


On va faire un exercice de développement en plusieurs étapes:
1ère étape

    sur start.spring.io créer un nouveau projet Spring
    sélectionner les modules REST, DATA, postresql, flyway ou liquibase
    intégrer ce projet sur un dépot git, ou me l'envoyer en zip.
    Créer une table "person"
    Faire le mapping, les couches logicielles classique pour exposer la liste des personnes en tant que service REST
    Faire un TU jupiter pour tester la requete

Pour les exercices suivants on pourrait continuer sur cette application:
2ème étape

    Ajouter des DTO, mapstruct, pour ne pas exposer directement les entity
    Ajouter swagger
    Ajouter une table group en relation n-n avec person
    Faire plusieurs requetes, des création, des jointures.
    Faire des tests paramétrés Jupiter

En terme d'outillage, installer un jdk17, maven, eclipse ou intelliij ou autre, git, docker, postman
ci-joint un docker-compose pour lancer postgresql sous docker.

Tu me dis si tu as besoin d'aide pour la 1ere étape, il y a pas mal de choses à faire au démarrage, et l'objectif étant que ce soit le plus clean possible.
