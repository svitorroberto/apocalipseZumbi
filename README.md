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

  /rest/atualizarLocalizacao/{id}/{localizcao}

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
