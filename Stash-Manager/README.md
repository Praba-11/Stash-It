# Stash-It Application

A Spring Boot application for managing file artifacts with Azure Blob Storage integration.

## Prerequisites

- Java 17 or higher
- PostgreSQL 12 or higher
- Gradle 7.0 or higher
- Azure Storage Account (for production)

## Setup Instructions

### 1. Database Setup

Create the database and run the SQL scripts:

```sql
-- Create database
CREATE DATABASE stashit;

-- Run the table creation scripts
\i src/main/resources/db/CREATE_MEMBER_TABLE.sql
\i src/main/resources/db/CREATE_ARTIFACT_TABLE.sql
```

### 2. Environment Configuration

Copy `env.example` to `.env` and configure your environment variables:

```bash
cp env.example .env
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

### 3. Local Development

For local development without Azure dependencies, use the dev profile:

```bash
./gradlew bootRun --args='--spring.profiles.active=dev'
```

### 4. Build and Run

```bash
# Build the application
./gradlew build

# Run the application
./gradlew bootRun

# Run with specific profile
./gradlew bootRun --args='--spring.profiles.active=dev'
```

## API Endpoints

- `POST /aps/api/artifact/upload` - Upload artifact with member details
- `GET /aps/api/artifact/find/all` - Get all artifact types
- `GET /aps/api/artifact/member/{memberId}` - Get artifacts by member ID
- `GET /aps/api/artifact/member/{memberId}/file` - Get artifacts by member ID and file path

## Configuration

The application supports multiple profiles:
- `default` - Production configuration with Azure integration
- `dev` - Local development configuration

## Troubleshooting

### Common Issues

1. **Database Connection**: Ensure PostgreSQL is running and accessible
2. **Azure Configuration**: Verify Azure credentials and storage account access
3. **Port Conflicts**: Change `SERVER_PORT` if 9080 is already in use

### Logs

Check application logs for detailed error information. Logging is configured at DEBUG level for development.
