package vn.com.fpt.requests;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class HandOverAssetsRequest {

    private Long handOverAssetId;

    private Long assetId;

    private String assetsAdditionalName;

    private Long assetsAdditionalType;

    private int handOverAssetQuantity;

    private Boolean handOverAssetStatus;

    private String handOverDateDelivery;
}
