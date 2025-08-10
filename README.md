# StashIt
**A simple file managing system for member-specific storage and retrieval.**
*Don't just store it, STASH it.*

</div>

## Prerequisites

- **Java 17+** (Spring Boot 3.3.2)
- **Gradle 7.0+** (Build tool)
- **PostgreSQL 12+** (Local development)
- **Azure CLI** (Production deployment)
- **Azure Storage Account** (for file storage)
- **Azure Key Vault** (for storing secrets)
- **Azure PostgreSQL Database** (production metadata storage)

## Features

- **File Upload & Storage**: Upload files with member-specific metadata
- **Azure Blob Storage Integration**: Secure cloud storage for artifacts
- **Member Management**: Organize files by department and designation
- **Metadata Tracking**: Store artifact details including expiry dates and issuer information
- **RESTful API**: Clean REST endpoints for file operations
- **Multi-Environment Support**: Development and production profiles

## Setup

### 1. **Clone the repository**
```bash
git clone https://github.com/yourusername/stashit.git
cd stashit
```

### 2. **Database Setup**
Create the database and run the SQL scripts:

```sql
-- Create database
CREATE DATABASE stashit;

-- Run the table creation scripts
\i Stash-Manager/src/main/resources/db/CREATE_MEMBER_TABLE.sql
\i Stash-Manager/src/main/resources/db/CREATE_ARTIFACT_TABLE.sql
```

### 3. **Environment Configuration**
Copy `env.example` to `.env` and configure your environment variables:

```bash
cp Stash-Manager/env.example .env
```

Edit `.env` with your actual values:

```bash
# Azure Configuration
AZURE_KEY_VAULT_ENDPOINT=https://your-keyvault.vault.azure.net/
AZURE_STORAGE_ACCOUNT_CONNECTION_STRING=DefaultEndpointsProtocol=https;AccountName=yourstorageaccount;AccountKey=yourkey;EndpointSuffix=core.windows.net
AZURE_STORAGE_CONTAINER_NAME=stashit

# Database Configuration
DATASOURCE_URL=jdbc:postgresql://localhost:5432/stashit
DATASOURCE_USERNAME=your_username
DATASOURCE_PASSWORD=your_password

# Server Configuration
SERVER_PORT=9080
SERVER_CONTEXT_PATH=/aps
```

### 4. **Local Development**
For local development without Azure dependencies, use the dev profile:

```bash
cd Stash-Manager
./gradlew bootRun --args='--spring.profiles.active=dev'
```

### 5. **Build and Run**
```bash
# Navigate to Stash-Manager directory
cd Stash-Manager

# Build the application
./gradlew build

# Run the application
./gradlew bootRun

# Run with specific profile
./gradlew bootRun --args='--spring.profiles.active=dev'
```

## API Endpoints

### Base URL: `http://localhost:9080/aps`

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/artifact/upload` | Upload artifact with member details |
| `GET` | `/api/artifact/find/all` | Get all artifact types |
| `GET` | `/api/artifact/member/{memberId}` | Get artifacts by member ID |
| `GET` | `/api/artifact/member/{memberId}/file` | Get artifacts by member ID and file path |

### Example Upload Request
```bash
curl -X POST http://localhost:9080/aps/api/artifact/upload \
  -F "file=@document.pdf" \
  -H "Content-Type: application/json" \
  -d '{
    "memberId": 1,
    "artifactTitle": "Professional Certificate",
    "artifactType": "CERTIFICATE",
    "expiryDate": "2025-12-31",
    "issuer": "Microsoft",
    "description": "Azure Developer Associate Certification"
  }'
```

## Configuration

The application supports multiple profiles:

- **`default`** - Production configuration with Azure integration
- **`dev`** - Local development configuration with local PostgreSQL

### Development Profile Features
- Local PostgreSQL database
- Debug logging enabled
- Port 9081 (to avoid conflicts)
- Azure dependencies optional

## Architecture

```
Stash-Manager/
├── src/main/java/files/stash/
│   ├── controller/          # REST API endpoints
│   ├── service/            # Business logic layer
│   ├── dao/                # Data access layer
│   ├── mapper/             # MyBatis SQL mappers
│   ├── domain/             # Entities and DTOs
│   ├── component/          # Azure storage components
│   └── config/             # Configuration classes
├── src/main/resources/
│   ├── application.yml     # Main configuration
│   ├── application-dev.yml # Development profile
│   └── db/                # Database scripts
└── build.gradle           # Build configuration
```

## Dependencies

- **Spring Boot 3.3.2** - Application framework
- **MyBatis** - Database persistence
- **PostgreSQL** - Database driver
- **Azure Storage Blob** - Cloud file storage
- **Azure Key Vault** - Secret management
- **Lombok** - Code generation

## Deployment

### Azure App Service
1. **Provision Azure resources**:
   - Storage Account, Key Vault, PostgreSQL Database
   - App Service with Managed Identity
   - Store database credentials in Key Vault

2. **Configure environment variables** in App Service:
   ```
   AZURE_KEY_VAULT_ENDPOINT=<your-keyvault-uri>
   AZURE_STORAGE_ACCOUNT_CONNECTION_STRING=<your-storage-connection-string>
   AZURE_STORAGE_CONTAINER_NAME=stashit
   DATASOURCE_URL=<your-postgresql-url>
   DATASOURCE_USERNAME=<your-username>
   DATASOURCE_PASSWORD=<your-password>
   ```

3. **Deploy the application**:
   ```bash
   ./gradlew build
   # Deploy the generated JAR to Azure App Service
   ```

## Troubleshooting

### Common Issues

1. **Database Connection**: Ensure PostgreSQL is running and accessible
2. **Azure Configuration**: Verify Azure credentials and storage account access
3. **Port Conflicts**: Change `SERVER_PORT` if 9080 is already in use
4. **Build Issues**: Ensure Java 17+ and Gradle 7.0+ are installed

### Logs
Check application logs for detailed error information. Logging is configured at DEBUG level for development.

### Health Checks
- Database connectivity
- Azure Storage access
- Application startup status

---

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request
