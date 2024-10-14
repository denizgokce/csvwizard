## API Endpoints

### `POST /api/records/upload`
- **Description**: Upload a CSV file.
- **Content-Type**: `multipart/form-data`
- **Payload**: CSV file

### `GET /api/records`
- **Description**: Retrieve all records.

### `GET /api/records/{code}`
- **Description**: Retrieve records by a specific code.
- **Parameters**: 
    - `code` (route parameter): The code to filter records by.

### `DELETE /api/records`
- **Description**: Delete all records.