# StashIt

<div align="center">

*Don't just store it, STASH it.*

<img width="588" height="223" alt="Screenshot" src="https://github.com/user-attachments/assets/00bcb282-7443-4408-91ea-f45cd2df178d" />

**A simple file managing system for member-specific storage and retrieval.**

</div>

## Prerequisites

- Java 11+
- Maven 3.6+
- Azure CLI
- **Azure Storage Account** (for file storage)
- **Azure Key Vault** (for storing secrets)
- **Azure PostgreSQL Database** (for metadata)
- **App Service with Managed Identity** (to access Key Vault secrets securely)

## Setup

1. **Clone the repository**
    ```bash
    git clone https://github.com/yourusername/stashit.git
    cd stashit
    ```

2. **Provision Azure resources**
    - Create Storage Account, Key Vault, PostgreSQL Database, and App Service with Managed Identity.
    - Store database credentials in Key Vault.

3. **Configure environment variables**
    ```
    AZURE_STORAGE_CONNECTION_STRING=<your-storage-connection-string>
    AZURE_KEY_VAULT_URI=<your-keyvault-uri>
    SPRING_DATASOURCE_URL=<your-postgresql-url>
    ```

4. **Deploy to Azure App Service**
    - Ensure Managed Identity is enabled and has access to Key Vault.

5. **Run locally**
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

## Usage

- Upload files per member; files are stored in Azure Storage.
- Metadata and access credentials are managed securely via Key Vault and PostgreSQL.

---

**[View Demo](http://localhost:8080)** • **[Report Issues](https://github.com/yourusername/stashit/issues)**
