## Rest Notes App

### Api End Points

**Base Path:** __http://localhost:1234__

| Method | URI     |Description | 
|:--------:| :-------------:|:--------:|
| GET | /api/v1/notes |find all notes |
| POST  | /api/v1/notes |add note |
| GET  | /api/v1/notes/content/{content}|find all note with the content like|
| GET  | /api/v1/notes/pageable|pageable notes , sort by title DESC |
| GET | /api/v1/notes/title/{title} |find all note with the title like |
| DELETE | /api/v1/notes/{id} |delete note |
| GET | /api/v1/notes/{id} |find one note by id|
| PUT| /api/v1/notes/{id} |update note |

**Swagger Documentation:** __http://localhost:1234/swagger-ui.html__

**H2 Console:** __http://localhost:1234/javamyadmin__

