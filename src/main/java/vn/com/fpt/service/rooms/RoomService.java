package vn.com.fpt.service.rooms;

import vn.com.fpt.entity.Rooms;
import vn.com.fpt.requests.RoomsRequest;
import vn.com.fpt.responses.GroupContractedResponse;
import vn.com.fpt.responses.RoomsResponse;

import java.util.List;

public interface RoomService {
    List<RoomsResponse> listRoom(Long groupId,
                                 Long groupContractId,
                                 Long floor,
                                 Integer status,
                                 String name);

    Rooms room (Long id);

    List<Rooms> listRoom(List<Long> roomId);

    List<GroupContractedResponse.RoomLeaseContracted> listRoomLeaseContracted(Long groupId);

    List<GroupContractedResponse.RoomNonLeaseContracted> listRoomLeaseNonContracted(Long groupId);

    List<Rooms> add(List<Rooms> rooms);



    List<Rooms> generateRoom(Integer totalRoom,
                             Integer totalFloor,
                             Integer generalLimitedPeople,
                             Double generalPrice,
                             Double generalArea,
                             String nameConvention,
                             Long operator);

    List<Rooms> previewGenerateRoom(Integer totalRoom,
                                    Integer totalFloor,
                                    Integer generalLimitedPeople,
                                    Double generalPrice,
                                    Double generalArea,
                                    String nameConvention,
                                    Long operator);

    Rooms add(Rooms rooms);

    Rooms removeRoom(Long id, Long operator);

    Rooms updateRoom(Long id, RoomsRequest roomsRequest);

    Rooms updateRoom(Rooms roomsRequest);

    List<Rooms> updateRoom(List<Rooms> rooms);

    Rooms setServiceIndex(Long id, Integer electric, Integer water, Long operator);

    Rooms roomChecker(Long id);

    Rooms getRoom(Long id);

    Rooms emptyRoom(Long id);

    Rooms updateRoomStatus(Long id, Long contractId, Long operator);
}
