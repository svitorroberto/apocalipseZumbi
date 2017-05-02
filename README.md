# API REST ApocalipseZumbi

## Funções

**Cadastrar Usuário**
----
  Returns json data about the oparation.

* **URL**

  /rest/usuarios/cadastrar/{nome}/{sexo}/{idade}/{localizacao}

* **Method:**

  `GET`
  
*  **URL Params**

   **Required:**
  `nome=[string]`
  `idade=[integer]`
  `sexo=[M or F]`
  `ultimaLocalizacao=[latitude,longitude]`

* **Data Params**

   `none`

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{Salvo com sucesso}`
 
* **Error Response:**

  * **Code:** 417 EXPECTATION FAILED <br />
    **Content:** `{Erro}`

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "/rest/usuarios/cadastrar/Maria/F/23/-16.6930378,-49.2476555",
      dataType: "json",
      type : "GET",
      success : function(r) {
        console.log(r);
      }
    });
  ```
**Atualizar Última Localização**
----
  Returns json data about the oparation.

* **URL**

  /rest/usuarios/atualizarLocalizacao/{id}/{localizcao}

* **Method:**

  `GET`
  
*  **URL Params**

   **Required:**
  `id=[integer]`
  `localizacao=[latitude,longitude]`

* **Data Params**

   `None`

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{Sucesso}`
 
* **Error Response:**

  * **Code:** 417 EXPECTATION FAILED <br />
    **Content:** `{Erro}`

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "/rest/usuarios/cadastrar/1/-16.6930378,-49.2476555",
      dataType: "json",
      type : "GET",
      success : function(r) {
        console.log(r);
      }
    });
  ```
  **Marcar usuário como infectado**
----
  Returns json data about the oparation.

* **URL**

  /rest/usuarios/reportar/{idInfectado}/{idReporter}

* **Method:**

  `GET`
  
*  **URL Params**

   **Required:**
  `idInfectado=[integer]`
  `idReporter=[integer]`

* **Data Params**

   `None`

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{Sucesso}`
 
* **Error Response:**

  * **Code:** 417 EXPECTATION FAILED <br />
    **Content:** `{Erro}`

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "/rest/usuarios/reportar/3/4",
      dataType: "json",
      type : "GET",
      success : function(r) {
        console.log(r);
      }
    });
  ```
  **Adicionar itens do inventário de um usuário**
----
  Returns json data about the oparation.

* **URL**

  /rest/inventarios/adicionar/{idItem}/{idPessoa}

* **Method:**

  `GET`
  
*  **URL Params**

   **Required:**
  `idItem=[integer]`
  `idPessoa=[integer]`

* **Data Params**

   `None`

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{Sucesso}`
 
* **Error Response:**

  * **Code:** 417 EXPECTATION FAILED <br />
    **Content:** `{Erro}`

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "/rest/inventarios/adicionar/3/1",
      dataType: "json",
      type : "GET",
      success : function(r) {
        console.log(r);
      }
    });
  ```
**Adicionar itens do inventário de um usuário**
----
  Returns json data about the oparation.

* **URL**

  /rest/inventarios/adicionar/{idItem}/{idPessoa}

* **Method:**

  `GET`
  
*  **URL Params**

   **Required:**
  `idItem=[integer]`
  `idPessoa=[integer]`

* **Data Params**

   `None`

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{Item incluido com sucesso}`
 
* **Error Response:**

  * **Code:** 417 EXPECTATION FAILED <br />
    **Content:** `{Ação inválida}`

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "/rest/inventarios/adicionar/3/1",
      dataType: "json",
      type : "GET",
      success : function(r) {
        console.log(r);
      }
    });
  ```
  **Adicionar itens do inventário de um usuário**
----
  Returns json data about the oparation.

* **URL**

  /rest/inventarios/remover/{idItem}/{idPessoa}

* **Method:**

  `GET`
  
*  **URL Params**

   **Required:**
  `idItem=[integer]`
  `idPessoa=[integer]`

* **Data Params**

   `None`

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{Item removido com sucesso}`
 
* **Error Response:**

  * **Code:** 417 EXPECTATION FAILED <br />
    **Content:** `{Ação inválida}` `{Item não disponível no inventário}`

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "/rest/inventarios/remover/3/1",
      dataType: "json",
      type : "GET",
      success : function(r) {
        console.log(r);
      }
    });
  ```
  **Escambo de bens**
----
  Returns json data about the oparation.

* **URL**

  /rest/inventarios/{idPessoa1}/{itens1}/escambo/{idPessoa2}/{itens2}

* **Method:**

  `GET`
  
*  **URL Params**

   **Required:**
  `idPessoa1=[integer]`
  `itens1=[integer,integer...]`
  `idPessoa1=[integer]`
  `itens1=[integer,integer...]`

* **Data Params**

   `None`

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{Escambo realizado com sucesso}`
 
* **Error Response:**

  * **Code:** 417 EXPECTATION FAILED <br />
    **Content:** `{Quantidade de pontos totais precisam ser iguais}` `{Usuário não possui estes itens no inventário}`

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "/rest/inventarios/6/2/escambo/4/3,4",
      dataType: "json",
      type : "GET",
      success : function(r) {
        console.log(r);
      }
    });
  ```
  
