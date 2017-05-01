# API REST ApocalipseZumbi

## Funções

**Cadastrar Usuário**
----
  Returns json data about the oparation.

* **URL**

  /rest/usuarios/cadastrar

* **Method:**

  `POST`
  
*  **URL Params**

   **Required:**
 

* **Data Params**

   `{nome, idade, sexo, ultimaLocalizacao}`

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{Sucesso}`
 
* **Error Response:**

  * **Code:** 401 UNAUTHORIZED <br />
    **Content:** `{Erro}`

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "/rest/usuarios/cadastrar",
      dataType: "json",
      type : "POST",
      success : function(r) {
        console.log(r);
      }
    });
  ```
