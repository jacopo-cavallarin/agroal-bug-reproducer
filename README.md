# agroal-bug-reproducer

This is a reproducer project for a bug introduced in Quarkus 3.8.1 and agroal 2.3 that prevents from using
multiple persistence units within a single transaction.

# Structure

This project has 2 submodules. Both contain exactly the same code and configuration properties.
The only difference between the two is the version of Quarkus:

- `quarkus-3.8.1` uses Quarkus 3.8.1. This was made to show that the example works in this version.
- `quarkus-3.8.2` uses Quarkus 3.8.2. This is the actual reproducer.

Both submodules contain a **command mode application** that attempts to insert 2 random records in two
different databases within a single transaction.

One database is associated to the default persistence unit,
the other is associated to the persistence unit named `other`.

# Requirements

- Docker (for the dev service databases)
- JDK 17+

# How to reproduce

Run a submodule with one of the following commands:

```shell
./mvnw compile quarkus:dev --pl quarkus-3.8.1
```

```shell
./mvnw compile quarkus:dev --pl quarkus-3.8.2
```

No other configuration should be needed.

## Expected outcome

On `quarkus-3.8.1`, the application terminates without any error. The two records have been successfully
inserted on their respective databases.

## Actual outcome

On `quarkus-3.8.2`, the application fails by throwing an exception. The root cause is:

```
java.sql.SQLException: Unable to enlist connection to existing transaction
```
