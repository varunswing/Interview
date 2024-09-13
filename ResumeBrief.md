## Resume Topics 

**Migration of Refund System:**

I led the complex migration of our entire refund system, which involved moving critical components like Kafka, databases, and deployment services from Paytm Bank to four other banks. This project required a lot of technical expertise and careful planning to ensure there was no impact on customers or merchants during the migration. Managing a project of this scale under pressure helped me strengthen my ability to deliver without disruptions.

**Team Transitions and Learning:**

Throughout my journey, I had the opportunity to work across three different teams – Fastag, UPI Refund, and Passbook & Payment Combination. These roles exposed me to a wide range of systems like user and pass management, refunds, and payment screens. Managing these systems simultaneously allowed me to gain a deep understanding of each domain and sharpen my ability to switch contexts and adapt quickly to new challenges.

**OnCall and Issue Resolution:**

As an OnCall engineer for the 24/7 UPI system, I was responsible for monitoring live production issues, such as success rate drops, database lags, and Kafka consumer lags. My debugging skills improved significantly during this time, as I learned to troubleshoot effectively using Kibana logs, analyze code, monitor Grafana graphs, and diagnose database issues. This experience gave me a better understanding of complex, real-time systems and how to resolve issues under pressure.

**Cron Jobs and Kafka:**

I implemented a series of cron jobs that increased our refund transaction success rate to 99.5% for an avg of 3-4Lack refund txns every day for around 80L merchant txns every Day. These jobs handled retries, reconciliation, and status checks. In addition, I worked with Kafka to manage fault-tolerant message delivery by implementing retry mechanisms for failed transactions, which gave me valuable insights into data pipelines and event-driven architectures.

**DownStreamCallbacks and Latch (Async calls, MultiThreading):**

Implemented downstream service callbacks using Spring Boot with both synchronous (RestTemplate) and asynchronous (WebClient) approaches to retrieve essential information for a passbook and payment combination system, fetching details such as sender/receiver information (VPA, account ID, bank details, etc.), ensuring smooth and accurate transaction processing.

**MySQL Database Management:**

I took the lead in migrating refund transaction data across multiple banks, optimizing our database queries for better performance, and managing db partitioning and sharding. This project really highlighted the importance of efficient database management and how to handle complex, multi-bank systems securely and efficiently.

**Grafana and Real-time Monitoring:**

Using Grafana, I set up precise alerts and monitored system health, which helped maintain a success rate above 99%. This proactive monitoring approach allowed me to catch discrepancies early and ensure quick resolution, keeping our systems running smoothly.

**API Development and Testing:**

I’ve developed and tested APIs across different teams, focusing on enhancing their functionality while making sure they met all business needs. I’m committed to rigorous testing and ensuring reliability, which helped me refine my RESTful API design skills.

**Jenkins and Deployments:**

In my work with Jenkins, I learned how to build and deploy services across multiple environments—dev, stage, and production. By managing deployments end-to-end, I ensured smooth and efficient releases, and this hands-on experience gave me confidence in automating and streamlining deployment processes.

**Test-Driven Development and SonarQube:**

By maintaining over 90% unit test coverage, I ensured our codebase was always reliable. I actively resolved issues identified by SonarQube, improving code quality by 45% and reducing production incidents by 30%. This focus on quality really strengthened my ability to deliver cleaner, more robust code.

**SDLC & Team Collaboration:**

I played an active role in conducting code reviews, tracking project progress in Jira, and leading Scrum meetings. These practices helped improve team synchronization and ensured that our projects stayed on track. My involvement in these processes strengthened my understanding of the software development lifecycle and the importance of team collaboration.


## Problems And Challenges Faced

1. One of the major challenges I faced was during the migration of the refund system from Paytm Bank to four other banks. Coordinating such a large-scale migration required managing multiple services like Kafka, databases, deployment properties, and real-time monitoring through Grafana and Kibana. The biggest difficulty was ensuring that there was no downtime for customers or merchants. To manage this, I had to be extremely careful with planning and coordination across different teams and ensure seamless integration between the systems. We ran several rounds of testing and monitoring to minimize risk, and during the live migration, we kept a close eye on all the services. Despite the scale of the task, we were able to execute it successfully without impacting any transactions.

2. Another challenge was during my time as an OnCall engineer for the UPI system. The UPI system requires 24/7 monitoring, and there were instances where we saw success rates dropping or database lags during peak hours. One particular issue I remember was a Kafka consumer lag that started affecting transaction processing speed. The challenge was to identify the root cause quickly, as it was affecting real-time transactions. I had to analyze logs in Kibana, monitor related graphs in Grafana, and dive into the relevant parts of the code to find out what was slowing down the consumers. After thorough debugging, we realized that one of the services wasn’t scaling properly, so I worked with the team to scale up the service and resolve the issue.

3. Handling database migrations and optimizations was another tricky area. When moving refund transaction data across multiple banks, we faced challenges around performance and partitioning. There was a lot of pressure to ensure that queries were optimized and that there was no data loss during migration. By implementing db sharding and partitioning, I was able to reduce the load on individual databases and make the whole system more efficient.

4. Throughout my time at Paytm, one recurring challenge was ensuring code quality and adhering to best practices, especially when managing tight deadlines. SonarQube was a big help here, as it flagged issues that we needed to address, but working with it required discipline in fixing bugs and blockers while also maintaining productivity. This process really strengthened my focus on delivering clean, high-quality code, even when under pressure."