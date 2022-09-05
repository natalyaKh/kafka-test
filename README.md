
JSON Schema Compatibility Rules

https://docs.confluent.io/platform/current/schema-registry/serdes-develop/serdes-json.html

The JSON Schema compatibility rules are loosely based on similar rules for Avro, however, the rules for backward compatibility are more complex. In addition to browsing the following sections, see Understanding JSON Schema Compatibility to learn more.

Object Compatibility
For object schemas, JSON Schema supports open content models, closed content models and partially open content models.

With the notion of content models, you can adapt the Avro rules as follows:

The ordering of fields may be different: fields are matched by name.
Schemas for fields with the same name in both records are resolved recursively.
##If the writer’s schema contains a field with a name not present in the reader’s schema, then the reader’s schema must have an open content model or a partially open content model that captures the missing field.
If the reader’s schema has a required field that contains a default value, and the writer’s schema has a closed content model and either does not have a field with the same name, or has an optional field with the same name, then the reader should use the default value from its field.
If the reader’s schema has a required field with no default value, and the writer’s schema either does not have a field with the same name, or has an optional field with the same name, an error is signaled.
If the reader’s schema has an optional field, and the writer’s schema has a closed content model and does not have a field with the same name, then the reader should ignore the field.
Here are some additional compatibility rules that are specific to JSON Schema:

The writer’s schema may have a minProperties value that is greater than the minProperties value in the reader’s schema or that is not present in the reader’s schema; or a maxProperties value that is less than the maxProperties value in the reader’s schema or that is not present in the reader’s schema.
The writer’s schema may have a required value that is a superset of the required value in the reader’s schema or that is not present in the reader’s schema.
The writer’s schema may have a dependencies value that is a superset of the dependencies value in the reader’s schema or that is not present in the reader’s schema.
The writer’s schema may have an additionalProperties value of false, whereas it can be true or a schema in the reader’s schema.