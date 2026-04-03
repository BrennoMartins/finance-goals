# Introduction to Finance Goals

Finance Goals is a REST API built with Clojure that helps manage and track financial objectives. This project demonstrates a simple yet effective way to create a web service for financial goal management.

## Architecture Style

The project is organized using hexagonal architecture:

- The **domain** layer contains the macro goal model and shared rules.
- The **application** layer contains use cases and ports.
- The **adapters** layer contains HTTP endpoints and the random data generator.
- The **core** namespace acts as the composition root that wires everything together.

## Features

- **REST API** - Retrieve financial goals via HTTP endpoints
- **Mock Data** - Automatic generation of realistic financial goal data
- **JSON Response** - Structured JSON responses with goal information
- **Easy Setup** - Simple configuration and deployment with Leiningen

## Getting Started

For complete API documentation, see [API.md](../API.md)

To learn more about building REST APIs in Clojure, visit the [Compojure Documentation](https://github.com/weavejester/compojure)
