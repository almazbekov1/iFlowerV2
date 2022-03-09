package i.flowers.service;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import i.flowers.database.model.OrderEntity;
import i.flowers.database.service.impl.OrderForPaypal;
import i.flowers.database.service.impl.OrderItemForPaypal;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PaypalService {

    private final APIContext apiContext;
    private final AskForPrice askForPrice;

    public Payment getPayment(OrderEntity order, String cancelUrl, String successUrl) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setIntent("authorize");
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");
        payment.setPayer(payer);

        payment.setTransactions(getTransactions(askForPrice.getOrderForPaypal(order)));
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);
        System.out.println(payment);
        return payment.create(apiContext);

    }

    public List<Transaction> getTransactions(OrderForPaypal order) {
        Transaction transaction = new Transaction();
        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(order.getTotal());
        Details details = new Details();
        details.setSubtotal(order.getSubtotal());
        details.setShipping(order.getShipping());
        details.setTax(order.getTax());
        details.setHandlingFee("0");
        details.setShippingDiscount("0");
        details.setInsurance("0");
        amount.setDetails(details);
        transaction.setAmount(amount);

        transaction.setDescription("iFlowers");
        PaymentOptions paymentOptions = new PaymentOptions();
        paymentOptions.setAllowedPaymentMethod("IMMEDIATE_PAY");
        transaction.setPaymentOptions(paymentOptions);

        ItemList itemList = new ItemList();

        List<Item> items = new ArrayList<>();
        for (OrderItemForPaypal o : order.getOrderItems()) {
            Item item = new Item();
            item.setSku(o.getSku());
            item.setName(o.getName());
            item.setDescription(o.getDescription());
            item.setQuantity(o.getQuantity());
            item.setPrice(o.getPrice());
            item.setTax(o.getTax());
            item.setCurrency("USD");
            items.add(item);
        }

        itemList.setItems(items);

        transaction.setItemList(itemList);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        return transactions;

    }

}
