# dms
Document Management System 


Following things are pending in this project 

Project level pending things 

1. Exception handling
2. Transaction handling 
3. Model should be a seperate project 
4. Database and other configurations (if any) should be externalize to application (Possibley JNDI configurations)
5. ** Unit and Integrations tests (Must)
6. ** Database scripts management (addition and updations to database scripts)
7. ** API design guidelines
8. deployment configurations (Jenkins / Automated builds)

Need to ponder on these things 
a. Code standardization (PMD / Checkstyle / findbug)
b. JSP / CSS standardization 

Each element can be developer developer / opdarational guidelines from above points(Above topics can be categorized in following things)

1. Development
2. Deployments
3. Operational things 


# Flyway configuration and mvn specific database migration commands

1. Flyway configuration with springboot is very easy , which provides the programatic  data migration and database scripts management approach
2. Flyway comes with MVN configuration as well (In this project we have used MVN configuration approach)


a. mvn flyway:migrate -Dflyway.configFile=flywayConfig.properties   - migrate new schema (It requires some conventions , PLease refer to flyway documentation)
b. mvn flyway:info -Dflyway.configFile=flywayConfig.properties  - provides version infos









