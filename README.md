# backend-api

Spring Boot REST API service for managing application features, users, and profile integrations.

<b>Author:</b> <a href="https://github.com/darksos34" target="_blank">Jordy Hamwijk</a><br>
<b>Created:</b> 04-05-2025<br>
<b>Last updated:</b> 29-09-2026<br>

[![](https://img.shields.io/badge/Spring%20Boot-4.1.1-8A2BE2)]() [![](https://img.shields.io/badge/Java-25-ED8B00)]() [![](https://img.shields.io/badge/release-Sep%2029,%202026-blue)]() [![](https://img.shields.io/badge/backend--api-0.0.6-blue)]() [![](https://img.shields.io/badge/domain--models-0.0.15-green)]()

---

## 1. Tech Stack

- **Java**: 25
- **Spring Boot**: 4.1.1
- **Database**: PostgreSQL with Liquibase migrations
- **Documentation**: SpringDoc OpenAPI 3.0.0 (Swagger UI)
- **Object Mapping**: MapStruct 1.6.3 & Lombok 1.18.38
- **Security**: Spring Security & JJWT 0.12.6
- **Shared DTO Library**: [Domain Models](https://github.com/darksos34/domain-models) (`dev.jda:domain-models:0.0.15`)
- **Containerization**: Docker (Eclipse Temurin 25 JRE)

---

## 2. Configuration & Dependencies

### GitHub Packages Repository

This application depends on the shared `domain-models` library hosted on GitHub Packages:

```xml
<dependency>
    <groupId>dev.jda</groupId>
    <artifactId>domain-models</artifactId>
    <version>0.0.14</version>
</dependency>
```

Add the GitHub repository under `<repositories>` in `pom.xml`:

```xml
<repositories>
    <repository>
        <id>github</id>
        <url>https://maven.pkg.github.com/darksos34/domain-models</url>
    </repository>
</repositories>
```

Configure your GitHub Personal Access Token in `~/.m2/settings.xml` (requires `read:packages` scope):

```xml
<settings>
    <servers>
        <server>
            <id>github</id>
            <username>darksos34</username>
            <password>${env.GH_PACKAGES_TOKEN}</password>
        </server>
    </servers>
</settings>
```

---

## 3. API Endpoints

Base path: `/v1`

### 3.1 User Endpoints (`/v1/user`)

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/v1/user/code/{code}` | Retrieve user by unique code |
| `GET` | `/v1/user/{uuid}` | Retrieve user by UUID |
| `GET` | `/v1/user` | Retrieve paginated list of users |
| `POST` | `/v1/user` | Create a new user |
| `PATCH` | `/v1/user/{uuid}` | Update an existing user |
| `DELETE` | `/v1/user/{uuid}` | Delete user by UUID |

```java
@Tag(name = "User", description = "User application Endpoints")
@RequestMapping(RequestPath.V1 + RequestPath.USER)
public interface UserApi {

    @GetMapping("/code/{code}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @Operation(summary = "User weergeven op basis van code.")
    UserDTO getUserByCode(@PathVariable(value = "code")
                          @Parameter(example = "ABCD", description = "Filter USER code") String code);

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @Operation(summary = "User weergeven op basis van uuid.")
    UserDTO getUserByUuid(@PathVariable String uuid);

    @GetMapping()
    @Operation(summary = "Lijst weergeven met alle users als paging.")
    @Parameter(name = "page", schema = @Schema(type = "integer", defaultValue = "0"), in = ParameterIn.QUERY)
    @Parameter(name = "size", schema = @Schema(type = "integer", defaultValue = "20"), in = ParameterIn.QUERY)
    PagedModel<UserDTO> getAllUserrsPageable(@ParameterObject @Parameter(hidden = true) Pageable pageable);

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Nieuwe user aanmaken.")
    @ResponseBody
    UserDTO createUser(@Valid @RequestBody UserDTO userDTO) throws CodeExistsExceptionHandler;

    @PatchMapping(path = "/{uuid}")
    @Operation(summary = "Bestaande user bijwerken.")
    @ResponseStatus(HttpStatus.OK)
    UserDTO patchUserByUuid(@RequestParam(value = "uuid") String uuid,
                            @RequestBody UserDTO userDTO);

    @DeleteMapping(path = "/{uuid}")
    @Operation(summary = "User verwijderen op basis van UUID.")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteUserByUuid(@PathVariable(value = "uuid") String uuid);
}
```

### 3.2 Profile Endpoints (`/v1/profile`)

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/v1/profile/{uuid}` | Retrieve profile by UUID |
| `POST` | `/v1/profile` | Create a new profile |
| `PUT` | `/v1/profile/putProfile/{uuid}` | Replace profile by UUID |
| `PATCH` | `/v1/profile/{uuid}` | Update existing profile by UUID |
| `DELETE` | `/v1/profile/{uuid}` | Delete profile by UUID |

```java
@Tag(name = "Profile", description = "Profile applicatie Endpoints")
@RequestMapping(RequestPath.V1 + RequestPath.PROFILE)
public interface ProfileApi {

    @GetMapping(path = "/{uuid}")
    @Operation(summary = "Profile weergeven op basis van uuid.")
    ProfileDTO getProfileByUuid(@PathVariable(value = "uuid") String uuid);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Nieuwe profile aanmaken.")
    ProfileDTO createProfile(@RequestBody ProfileDTO profileDTO);

    @PutMapping(path = "/putProfile/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Bestaande profile bijwerken.")
    ProfileDTO putProfileByUuid(@RequestParam(value = "uuid") String uuid,
                                @RequestBody ProfileDTO profileDTO);

    @PatchMapping(path = "/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Bestaande profile bijwerken.")
    ProfileDTO patchProfileByUuid(@PathVariable("uuid") String uuid,
                                  @RequestBody ProfileDTO profileDTO);

    @DeleteMapping(path = "/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Profile verwijderen op basis van UUID.")
    void deleteProfileByUuid(@PathVariable(value = "uuid") String uuid);
}
```

---

## 4. Build and Run

### Run Locally

```bash
mvn clean spring-boot:run
```

### Build Docker Image

```bash
docker build -t ghcr.io/darksos34/backend-api:0.0.6 .
```

### Run with Docker Compose

```bash
docker compose up -d
```

### Swagger UI Documentation

Once the application is running, the interactive API documentation is available at:
- `http://localhost:8081/swagger-ui/index.html`

---

## Let's Stay Connected

Feedback is always welcome. If you have any questions, comments, or suggestions, please do not hesitate to reach out.

- <b>Star</b> the repository to show your support.
- Follow me on [GitHub](https://github.com/darksos34) for more projects!
