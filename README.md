## API Endpoint

### 1. **Send Bulk Mail with Attachment**
- **URL:** `http://localhost:8080/sendBulkMailWithAttachment`
- **Method:** `POST`
- **Content-Type:** `application/json`

#### Request Body:
```json
{
  "recipient": "xyx2@gmail.com",
  "msgBody": "hello",
  "subject": "Interest in Software Development Engineer Role at Your Organization",
  "attachment": "/Users/mudrakoradia/Downloads/STUDY/Resume/New Helix Resume/Mudra_Koradia_Resume.pdf",
  "csv": "/Users/mudrakoradia/Downloads/Book1.csv"
}
