package i.flowers.database.service;

public interface PaymentService {

    String payForPaypal(Long id);
    String payForZelle(Long id);
    String payForOther(Long id);
}
