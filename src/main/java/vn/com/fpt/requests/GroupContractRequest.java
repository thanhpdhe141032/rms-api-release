package vn.com.fpt.requests;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GroupContractRequest {
    private Long groupId;

    private String contractName;
    private Double contractPrice;
    private Double contractDeposit;

    private Integer contractPaymentCycle;

    private String contractStartDate;
    private String contractEndDate;

    private String contractNote;

    @Schema(description = "List id của phòng để lập hợp đồng", required = true, example = "[1,2,3,4,5]")
    private List<Long> listRoom;
    private List<HandOverAssetsRequest> listHandOverAsset;

}
