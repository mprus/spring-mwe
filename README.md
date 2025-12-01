This is a Minimal Working Example demonstrating a bug in BridgeMethodResolver that was introduced in spring-core version 6.2.13.

Run `mvn clean test -Dspring-core.version=6.2.13` to reproduce the issue.

Run `mvn clean test -Dspring-core.version=6.2.12` to see that it works correctly.

This bug affects all spring-data-* projects, as they rely on BridgeMethodResolver for method introspection in entity classes.