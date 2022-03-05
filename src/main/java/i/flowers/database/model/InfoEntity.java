package i.flowers.database.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(
        name = "info"
)
@Getter
@Setter
public class InfoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;
    private String email;
    private String phone;
    private String zelle;
    private Double priceForDistance;
    @Column(length = 5000)
    private String description;
    private String image;

    private String mode = "sandbox";
    private String paypalId = "ATIxHqdHjbPNxeO6eCTxA5xcqBADx7cfVkR18CN2kqw5E1vSK_5VjKlMnMTKzrzu9iDxpmgqaTRVGIFm";
    private String paypal = "98324739847834793847";
    private String paypalSecret = "EPLfSdWbiOoWWK1OeKohvoas4ugfiA_F44Yp_gzpOrvUgUNyFYRYT4f_M5Hs0JnagvryaTkEXtHXE0B9";




}
