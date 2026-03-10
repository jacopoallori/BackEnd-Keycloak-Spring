# Progetto Keycloak Backend

## Panoramica

Questo progetto espone un **backend amministrativo** per la gestione centralizzata di un realm Keycloak tramite API REST.
L'obiettivo dell'applicativo è fornire un layer backend pulito, controllato e facilmente integrabile con un futuro **backoffice web** per amministrare utenti, gruppi, ruoli, client, provider di identità, sessioni ed eventi.

In pratica, l'applicazione si posiziona tra il frontend amministrativo e le API Admin di Keycloak, con questi obiettivi principali:

* semplificare l'utilizzo delle API native di Keycloak;
* organizzare le operazioni in endpoint coerenti e orientati al dominio;
* centralizzare la logica di gestione amministrativa;
* fornire un punto unico per controlli, validazioni, mapping e auditing;
* rendere più semplice la costruzione di un pannello di backoffice.

---

## Obiettivo dell'applicativo

L'applicativo consente a un amministratore di:

* gestire gli utenti del realm;
* assegnare e revocare ruoli e gruppi;
* consultare e gestire le sessioni utente;
* amministrare i client applicativi;
* configurare i provider di identità esterni;
* consultare eventi utente e admin events;
* esporre un set di API backend uniforme per il backoffice.

---

## Funzionalità principali

### 1. Gestione utenti

Operazioni legate alla gestione e manipolazione degli utenti.

Funzionalità:

* Operazioni CRUD su utenti.
* Gestioni operative dell'utente.
* Gestione delle relazioni di un utente a livello di clients.
* Gestione delle relazioni di un utente a livello di realms.
* Gestione delle relazioni di un utente a livello di groups.
* Monitoraggio audit.
* Gestione sessione utente.



### 2. Relazioni utente

Permette di gestire tutte le associazioni di un utente con gli altri oggetti del dominio.

Funzionalità:

* visualizzazione gruppi di appartenenza;
* aggiunta/rimozione dell'utente da gruppi;
* visualizzazione ruoli realm assegnati;
* assegnazione/revoca ruoli realm;
* visualizzazione ruoli client assegnati;
* assegnazione/revoca ruoli client;
* consultazione dei consensi e delle relazioni applicative, se abilitate.

### 3. Azioni utente

Raccoglie operazioni amministrative sull'utente che non sono puro CRUD.

Funzionalità:

* reset password;
* invio email per verifica account;
* invio email per update password / update profile;
* logout forzato;
* invalidazione sessioni;
* gestione required actions;
* eventuale impersonazione, se prevista dalle policy del progetto.

### 4. Gestione gruppi

Permette di amministrare la struttura dei gruppi del realm.

Funzionalità:

* creazione gruppo;
* modifica gruppo;
* eliminazione gruppo;
* consultazione struttura gruppi;
* gestione sottogruppi;
* gestione membri del gruppo;
* gestione ruoli associati al gruppo.

### 5. Gestione ruoli

Permette di amministrare i ruoli di realm e i ruoli client.

Funzionalità:

* consultazione ruoli realm;
* creazione/modifica/eliminazione ruoli realm;
* consultazione ruoli client;
* gestione ruoli compositi;
* assegnazione ruoli a utenti e gruppi.

### 6. Gestione client

Consente di amministrare le applicazioni registrate in Keycloak.

Funzionalità:

* creazione client;
* modifica configurazione client;
* eliminazione client;
* ricerca client;
* consultazione dettaglio client;
* gestione service account;
* gestione secret / rotazione secret;
* gestione ruoli client;
* gestione scope e configurazioni utili all'integrazione.

### 7. Gestione Identity Providers

Consente di amministrare provider esterni di autenticazione.

Funzionalità:

* creazione provider;
* modifica configurazione provider;
* eliminazione provider;
* consultazione provider configurati;
* gestione mapper IdP.

### 8. Gestione sessioni

Permette di monitorare e intervenire sulle sessioni attive nel realm.

Funzionalità:

* consultazione sessioni utente;
* logout di singole sessioni o di tutte le sessioni di un utente;
* analisi accessi attivi;
* supporto alla governance della sicurezza.

### 9. Gestione eventi

Permette di consultare gli eventi generati da Keycloak.

Funzionalità:

* consultazione eventi utente;
* consultazione admin events;
* filtro per tipo evento, utente, client e intervallo temporale;
* supporto auditing e troubleshooting.

---

## Architettura logica del progetto

Il backend è pensato come uno strato applicativo composto da:

* **Controller REST**: espongono gli endpoint verso il frontend/backoffice;
* **Service layer**: contiene la logica di orchestrazione;
* **Client/Adapter Keycloak**: incapsula le chiamate alle Admin API di Keycloak;
* **DTO / Mapper**: gestiscono input e output delle API;
* **Gestione errori**: uniforma le risposte di errore;
* **Sicurezza**: protegge gli endpoint amministrativi.

---

## API esposte

Di seguito la struttura proposta delle API del backend.


## 1. Operazioni di controllo su utente ==> Controller/Users

## 1.1. Users CRUD Controller ==> UserManagementController
* Controller destinato a gestire le operazioni Crud di un utente

Base path:
`/api/admin/users`

### 1.Endpoint
#### `" "` - `GET`
Recupero lista utenti con paginazione.


### 2. Endpoint:
#### `"/{userId}"` - `GET`
Estrazione dati di un utente specifico.


### 3. Endpoint:
#### `"/create"` - `POST`
Creazione di un nuovo utente.


### 4. Endpoint:
#### `"/create"` - `POST`
Aggiornamento dati di un utente.


### 5. Endpoint:
#### `"/{userId}"` - `DELETE`
Eliminazione di un utente.

---
## 1.2. Users Actions Controller ==> UserActionsController

Base path:
`/api/admin/users`

### 1.Endpoint
#### `"{userId}/enable"` - `PUT`
Abilitazione di un utente.


### 2.Endpoint
#### `"{userId}/disable"` - `PUT`
Disabilitazione di un utente.


### 3.Endpoint
#### `"{userId}/resetPassword"` - `POST`
Reset password di un utente.


### 4.Endpoint
#### `"{userId}/sendVerifyEmail"` - `POST`
Invio email di verifica email di un utente.


### 5.Endpoint
#### `"{userId}/adminVerifyEmail"` - `POST`
Azione che permette di verificare l'email di un utente da partet dell'amministratore.


### 6.Endpoint
#### `"{userId}/logout"` - `POST`
Logout di un utente.

---
## 1.3. Users Relations Clients Controller ==> UserRelationsClientsController

Base path:
`/api/admin/users`

### 1.Endpoint
#### `"clients/{userId}/"` - `GET`
Get lista ruoli assegnati a un utente a livello di Clients.


### 2.Endpoint
#### `"clients/{userId}"` - `POST`
Aggiungi ruolo ad utente livello di clients.


### 3.Endpoint
#### `"clients/{userId}"` - `DELETE`
Rimuovi ruolo ad utente livello di clients.

---
## 1.4. Users Relations Realm Controller ==> UserRelationsRealmController

Base path:
`/api/admin/users`

### 1.Endpoint
#### `"realm/{userId}/"` - `GET`
Get lista ruoli assegnati a un utente a livello di realm.


### 2.Endpoint
#### `"realm/{userId}"` - `POST`
Aggiungi ruolo ad utente a livello di realm.


### 3.Endpoint
#### `"realm/{userId}"` - `DELETE`
Rimuovi ruolo ad utente a livello di realm.

---
## 1.5. Users Relations Groups Controller ==> UserRelationsGroupsController

Base path:
`/api/admin/users`

### 1.Endpoint
#### `"groups/{userId}"` - `GET`
Get dei gruppi assegnati a un utente.


### 2.Endpoint
#### `"groups/{userId}/{groupId}"` - `POST`
Assegna gruppo a un utente .


### 3.Endpoint
#### `"clients/{userId}"` - `DELETE`
Rimuovi gruppo a un utente.

---
## 1.6. Users Audit Controller ==> UserAuditController

Base path:
`/api/admin/users`

### 1.Endpoint
#### `"/{userId}/events"` - `GET`
Azioni di un utente(browser/dispositivi).


### 2.Endpoint
#### `"/{userId}/loginHistory"` - `GET`
Storico login attivi dell’utente (browser/dispositivi).


### 3.Endpoint
#### `"/{userId}/loginFailures"` - `GET`
Storico login falliti da un utente.

---
## 1.7. Users Sessions Controller ==> UserSessionsController

Base path:
`/api/admin/users`

### 1.Endpoint
#### `"/{userId}/sessions"` - `GET`
Get delle sessioni attive.
Obbiettivo: vedere le sessioni attivi di un utente (browser/dispositivi)


### 2.Endpoint
#### `"groups/{userId}/{groupId}"` - `GET`
Get delle sessioni attive. 
Obbiettivo: vedere le sessioni offline di un utente (browser/dispositivi).







Possibili parametri:

* username
* email
* firstName
* lastName
* enabled
* first
* max



---

## 2. User Actions Controller

Base path:
`/api/admin/users/{userId}/actions`

### Endpoint

#### `PUT /api/admin/users/{userId}/actions/reset-password`

Esegue il reset password dell'utente.

#### `PUT /api/admin/users/{userId}/actions/send-verify-email`

Invia email di verifica account.

#### `PUT /api/admin/users/{userId}/actions/execute-actions-email`

Invia una email con required actions (es. update password, verify email, update profile).

#### `PUT /api/admin/users/{userId}/actions/logout`

Effettua il logout forzato dell'utente.

#### `PUT /api/admin/users/{userId}/actions/enable`

Abilita l'utente.

#### `PUT /api/admin/users/{userId}/actions/disable`

Disabilita l'utente.

#### `PUT /api/admin/users/{userId}/actions/unlock`

Sblocca l'utente se bloccato.

---

## 3. User Relations Controller

Base path:
`/api/admin/users/{userId}`

### Gruppi

#### `GET /api/admin/users/{userId}/groups`

Restituisce i gruppi di appartenenza dell'utente.

#### `PUT /api/admin/users/{userId}/groups/{groupId}`

Aggiunge l'utente al gruppo.

#### `DELETE /api/admin/users/{userId}/groups/{groupId}`

Rimuove l'utente dal gruppo.

### Ruoli realm

#### `GET /api/admin/users/{userId}/roles/realm`

Restituisce i ruoli realm assegnati all'utente.

#### `POST /api/admin/users/{userId}/roles/realm`

Assegna uno o più ruoli realm all'utente.

#### `DELETE /api/admin/users/{userId}/roles/realm`

Revoca uno o più ruoli realm all'utente.

### Ruoli client

#### `GET /api/admin/users/{userId}/roles/clients/{clientUuid}`

Restituisce i ruoli client assegnati all'utente per uno specifico client.

#### `POST /api/admin/users/{userId}/roles/clients/{clientUuid}`

Assegna ruoli client all'utente.

#### `DELETE /api/admin/users/{userId}/roles/clients/{clientUuid}`

Revoca ruoli client all'utente.

---

## 4. Groups Controller

Base path:
`/api/admin/groups`

### Endpoint

#### `GET /api/admin/groups`

Restituisce la lista o l'albero dei gruppi.

#### `GET /api/admin/groups/{groupId}`

Restituisce il dettaglio di un gruppo.

#### `POST /api/admin/groups`

Crea un nuovo gruppo.

#### `PUT /api/admin/groups/{groupId}`

Aggiorna un gruppo.

#### `DELETE /api/admin/groups/{groupId}`

Elimina un gruppo.

#### `GET /api/admin/groups/{groupId}/members`

Recupera i membri del gruppo.

#### `GET /api/admin/groups/{groupId}/roles/realm`

Recupera i ruoli realm associati al gruppo.

#### `POST /api/admin/groups/{groupId}/roles/realm`

Associa ruoli realm al gruppo.

#### `DELETE /api/admin/groups/{groupId}/roles/realm`

Rimuove ruoli realm dal gruppo.

---

## 5. Roles Controller

Base path:
`/api/admin/roles`

### Ruoli realm

#### `GET /api/admin/roles/realm`

Lista ruoli realm.

#### `GET /api/admin/roles/realm/{roleName}`

Dettaglio ruolo realm.

#### `POST /api/admin/roles/realm`

Crea ruolo realm.

#### `PUT /api/admin/roles/realm/{roleName}`

Aggiorna ruolo realm.

#### `DELETE /api/admin/roles/realm/{roleName}`

Elimina ruolo realm.

### Ruoli client

#### `GET /api/admin/roles/clients/{clientUuid}`

Lista ruoli del client.

#### `GET /api/admin/roles/clients/{clientUuid}/{roleName}`

Dettaglio ruolo client.

#### `POST /api/admin/roles/clients/{clientUuid}`

Crea ruolo client.

#### `PUT /api/admin/roles/clients/{clientUuid}/{roleName}`

Aggiorna ruolo client.

#### `DELETE /api/admin/roles/clients/{clientUuid}/{roleName}`

Elimina ruolo client.

---

## 6. Clients Controller

Base path:
`/api/admin/clients`

### CRUD client

#### `GET /api/admin/clients`

Restituisce la lista dei client con supporto ricerca.

#### `GET /api/admin/clients/{clientUuid}`

Restituisce il dettaglio di un client.

#### `POST /api/admin/clients`

Crea un nuovo client.

#### `PUT /api/admin/clients/{clientUuid}`

Aggiorna un client.

#### `DELETE /api/admin/clients/{clientUuid}`

Elimina un client.

### Service account

#### `GET /api/admin/clients/{clientUuid}/service-account-user`

Restituisce l'utente tecnico associato al service account del client.

#### `GET /api/admin/clients/{clientUuid}/service-account-user/roles`

Restituisce i ruoli del service account.

#### `POST /api/admin/clients/{clientUuid}/service-account-user/roles`

Assegna ruoli al service account.

#### `DELETE /api/admin/clients/{clientUuid}/service-account-user/roles`

Revoca ruoli dal service account.

### Secret e ruoli client

#### `POST /api/admin/clients/{clientUuid}/client-secret/rotate`

Rigenera o ruota il client secret.

#### `GET /api/admin/clients/{clientUuid}/roles`

Restituisce i ruoli del client.

---

## 7. Identity Providers Controller

Base path:
`/api/admin/identity-providers`

### CRUD IdP

#### `GET /api/admin/identity-providers`

Lista identity providers configurati.

#### `GET /api/admin/identity-providers/{alias}`

Dettaglio di un identity provider.

#### `POST /api/admin/identity-providers`

Crea un nuovo identity provider.

#### `PUT /api/admin/identity-providers/{alias}`

Aggiorna un identity provider.

#### `DELETE /api/admin/identity-providers/{alias}`

Elimina un identity provider.

### Mapper IdP

#### `GET /api/admin/identity-providers/{alias}/mappers`

Lista i mapper associati all'IdP.

#### `POST /api/admin/identity-providers/{alias}/mappers`

Crea un nuovo mapper per l'IdP.

#### `PUT /api/admin/identity-providers/{alias}/mappers/{mapperId}`

Aggiorna un mapper.

#### `DELETE /api/admin/identity-providers/{alias}/mappers/{mapperId}`

Elimina un mapper.

---

## 8. Sessions Controller

Base path:
`/api/admin/sessions`

### Endpoint

#### `GET /api/admin/users/{userId}/sessions`

Restituisce le sessioni attive di un utente.

#### `DELETE /api/admin/users/{userId}/sessions`

Invalida tutte le sessioni dell'utente.

#### `DELETE /api/admin/users/{userId}/sessions/{sessionId}`

Invalida una specifica sessione, se disponibile a livello di integrazione.

Le sessioni rappresentano gli accessi attivi dell'utente alle applicazioni federate o protette da Keycloak. Questi endpoint sono utili per attività di sicurezza, supporto operativo e amministrazione account.

---

## 9. Events Controller

Base path:
`/api/admin/events`

### Endpoint

#### `GET /api/admin/events`

Restituisce gli eventi realm con filtri opzionali:

* type
* user
* client
* dateFrom
* dateTo
* first
* max

#### `GET /api/admin/events/admin`

Restituisce gli admin events, utili per audit delle operazioni amministrative.

#### `DELETE /api/admin/events`

Pulisce gli eventi salvati, se si decide di esporre anche questa funzionalità.

Questi endpoint servono a tracciare autenticazioni, errori, operazioni amministrative e attività di sicurezza nel realm.

---

## 10. Realm / System Controller

Base path:
`/api/admin/realm`

### Endpoint suggeriti

#### `GET /api/admin/realm`

Recupera le informazioni principali del realm.

#### `GET /api/admin/realm/themes`

Recupera informazioni utili su temi o configurazioni collegate, se esposte.

#### `GET /api/admin/health`

Endpoint tecnico per verificare che il backend sia attivo.

#### `GET /api/admin/info`

Endpoint informativo con metadata applicativi.

---

## Funzionalità esposte al backoffice

Il backend è progettato per essere consumato da un pannello amministrativo e quindi abilita queste macro-funzionalità di UI:

### Dashboard amministrativa

* overview utenti;
* overview client;
* overview gruppi e ruoli;
* accesso rapido a eventi e sessioni.

### Gestione utenti

* lista utenti;
* dettaglio utente;
* creazione/modifica/eliminazione;
* reset password;
* abilitazione/disabilitazione;
* gestione gruppi e ruoli;
* visualizzazione sessioni attive.

### Gestione gruppi

* lista gruppi;
* dettaglio gruppo;
* struttura gerarchica;
* membri;
* ruoli associati.

### Gestione ruoli

* ruoli realm;
* ruoli client;
* assegnazioni ad utenti e gruppi.

### Gestione client

* lista client;
* dettaglio client;
* configurazione service account;
* ruoli client;
* rotazione secret.

### Gestione IdP

* lista provider;
* configurazione;
* gestione mapper.

### Monitoring e audit

* eventi utente;
* admin events;
* sessioni attive;
* logout forzato.

---

## Benefici del progetto

I principali vantaggi di questo backend sono:

* isolamento della complessità delle Admin API di Keycloak;
* esposizione di API più leggibili e coerenti;
* facilità di integrazione con frontend Angular o altri client;
* maggiore controllo su sicurezza, validazione e auditing;
* scalabilità dell'architettura nel tempo.

---

## Possibili evoluzioni future

Possibili estensioni del progetto:

* gestione permission / authorization policies;
* gestione authentication flows;
* esportazione report utenti/eventi;
* auditing avanzato;
* paginazione e filtri avanzati su tutte le entità;
* caching delle informazioni più consultate;
* supporto multi-realm;
* integrazione con workflow approvativi.

---

## Note finali

Questo backend non sostituisce Keycloak, ma ne rappresenta un **layer applicativo di governance e amministrazione**.
Il suo scopo è fornire una base solida per costruire un sistema di backoffice moderno, ordinato e facilmente manutenibile per la gestione delle identità e degli accessi.
