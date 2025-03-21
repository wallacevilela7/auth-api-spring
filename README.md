# Spring Security JWT 🔐🚀

Bem-vindo, essa é uma aplicação de exemplo que demonstra como implementar autenticação e autorização utilizando **Spring Security** com **JWT (JSON Web Token)**. Este projeto exemplifica como integrar segurança em uma aplicação Spring Boot, fornecendo uma maneira robusta de proteger endpoints e autenticar usuários.

## **Funcionalidades** 🚀

- **Registro de Usuários**: Permite que novos usuários se registrem com dados como nome, email e senha.
- **Autenticação via JWT**: Gera um token JWT para autenticação segura de usuários.
- **Autorização de Endpoints**: Protege endpoints da aplicação, garantindo que apenas usuários autenticados possam acessá-los.
- **Validação de Token**: Verifica a autenticidade do JWT em todas as requisições subsequentes para garantir que o usuário está autenticado.

## **Regras de Negócio** 📌

- **Cadastro de Usuário**: Nome completo, email e senha são necessários para registrar um usuário.
- **Login com Email e Senha**: O login é feito utilizando o email e a senha, e em caso de sucesso, um JWT é retornado.
- **Token Expirado**: O JWT tem um tempo de expiração e precisa ser renovado periodicamente.
- **Segurança**: O Spring Security é utilizado para garantir que os endpoints sejam protegidos.

## **Tecnologias Utilizadas** 🛠️

- **Java 21**
- **Spring Boot 3.4.3** (Spring Security, Spring Web, Spring Data JPA)
- **JWT** (para autenticação)
- **PostgreSQL** (Banco de Dados)
- **Docker** (para containerização)
- **Docker Compose** (para orquestrar contêineres)
- **Maven** (para gerenciamento de dependências e build)

## **Endpoints da API** 🌍

### **Registro de Usuário**
- **POST** `/api/auth/register`
- **Body**:
  ```json
  {
    "name": "Wallace Vilela",
    "email": "wallace@email.com",
    "password": "senhaSegura123"
  }
  ```
- **Response**:
  ```json
  {
    "id" : 1,
    "name": "Wallace Vilela",
    "email": "wallace@email.com",
  }
  ```

### **Login de Usuário**
- **POST** `/api/auth/login`
- **Body**:
  ```json
  {
    "email": "wallace@email.com",
    "password": "senhaSegura123"
  }
  ```
- **Response**:
  ```json
  {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ3YWxsYWNlQGVtYWlsLmNvbSIsInVzZXJJZCI6MSwibmFtZSI6IldhbGxhY2UgVmlsZWxhIiwiZXhwIjoxNjcwNTMyNjY3LCJpYXQiOjE2NzA1Mjg2NjcsImlzcyI6ImF1dGgtcHJvamVjdC1hcGkiLCJ1c2VySWQiOjF9.WmeFVhSAt5o6EmG0E6V3u8HAdTpYph1hNqaZCNmn33A"
  }
  ```

### **Acessar Endpoint Protegido de Produtos**
- **GET** `/api/products/1`
- **Headers**:
  ```plaintext
  Authorization: Bearer <JWT_Token>
  ```
- **Response**:
  ```json
  {
    "name" : "Computer",
    "price" : "10"
  }
  ```

## **Configuração do Banco de Dados** 🗄️

O banco de dados utilizado neste projeto é o **PostgreSQL**, configurado via **Docker Compose**. O Spring Boot irá se conectar ao banco utilizando a URL configurada no `application.yml`:


O banco de dados é iniciado automaticamente via Docker quando você executa o `docker compose up` dentro do diretório `/docker`.

## **Como Executar o Projeto** ▶️

Siga os passos abaixo para rodar o projeto na sua máquina local:

### Passo 1: Clone o repositório

Clone o repositório para o seu computador:

```bash
git clone https://github.com/seu-usuario/SpringSecurity-JWT-Exemplo.git
cd SpringSecurity-JWT-Exemplo
```

### Passo 2: Iniciar os Contêineres com Docker Compose

Inicie o banco de dados PostgreSQL e o back-end Spring Boot usando o **Docker Compose**:

```bash
docker-compose up --build
```

Isso irá construir e iniciar os contêineres do projeto, incluindo o back-end e o PostgreSQL.

### Passo 3: Executar o Projeto

Após os contêineres estarem em execução, você pode iniciar o back-end Spring Boot:

```bash
./mvnw spring-boot:run
```

A aplicação estará disponível em **http://localhost:8080**.

---

## **Contribuição** 🤝

Se você deseja contribuir com melhorias ou novas funcionalidades, fique à vontade para abrir uma **issue** ou enviar um **pull request**. Qualquer contribuição será muito bem-vinda!
