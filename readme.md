# ⚡ URL Shortener

A full-stack URL shortening service built with **Java 21** and **Spring Boot 3** on the backend, featuring **JWT authentication**, **Base62 encoding**, and **Redis caching** for sub-millisecond redirects. The frontend is built with **Vue 3**, **TypeScript**, and **Tailwind CSS**. Data is persisted in **PostgreSQL** and infrastructure is containerized using **Docker Compose**. Users can shorten URLs, set custom aliases, track click counts, and manage their links through a clean dashboard — all behind secure user authentication.

🔗 Live Demo: [Coming soon]


## 🧰 Tech Stack

**Backend:** Java 21, Spring Boot 3, Spring Security + JWT, Spring Data JPA, PostgreSQL, Redis, Docker

**Frontend:** Vue 3, TypeScript, Pinia, Vue Router, Tailwind CSS, Axios


## ✨ Features

- 🔐 JWT Authentication (register/login)
- 🔗 Base62 URL shortening (supports 3.5 trillion unique URLs)
- 🎨 Custom aliases (e.g. `/my-portfolio`)
- ⚡ Redis caching for sub-millisecond redirects
- 📊 Click count tracking
- 🔒 User privacy — each user sees only their own URLs
- 🗑️ URL management (create, copy, delete)


## 🏗️ Architecture

```
Vue 3 Frontend (port 5173)
        │ REST API + JWT
Spring Boot Backend (port 8080)
        │
   ┌────┴────┐
PostgreSQL  Redis
 (storage) (cache)
        │
   Docker Compose
```

---

## 🐳 Docker

PostgreSQL and Redis run as Docker containers — no manual installation needed:

```bash
docker-compose up -d   # start containers
docker-compose down    # stop containers
```

---

## 🚀 Getting Started

### Prerequisites
- Java 21, Maven 3.9+, Node.js 20+, Docker Desktop

### 1. Clone the repo
```bash
git clone https://github.com/Prathu270710/Shortify
cd url-shortener
```

### 2. Start Docker containers
```bash
cd urlshortener
docker-compose up -d
```

### 3. Start backend
```bash
mvn spring-boot:run
runs on http://localhost:8080
```

### 4. Start frontend
```bash
cd ../frontend
npm install
npm run dev
# runs on http://localhost:5173
```

---

## 🔌 API Endpoints

| Method | Endpoint | Auth | Description |
|---|---|---|---|
| POST | `/api/auth/register` | ❌ | Register |
| POST | `/api/auth/login` | ❌ | Login |
| POST | `/api/urls` | ✅ | Create short URL |
| GET | `/api/urls` | ✅ | Get all user URLs |
| DELETE | `/api/urls/{id}` | ✅ | Delete URL |
| GET | `/{shortCode}` | ❌ | Redirect to original |

---

## 📁 Project Structure

```
url-shortener/
├── urlshortener/                     ← Spring Boot Backend
│   ├── src/main/java/com/urlshortener/urlshortener/
│   │   ├── config/                   ← Security, Cache, JWT Filter
│   │   ├── controller/               ← Auth, URL, Redirect Controllers
│   │   ├── dto/                      ← Request/Response DTOs
│   │   ├── entity/                   ← User, Url entities
│   │   ├── repository/               ← JPA Repositories
│   │   ├── service/                  ← AuthService, UrlService
│   │   └── util/                     ← Base62Util, JwtUtil
│   ├── docker-compose.yml            ← PostgreSQL + Redis
│   └── pom.xml
│
└── frontend/                         ← Vue 3 Frontend
    └── src/
        ├── views/
        │   ├── HomeView.vue          ← Dashboard
        │   ├── LoginView.vue         ← Login page
        │   └── RegisterView.vue      ← Register page
        ├── stores/
        │   ├── authStore.ts          ← JWT auth state
        │   └── urlStore.ts           ← URL CRUD state
        └── router/
            └── index.ts              ← Route guards
```

---

## 🔐 Security
- Passwords hashed with **BCrypt**
- JWT tokens expire after **24 hours**
- Stateless authentication — no server sessions
- CORS restricted to frontend origin

---

## 🗺️ Roadmap
- [ ] QR code generation
- [ ] Click analytics charts
- [ ] Rate limiting (Bucket4j)
- [ ] Unit tests (JUnit 5 + Testcontainers)

---

## 👨‍💻 Author
**Prathamesh Parab** — [GitHub](https://github.com/Prathu270710) · [LinkedIn](https://www.linkedin.com/in/prathameshparab27/)