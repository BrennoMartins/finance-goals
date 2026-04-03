# Finance Goals

A REST API in Clojure for managing and tracking financial objectives (macro goals).

## Overview

This project provides a simple REST API to manage and retrieve a list of financial goals with information about progress, target dates, and monetary values.

## Architecture

This project follows a lightweight hexagonal architecture so business logic does not depend directly on HTTP or infrastructure details.

### Layers

- **Domain** - core business structures and invariants for macro goals
- **Application** - use cases and ports that describe what the system needs
- **Adapters** - inbound HTTP routes and outbound random goal generation
- **Composition root** - `src/finance_goals/core.clj` wires the application together

### Project Structure

```text
src/finance_goals/
├── core.clj
├── domain/
│   └── macro_goal.clj
├── application/
│   ├── ports/
│   │   └── macro_goal_generator.clj
│   └── use_cases/
│       └── list_macro_goals.clj
└── adapters/
	├── inbound/http/
	│   └── routes.clj
	└── outbound/
		└── random_macro_goal_generator.clj
```

## Quick Start

### Prerequisites
- Clojure 1.11.1 or higher
- Leiningen

### Running the API

```bash
lein run
```

The API will start on `http://localhost:3000`

### Running Tests

```bash
lein test
```

## Documentation

- [API.md](./API.md) - Complete API documentation with endpoints and examples

## Dependencies

- **Clojure** - Programming language
- **Ring** - HTTP server abstraction
- **Compojure** - Web routing library
- **Ring JSON** - JSON middleware for Ring

## License

This project is licensed under the EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0 license.
