package vn.com.fpt.controller.manager;

import io.sentry.protocol.App;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.fpt.common.BusinessException;
import vn.com.fpt.common.response.AppResponse;
import vn.com.fpt.common.response.BaseResponse;
import vn.com.fpt.entity.RecurringBill;
import vn.com.fpt.requests.AddBillRequest;
import vn.com.fpt.responses.BillRoomStatusResponse;
import vn.com.fpt.responses.ListRoomWithBillStatusResponse;
import vn.com.fpt.responses.PreviewAddBillResponse;
import vn.com.fpt.service.bill.BillService;

import java.util.List;
import java.util.regex.Pattern;

import static vn.com.fpt.configs.AppConfigs.*;

@SecurityRequirement(name = "BearerAuth")
@Tag(name = "Bill Manager", description = "Quản lý hóa đơn")
@RestController
@RequiredArgsConstructor
@RequestMapping(BillController.PATH)
public class BillController {
    public static final String PATH = V1_PATH + MANAGER_PATH + BILL_PATH;

    private final BillService billService;

    @Operation(summary = "Trạng thái hóa đơn các phòng trong tháng và theo kỳ")
    @GetMapping("/room/bill-status")
    public ResponseEntity<BaseResponse<List<BillRoomStatusResponse>>> listNotBilled(@RequestParam Integer paymentCycle,
                                                                                    @RequestParam Long groupId) {
        Pattern pattern = Pattern.compile("(0|15|30)", Pattern.CASE_INSENSITIVE);
        if (!pattern.matcher(paymentCycle.toString()).matches())
            throw new BusinessException("Kỳ hạn thanh toán hóa đơn không hợp lệ");
        return AppResponse.success(billService.listBillRoomStatus(groupId, paymentCycle));
    }

    @Operation(summary = "Danh trang thái hóa đơn của các phòng theo tòa")
    @GetMapping("/room/list/{groupId}")
    public ResponseEntity<BaseResponse<List<ListRoomWithBillStatusResponse>>> listRoomWithBill(@PathVariable Long groupId){
        return AppResponse.success(billService.listRoomWithBillStatus(groupId));
    }

    @PostMapping("/room/create/preview")
    @Operation(summary = "Xem trước list hóa đơn tạo cho nhiều phòng")
    public ResponseEntity<BaseResponse<List<PreviewAddBillResponse>>> preview(@RequestBody List<AddBillRequest> requests){
        return AppResponse.success(billService.addBillPreview(requests));
    }

    @Operation(summary = "Tạo một hoặc nhiều hóa đơn cho phòng")
    @PostMapping("/room/create")
    public ResponseEntity<BaseResponse<List<AddBillRequest>>> createBill(@RequestBody List<AddBillRequest> requests) {
        return AppResponse.success(billService.addBill(requests));
    }

    @Operation(summary = "Xem lịch sử hóa đơn của phòng")
    @GetMapping("/room/history/{roomId}")
    public ResponseEntity<BaseResponse<List<RecurringBill>>> roomBillHistory(@PathVariable Long roomId){
        return AppResponse.success(billService.roomBillHistory(roomId));
    }
    @Operation(summary = "Chi trả một hoặc nhiều hóa đơn định kỳ ")
    @PutMapping("/room/pay")
    public ResponseEntity<BaseResponse<String>> payBill(@RequestParam List<Long> billId) {
        billService.payRoomBill(billId);
        return AppResponse.success("Chi trả thành công");
    }

    @Operation(summary = "Xóa một hoặc nhiều hóa đơn định định kỳ")
    @DeleteMapping("/room/delete")
    public ResponseEntity<BaseResponse<String>> deleteBill(@RequestParam List<Long> billId) {
        billService.deleteRoomBill(billId);
        return AppResponse.success("Xóa hóa đơn thành công");
    }
}
