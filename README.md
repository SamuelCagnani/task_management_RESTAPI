# Task Management REST API 🚀

Uma API REST profissional para gerenciamento de tarefas, desenvolvida com foco em segurança, escalabilidade e boas práticas de arquitetura. Este projeto foi estruturado para demonstrar competências sólidas em desenvolvimento Backend com o ecossistema Spring.

## 🛠 Tecnologias e Ferramentas

- **Linguagem:** Java 21
- **Framework:** Spring Boot 3.2
- **Segurança:** Spring Security & JWT (JSON Web Token)
- **Banco de Dados:** PostgreSQL
- **Documentação:** Swagger / OpenAPI 3
- **Containerização:** Docker & Docker Compose
- **Utilitários:** Lombok, Jakarta Validation, SLF4J (Logging)

## 🏗 Arquitetura

O projeto segue o padrão de **Arquitetura em Camadas**, garantindo a separação de responsabilidades:

- **Controller:** Pontos de entrada da API (Exposição dos endpoints).
- **Service:** Camada de lógica de negócio e regras de validação.
- **Repository:** Interface de comunicação com o banco de dados via Spring Data JPA.
- **Entity:** Modelagem do domínio.
- **DTO (Data Transfer Object):** Proteção da camada de domínio, expondo apenas o necessário para o cliente.

## 🔐 Funcionalidades Principais

- **Autenticação:** Sistema de registro e login de usuários com senhas criptografadas (BCrypt).
- **Autorização:** Acesso protegido por tokens JWT.
- **Isolamento de Dados:** Cada usuário gerencia apenas suas próprias tarefas.
- **CRUD de Tarefas:** Operações completas de Criação, Leitura (com paginação), Atualização e Deleção.
- **Tratamento de Erros:** Handler global para respostas de erro padronizadas.

## 🚀 Como Executar

### Pré-requisitos
- Docker e Docker Compose instalados.

### Passo a Passo

1. **Clonar o repositório:**
   ```bash
   git clone https://github.com/seu-usuario/api-rest-task-manager.git
   cd api-rest-task-manager
   ```

2. **Subir a infraestrutura com Docker:**
   ```bash
   docker-compose up -d --build
   ```
   *Este comando irá baixar a imagem do PostgreSQL, compilar a aplicação Java e subir ambos os containers.*

3. **Acessar a API:**
   - A API estará disponível em: `http://localhost:8080`
   - Documentação Interativa (Swagger): `http://localhost:8080/swagger-ui.html`

## 📖 Documentação da API

Principais Endpoints:

| Método | Endpoint | Descrição | Acesso |
| :--- | :--- | :--- | :--- |
| POST | `/api/auth/register` | Cadastra um novo usuário | Público |
| POST | `/api/auth/login` | Realiza login e retorna o JWT | Público |
| GET | `/api/tasks` | Lista tarefas do usuário (paginado) | Privado |
| POST | `/api/tasks` | Cria uma nova tarefa | Privado |
| GET | `/api/tasks/{id}` | Busca uma tarefa específica | Privado |
| PUT | `/api/tasks/{id}` | Atualiza uma tarefa | Privado |
| DELETE | `/api/tasks/{id}` | Remove uma tarefa | Privado |

---
Desenvolvido por [Seu Nome] - [Seu LinkedIn]
