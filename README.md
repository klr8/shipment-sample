# Shipment sample application

Write a "ship package" service that can be used to request the shipment of a package to a customer:
- Input: customer ID and package ID
- Output: shipment tracking number

The service will do several things:
- check whether or not the package is already available in the warehouse
- check the customer service system to lookup the name, address and "grade" of the customer
     - a grade between below 10 will receive standard shipping
     - a grade from 10 and up will receive priority shipping
- ship out the package to the customer


Each implementation has a build-and-run.sh script to build and run the code.

Once everything is up-and-running, test using:

```
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"customerId":"foo","packageId":"bar"}' \
  http://localhost:8080/ship
```