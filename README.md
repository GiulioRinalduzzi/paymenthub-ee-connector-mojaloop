# paymenthub-ee-connector-mojaloop

A Payment Hub EE connector that talks to a Mojaloop / vNext payment switch to look up parties, agree quotes, and move transfers.

[![License](https://img.shields.io/badge/License-MPL--2.0-blue.svg)](LICENSE)

## What it does

- Looks up a party (payer or payee) on the switch through its account lookup service.
- Asks for and returns a quote for a transfer.
- Runs the transfer itself, moving funds between financial service providers on the switch.
- Builds and checks ILP packets and conditions, so both sides agree on the same transfer terms.
- Handles the transaction request flow (for example, one side asking another to pay).
- Runs Zeebe (Camunda) workers so these steps happen as part of a larger workflow.

## How it fits into Payment Hub EE

Payment Hub EE runs each payment as a Zeebe (Camunda) workflow. A channel connector starts a
payment and the workflow decides what happens next. This connector is the piece that speaks to
the Mojaloop / vNext switch. When the workflow reaches a party lookup, a quote, or a transfer
step, its Zeebe workers pick up that job, call the switch over HTTP, and report the result back
to the workflow. So it sits between the Payment Hub orchestration layer and the external switch,
translating between the two.

## Tech stack

- Java 21
- Spring Boot 3.4
- Apache Camel 4 (routes for party, quote, transfer, and transaction request flows)
- Zeebe / Camunda workers (via the Zeebe Java client)
- Gradle build
- Depends on `paymenthub-ee-bom` (for versions) and `paymenthub-ee-core`, plus the
  `interop-ilp-conditions` library for ILP packets and conditions.

## Branches

- `dev` is the active development branch — all PRs should target `dev`.
- `main` holds released versions.

## Contributing

See [CONTRIBUTING.md](CONTRIBUTING.md) and our [Code of Conduct](CODE_OF_CONDUCT.md).
