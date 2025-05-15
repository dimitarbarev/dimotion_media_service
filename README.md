# 🖼️ Dimotion Media Service

A lightweight Spring Boot microservice for uploading, storing, and retrieving images in a MySQL database.

---

## 📦 Features

- Upload images via REST API
- Store images as LONGBLOBs in MySQL
- Retrieve image files by ID
- Link images to specific users and boards

---

## 🚀 How It Works

1. A client uploads an image (PNG, JPG, etc.)
2. The service extracts the metadata and stores:
    - File content (as binary)
    - File type (MIME)
    - Filename
    - Linked user ID and board ID
3. The image can then be retrieved by ID or filtered by board/user.

> 🗄️ Images are saved to the `dimotion_media_db` database in the `image_entity` table.

---

## 🔧 API Endpoints

| Method | Endpoint               | Description             |
|--------|------------------------|-------------------------|
| POST   | `/api/images/upload`   | Upload an image         |
| GET    | `/api/images/{id}`     | Get image by ID         |
| GET    | `/api/images/verify`   | Check if service is up  |

---

## 🐳 Docker

To run the service using Docker:

```bash
docker-compose up --build
```

Make sure your application.properties is configured to connect to MySQL via:
```
spring.datasource.url=jdbc:mysql://mysql:3306/dimotion_media_db
```

## 🧪 Testing
Run all tests using Gradle:
```
./gradlew test
```
Tests are written using JUnit 5 and verify upload and retrieval logic.

## 📸 Example Request

POST /api/images/upload

Form Data:

- imageRequest.file = your_image.png

- imageRequest.user_id = 1

- imageRequest.board_id = 2

## 🤝 License

MIT License © [Dimitar Barev](https://github.com/dimitarbarev)

