# System Design Practice

[Most Asked](https://www.linkedin.com/posts/jainshrayansh_softwareengineer-java-springboot-activity-7269204973043757056-qU35?utm_source=share&utm_medium=member_desktop)
[Machine Coding Practice](https://workat.tech/machine-coding)
[Educative IO Cheatsheets](https://www.educative.io/cheatsheets)
[Awesome System Design Resources](https://github.com/ashishps1/awesome-system-design-resources)


### How do you write SOLID code?

**Ans:** 
The key principle of writing SOLID code is using the single responsibility principle, open/closed principle, Liskov substitution principle, Interface segregation principle, and dependency inversion principle with the help of these principles you can write solid code.

[Detailed](https://www.qfles.com/interview-question/solid-principles-interview-questions)
[GFG](https://www.geeksforgeeks.org/solid-principle-in-programming-understand-with-real-life-examples/)

Single Responsibility Principle (SRP)
Open/Closed Principle
Liskov’s Substitution Principle (LSP)
Interface Segregation Principle (ISP)
Dependency Inversion Principle (DIP)

### Database indexing

**Ans:**

* Indexing is a data structure technique to efficiently retrieve records from the database files based on some attributes on which the indexing has been done. Indexing in database systems is similar to what we see in books.

* Indexing is defined based on its indexing attributes. Indexing can be of the following types −

1. Primary Index − Primary index is defined on an ordered data file. The data file is ordered on a key field. The key field is generally the primary key of the relation.

2. Secondary Index − Secondary index may be generated from a field which is a candidate key and has a unique value in every record, or a non-key with duplicate values.

3. Clustering Index − Clustering index is defined on an ordered data file. The data file is ordered on a non-key field.

4. Ordered Indexing is of two types −
    Dense Index
    Sparse Index

[Detailed](https://www.scaler.com/topics/dbms/indexing-in-dbms/)    
[GFG](https://www.geeksforgeeks.org/indexing-in-databases-set-1/)

### Cache Policy

**Ans:**

The way in which the cache gets updated when data changes are goverened by the cache write policy.

There are three primary cache write policies.

1. write-through
2. write-back
3. write-around

[Detailed](https://www.bytesizedpieces.com/posts/cache-types)

### Sql vs NoSql

**Ans:**

## SQL (Relational Databases):

**Structured data:** Fixed schema, tables, rows, and columns.
**ACID compliance:** Ensures database consistency and reliability.
**Scalability:** Vertical scaling (increase power of single server).
**Querying:** Uses SQL (Structured Query Language).
**Use cases:** Transactional systems, complex queries, and joins.

## NoSQL (Non-Relational Databases):

**Unstructured or semi-structured data:** Flexible schema, documents, key-value pairs, or graphs.
**High scalability:** Horizontal scaling (add more servers).
**High performance:** Fast data retrieval and storage.
**Querying:** Varies by database (e.g., MongoDB uses query language).
**Use cases:** Big data, real-time analytics, content management, and IoT data.

### Key differences:

**Schema flexibility:** NoSQL has dynamic schema, while SQL has fixed schema.
**Scalability:** NoSQL scales horizontally, while SQL scales vertically.
**Data model:** NoSQL uses documents, key-value pairs, or graphs, while SQL uses tables.
**Querying:** NoSQL uses varying query languages, while SQL uses standard SQL.
**Choose SQL for:**
Complex transactions and joins.
Robust data consistency and ACID compliance.
**Choose NoSQL for:**
Big data and real-time analytics.
Flexible schema and high scalability.
Fast data retrieval and storage.

Remember, the choice between SQL and NoSQL depends on your specific use case and data requirements.


### HTTP Requests

**HTTP Status Codes:**
1xx: Informational
2xx: Success
3xx: Redirection
4xx: Client Error
5xx: Server Error

**Top 3 HTTP methods:**

1. GET:
*Purpose:* Retrieve a resource from the server.
*Request Body:* None.
*Response:* The requested resource is returned in the response body.
*Example:* Fetching a web page, retrieving a user's profile information.
*Idempotent:* Yes, making multiple GET requests will return the same result.

2. POST:
*Purpose:* Create a new resource on the server.
*Request Body:* Contains the data to be created (e.g., form data, JSON payload).
*Response:* A confirmation of the creation, often with a unique identifier.
*Example:* Submitting a form, creating a new user account.
*Idempotent:* No, making multiple POST requests can create duplicate resources.

3. PUT:
*Purpose:* Update an existing resource on the server.
*Request Body:* Contains the updated data (e.g., JSON payload).
*Response:* A confirmation of the update, often with the updated resource.
*Example:* Editing a user's profile information, updating a product's details.
*Idempotent:* Yes, making multiple PUT requests will update the resource to the same state.

**Key differences:**
* GET retrieves a resource, while POST creates a new resource.
* PUT updates an existing resource, while POST creates a new one.
* PUT is idempotent, while POST is not.
