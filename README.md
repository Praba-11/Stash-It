# Vaultify 🔐☁️

Vaultify is a cloud-native application that enables seamless file uploads to Azure Blob Storage while persisting associated JSON metadata into a database. Built for reliability and scalability, it's ideal for use cases involving document storage, media asset management, or hybrid data ingestion workflows.

---

## 📚 Overview

Vaultify provides RESTful APIs to:

- Upload files (e.g., images, PDFs) to Azure Blob Storage
- Persist structured metadata (JSON) related to each file into a database
- Retrieve uploaded file info and metadata

Technologies used:
- Java 17
- Spring Boot
- Azure SDK for Blob Storage
- MyBatis (for DB interactions)
- Gradle

---

## ✨ Features

- 🔄 Upload and store files to Azure Blob
- 🧾 Store structured metadata (JSON) into relational DB
- 📦 API endpoints for data ingestion and retrieval
- 🔐 Managed identity / secure credentials support

---

## ⚙️ Prerequisites

- Java 17+
- Gradle 7
