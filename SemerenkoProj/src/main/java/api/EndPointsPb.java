package api;

public interface EndPointsPb {
    String BASE_URL = "https://api.privatbank.ua/p24api";
    String EXCH_ARCH = BASE_URL+"/exchange_rates?date=22.03.2022";
    String EXCH_ARCH_PARAM = BASE_URL+"/exchange_rates?date={0}";
}
