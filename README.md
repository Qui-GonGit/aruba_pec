# Prefazione
Per realizzare il caso d'uso descritto, è necessario un sistema che possa gestire un elevato numero di caselle PEC, messaggi e utenti, nonché una grande quantità di dati.
L'architettura proposta potrebbe includere le seguenti componenti:
* Un sistema di gestione delle caselle PEC, che utilizzi un database distribuito per gestire i metadati dei messaggi e un sistema di file storage distribuito per gestire gli allegati.
* Un sistema di gestione della firma digitale, che utilizzi un servizio di firma remota per firmare gli allegati selezionati dall'utente e un sistema di gestione dei certificati per autenticare gli utenti.
* Un sistema di gestione della conservazione sostitutiva, che utilizzi un sistema di archiviazione a lungo termine, ad esempio un sistema di object storage, per conservare i documenti firmati.
* Un sistema di gestione dell'autenticazione e autorizzazione, che utilizzi una soluzione OAuth per gestire l'accesso degli utenti alle API della piattaforma con IdP.
* Un sistema di orchestrazione dei microservizi utilizzando Kubernetes(K8s) per permettere una facile scalabilità e gestione dei container
* Un sistema di gestione dei tenant che utilizzi un database distribuito per gestire i metadati dei tenant e un sistema di gestione degli accessi per controllare l'accesso degli utenti ai dati dei tenant specifici.
* Un sistema di gestione dei carichi che bilanci le chiamate nelle varie istanze dei microservizi.
* Un sistema di registro dei servizi per facilitare il monitoraggio della salute dei servizi.
* Un front-end web per l'interfaccia utente, che permetta agli utenti di accedere alla piattaforma e gestire le loro caselle PEC, i loro documenti e i loro workflow.

Tenendo conto dei requisiti descritti, si potrebbe utilizzare le seguenti tecnologie:
* Spring boot per lo sviluppo di microservizi
* Spring Securiry per la parte di sicurezza con IdP Keycloak
* Un API Gateway per il corretto reinderizzamento dei servizi 
* Eureka netflix per il discovery services
* Zuul netflix per la gestione dei bilanciamenti dei servizi
* Database distribuito come Cassandra per la gestione dei tenant e Mysql o PostgreSql per la gestione dei messaggi/chiavi private etc.
* Elasticsearch + Kibana + LogStash per la corretta visulaizzazione dei log
* Cassandra per la gestione dei metadati dei tenant.

# Svolgimento
Il lavoro svolto è diverso rispetto alla soluzione architetturale descritta sopra, poichè non ho avuto molto tempo per lo svolgimento, ma potrebbe essere considerato come possibile Spike in un processo SCRUM AGILE come ad esempio una POC (Proof of Concept)

Per l'autenticazione e verifica token utente è stato utilizzato Spring Boot Security più Idp Keycloack. Il server di keycloack può essere lanciato utilizzando il docker engine:
```
docker run -p 8181:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:20.0.3 start-dev
http://localhost:8181/realms/Aruba_Realm
```
l'autenticazione avviene tramite OAuth2 cone Bearer token (JWT) per l'autorizzazione di tutte le API. Basterà creare il reame Aruba_Realm ed un client "client_1" e un paio di utenze con scope assegnati.

La parte di security è sul gateway, che parlerà direttamente con l'Idp per l'autenticazione e per la sicurezza delle API. 
In fase di colloquio spiegherò tutto il giro.

E' stato utilizzato Spring Boot per creare i servizi di  PEC dei filtro messaggi, FIRMA allegati, e CONSERVAZIONE documenti firmati.

Per la firma elettronica dei documenti si è utilizzato la libreria IText. 

Per fare i test sono stati creati dei certificati con tool openssl :
```
openssl genpkey -algorithm RSA -out private_key.pem -aes256
openssl req -new -x509 -key private_key.pem -out certificate.pem
openssl pkcs12 -export -in certificate.pem -inkey private_key.pem -out keystore.p12
```
I file firmati vengono salvati sul file system, in fase di test vengono salvati su rsc/test/resources lo stesso accade per la conservazione dei documenti. Tutti e due i microservizi (Firma/Conservazione) vanno avviati con la variabile -DPATH_UPLOAD=filepath per identificare i file di upload degli allegati e il path dei documenti sicuri.
Per il client si è utilizzato Angular con login e dashboard per gestione della PEC e filtro messaggi.

Di seguito l'architettura:

![Architettura_POC_IDEALE](https://user-images.githubusercontent.com/56196785/216554413-4bca8616-14dc-457e-895b-6611e66b696f.png)


I servizi sono stati coperti da test di integrazione:

![Screenshot 2023-02-02 184306](https://user-images.githubusercontent.com/56196785/216455326-29b06e83-e6b5-4bc2-b7e2-6f1efc7dfb15.png)

La discussione dei singoli flussi la lascio al colloquio.
Comunque grazie mi sono divertito :)


