# CouponSystem
Author by: Gadi Engelsman.

Coupons system with SpringBoot implementation, with access to database (MySQL).

## The Coupon System project phase 2:

Implementation SpringBoot, which allows companies CRUD coupons for their customers. The customers can purchase coupons
and use them. The Admin has extra utilities:

- monitor and view info gathered by the transaction system.

### There are 3 permissions:

    - Admin: can create, read, update, delete: companies, coupons, customers.
    - Company: can create, read, update, delete: coupons.
    - Customer: can purchase, check if available: coupons.

### How to use:

Add MySQL-connector-java-8.0.23 External Libraries

- url = "jdbc:mysql://localhost:3306/couponsystem82204"
- username = "root"
- password = "311217905"

### Tables

##### Customer:

| ID | FirstName | LastName | Email | Password
|----|-----------|----------|-------|---------
0        |Ted    |   Mosby    |TedM@Gmail.com    |   TedMosby
1        |Barney    |   Stinson    |Barney@Gmail.com    |   BarneyStinson
2        |Marshall    |   Eriksen    |Marshall@Gmail.com    |   MarshallEriksen
3        |Robin    |   Scherbatsky    |Robin@Gmail.com    |   RobinScherbatsky
4        |Lily    |   Aldrin    |Lily@Gmail.com    |   LilyAldrin
5        |Rachel    |   Green    |TedM@Gmail.com    |   TedMosby
6        |Monica    |   Geller    |Barney@Gmail.com    |   BarneyStinson
7        |Phoebe    |   Buffay    |Marshall@Gmail.com    |   MarshallEriksen
8        |Ross    |   Geller    |Robin@Gmail.com    |   RobinScherbatsky
9        |Joey    |   Tribbiani    |Tribbiani@Gmail.com    |   Tribbiani
10        |Chandler    |   Bing    |Bing@Gmail.com    |   Bing

<br>

##### Coupon:

| ID | CompanyID | CategoryID | Title | Description | StartDate | EndDate | Amount | Price | Image|
|----|-----------|------------|-------|-------------|-----------|---------|--------|-------|------|
1    |1    |1    |Electricity    |Electricity products discount    |2021-04-10    |2021-05-10    |10    |500    |c://programfile|
2    |2    |0    |Drinks            |Drinks discount        |2021-04-10    |2021-05-10|    10    |50        |c://programfile|
3    |3    |0    |Drinks            |Discount on any product of Nestle    |2021-04-10    |2021-05-10    |10    |50    |c://programfile|
4    |4    |3    |Vacation        |Vacation discount        |2021-04-10    |2021-05-10    |10    |180        |c://programfile|
5    |5    |2    |Food            |Food discount            |2021-04-10    |2021-05-10    |10    |150        |c://programfile|

<br>

##### Company:

| ID | Name | Email | Password
|----|------|-------|---------|
1    |Bug    |Bug@Gmail.com    |Bug
2    |Tempo    |Tempo@Gmail.com    |Tempo
3    |Nestle    |Nestle@Gmail.com    |Nestle
4    |Isrotel    |Isrotel@Gmail.com    |Isrotel
5    |Wolt    |Wolt@Gmail.com    |Wolt

<br>

##### Customer_VS_Coupon:

| CustomerID | CouponID | 
|------------|----------|
|0  |8|

<br>

##### Category (Enum):

| CategoryID | CategoryName | 
|------------|--------------|
|0    |FOOD|
|1    |ELECTRICITY|
|2    |RESTAURANT|
|3    |VACATION|




