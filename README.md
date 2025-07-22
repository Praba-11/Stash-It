# StashIt

<div align="center">

*Don't just store it, STASH it.*

<img width="588" height="223" alt="Screenshot from 2025-07-20 11-25-54" src="https://github.com/user-attachments/assets/00bcb282-7443-4408-91ea-f45cd2df178d" />

**A simple file managing system that stores and retrieves files for members**

</div>

## About

StashIt is a simple yet effective file managing system that provides member-specific file storage and retrieval. Built with Spring Boot and leveraging Azure cloud services, the application ensures secure and efficient file management operations.

## How It Works

1. **File Storage**: Uploaded files are stored in Azure Storage Account
2. **Metadata Management**: Member details and file paths are stored in Azure PostgreSQL Server
3. **File Retrieval**: Files are retrieved on-demand using the stored path information
4. **API Management**: Stash Manager sub-module handles all API calls between UI and storage services

## Tech Stack

* [![Spring][Spring.js]][Spring-url]
* [![HTML][HTML.js]][HTML-url]
* [![CSS][CSS.js]][CSS-url]
* [![Javascript][Javascript.js]][Javascript-url]

## Features

- **Member-specific storage**: Each member has dedicated file space
- **Any file type support**: Store and retrieve files of any format
- **On-demand retrieval**: Access files whenever needed
- **Azure-powered security**: Leverages Azure's security infrastructure
- **Simple architecture**: Clean Spring Boot application with dedicated API management

## Quick Start

### Prerequisites
- Java 11+
- Maven 3.6+
- Azure CLI

### Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/stashit.git
   cd stashit
   ```

2. **Configure Azure services**
   Set up Azure Storage Account and PostgreSQL Server, then update `application.properties`:
   ```properties
   azure.storage.connection-string=YOUR_STORAGE_CONNECTION_STRING
   spring.datasource.url=YOUR_POSTGRESQL_URL
   spring.datasource.username=YOUR_USERNAME
   spring.datasource.password=YOUR_PASSWORD
   ```

3. **Build and run**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Access the application**
   Open `http://localhost:8080`

## Usage

### Store Files
- Create new member profiles or select existing members
- Upload any file type with metadata
- Files automatically stored in Azure Storage Account with member-specific organization

### Retrieve Files
- Search for members by ID, name, or email
- Browse member's uploaded files
- Download files on-demand with secure access

## Architecture

The application uses a straightforward architecture where the **Stash Manager** sub-module manages all API communications between the UI and Azure services. When files are uploaded, they're stored in the Azure Storage Account while the file path and member information are recorded in the PostgreSQL database, enabling efficient retrieval operations.

---

<div align="center">

**[View Demo](http://localhost:8080)** • **[Report Issues](https://github.com/yourusername/stashit/issues)**

</div>

<!-- MARKDOWN LINKS & IMAGES -->
[Spring.js]: https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white
[Spring-url]: https://spring.io/
[CSS.js]: https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white
[CSS-url]: https://css3.com/
[HTML.js]: https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white
[HTML-url]: https://developer.mozilla.org/en-US/docs/Web/HTML
[Javascript.js]: https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black
[Javascript-url]: https://www.javascript.com/
