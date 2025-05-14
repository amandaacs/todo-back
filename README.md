<h1 align="center">
  TODO List 
</h1>

<p align="center">
 <img src="https://img.shields.io/static/v1?label=Tipo&message=Full%20Stack&color=8257E5&labelColor=000000" alt="Full Stack" />

</p>

API para gerenciar tarefas (CRUD) com registro de usuários e login com token.



## 💻 Tecnologias

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [PostgreSQL](https://www.postgresql.org/download/)

## ⌨️ Práticas adotadas

- SOLID, DRY, YAGNI, KISS
- API RESTful
- Autenticação com JWT
- Spring Security
- Consultas com Spring Data JPA
- Mapeamento entre entidades e DTOs
- Injeção de Dependências com @RequiredArgsConstructor
- Separação de camadas (@Service, @Controller, @Repository)
- Tratamento de erros com ResponseEntity
- Persistência automática de datas com @PrePersist
- Relacionamento entre entidades com JPA (@ManyToOne)


## ▶️ Como Executar

- Clonar repositório git
- Construir o projeto:
```
$ ./mvnw clean package
```
- Executar a aplicação:
```
$ java -jar target/todolist-0.0.1-SNAPSHOT.jar
```

A API poderá ser acessada em [localhost:8080](http://localhost:8080).


## API Endpoints

## 📫 Como testar a API (Postman)

> Todas as rotas protegidas exigem um token JWT no header:  
> `Authorization: Bearer SEU_TOKEN_AQUI`

---

## 🔐 Autenticação

### ➕ Registrar novo usuário

**POST** `/api/auth/register`

**Body (JSON):**
```json
{
  "username": "usuario",
  "password": "senha"
}
```

---

### 🔑 Login

**POST** `/api/auth/login`

**Body (JSON):**
```json
{
  "username": "usuario",
  "password": "senha"
}
```

**Response:**
```json
{
  "token": "JWT_TOKEN_AQUI"
}
```

---

## 📌 Endpoints de Tarefas (protegidos)

---

### ✅ Criar tarefa

**POST** `/api/tasks`

**Headers:**
```
Authorization: Bearer JWT_TOKEN_AQUI
```

**Body (JSON):**
```json
{
  "title": "Estudar Spring",
  "description": "Ler documentação oficial"
}
```

**Response:**
```json
{
  "id": 1,
  "title": "Estudar Spring",
  "description": "Ler documentação oficial",
  "completed": false
}
```

---

### 📋 Listar tarefas do usuário autenticado

**GET** `/api/tasks`

**Headers:**
```
Authorization: Bearer JWT_TOKEN_AQUI
```

**Response:**
```json
[
  {
    "id": 1,
    "title": "Estudar Spring",
    "description": "Ler documentação oficial",
    "completed": false
  }
]
```

---

### ✏️ Atualizar tarefa

**PUT** `/api/tasks/{id}`

**Headers:**
```
Authorization: Bearer JWT_TOKEN_AQUI
```

**Body (JSON):**
```json
{
  "title": "Estudar Spring Boot",
  "description": "Ler sobre segurança e JWT",
  "completed": false
}
```

---

### ✅ Marcar tarefa como concluída

**PUT** `/api/tasks/{id}/complete`

**Headers:**
```
Authorization: Bearer JWT_TOKEN_AQUI
```

---

### ↩️ Desmarcar tarefa como concluída

**PUT** `/api/tasks/{id}/undo`

**Headers:**
```
Authorization: Bearer JWT_TOKEN_AQUI
```

---

### 🗑️ Deletar tarefa

**DELETE** `/api/tasks/{id}`

**Headers:**
```
Authorization: Bearer JWT_TOKEN_AQUI
```

---

## 📎 Observações

- Todos os endpoints de tarefas estão protegidos por autenticação.
- As tarefas são associadas ao usuário logado.
