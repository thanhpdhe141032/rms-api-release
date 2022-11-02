package vn.com.fpt.service.rooms;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.com.fpt.common.BusinessException;
import vn.com.fpt.constants.ErrorStatusConstants;
import vn.com.fpt.repositories.RoomsRepository;
import vn.com.fpt.requests.RoomsRequest;
import vn.com.fpt.responses.RoomsResponse;

import java.util.List;

import static vn.com.fpt.constants.ErrorStatusConstants.ROOM_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomsRepository roomsRepository;

    @Override
    public List<RoomsResponse> listRoom(Long groupId, Long floor, Boolean status, String name) {
        return null;
    }

    @Override
    public RoomsResponse room(Long id) {
        return null;
    }

    @Override
    public RoomsResponse removeRoom(Long id) {
        return null;
    }

    @Override
    public RoomsResponse updateRoom(Long id, RoomsRequest roomsRequest) {
        return null;
    }

    @Override
    public void roomChecker(Long id) {
        if (!roomsRepository.findById(id).isPresent())
            throw new BusinessException(ROOM_NOT_FOUND, "Không tìm thấy phòng room_id: " + id);
    }
}
