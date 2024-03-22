package api.privatBank;

public interface PrivatEndPoints {
    String PRIVAT_BASE_URL = "https://api.privatbank.ua/p24api";
    String PRIVAT_GET_EXCHANGE_RATES = PRIVAT_BASE_URL + "/exchange_rates";

    String PRIVAT_GET_CURRENT_RATES = PRIVAT_BASE_URL + "/pubinfo";
}
