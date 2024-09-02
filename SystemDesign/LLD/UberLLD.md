[Detailed](https://github.com/keertipurswani/Uber-Ola-Low-Level-Design)

![Alt Tex](images/uber_lld.png)

1. Trip Manager
    i. Trip
    ii. TripMetaData
    iii. RiderManager
        a. RiderDetails
    iv. DriverManager
        a. DriverDetails
    v. StrategyManager
        a. PricingStrategy (Default, RatingBased)
        b. DriverMatchingStrategy (LeastTimeBased, RatingBased)