# Finance Goals API

A REST API in Clojure to manage and return a list of financial objectives (macro goals).

## Running the API

To start the server:

```bash
lein run
```

The server will be started at `http://localhost:3000`

## Endpoints

### GET /api/macro-goals

Returns a list of 5 random macro goals with mocked data.

**Response (200 OK):**
```json
{
  "data": [
    {
      "name": "Save for retirement",
      "step": 5,
      "current-date": "2026-04-03",
      "goal-date": "2028-10-15",
      "goal-value": 500000
    },
    {
      "name": "Buy a property",
      "step": 3,
      "current-date": "2026-04-03",
      "goal-date": "2027-06-20",
      "goal-value": 300000
    },
    ...
  ],
  "count": 5
}
```

## Macro Goal Fields

- **name** (string): Name of the financial objective
- **step** (integer): Current step of the objective (0-9)
- **current-date** (string): Current date in ISO 8601 format (YYYY-MM-DD)
- **goal-date** (string): Target date to achieve the objective in ISO 8601 format (YYYY-MM-DD)
- **goal-value** (integer): Target monetary value for the goal

## Running Tests

```bash
lein test
```

## Usage Examples

### With curl:
```bash
curl http://localhost:3000/api/macro-goals
```

### With httpie:
```bash
http http://localhost:3000/api/macro-goals
```

### With JavaScript/Fetch:
```javascript
fetch('http://localhost:3000/api/macro-goals')
  .then(response => response.json())
  .then(data => console.log(data));
```

