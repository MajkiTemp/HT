package tcom.group.shopping.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class CartItem {
    @Id
    private String id;
    private String offerId;
    private CartItemAction action;
    private Price price;
    private LocalDateTime addedAt;
}


