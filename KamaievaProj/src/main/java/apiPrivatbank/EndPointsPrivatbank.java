package apiPrivatbank;

public interface EndPointsPrivatbank {

    String BASE_URL = "https://api.privatbank.ua/p24api";
    String EXCHANGE_BY_DATE = BASE_URL + "/exchange_rates?date={0}";
    String EXCHANGE_BY_CURRENCY =  BASE_URL + "/pubinfo?json&exchange&coursid=5";
}
