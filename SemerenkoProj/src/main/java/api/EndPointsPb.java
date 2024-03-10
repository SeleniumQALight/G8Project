package api;

public interface EndPointsPb {
    String BASE_URL = "https://api.privatbank.ua/p24api";
    String EXCH_ARCH = BASE_URL+"/exchange_rates";
    String EXCH_ARCH_PARAM = BASE_URL+"/exchange_rates?date={0}";
    String EXCH_RATE = BASE_URL +"/pubinfo?json&exchange&coursid=5";
}
