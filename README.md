# Sobre o projeto

"Projeto feito para gerenciamento de clientes".

# Funcionalidades
* Cadastrar clientes.
* Buscar todos os clientes.
* Buscar um cliente em específico.
* Atualizar informações do cliente.
* Deletar cliente do banco de dados.
* Cadastrar usuário.
* Login de usuário. 

# Construido com

* Java 8
* Spring Boot
* JPA / Hibernate
* PostgreSQL
* Map Struct
* Lombok
* Spring Security
* JWT
* API ViaCep
* Postman

# Como Executar o Projeto

  * Pré Requisitos:
    
    - Java 8
    - Maven
    - PostgreSQL
    - IDE como Intellij IDEA ou outra de sua preferência
  
  * Passos para executar o projeto localmente:

    1° Clone o repositório:
      - git clone https://github.com/Rodrigodx/CadastroCliente.git
        
        
    2° Abra o projeto na sua IDE e altere as informações do application.properties:
      - Caso não use a database padrão do postgreSQL (postgres), altere para a sua.
        
   
    3° Altere usuário e senha também, este projeto está com "user: postgres e senha: 12345".


    4° Execute o projeto, provavelmente pedirá para aprovar o lombok e propriedades maven, caso esteja executando na IDE que eu utilizei
    o Intellij, caso esteja usando outra, não sei se fará o mesmo.
    

    5° Caso queira executar via prompt, terá que ir até a pasta raiz do projeto:

    ![Pasta raiz](https://github.com/Rodrigodx/CadastroCliente/blob/main/imagens/ExemploPastaRaizProjeto.jpeg)

      - O projeto vem com a seguinte configuração de pasta para chegar na raiz: CadastroCliente\Back-end\CadastroCliente

        
      - Após abrir a ultima CadastroCliente, chegou até a pasta raiz.
        
        
    6° Próximo passo é abrir o seu prompt de comando e executar o seguinte código na raiz do projeto:

      - Caso esteja usando Maven: ./mvnw spring-boot:run
   
        
      - Somente Windows: mvnw spring-boot:run
        

# Consultas

  * URL para registro de usuário: http://localhost:8080/api/registration

    Modelo de requisição de cadastro de usuario via postman:
  
    {
      "login": "teste",
      "senha": "12345",
      "dataAtualSenha": "2025-05-20",
      "role": "ROLE_USUARIO" ou "ROLE_ADMIN"
    }
    
  * URL para registro de usuário: http://localhost:8080/api/login

    Modelo de requisição de login via postman:

    {
      "login": "teste",
      "senha": "12345"
    }

  * Após Login, deve-se pegar o token que será mostrado na resposta da requisição, e se for fazer outra requisição, tem que preencher o Header com o token e a área de authorization também e só após conseguirá fazer a requisição:

   ![Authorization](https://github.com/Rodrigodx/CadastroCliente/blob/main/imagens/ExemploAutorizationPostman.jpeg)

   
   ![Header](https://github.com/Rodrigodx/CadastroCliente/blob/main/imagens/ExemploHeaderPostman.jpeg)

   *Aqui no Header acrescente o "Bearer " antes de acrescentar o token. Ex: "Bearer (Seu Token)".

  * URL para cadastro de cliente: http://localhost:8080/api/cliente

    Modelo de requisição de cadastro de cliente via postman:

    {
      "nome": "Teste",
      "cpf": "11311212123",
      "telefones": [
        { "numero": "1111111111", "tipo": "RESIDENCIAL" ou "COMERCIAL" ou "CELULAR" }
      ],
      "emails": [
        { "email": "teste@email.com" }
      ],
      "enderecos": [
        { "cep": "01001000" }
      ]
    }

  * URL para requisição de consulta para todos os clientes: http://localhost:8080/api/cliente

    
  * URL para requisição de consultar, deletar ou atualizar cliente: http://localhost:8080/api/cliente/1

# Contato 

E-mail: rodrigodx52@gmail.com

Conecte-se comigo no [Linkedin](https://www.linkedin.com/in/rodrigobcorreia?lipi=urn%3Ali%3Apage%3Ad_flagship3_profile_view_base_contact_details%3BzFeSdO%2FIQ%2ByN80cn%2BhbCcg%3D%3D).
